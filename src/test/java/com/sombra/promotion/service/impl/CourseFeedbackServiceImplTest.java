package com.sombra.promotion.service.impl;

import com.sombra.promotion.domain.Course;
import com.sombra.promotion.domain.CourseFeedback;
import com.sombra.promotion.domain.Student;
import com.sombra.promotion.domain.User;
import com.sombra.promotion.dto.CourseDTO;
import com.sombra.promotion.dto.CourseFeedbackDTO;
import com.sombra.promotion.dto.StudentDTO;
import com.sombra.promotion.repository.CourseFeedbackRepository;
import com.sombra.promotion.service.CourseService;
import com.sombra.promotion.service.StudentService;
import com.sombra.promotion.service.mapper.CourseFeedbackMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CourseFeedbackServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CourseFeedbackServiceImplTest {
    @MockBean
    private CourseFeedbackMapper courseFeedbackMapper;

    @MockBean
    private CourseFeedbackRepository courseFeedbackRepository;

    @Autowired
    private CourseFeedbackServiceImpl courseFeedbackServiceImpl;

    @MockBean
    private CourseService courseService;

    @MockBean
    private StudentService studentService;

    /**
     * Method under test: {@link CourseFeedbackServiceImpl#save(CourseFeedbackDTO)}
     */
    @Test
    void testSave() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setCourses(new HashSet<>());
        studentDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        studentDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentDTO.setEmail("jane.doe@example.org");
        studentDTO.setFirstName("Jane");
        studentDTO.setId(123L);
        studentDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        studentDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentDTO.setSecondName("Second Name");
        studentDTO.setUserId(123L);
        when(this.studentService.getStudentOrThrow((Long) any())).thenReturn(studentDTO);

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDTO.setId(123L);
        courseDTO.setInstructorIds(new HashSet<>());
        courseDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDTO.setName("Name");
        courseDTO.setNumberOfLessons((short) 1);
        courseDTO.setStudentIds(new HashSet<>());
        when(this.courseService.findByIdOrThrow((Long) any())).thenReturn(courseDTO);

        Course course = new Course();
        course.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        course.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course.setId(123L);
        course.setInstructors(new HashSet<>());
        course.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        course.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course.setName("Name");
        course.setNumberOfLessons((short) 1);
        course.setStudents(new HashSet<>());

        User user = new User();
        user.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        user.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        user.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setPassword("iloveyou");
        user.setSecondName("Second Name");
        user.setUserRoles("User Roles");

        Student student = new Student();
        student.setCourses(new HashSet<>());
        student.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        student.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        student.setId(123L);
        student.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        student.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        student.setUser(user);

        CourseFeedback courseFeedback = new CourseFeedback();
        courseFeedback.setCourse(course);
        courseFeedback.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseFeedback.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedback.setFeedback("Feedback");
        courseFeedback.setId(123L);
        courseFeedback.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseFeedback.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedback.setStudent(student);
        when(this.courseFeedbackRepository.save((CourseFeedback) any())).thenReturn(courseFeedback);

        Course course1 = new Course();
        course1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        course1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course1.setId(123L);
        course1.setInstructors(new HashSet<>());
        course1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        course1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course1.setName("Name");
        course1.setNumberOfLessons((short) 1);
        course1.setStudents(new HashSet<>());

        User user1 = new User();
        user1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        user1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        user1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setPassword("iloveyou");
        user1.setSecondName("Second Name");
        user1.setUserRoles("User Roles");

        Student student1 = new Student();
        student1.setCourses(new HashSet<>());
        student1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        student1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        student1.setId(123L);
        student1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        student1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        student1.setUser(user1);

        CourseFeedback courseFeedback1 = new CourseFeedback();
        courseFeedback1.setCourse(course1);
        courseFeedback1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseFeedback1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedback1.setFeedback("Feedback");
        courseFeedback1.setId(123L);
        courseFeedback1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseFeedback1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedback1.setStudent(student1);

        CourseFeedbackDTO courseFeedbackDTO = new CourseFeedbackDTO();
        courseFeedbackDTO.setCourseId(123L);
        courseFeedbackDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseFeedbackDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO.setFeedback("Feedback");
        courseFeedbackDTO.setId(123L);
        courseFeedbackDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseFeedbackDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO.setStudentId(123L);
        when(this.courseFeedbackMapper.toEntity((CourseFeedbackDTO) any())).thenReturn(courseFeedback1);
        when(this.courseFeedbackMapper.toDto((CourseFeedback) any())).thenReturn(courseFeedbackDTO);

        CourseFeedbackDTO courseFeedbackDTO1 = new CourseFeedbackDTO();
        courseFeedbackDTO1.setCourseId(123L);
        courseFeedbackDTO1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseFeedbackDTO1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO1.setFeedback("Feedback");
        courseFeedbackDTO1.setId(123L);
        courseFeedbackDTO1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseFeedbackDTO1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO1.setStudentId(123L);
        assertSame(courseFeedbackDTO, this.courseFeedbackServiceImpl.save(courseFeedbackDTO1));
        verify(this.studentService).getStudentOrThrow((Long) any());
        verify(this.courseService).findByIdOrThrow((Long) any());
        verify(this.courseFeedbackRepository).save((CourseFeedback) any());
        verify(this.courseFeedbackMapper).toEntity((CourseFeedbackDTO) any());
        verify(this.courseFeedbackMapper).toDto((CourseFeedback) any());
    }

    /**
     * Method under test: {@link CourseFeedbackServiceImpl#findAll()}
     */
    @Test
    void testFindAll() {
        when(this.courseFeedbackRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(this.courseFeedbackServiceImpl.findAll().isEmpty());
        verify(this.courseFeedbackRepository).findAll();
    }

    /**
     * Method under test: {@link CourseFeedbackServiceImpl#findOne(Long)}
     */
    @Test
    void testFindOne() {
        Course course = new Course();
        course.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        course.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course.setId(123L);
        course.setInstructors(new HashSet<>());
        course.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        course.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course.setName("Name");
        course.setNumberOfLessons((short) 1);
        course.setStudents(new HashSet<>());

        User user = new User();
        user.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        user.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        user.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setPassword("iloveyou");
        user.setSecondName("Second Name");
        user.setUserRoles("User Roles");

        Student student = new Student();
        student.setCourses(new HashSet<>());
        student.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        student.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        student.setId(123L);
        student.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        student.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        student.setUser(user);

        CourseFeedback courseFeedback = new CourseFeedback();
        courseFeedback.setCourse(course);
        courseFeedback.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseFeedback.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedback.setFeedback("Feedback");
        courseFeedback.setId(123L);
        courseFeedback.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseFeedback.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedback.setStudent(student);
        Optional<CourseFeedback> ofResult = Optional.of(courseFeedback);
        when(this.courseFeedbackRepository.findById((Long) any())).thenReturn(ofResult);

        CourseFeedbackDTO courseFeedbackDTO = new CourseFeedbackDTO();
        courseFeedbackDTO.setCourseId(123L);
        courseFeedbackDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseFeedbackDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO.setFeedback("Feedback");
        courseFeedbackDTO.setId(123L);
        courseFeedbackDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseFeedbackDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO.setStudentId(123L);
        when(this.courseFeedbackMapper.toDto((CourseFeedback) any())).thenReturn(courseFeedbackDTO);
        assertTrue(this.courseFeedbackServiceImpl.findOne(123L).isPresent());
        verify(this.courseFeedbackRepository).findById((Long) any());
        verify(this.courseFeedbackMapper).toDto((CourseFeedback) any());
    }

    /**
     * Method under test: {@link CourseFeedbackServiceImpl#delete(Long)}
     */
    @Test
    void testDelete() {
        doNothing().when(this.courseFeedbackRepository).deleteById((Long) any());
        this.courseFeedbackServiceImpl.delete(123L);
        verify(this.courseFeedbackRepository).deleteById((Long) any());
    }
}

