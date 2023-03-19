package com.sombra.promotion.integration;

import com.sombra.promotion.domain.Course;
import com.sombra.promotion.domain.CourseFeedback;
import com.sombra.promotion.dto.CourseFeedbackDTO;
import com.sombra.promotion.repository.CourseFeedbackRepository;
import com.sombra.promotion.service.mapper.CourseFeedbackMapper;
import com.sombra.promotion.util.ApiTestConfiguration;
import com.sombra.promotion.util.JacksonUtil;
import com.sombra.promotion.util.TestUtil;
import com.sombra.promotion.web.rest.CourseFeedbackResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CourseFeedbackResource} REST controller.
 */
@ApiTestConfiguration
class CourseFeedbackIT {

    private static final String DEFAULT_FEEDBACK = "AAAAAAAAAA";
    private static final String UPDATED_FEEDBACK = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/course-feedback";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CourseFeedbackRepository courseFeedbackRepository;

    @Autowired
    private CourseFeedbackMapper courseFeedbackMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCourseFeedbackMockMvc;

    @Autowired
    private TestUtil testUtil;

    private CourseFeedback courseFeedback;

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public CourseFeedback createEntity(EntityManager em) {
        final Course course = testUtil.createCourse();
        CourseFeedback courseFeedback = CourseFeedback.builder()
                .feedback(DEFAULT_FEEDBACK)
                .course(course)
                .student(testUtil.createTestStudent(Collections.singleton(course)))
                .build();
        return courseFeedback;
    }

    @BeforeEach
    public void initTest() {
        courseFeedback = createEntity(em);
    }

    @Test
    @Transactional
    void createCourseFeedback() throws Exception {
        int databaseSizeBeforeCreate = courseFeedbackRepository.findAll().size();
        // Create the CourseFeedback
        CourseFeedbackDTO courseFeedbackDTO = courseFeedbackMapper.toDto(courseFeedback);
        restCourseFeedbackMockMvc
                .perform(post(ENTITY_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JacksonUtil.serialize(courseFeedbackDTO)))
                .andExpect(status().isCreated());

        // Validate the CourseFeedback in the database
        List<CourseFeedback> courseFeedbackList = courseFeedbackRepository.findAll();
        assertThat(courseFeedbackList).hasSize(databaseSizeBeforeCreate + 1);
        CourseFeedback testCourseFeedback = courseFeedbackList.get(courseFeedbackList.size() - 1);
        assertThat(testCourseFeedback.getFeedback()).isEqualTo(DEFAULT_FEEDBACK);
    }

    @Test
    @Transactional
    void createCourseFeedbackWithExistingId() throws Exception {
        // Create the CourseFeedback with an existing ID
        courseFeedback.setId(1L);
        CourseFeedbackDTO courseFeedbackDTO = courseFeedbackMapper.toDto(courseFeedback);

        int databaseSizeBeforeCreate = courseFeedbackRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourseFeedbackMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(JacksonUtil.serialize(courseFeedbackDTO)))
                .andExpect(status().isBadRequest());

        // Validate the CourseFeedback in the database
        List<CourseFeedback> courseFeedbackList = courseFeedbackRepository.findAll();
        assertThat(courseFeedbackList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkFeedbackIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseFeedbackRepository.findAll().size();
        // set the field null
        courseFeedback.setFeedback(null);

        // Create the CourseFeedback, which fails.
        CourseFeedbackDTO courseFeedbackDTO = courseFeedbackMapper.toDto(courseFeedback);

        restCourseFeedbackMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(JacksonUtil.serialize(courseFeedbackDTO)))
                .andExpect(status().isBadRequest());

