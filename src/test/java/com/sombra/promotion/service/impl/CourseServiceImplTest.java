package com.sombra.promotion.service.impl;

import com.sombra.promotion.config.error.ErrorCode;
import com.sombra.promotion.config.error.SystemException;
import com.sombra.promotion.domain.Course;
import com.sombra.promotion.domain.Instructor;
import com.sombra.promotion.domain.Student;
import com.sombra.promotion.domain.User;
import com.sombra.promotion.dto.CourseDTO;
import com.sombra.promotion.repository.CourseRepository;
import com.sombra.promotion.repository.InstructorRepository;
import com.sombra.promotion.repository.StudentRepository;
import com.sombra.promotion.service.mapper.CourseMapper;
import com.sombra.promotion.service.mapper.StudentMapper;
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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CourseServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CourseServiceImplTest {
    @MockBean
    private CourseMapper courseMapper;

    @MockBean
    private CourseRepository courseRepository;

    @Autowired
    private CourseServiceImpl courseServiceImpl;

    @MockBean
    private InstructorRepository instructorRepository;

    @MockBean
    private StudentMapper studentMapper;

    @MockBean
    private StudentRepository studentRepository;

    /**
     * Method under test: {@link CourseServiceImpl#save(CourseDTO)}
     */
    @Test
    void testSave() {
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
        when(this.courseMapper.toEntity((CourseDTO) any())).thenReturn(course);

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
        assertThrows(SystemException.class, () -> this.courseServiceImpl.save(courseDTO));
        verify(this.courseMapper).toEntity((CourseDTO) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#save(CourseDTO)}
     */
    @Test
    void testSave2() {
        when(this.instructorRepository.existsById((Long) any())).thenReturn(true);

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
        when(this.courseRepository.save((Course) any())).thenReturn(course);

        User user = new User();
        user.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        user.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        user.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setPassword("iloveyou");
        user.setSecondName("Request to save Course : {}");
        user.setUserRoles("Request to save Course : {}");

        Instructor instructor = new Instructor();
        instructor.setCourses(new HashSet<>());
        instructor.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        instructor.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructor.setId(123L);
        instructor.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        instructor.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructor.setUser(user);

        HashSet<Instructor> instructorSet = new HashSet<>();
        instructorSet.add(instructor);

        Course course1 = new Course();
        course1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        course1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course1.setId(123L);
        course1.setInstructors(instructorSet);
        course1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        course1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course1.setName("Name");
        course1.setNumberOfLessons((short) 1);
        course1.setStudents(new HashSet<>());

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
        when(this.courseMapper.toDto((Course) any())).thenReturn(courseDTO);
        when(this.courseMapper.toEntity((CourseDTO) any())).thenReturn(course1);

        CourseDTO courseDTO1 = new CourseDTO();
        courseDTO1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseDTO1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDTO1.setId(123L);
        courseDTO1.setInstructorIds(new HashSet<>());
        courseDTO1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseDTO1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDTO1.setName("Name");
        courseDTO1.setNumberOfLessons((short) 1);
        courseDTO1.setStudentIds(new HashSet<>());
        assertSame(courseDTO, this.courseServiceImpl.save(courseDTO1));
        verify(this.instructorRepository).existsById((Long) any());
        verify(this.courseRepository).save((Course) any());
        verify(this.courseMapper).toEntity((CourseDTO) any());
        verify(this.courseMapper).toDto((Course) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#save(CourseDTO)}
     */
    @Test
    void testSave3() {
        when(this.instructorRepository.existsById((Long) any())).thenReturn(true);

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
        when(this.courseRepository.save((Course) any())).thenReturn(course);

        User user = new User();
        user.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        user.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        user.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setPassword("iloveyou");
        user.setSecondName("Request to save Course : {}");
        user.setUserRoles("Request to save Course : {}");

        Instructor instructor = new Instructor();
        instructor.setCourses(new HashSet<>());
        instructor.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        instructor.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructor.setId(123L);
        instructor.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        instructor.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructor.setUser(user);

        HashSet<Instructor> instructorSet = new HashSet<>();
        instructorSet.add(instructor);

        Course course1 = new Course();
        course1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        course1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course1.setId(123L);
        course1.setInstructors(instructorSet);
        course1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        course1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course1.setName("Name");
        course1.setNumberOfLessons((short) 1);
        course1.setStudents(new HashSet<>());
        when(this.courseMapper.toDto((Course) any()))
                .thenThrow(new SystemException("An error occurred", ErrorCode.INTERNAL_SERVER_ERROR));
        when(this.courseMapper.toEntity((CourseDTO) any())).thenReturn(course1);

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
        assertThrows(SystemException.class, () -> this.courseServiceImpl.save(courseDTO));
        verify(this.instructorRepository).existsById((Long) any());
        verify(this.courseRepository).save((Course) any());
        verify(this.courseMapper).toEntity((CourseDTO) any());
        verify(this.courseMapper).toDto((Course) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#save(CourseDTO)}
     */
    @Test
    void testSave4() {
        when(this.instructorRepository.existsById((Long) any())).thenReturn(false);

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
        when(this.courseRepository.save((Course) any())).thenReturn(course);

        User user = new User();
        user.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        user.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        user.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setPassword("iloveyou");
        user.setSecondName("Request to save Course : {}");
        user.setUserRoles("Request to save Course : {}");

        Instructor instructor = new Instructor();
        instructor.setCourses(new HashSet<>());
        instructor.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        instructor.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructor.setId(123L);
        instructor.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        instructor.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructor.setUser(user);

        HashSet<Instructor> instructorSet = new HashSet<>();
        instructorSet.add(instructor);

        Course course1 = new Course();
        course1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        course1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course1.setId(123L);
        course1.setInstructors(instructorSet);
        course1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        course1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course1.setName("Name");
        course1.setNumberOfLessons((short) 1);
        course1.setStudents(new HashSet<>());

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
        when(this.courseMapper.toDto((Course) any())).thenReturn(courseDTO);
        when(this.courseMapper.toEntity((CourseDTO) any())).thenReturn(course1);

        CourseDTO courseDTO1 = new CourseDTO();
        courseDTO1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseDTO1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDTO1.setId(123L);
        courseDTO1.setInstructorIds(new HashSet<>());
        courseDTO1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseDTO1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDTO1.setName("Name");
        courseDTO1.setNumberOfLessons((short) 1);
        courseDTO1.setStudentIds(new HashSet<>());
        assertThrows(SystemException.class, () -> this.courseServiceImpl.save(courseDTO1));
        verify(this.instructorRepository).existsById((Long) any());
        verify(this.courseMapper).toEntity((CourseDTO) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#findAll()}
     */
    @Test
    void testFindAll() {
        when(this.courseRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(this.courseServiceImpl.findAll().isEmpty());
        verify(this.courseRepository).findAll();
    }


    /**
     * Method under test: {@link CourseServiceImpl#getAllStudentsPerCourse(java.util.Collection)}
     */
    @Test
    void testGetAllStudentsPerCourse() {
        when(this.courseRepository.findAllByIdIn((java.util.Collection<Long>) any())).thenReturn(new ArrayList<>());
        assertTrue(this.courseServiceImpl.getAllStudentsPerCourse(new ArrayList<>()).isEmpty());
        verify(this.courseRepository).findAllByIdIn((java.util.Collection<Long>) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#getAllStudentsPerCourse(java.util.Collection)}
     */
    @Test
    void testGetAllStudentsPerCourse2() {
        when(this.studentMapper.toDto((Set<Student>) any())).thenReturn(new HashSet<>());

        Course course = new Course();
        course.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        course.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course.setId(123L);
        course.setInstructors(new HashSet<>());
        course.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        course.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course.setName("Request to get all Students");
        course.setNumberOfLessons((short) 1);
        course.setStudents(new HashSet<>());

        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(course);
        when(this.courseRepository.findAllByIdIn((java.util.Collection<Long>) any())).thenReturn(courseList);
        assertEquals(1, this.courseServiceImpl.getAllStudentsPerCourse(new ArrayList<>()).size());
        verify(this.studentMapper).toDto((Set<Student>) any());
        verify(this.courseRepository).findAllByIdIn((java.util.Collection<Long>) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#getAllStudentsPerCourse(java.util.Collection)}
     */

    /**
     * Method under test: {@link CourseServiceImpl#getAllStudentsPerCourse(java.util.Collection)}
     */
    @Test
    void testGetAllStudentsPerCourse4() {
        when(this.studentMapper.toDto((Set<Student>) any()))
                .thenThrow(new SystemException("An error occurred", ErrorCode.INTERNAL_SERVER_ERROR));

        Course course = new Course();
        course.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        course.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course.setId(123L);
        course.setInstructors(new HashSet<>());
        course.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        course.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course.setName("Request to get all Students");
        course.setNumberOfLessons((short) 1);
        course.setStudents(new HashSet<>());

        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(course);
        when(this.courseRepository.findAllByIdIn((java.util.Collection<Long>) any())).thenReturn(courseList);
        assertThrows(SystemException.class, () -> this.courseServiceImpl.getAllStudentsPerCourse(new ArrayList<>()));
        verify(this.studentMapper).toDto((Set<Student>) any());
        verify(this.courseRepository).findAllByIdIn((java.util.Collection<Long>) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#findOne(Long)}
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
        Optional<Course> ofResult = Optional.of(course);
        when(this.courseRepository.findById((Long) any())).thenReturn(ofResult);

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
        when(this.courseMapper.toDto((Course) any())).thenReturn(courseDTO);
        assertTrue(this.courseServiceImpl.findOne(123L).isPresent());
        verify(this.courseRepository).findById((Long) any());
        verify(this.courseMapper).toDto((Course) any());
    }


    /**
     * Method under test: {@link CourseServiceImpl#findByIdOrThrow(Long)}
     */
    @Test
    void testFindByIdOrThrow() throws SystemException {
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
        Optional<Course> ofResult = Optional.of(course);
        when(this.courseRepository.findById((Long) any())).thenReturn(ofResult);

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
        when(this.courseMapper.toDto((Course) any())).thenReturn(courseDTO);
        assertSame(courseDTO, this.courseServiceImpl.findByIdOrThrow(123L));
        verify(this.courseRepository).findById((Long) any());
        verify(this.courseMapper).toDto((Course) any());
    }


    /**
     * Method under test: {@link CourseServiceImpl#findByIdOrThrow(Long)}
     */
    @Test
    void testFindByIdOrThrow3() throws SystemException {
        when(this.courseRepository.findById((Long) any())).thenReturn(Optional.empty());

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
        when(this.courseMapper.toDto((Course) any())).thenReturn(courseDTO);
        assertThrows(SystemException.class, () -> this.courseServiceImpl.findByIdOrThrow(123L));
        verify(this.courseRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#delete(Long)}
     */
    @Test
    void testDelete() {
        doNothing().when(this.courseRepository).deleteById((Long) any());
        this.courseServiceImpl.delete(123L);
        verify(this.courseRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#delete(Long)}
     */
    @Test
    void testDelete2() {
        doThrow(new SystemException("An error occurred", ErrorCode.INTERNAL_SERVER_ERROR)).when(this.courseRepository)
                .deleteById((Long) any());
        assertThrows(SystemException.class, () -> this.courseServiceImpl.delete(123L));
        verify(this.courseRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#assignStudentToCourse(Long, java.util.List)}
     */
    @Test
    void testAssignStudentToCourse() throws SystemException {
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
        Optional<Course> ofResult = Optional.of(course);
        when(this.courseRepository.findById((Long) any())).thenReturn(ofResult);

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
        when(this.courseMapper.toEntity((CourseDTO) any())).thenReturn(course1);
        when(this.courseMapper.toDto((Course) any())).thenReturn(courseDTO);
        assertThrows(SystemException.class, () -> this.courseServiceImpl.assignStudentToCourse(123L, new ArrayList<>()));
        verify(this.courseRepository).findById((Long) any());
        verify(this.courseMapper).toEntity((CourseDTO) any());
        verify(this.courseMapper).toDto((Course) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#assignStudentToCourse(Long, java.util.List)}
     */
    @Test
    void testAssignStudentToCourse2() throws SystemException {
        when(this.courseRepository.findById((Long) any())).thenReturn(Optional.empty());

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
        when(this.courseMapper.toEntity((CourseDTO) any())).thenReturn(course);
        when(this.courseMapper.toDto((Course) any())).thenReturn(courseDTO);
        assertThrows(SystemException.class, () -> this.courseServiceImpl.assignStudentToCourse(123L, new ArrayList<>()));
        verify(this.courseRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#assignStudentToCourse(Long, java.util.List)}
     */
    @Test
    void testAssignStudentToCourse3() throws SystemException {
        when(this.instructorRepository.existsById((Long) any())).thenReturn(true);

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
        Optional<Course> ofResult = Optional.of(course);

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
        when(this.courseRepository.save((Course) any())).thenReturn(course1);
        when(this.courseRepository.findById((Long) any())).thenReturn(ofResult);

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

        User user = new User();
        user.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        user.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        user.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setPassword("iloveyou");
        user.setSecondName("Request to get Course : {}");
        user.setUserRoles("Request to get Course : {}");

        Instructor instructor = new Instructor();
        instructor.setCourses(new HashSet<>());
        instructor.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        instructor.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructor.setId(123L);
        instructor.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        instructor.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructor.setUser(user);

        HashSet<Instructor> instructorSet = new HashSet<>();
        instructorSet.add(instructor);

        Course course2 = new Course();
        course2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        course2.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course2.setId(123L);
        course2.setInstructors(instructorSet);
        course2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        course2.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course2.setName("Name");
        course2.setNumberOfLessons((short) 1);
        course2.setStudents(new HashSet<>());
        when(this.courseMapper.toEntity((CourseDTO) any())).thenReturn(course2);
        when(this.courseMapper.toDto((Course) any())).thenReturn(courseDTO);
        assertSame(courseDTO, this.courseServiceImpl.assignStudentToCourse(123L, new ArrayList<>()));
        verify(this.instructorRepository).existsById((Long) any());
        verify(this.courseRepository).save((Course) any());
        verify(this.courseRepository).findById((Long) any());
        verify(this.courseMapper).toEntity((CourseDTO) any());
        verify(this.courseMapper, atLeast(1)).toDto((Course) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#assignStudentToCourse(Long, java.util.List)}
     */
    @Test
    void testAssignStudentToCourse4() throws SystemException {
        when(this.instructorRepository.existsById((Long) any())).thenReturn(true);

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
        Optional<Course> ofResult = Optional.of(course);
        when(this.courseRepository.save((Course) any()))
                .thenThrow(new SystemException("An error occurred", ErrorCode.INTERNAL_SERVER_ERROR));
        when(this.courseRepository.findById((Long) any())).thenReturn(ofResult);

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

        User user = new User();
        user.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        user.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        user.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setPassword("iloveyou");
        user.setSecondName("Request to get Course : {}");
        user.setUserRoles("Request to get Course : {}");

        Instructor instructor = new Instructor();
        instructor.setCourses(new HashSet<>());
        instructor.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        instructor.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructor.setId(123L);
        instructor.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        instructor.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructor.setUser(user);

        HashSet<Instructor> instructorSet = new HashSet<>();
        instructorSet.add(instructor);

        Course course1 = new Course();
        course1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        course1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course1.setId(123L);
        course1.setInstructors(instructorSet);
        course1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        course1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course1.setName("Name");
        course1.setNumberOfLessons((short) 1);
        course1.setStudents(new HashSet<>());
        when(this.courseMapper.toEntity((CourseDTO) any())).thenReturn(course1);
        when(this.courseMapper.toDto((Course) any())).thenReturn(courseDTO);
        assertThrows(SystemException.class, () -> this.courseServiceImpl.assignStudentToCourse(123L, new ArrayList<>()));
        verify(this.instructorRepository).existsById((Long) any());
        verify(this.courseRepository).save((Course) any());
        verify(this.courseRepository).findById((Long) any());
        verify(this.courseMapper).toEntity((CourseDTO) any());
        verify(this.courseMapper).toDto((Course) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#assignStudentToCourse(Long, java.util.List)}
     */
    @Test
    void testAssignStudentToCourse5() throws SystemException {
        when(this.instructorRepository.existsById((Long) any())).thenReturn(false);

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
        Optional<Course> ofResult = Optional.of(course);

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
        when(this.courseRepository.save((Course) any())).thenReturn(course1);
        when(this.courseRepository.findById((Long) any())).thenReturn(ofResult);

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

        User user = new User();
        user.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        user.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        user.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setPassword("iloveyou");
        user.setSecondName("Request to get Course : {}");
        user.setUserRoles("Request to get Course : {}");

        Instructor instructor = new Instructor();
        instructor.setCourses(new HashSet<>());
        instructor.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        instructor.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructor.setId(123L);
        instructor.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        instructor.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructor.setUser(user);

        HashSet<Instructor> instructorSet = new HashSet<>();
        instructorSet.add(instructor);

        Course course2 = new Course();
        course2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        course2.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course2.setId(123L);
        course2.setInstructors(instructorSet);
        course2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        course2.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course2.setName("Name");
        course2.setNumberOfLessons((short) 1);
        course2.setStudents(new HashSet<>());
        when(this.courseMapper.toEntity((CourseDTO) any())).thenReturn(course2);
        when(this.courseMapper.toDto((Course) any())).thenReturn(courseDTO);
        assertThrows(SystemException.class, () -> this.courseServiceImpl.assignStudentToCourse(123L, new ArrayList<>()));
        verify(this.instructorRepository).existsById((Long) any());
        verify(this.courseRepository).findById((Long) any());
        verify(this.courseMapper).toEntity((CourseDTO) any());
        verify(this.courseMapper).toDto((Course) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#assignInstructorToCourse(Long, java.util.List)}
     */
    @Test
    void testAssignInstructorToCourse() throws SystemException {
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
        Optional<Course> ofResult = Optional.of(course);
        when(this.courseRepository.findById((Long) any())).thenReturn(ofResult);

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
        when(this.courseMapper.toEntity((CourseDTO) any())).thenReturn(course1);
        when(this.courseMapper.toDto((Course) any())).thenReturn(courseDTO);
        assertThrows(SystemException.class, () -> this.courseServiceImpl.assignInstructorToCourse(123L, new ArrayList<>()));
        verify(this.courseRepository).findById((Long) any());
        verify(this.courseMapper).toEntity((CourseDTO) any());
        verify(this.courseMapper).toDto((Course) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#assignInstructorToCourse(Long, java.util.List)}
     */
    @Test
    void testAssignInstructorToCourse2() throws SystemException {
        when(this.courseRepository.findById((Long) any())).thenReturn(Optional.empty());

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
        when(this.courseMapper.toEntity((CourseDTO) any())).thenReturn(course);
        when(this.courseMapper.toDto((Course) any())).thenReturn(courseDTO);
        assertThrows(SystemException.class, () -> this.courseServiceImpl.assignInstructorToCourse(123L, new ArrayList<>()));
        verify(this.courseRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#assignInstructorToCourse(Long, java.util.List)}
     */
    @Test
    void testAssignInstructorToCourse3() throws SystemException {
        when(this.instructorRepository.existsById((Long) any())).thenReturn(true);

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
        Optional<Course> ofResult = Optional.of(course);

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
        when(this.courseRepository.save((Course) any())).thenReturn(course1);
        when(this.courseRepository.findById((Long) any())).thenReturn(ofResult);

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

        User user = new User();
        user.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        user.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        user.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setPassword("iloveyou");
        user.setSecondName("Request to get Course : {}");
        user.setUserRoles("Request to get Course : {}");

        Instructor instructor = new Instructor();
        instructor.setCourses(new HashSet<>());
        instructor.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        instructor.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructor.setId(123L);
        instructor.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        instructor.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructor.setUser(user);

        HashSet<Instructor> instructorSet = new HashSet<>();
        instructorSet.add(instructor);

        Course course2 = new Course();
        course2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        course2.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course2.setId(123L);
        course2.setInstructors(instructorSet);
        course2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        course2.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course2.setName("Name");
        course2.setNumberOfLessons((short) 1);
        course2.setStudents(new HashSet<>());
        when(this.courseMapper.toEntity((CourseDTO) any())).thenReturn(course2);
        when(this.courseMapper.toDto((Course) any())).thenReturn(courseDTO);
        assertSame(courseDTO, this.courseServiceImpl.assignInstructorToCourse(123L, new ArrayList<>()));
        verify(this.instructorRepository).existsById((Long) any());
        verify(this.courseRepository).save((Course) any());
        verify(this.courseRepository).findById((Long) any());
        verify(this.courseMapper).toEntity((CourseDTO) any());
        verify(this.courseMapper, atLeast(1)).toDto((Course) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#assignInstructorToCourse(Long, java.util.List)}
     */
    @Test
    void testAssignInstructorToCourse4() throws SystemException {
        when(this.instructorRepository.existsById((Long) any())).thenReturn(true);

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
        Optional<Course> ofResult = Optional.of(course);
        when(this.courseRepository.save((Course) any()))
                .thenThrow(new SystemException("An error occurred", ErrorCode.INTERNAL_SERVER_ERROR));
        when(this.courseRepository.findById((Long) any())).thenReturn(ofResult);

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

        User user = new User();
        user.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        user.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        user.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setPassword("iloveyou");
        user.setSecondName("Request to get Course : {}");
        user.setUserRoles("Request to get Course : {}");

        Instructor instructor = new Instructor();
        instructor.setCourses(new HashSet<>());
        instructor.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        instructor.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructor.setId(123L);
        instructor.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        instructor.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructor.setUser(user);

        HashSet<Instructor> instructorSet = new HashSet<>();
        instructorSet.add(instructor);

        Course course1 = new Course();
        course1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        course1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course1.setId(123L);
        course1.setInstructors(instructorSet);
        course1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        course1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course1.setName("Name");
        course1.setNumberOfLessons((short) 1);
        course1.setStudents(new HashSet<>());
        when(this.courseMapper.toEntity((CourseDTO) any())).thenReturn(course1);
        when(this.courseMapper.toDto((Course) any())).thenReturn(courseDTO);
        assertThrows(SystemException.class, () -> this.courseServiceImpl.assignInstructorToCourse(123L, new ArrayList<>()));
        verify(this.instructorRepository).existsById((Long) any());
        verify(this.courseRepository).save((Course) any());
        verify(this.courseRepository).findById((Long) any());
        verify(this.courseMapper).toEntity((CourseDTO) any());
        verify(this.courseMapper).toDto((Course) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#assignInstructorToCourse(Long, java.util.List)}
     */
    @Test
    void testAssignInstructorToCourse5() throws SystemException {
        when(this.instructorRepository.existsById((Long) any())).thenReturn(false);

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
        Optional<Course> ofResult = Optional.of(course);

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
        when(this.courseRepository.save((Course) any())).thenReturn(course1);
        when(this.courseRepository.findById((Long) any())).thenReturn(ofResult);

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

        User user = new User();
        user.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        user.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        user.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setPassword("iloveyou");
        user.setSecondName("Request to get Course : {}");
        user.setUserRoles("Request to get Course : {}");

        Instructor instructor = new Instructor();
        instructor.setCourses(new HashSet<>());
        instructor.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        instructor.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructor.setId(123L);
        instructor.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        instructor.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructor.setUser(user);

        HashSet<Instructor> instructorSet = new HashSet<>();
        instructorSet.add(instructor);

        Course course2 = new Course();
        course2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        course2.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course2.setId(123L);
        course2.setInstructors(instructorSet);
        course2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        course2.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course2.setName("Name");
        course2.setNumberOfLessons((short) 1);
        course2.setStudents(new HashSet<>());
        when(this.courseMapper.toEntity((CourseDTO) any())).thenReturn(course2);
        when(this.courseMapper.toDto((Course) any())).thenReturn(courseDTO);
        assertThrows(SystemException.class, () -> this.courseServiceImpl.assignInstructorToCourse(123L, new ArrayList<>()));
        verify(this.instructorRepository).existsById((Long) any());
        verify(this.courseRepository).findById((Long) any());
        verify(this.courseMapper).toEntity((CourseDTO) any());
        verify(this.courseMapper).toDto((Course) any());
    }
}

