package com.sombra.promotion.integration;

import com.sombra.promotion.domain.Instructor;
import com.sombra.promotion.domain.enumeration.UserRole;
import com.sombra.promotion.dto.InstructorDTO;
import com.sombra.promotion.repository.InstructorRepository;
import com.sombra.promotion.service.mapper.InstructorMapper;
import com.sombra.promotion.util.ApiTestConfiguration;
import com.sombra.promotion.util.JacksonUtil;
import com.sombra.promotion.util.TestUtil;
import com.sombra.promotion.web.rest.InstructorResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import static com.sombra.promotion.util.TestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link InstructorResource} REST controller.
 */
@ApiTestConfiguration
class InstructorIT {

    private static final String DEFAULT_USER_ROLE = UserRole.INSTRUCTOR.name();

    private static final String ENTITY_API_URL = "/api/instructors";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private InstructorMapper instructorMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInstructorMockMvc;

    private Instructor instructor;

    @Autowired
    private TestUtil testUtil;

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public Instructor createEntity(EntityManager em) {
        Instructor instructor = testUtil.createTestInstructorEntity();
        return instructor;
    }

    @BeforeEach
    public void initTest() {
        instructor = createEntity(em);
    }

    @Test
    @Transactional
    void createInstructor() throws Exception {
        int databaseSizeBeforeCreate = instructorRepository.findAll().size();
        // Create the Instructor
        InstructorDTO instructorDTO = instructorMapper.toDto(instructor);
        restInstructorMockMvc
                .perform(post(ENTITY_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JacksonUtil.serialize(instructorDTO)))
                .andExpect(status().isCreated());

        // Validate the Instructor in the database
        List<Instructor> instructorList = instructorRepository.findAll();
        assertThat(instructorList).hasSize(databaseSizeBeforeCreate + 1);
        Instructor testInstructor = instructorList.get(instructorList.size() - 1);
        assertThat(testInstructor.getUser().getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testInstructor.getUser().getSecondName()).isEqualTo(DEFAULT_SECOND_INSTRUCTOR_NAME);
        assertThat(testInstructor.getUser().getEmail()).isEqualTo(DEFAULT_INSTRUCTOR_EMAIL);
        assertThat(testInstructor.getUser().getUserRoles()).contains(DEFAULT_USER_ROLE);
    }

    @Test
    @Transactional
    void createInstructorWithExistingId() throws Exception {
        // Create the Instructor with an existing ID
        instructor.setId(1L);
        InstructorDTO instructorDTO = instructorMapper.toDto(instructor);

        int databaseSizeBeforeCreate = instructorRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInstructorMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(JacksonUtil.serialize(instructorDTO)))
                .andExpect(status().isBadRequest());

        // Validate the Instructor in the database
        List<Instructor> instructorList = instructorRepository.findAll();
        assertThat(instructorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = instructorRepository.findAll().size();
        // set the field null
        instructor.setUser(null);

        // Create the Instructor, which fails.
        InstructorDTO instructorDTO = instructorMapper.toDto(instructor);

        restInstructorMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(JacksonUtil.serialize(instructorDTO)))
                .andExpect(status().isBadRequest());

        List<Instructor> instructorList = instructorRepository.findAll();
        assertThat(instructorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllInstructors() throws Exception {
        // Initialize the database
        instructorRepository.saveAndFlush(instructor);

        // Get all the instructorList
        restInstructorMockMvc
                .perform(get(ENTITY_API_URL + "?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(instructor.getId().intValue())))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_INSTRUCTOR_EMAIL)))
                .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
                .andExpect(jsonPath("$.[*].secondName").value(hasItem(DEFAULT_SECOND_INSTRUCTOR_NAME)))
                .andExpect(jsonPath("$.[*].userId").value(hasItem(instructor.getUser().getId().intValue())));
    }

    @Test
    @Transactional
    void getInstructor() throws Exception {
        // Initialize the database
        instructorRepository.saveAndFlush(instructor);

        // Get the instructor
        restInstructorMockMvc
                .perform(get(ENTITY_API_URL_ID, instructor.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(instructor.getId().intValue()))
                .andExpect(jsonPath("$.email").value(DEFAULT_INSTRUCTOR_EMAIL))
                .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
                .andExpect(jsonPath("$.secondName").value(DEFAULT_SECOND_INSTRUCTOR_NAME))
                .andExpect(jsonPath("$.userId").value(instructor.getUser().getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingInstructor() throws Exception {
        // Get the instructor
        restInstructorMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNonExistingInstructor() throws Exception {
        int databaseSizeBeforeUpdate = instructorRepository.findAll().size();
        instructor.setId(count.incrementAndGet());

        // Create the Instructor
        InstructorDTO instructorDTO = instructorMapper.toDto(instructor);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInstructorMockMvc
                .perform(
                        put(ENTITY_API_URL_ID, instructorDTO.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JacksonUtil.serialize(instructorDTO))
                )
                .andExpect(status().isBadRequest());

        // Validate the Instructor in the database
        List<Instructor> instructorList = instructorRepository.findAll();
        assertThat(instructorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInstructor() throws Exception {
        int databaseSizeBeforeUpdate = instructorRepository.findAll().size();
        instructor.setId(count.incrementAndGet());

        // Create the Instructor
        InstructorDTO instructorDTO = instructorMapper.toDto(instructor);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInstructorMockMvc
                .perform(
                        put(ENTITY_API_URL_ID, count.incrementAndGet())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JacksonUtil.serialize(instructorDTO))
                )
                .andExpect(status().isBadRequest());

        // Validate the Instructor in the database
        List<Instructor> instructorList = instructorRepository.findAll();
        assertThat(instructorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInstructor() throws Exception {
        int databaseSizeBeforeUpdate = instructorRepository.findAll().size();
        instructor.setId(count.incrementAndGet());

        // Create the Instructor
        InstructorDTO instructorDTO = instructorMapper.toDto(instructor);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInstructorMockMvc
                .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(JacksonUtil.serialize(instructorDTO)))
                .andExpect(status().isMethodNotAllowed());

        // Validate the Instructor in the database
        List<Instructor> instructorList = instructorRepository.findAll();
        assertThat(instructorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInstructor() throws Exception {
        // Initialize the database
        instructorRepository.saveAndFlush(instructor);

        int databaseSizeBeforeDelete = instructorRepository.findAll().size();

        // Delete the instructor
        restInstructorMockMvc
                .perform(delete(ENTITY_API_URL_ID, instructor.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Instructor> instructorList = instructorRepository.findAll();
        assertThat(instructorList).hasSize(databaseSizeBeforeDelete - 1);
    }

}
