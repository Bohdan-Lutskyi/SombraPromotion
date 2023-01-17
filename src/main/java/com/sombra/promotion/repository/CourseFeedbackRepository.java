package com.sombra.promotion.repository;

import com.sombra.promotion.domain.CourseFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseFeedbackRepository extends JpaRepository<CourseFeedback, Long>, JpaSpecificationExecutor {
}
