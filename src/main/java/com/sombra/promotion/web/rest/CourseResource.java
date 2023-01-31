package com.sombra.promotion.web.rest;

import com.sombra.promotion.config.error.ErrorCode;
import com.sombra.promotion.config.error.SystemException;
import com.sombra.promotion.dto.CourseDTO;
import com.sombra.promotion.dto.StudentDTO;
import com.sombra.promotion.repository.CourseRepository;
import com.sombra.promotion.service.CourseService;
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
import java.util.*;


/**
 * REST controller for managing {@link com.sombra.promotion.domain.Course}.
 */
@RestController
@RequestMapping("/api")
public class CourseResource {

    private final Logger log = LoggerFactory.getLogger(CourseResource.class);

    private static final String ENTITY_NAME = "course";

    private final CourseService courseService;

    private final CourseRepository courseRepository;

    public CourseResource(CourseService courseService, CourseRepository courseRepository) {
        this.courseService = courseService;
        this.courseRepository = courseRepository;
    }

    /**
     * {@code POST  /courses} : Create a new course.
     *
     * @param courseDTO the courseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new courseDTO, or with status {@code 400 (Bad Request)} if the course has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/courses")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseDTO courseDTO) throws URISyntaxException, SystemException {
        log.debug("REST request to save Course : {}", courseDTO);
        if (courseDTO.getId() != null) {
            throw new SystemException("A new course cannot already have an ID", ErrorCode.BAD_REQUEST);
        }
        CourseDTO result = courseService.save(courseDTO);
        return ResponseEntity
                .created(new URI("/api/courses/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("applicationName", true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PostMapping("/course/{courseId}/add-student")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<CourseDTO> addStudentToCourse(@PathVariable final Long courseId, final List<Long> studentIds) throws URISyntaxException, SystemException {
        log.debug("REST request to add Student(s) with id: {}, to course: {}", studentIds, courseId);
        CourseDTO result = courseService.assignStudentToCourse(courseId, studentIds);
        return ResponseEntity
                .created(new URI("/api/courses/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("applicationName", true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PostMapping("/course/{courseId}/add-instructor")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<CourseDTO> addInstructorToCourse(@PathVariable final Long courseId, final List<Long> instructorIds) throws URISyntaxException, SystemException {
        log.debug("REST request to add Instructor(s) with id: {}, to course: {}", instructorIds, courseId);
        CourseDTO result = courseService.assignInstructorToCourse(courseId, instructorIds);
        return ResponseEntity
                .created(new URI("/api/courses/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("applicationName", true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /courses/:id} : Updates an existing course.
     *
     * @param id        the id of the courseDTO to save.
     * @param courseDTO the courseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated courseDTO,
     * or with status {@code 400 (Bad Request)} if the courseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the courseDTO couldn't be updated.
     */
    @PutMapping("/courses/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<CourseDTO> updateCourse(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody CourseDTO courseDTO
    ) {
        log.debug("REST request to update Course : {}, {}", id, courseDTO);
        if (courseDTO.getId() == null) {
            throw new SystemException("Invalid id", ErrorCode.BAD_REQUEST);
        }
        if (!Objects.equals(id, courseDTO.getId())) {
            throw new SystemException("Invalid ID", ErrorCode.BAD_REQUEST);
        }

        if (!courseRepository.existsById(id)) {
            throw new SystemException("Entity not found", ErrorCode.BAD_REQUEST);
        }

        CourseDTO result = courseService.save(courseDTO);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert("applicationName", true, ENTITY_NAME, courseDTO.getId().toString()))
                .body(result);
    }


    /**
     * {@code GET  /courses} : get all the courses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of courses in body.
     */
    @GetMapping("/courses")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CourseDTO> getAllCourses() {
        log.debug("REST request to get all Courses");
        return courseService.findAll();
    }

    @GetMapping("/courses/students")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public Map<Long, Set<StudentDTO>> getAllStudentsPerCourse(@RequestParam Collection<Long> courseIds) {
        log.debug("REST request to get Courses by ids: {}", courseIds);
        return courseService.getAllStudentsPerCourse(courseIds);
    }

    /**
     * {@code GET  /courses/:id} : get the "id" course.
     *
     * @param id the id of the courseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the courseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/courses/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR', 'STUDENT')")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable Long id) {
        log.debug("REST request to get Course : {}", id);
        Optional<CourseDTO> courseDTO = courseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(courseDTO);
    }

    /**
     * {@code DELETE  /courses/:id} : delete the "id" course.
     *
     * @param id the id of the courseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/courses/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        log.debug("REST request to delete Course : {}", id);
        courseService.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert("applicationName", true, ENTITY_NAME, id.toString()))
                .build();
    }
}
