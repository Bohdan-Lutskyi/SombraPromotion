package com.sombra.promotion.web.rest;

import com.sombra.promotion.config.error.ErrorCode;
import com.sombra.promotion.config.error.SystemException;
import com.sombra.promotion.dto.CourseFeedbackDTO;
import com.sombra.promotion.repository.CourseFeedbackRepository;
import com.sombra.promotion.service.CourseFeedbackService;
import com.sombra.promotion.util.HeaderUtil;
import com.sombra.promotion.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


/**
 * REST controller for managing {@link com.sombra.promotion.domain.CourseFeedback}.
 */
@RestController
@RequestMapping("/api")
public class CourseFeedbackResource {

    private final Logger log = LoggerFactory.getLogger(CourseFeedbackResource.class);

    private static final String ENTITY_NAME = "course-feedback";

    private final CourseFeedbackService courseFeedbackService;
    private final CourseFeedbackRepository courseFeedbackRepository;

    public CourseFeedbackResource(final CourseFeedbackService courseFeedbackService,
                                  final CourseFeedbackRepository courseFeedbackRepository) {
        this.courseFeedbackService = courseFeedbackService;
        this.courseFeedbackRepository = courseFeedbackRepository;
    }

    /**
     * {@code POST  /courses} : Create a new courseFeedback.
     *
     * @param courseFeedbackDTO the courseFeedbackDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new courseFeedbackDTO, or with status {@code 400 (Bad Request)} if the courseFeedback has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/course-feedback")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<CourseFeedbackDTO> createCourseFeedback(@Valid @RequestBody CourseFeedbackDTO courseFeedbackDTO) throws URISyntaxException, SystemException {
        log.debug("REST request to save CourseFeedback : {}", courseFeedbackDTO);
        if (courseFeedbackDTO.getId() != null) {
            throw new SystemException("A new courseFeedback cannot already have an ID", ErrorCode.BAD_REQUEST);
        }
        CourseFeedbackDTO result = courseFeedbackService.save(courseFeedbackDTO);
        return ResponseEntity
                .created(new URI("/api/course-feedback/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("applicationName", true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /courses/:id} : Updates an existing courseFeedback.
     *
     * @param id                the id of the courseFeedbackDTO to save.
     * @param courseFeedbackDTO the courseFeedbackDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated courseFeedbackDTO,
     * or with status {@code 400 (Bad Request)} if the courseFeedbackDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the courseFeedbackDTO couldn't be updated.
     */
    @PutMapping("/course-feedback/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<CourseFeedbackDTO> updateCourseFeedback(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody CourseFeedbackDTO courseFeedbackDTO) {
        log.debug("REST request to update CourseFeedback : {}, {}", id, courseFeedbackDTO);
        if (courseFeedbackDTO.getId() == null) {
            throw new SystemException("Invalid id", ErrorCode.BAD_REQUEST);
        }
        if (!Objects.equals(id, courseFeedbackDTO.getId())) {
            throw new SystemException("Invalid ID", ErrorCode.BAD_REQUEST);
        }

        if (!courseFeedbackRepository.existsById(id)) {
            throw new SystemException("Entity not found", ErrorCode.BAD_REQUEST);
        }

        CourseFeedbackDTO result = courseFeedbackService.save(courseFeedbackDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("applicationName", true, ENTITY_NAME, courseFeedbackDTO.getId().toString()))
                .body(result);
    }


    /**
     * {@code GET  /courses} : get all the courses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of courses in body.
     */
    @GetMapping("/course-feedbacks")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<CourseFeedbackDTO> getAllCourseFeedbacks() {
        log.debug("REST request to get all Courses");
        return courseFeedbackService.findAll();
    }

    /**
     * {@code GET  /courses/:id} : get the "id" courseFeedback.
     *
     * @param id the id of the courseFeedbackDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the courseFeedbackDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/course-feedback/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<CourseFeedbackDTO> getCourseFeedback(@PathVariable Long id) {
        log.debug("REST request to get CourseFeedback : {}", id);
        Optional<CourseFeedbackDTO> courseFeedbackDTO = courseFeedbackService.findOne(id);
        return ResponseUtil.wrapOrNotFound(courseFeedbackDTO);
    }

    /**
     * {@code DELETE  /courses/:id} : delete the "id" courseFeedback.
     *
     * @param id the id of the courseFeedbackDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/course-feedback/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<Void> deleteCourseFeedback(@PathVariable Long id) {
        log.debug("REST request to delete CourseFeedback : {}", id);
        courseFeedbackService.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert("applicationName", true, ENTITY_NAME, id.toString()))
                .build();
    }
}
