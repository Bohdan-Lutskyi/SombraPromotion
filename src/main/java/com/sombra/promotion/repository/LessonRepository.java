package com.sombra.promotion.repository;

import com.sombra.promotion.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long>, JpaSpecificationExecutor {

    List<Lesson> findByStudentId(Long studentId);

    List<Lesson> findByStudentIdAndCourseId(Long studentId, Long courseId);
}
