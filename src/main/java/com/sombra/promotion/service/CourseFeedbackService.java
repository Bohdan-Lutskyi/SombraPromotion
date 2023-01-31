package com.sombra.promotion.service;

import com.sombra.promotion.dto.CourseFeedbackDTO;

import java.util.List;
import java.util.Optional;

public interface CourseFeedbackService {
    /**
     * Save a course.
     *
     * @param courseFeedbackDTO the entity to save.
     * @return the persisted entity.
     */
    CourseFeedbackDTO save(CourseFeedbackDTO courseFeedbackDTO);

    /**
     * Get all the courses.
     *
     * @return the list of entities.
     */
    List<CourseFeedbackDTO> findAll();

    /**
     * Get the "id" course.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CourseFeedbackDTO> findOne(Long id);

    /**
     * Delete the "id" course.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

}