        List<CourseFeedback> courseFeedbackList = courseFeedbackRepository.findAll();
        assertThat(courseFeedbackList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCourseFeedbacks() throws Exception {
        // Initialize the database
        courseFeedbackRepository.saveAndFlush(courseFeedback);

        // Get all the courseFeedbackList
        restCourseFeedbackMockMvc
                .perform(get("/api/course-feedbacks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(courseFeedback.getId().intValue())))
                .andExpect(jsonPath("$.[*].feedback").value(hasItem(DEFAULT_FEEDBACK)));
    }

    @Test
    @Transactional
    void getCourseFeedback() throws Exception {
        // Initialize the database
        courseFeedbackRepository.saveAndFlush(courseFeedback);

        // Get the courseFeedback
        restCourseFeedbackMockMvc
                .perform(get(ENTITY_API_URL_ID, courseFeedback.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(courseFeedback.getId().intValue()))
                .andExpect(jsonPath("$.feedback").value(DEFAULT_FEEDBACK));
    }

    @Test
    @Transactional
    void getNonExistingCourseFeedback() throws Exception {
        // Get the courseFeedback
        restCourseFeedbackMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCourseFeedback() throws Exception {
        // Initialize the database
        courseFeedbackRepository.saveAndFlush(courseFeedback);

        int databaseSizeBeforeUpdate = courseFeedbackRepository.findAll().size();

        // Update the courseFeedback
        CourseFeedback updatedCourseFeedback = courseFeedbackRepository.findById(courseFeedback.getId()).get();
        // Disconnect from session so that the updates on updatedCourseFeedback are not directly saved in db
        em.detach(updatedCourseFeedback);
        updatedCourseFeedback.setFeedback(UPDATED_FEEDBACK);
        CourseFeedbackDTO courseFeedbackDTO = courseFeedbackMapper.toDto(updatedCourseFeedback);

        restCourseFeedbackMockMvc
                .perform(
                        put(ENTITY_API_URL_ID, courseFeedbackDTO.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JacksonUtil.serialize(courseFeedbackDTO))
                )
                .andExpect(status().isOk());

        // Validate the CourseFeedback in the database
        List<CourseFeedback> courseFeedbackList = courseFeedbackRepository.findAll();
        assertThat(courseFeedbackList).hasSize(databaseSizeBeforeUpdate);
        CourseFeedback testCourseFeedback = courseFeedbackList.get(courseFeedbackList.size() - 1);
        assertThat(testCourseFeedback.getFeedback()).isEqualTo(UPDATED_FEEDBACK);
    }

    @Test
    @Transactional
    void putNonExistingCourseFeedback() throws Exception {
        int databaseSizeBeforeUpdate = courseFeedbackRepository.findAll().size();
        courseFeedback.setId(count.incrementAndGet());

        // Create the CourseFeedback
        CourseFeedbackDTO courseFeedbackDTO = courseFeedbackMapper.toDto(courseFeedback);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCourseFeedbackMockMvc
                .perform(
                        put(ENTITY_API_URL_ID, courseFeedbackDTO.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JacksonUtil.serialize(courseFeedbackDTO))
                )
                .andExpect(status().isBadRequest());

        // Validate the CourseFeedback in the database
        List<CourseFeedback> courseFeedbackList = courseFeedbackRepository.findAll();
        assertThat(courseFeedbackList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCourseFeedback() throws Exception {
        int databaseSizeBeforeUpdate = courseFeedbackRepository.findAll().size();
        courseFeedback.setId(count.incrementAndGet());

        // Create the CourseFeedback
        CourseFeedbackDTO courseFeedbackDTO = courseFeedbackMapper.toDto(courseFeedback);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCourseFeedbackMockMvc
                .perform(
                        put(ENTITY_API_URL_ID, count.incrementAndGet())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JacksonUtil.serialize(courseFeedbackDTO))
                )
                .andExpect(status().isBadRequest());

        // Validate the CourseFeedback in the database
        List<CourseFeedback> courseFeedbackList = courseFeedbackRepository.findAll();
        assertThat(courseFeedbackList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCourseFeedback() throws Exception {
        int databaseSizeBeforeUpdate = courseFeedbackRepository.findAll().size();
        courseFeedback.setId(count.incrementAndGet());

        // Create the CourseFeedback
        CourseFeedbackDTO courseFeedbackDTO = courseFeedbackMapper.toDto(courseFeedback);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCourseFeedbackMockMvc
                .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(JacksonUtil.serialize(courseFeedbackDTO)))
                .andExpect(status().isMethodNotAllowed());

        // Validate the CourseFeedback in the database
        List<CourseFeedback> courseFeedbackList = courseFeedbackRepository.findAll();
        assertThat(courseFeedbackList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCourseFeedback() throws Exception {
        // Initialize the database
        courseFeedbackRepository.saveAndFlush(courseFeedback);

        int databaseSizeBeforeDelete = courseFeedbackRepository.findAll().size();

        // Delete the courseFeedback
        restCourseFeedbackMockMvc
                .perform(delete(ENTITY_API_URL_ID, courseFeedback.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CourseFeedback> courseFeedbackList = courseFeedbackRepository.findAll();
        assertThat(courseFeedbackList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
