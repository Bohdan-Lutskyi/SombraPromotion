package com.sombra.promotion.service.mapper;

import com.sombra.promotion.domain.CourseFeedback;
import com.sombra.promotion.dto.CourseFeedbackDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, CourseMapper.class})
public interface CourseFeedbackMapper extends EntityMapper<CourseFeedbackDTO, CourseFeedback> {

    @Override
    @Mapping(source = "courseId", target = "course")
    @Mapping(source = "studentId", target = "student.id")
    CourseFeedback toEntity(CourseFeedbackDTO dto);

    @Override
    @Mapping(source = "course", target = "courseId")
    @Mapping(source = "student.id", target = "studentId")
    CourseFeedbackDTO toDto(CourseFeedback entity);
}
