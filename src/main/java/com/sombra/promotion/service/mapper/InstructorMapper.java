package com.sombra.promotion.service.mapper;

import com.sombra.promotion.domain.Instructor;
import com.sombra.promotion.dto.InstructorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for the entity {@link Instructor} and its DTO {@link InstructorDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, CourseMapper.class})
public interface InstructorMapper extends EntityMapper<InstructorDTO, Instructor> {

    @Override
    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "courses", ignore = true)
    Instructor toEntity(InstructorDTO dto);

    @Override
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.secondName", target = "secondName")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "courses", target = "courses")
    InstructorDTO toDto(Instructor entity);

    default Instructor fromId(Long id) {
        if (id == null) {
            return null;
        }
        Instructor instructor = new Instructor();
        instructor.setId(id);
        return instructor;
    }

    default Set<Instructor> fromIds(Set<Long> ids) {
        if (ids.isEmpty()) {
            return Collections.emptySet();
        }
        final Set<Instructor> instructors = ids.stream().map(this::fromId).collect(Collectors.toSet());
        return instructors;
    }

    default Long fromInstructor(Instructor instructor) {
        if (instructor == null) {
            return null;
        }
        return instructor.getId();
    }

    default Set<Long> fromInstructors(Set<Instructor> instructors) {
        if (instructors == null || instructors.isEmpty()) {
            return Collections.emptySet();
        }
        final Set<Long> ids = instructors.stream().map(this::fromInstructor).collect(Collectors.toSet());
        return ids;
    }
}
