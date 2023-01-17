package com.sombra.promotion.service;


import com.sombra.promotion.config.error.SystemException;
import com.sombra.promotion.dto.LessonDTO;
import com.sombra.promotion.dto.StudentCourseDTO;

import java.util.List;
import java.util.Optional;

public interface LessonService {
    /**
     * Save a lesson.
     *
     * @param lessonDTO the entity to save.
     * @return the persisted entity.
     */
    LessonDTO save(LessonDTO lessonDTO);

    /**
     * Get all the lessons.
     *
     * @return the list of entities.
     */
    List<LessonDTO> findAll();

    /**
     * Get the "id" lesson.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LessonDTO> findOne(Long id);

    List<LessonDTO> getAllLessonsByStudentId(Long studentId);

    /**
     * Delete the "id" lesson.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    Double calculateFinalMark(StudentCourseDTO studentCourseDTO);

    LessonDTO findByIdOrThrow(Long id) throws SystemException;
}
