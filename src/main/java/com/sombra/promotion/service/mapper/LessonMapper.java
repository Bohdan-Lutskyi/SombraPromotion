package com.sombra.promotion.service.mapper;

import com.sombra.promotion.domain.Lesson;
import com.sombra.promotion.dto.LessonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Lesson} and its DTO {@link LessonDTO}.
 */
@Mapper(componentModel = "spring", uses = {StudentAttachmentMapper.class})
public interface LessonMapper extends EntityMapper<LessonDTO, Lesson> {

    @Override
    @Mapping(source = "courseId", target = "course.id")
    @Mapping(source = "studentId", target = "student.id")
    Lesson toEntity(LessonDTO dto);

    @Override
    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "studentAttachments", target = "studentAttachmentIds")
    LessonDTO toDto(Lesson lesson);
}
