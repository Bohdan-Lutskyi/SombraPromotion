package com.sombra.promotion.web.rest;

import com.sombra.promotion.config.error.ErrorCode;
import com.sombra.promotion.config.error.SystemException;
import com.sombra.promotion.dto.InstructorDTO;
import com.sombra.promotion.repository.InstructorRepository;
import com.sombra.promotion.service.InstructorService;
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
 * REST controller for managing {@link com.sombra.promotion.domain.Instructor}.
 */
@RestController
@RequestMapping("/api")
public class InstructorResource {

    private final Logger log = LoggerFactory.getLogger(InstructorResource.class);

    private static final String ENTITY_NAME = "instructor";

    private final InstructorService instructorService;

    private final InstructorRepository instructorRepository;

    public InstructorResource(InstructorService instructorService, InstructorRepository instructorRepository) {
        this.instructorService = instructorService;
        this.instructorRepository = instructorRepository;
    }

    /**
     * {@code POST  /instructors} : Create a new lesson.
     *
     * @param instructorDTO the instructorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new instructorDTO, or with status {@code 400 (Bad Request)} if the lesson has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/instructors")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<InstructorDTO> createInstructor(@Valid @RequestBody InstructorDTO instructorDTO) throws URISyntaxException {
        log.debug("REST request to save Instructor : {}", instructorDTO);
        if (instructorDTO.getId() != null) {
            throw new SystemException("A new lesson cannot already have an ID", ErrorCode.BAD_REQUEST);
        }
        InstructorDTO result = instructorService.save(instructorDTO);
        return ResponseEntity
                .created(new URI("/api/instructors/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("applicationName", true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /instructors/:id} : Updates an existing lesson.
     *
     * @param id         the id of the instructorDTO to save.
     * @param instructorDTO the instructorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated instructorDTO,
     * or with status {@code 400 (Bad Request)} if the instructorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the instructorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/instructors/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<InstructorDTO> updateInstructor(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody InstructorDTO instructorDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Instructor : {}, {}", id, instructorDTO);
        if (instructorDTO.getId() == null) {
            throw new SystemException("Invalid id", ErrorCode.BAD_REQUEST);
        }
        if (!Objects.equals(id, instructorDTO.getId())) {
            throw new SystemException("Invalid ID", ErrorCode.BAD_REQUEST);
        }

        if (!instructorRepository.existsById(id)) {
            throw new SystemException("Entity not found", ErrorCode.BAD_REQUEST);
        }
        if (!Objects.equals(instructorRepository.getById(id).getUser().getId(), instructorDTO.getUserId())) {
            throw new SystemException("You are not able to change user reference.", ErrorCode.BAD_REQUEST);
        }

        InstructorDTO result = instructorService.save(instructorDTO);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert("applicationName", true, ENTITY_NAME, instructorDTO.getId().toString()))
                .body(result);
    }


    /**
     * {@code GET  /instructors} : get all the instructors.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of instructors in body.
     */
    @GetMapping("/instructors")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<InstructorDTO> getAllInstructors() {
        log.debug("REST request to get all Instructors");
        return instructorService.findAll();
    }

    /**
     * {@code GET  /instructors/:id} : get the "id" lesson.
     *
     * @param id the id of the instructorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the instructorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/instructors/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<InstructorDTO> getInstructor(@PathVariable Long id) {
        log.debug("REST request to get Instructor : {}", id);
        Optional<InstructorDTO> instructorDTO = instructorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(instructorDTO);
    }

    /**
     * {@code DELETE  /instructors/:id} : delete the "id" lesson.
     *
     * @param id the id of the instructorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/instructors/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        log.debug("REST request to delete Instructor : {}", id);
        instructorService.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert("applicationName", true, ENTITY_NAME, id.toString()))
                .build();
    }
}
