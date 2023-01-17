package com.sombra.promotion.service.mapper;

import com.sombra.promotion.domain.StudentAttachment;
import com.sombra.promotion.dto.StudentAttachmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Mapper for the entity {@link StudentAttachment} and its DTO {@link StudentAttachmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StudentAttachmentMapper extends EntityMapper<StudentAttachmentDTO, StudentAttachment> {

    @Override
    @Mapping(source = "lessonId", target = "lesson.id")
    StudentAttachment toEntity(StudentAttachmentDTO dto);

    @Override
    @Mapping(source = "lesson.id", target = "lessonId")
    StudentAttachmentDTO toDto(StudentAttachment attachment);

    @Named(value = "fromStudentAttachments")
    default Collection<Long> fromStudentAttachments(Collection<StudentAttachment> studentAttachments) {
        if (studentAttachments == null || studentAttachments.isEmpty()) {
            return Collections.emptySet();
        }
        final Collection<Long> ids = studentAttachments.stream().map(this::fromStudentAttachment).collect(Collectors.toSet());
        return ids;
    }

    default Long fromStudentAttachment(StudentAttachment studentAttachment) {
        if (studentAttachment == null) {
            return null;
        }
        return studentAttachment.getId();
    }
}
