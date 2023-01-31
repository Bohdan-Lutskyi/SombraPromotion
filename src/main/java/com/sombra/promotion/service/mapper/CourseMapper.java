package com.sombra.promotion.service.mapper;

import com.sombra.promotion.domain.Course;
import com.sombra.promotion.dto.CourseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for the entity {@link Course} and its DTO {@link CourseDTO}.
 */
@Mapper(componentModel = "spring", uses = {StudentMapper.class, InstructorMapper.class})
public interface CourseMapper extends EntityMapper<CourseDTO, Course> {
    @Override
    @Mapping(source = "studentIds", target = "students")
    @Mapping(source = "instructorIds", target = "instructors")
    Course toEntity(CourseDTO dto);

    @Override
    @Mapping(source = "students", target = "studentIds")
    @Mapping(source = "instructors", target = "instructorIds")
    CourseDTO toDto(Course entity);

    default Course fromId(Long id) {
        if (id == null) {
            return null;
        }
        Course course = new Course();
        course.setId(id);
        return course;
    }

    default Set<Course> fromIds(Set<Long> ids) {
        if (ids.isEmpty()) {
            return Collections.emptySet();
        }
        final Set<Course> courses = ids.stream().map(this::fromId).collect(Collectors.toSet());
        return courses;
    }

    default Long fromCourse(Course course) {
        if (course == null) {
            return null;
        }
        return course.getId();
    }

    default Set<Long> fromCourses(Set<Course> courses) {
        if (courses == null || courses.isEmpty()) {
            return Collections.emptySet();
        }
        final Set<Long> ids = courses.stream().map(this::fromCourse).collect(Collectors.toSet());
        return ids;
    }

}
