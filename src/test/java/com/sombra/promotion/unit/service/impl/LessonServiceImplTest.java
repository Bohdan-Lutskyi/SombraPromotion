package com.sombra.promotion.unit.service.impl;

import com.sombra.promotion.config.SystemProperties;
import com.sombra.promotion.config.error.ErrorCode;
import com.sombra.promotion.config.error.SystemException;
import com.sombra.promotion.domain.Course;
import com.sombra.promotion.domain.Lesson;
import com.sombra.promotion.domain.Student;
import com.sombra.promotion.domain.User;
import com.sombra.promotion.dto.CourseDTO;
import com.sombra.promotion.dto.LessonDTO;
import com.sombra.promotion.dto.StudentCourseDTO;
import com.sombra.promotion.dto.StudentDTO;
import com.sombra.promotion.repository.LessonRepository;
import com.sombra.promotion.service.CourseService;
import com.sombra.promotion.service.StudentService;
import com.sombra.promotion.service.impl.LessonServiceImpl;
import com.sombra.promotion.service.mapper.LessonMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {LessonServiceImpl.class})
@ExtendWith(SpringExtension.class)
class LessonServiceImplTest {
    @MockBean
    private CourseService courseService;

    @MockBean
    private LessonMapper lessonMapper;

    @MockBean
    private LessonRepository lessonRepository;

    @Autowired
    private LessonServiceImpl lessonServiceImpl;

    @MockBean
    private StudentService studentService;
    @MockBean
    private SystemProperties systemProperties;

    /**
     * Method under test: {@link LessonServiceImpl#save(LessonDTO)}
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

        Lesson lesson = new Lesson();
        lesson.setCourse(course);
        lesson.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        lesson.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lesson.setId(123L);
        lesson.setLessonNumber((short) 1);
        lesson.setMark((short) 1);
        lesson.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        lesson.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lesson.setStudent(student);
        lesson.setStudentAttachments(new HashSet<>());
        when(this.lessonRepository.save((Lesson) any())).thenReturn(lesson);

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

        Lesson lesson1 = new Lesson();
        lesson1.setCourse(course1);
        lesson1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        lesson1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lesson1.setId(123L);
        lesson1.setLessonNumber((short) 1);
        lesson1.setMark((short) 1);
        lesson1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        lesson1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lesson1.setStudent(student1);
        lesson1.setStudentAttachments(new HashSet<>());

        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setCourseId(123L);
        lessonDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        lessonDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lessonDTO.setId(123L);
        lessonDTO.setLessonNumber((short) 1);
        lessonDTO.setMark((short) 1);
        lessonDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        lessonDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lessonDTO.setStudentAttachmentIds(new HashSet<>());
        lessonDTO.setStudentId(123L);
        when(this.lessonMapper.toEntity((LessonDTO) any())).thenReturn(lesson1);
        when(this.lessonMapper.toDto((Lesson) any())).thenReturn(lessonDTO);

        LessonDTO lessonDTO1 = new LessonDTO();
        lessonDTO1.setCourseId(123L);
        lessonDTO1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        lessonDTO1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lessonDTO1.setId(123L);
        lessonDTO1.setLessonNumber((short) 1);
        lessonDTO1.setMark((short) 1);
        lessonDTO1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        lessonDTO1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lessonDTO1.setStudentAttachmentIds(new HashSet<>());
        lessonDTO1.setStudentId(123L);
        assertSame(lessonDTO, this.lessonServiceImpl.save(lessonDTO1));
        verify(this.lessonRepository).save((Lesson) any());
        verify(this.lessonMapper).toEntity((LessonDTO) any());
        verify(this.lessonMapper).toDto((Lesson) any());
    }

    /**
     * Method under test: {@link LessonServiceImpl#save(LessonDTO)}
     */
    @Test
    void testSave2() {
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

        Lesson lesson = new Lesson();
        lesson.setCourse(course);
        lesson.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        lesson.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lesson.setId(123L);
        lesson.setLessonNumber((short) 1);
        lesson.setMark((short) 1);
        lesson.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        lesson.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lesson.setStudent(student);
        lesson.setStudentAttachments(new HashSet<>());
        when(this.lessonRepository.save((Lesson) any())).thenReturn(lesson);
        when(this.lessonMapper.toEntity((LessonDTO) any()))
                .thenThrow(new SystemException("An error occurred", ErrorCode.INTERNAL_SERVER_ERROR));
        when(this.lessonMapper.toDto((Lesson) any()))
                .thenThrow(new SystemException("An error occurred", ErrorCode.INTERNAL_SERVER_ERROR));

        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setCourseId(123L);
        lessonDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        lessonDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lessonDTO.setId(123L);
        lessonDTO.setLessonNumber((short) 1);
        lessonDTO.setMark((short) 1);
        lessonDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        lessonDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lessonDTO.setStudentAttachmentIds(new HashSet<>());
        lessonDTO.setStudentId(123L);
        assertThrows(SystemException.class, () -> this.lessonServiceImpl.save(lessonDTO));
        verify(this.lessonMapper).toEntity((LessonDTO) any());
    }

    /**
     * Method under test: {@link LessonServiceImpl#findAll()}
     */
    @Test
    void testFindAll() {
        when(this.lessonRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(this.lessonServiceImpl.findAll().isEmpty());
        verify(this.lessonRepository).findAll();
    }

    /**
     * Method under test: {@link LessonServiceImpl#findOne(Long)}
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

        Lesson lesson = new Lesson();
        lesson.setCourse(course);
        lesson.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        lesson.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lesson.setId(123L);
        lesson.setLessonNumber((short) 1);
        lesson.setMark((short) 1);
        lesson.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        lesson.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lesson.setStudent(student);
        lesson.setStudentAttachments(new HashSet<>());
        Optional<Lesson> ofResult = Optional.of(lesson);
        when(this.lessonRepository.findById((Long) any())).thenReturn(ofResult);

        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setCourseId(123L);
        lessonDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        lessonDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lessonDTO.setId(123L);
        lessonDTO.setLessonNumber((short) 1);
        lessonDTO.setMark((short) 1);
        lessonDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        lessonDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lessonDTO.setStudentAttachmentIds(new HashSet<>());
        lessonDTO.setStudentId(123L);
        when(this.lessonMapper.toDto((Lesson) any())).thenReturn(lessonDTO);
        assertTrue(this.lessonServiceImpl.findOne(123L).isPresent());
        verify(this.lessonRepository).findById((Long) any());
        verify(this.lessonMapper).toDto((Lesson) any());
    }

    /**
     * Method under test: {@link LessonServiceImpl#getAllLessonsByStudentId(Long)}
     */
    @Test
    void testGetAllLessonsByStudentId() {
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
        when(this.lessonRepository.findByStudentId((Long) any())).thenReturn(new ArrayList<>());
        ArrayList<LessonDTO> lessonDTOList = new ArrayList<>();
        when(this.lessonMapper.toDto((List<Lesson>) any())).thenReturn(lessonDTOList);
        List<LessonDTO> actualAllLessonsByStudentId = this.lessonServiceImpl.getAllLessonsByStudentId(123L);
        assertSame(lessonDTOList, actualAllLessonsByStudentId);
        assertTrue(actualAllLessonsByStudentId.isEmpty());
        verify(this.studentService).getStudentOrThrow((Long) any());
        verify(this.lessonRepository).findByStudentId((Long) any());
        verify(this.lessonMapper).toDto((List<Lesson>) any());
    }

    /**
     * Method under test: {@link LessonServiceImpl#getAllLessonsByStudentId(Long)}
     */
    @Test
    void testGetAllLessonsByStudentId2() {
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
        when(this.lessonRepository.findByStudentId((Long) any())).thenReturn(new ArrayList<>());
        when(this.lessonMapper.toDto((List<Lesson>) any()))
                .thenThrow(new SystemException("An error occurred", ErrorCode.INTERNAL_SERVER_ERROR));
        assertThrows(SystemException.class, () -> this.lessonServiceImpl.getAllLessonsByStudentId(123L));
        verify(this.studentService).getStudentOrThrow((Long) any());
        verify(this.lessonRepository).findByStudentId((Long) any());
        verify(this.lessonMapper).toDto((List<Lesson>) any());
    }

    /**
     * Method under test: {@link LessonServiceImpl#delete(Long)}
     */
    @Test
    void testDelete() {
        doNothing().when(this.lessonRepository).deleteById((Long) any());
        this.lessonServiceImpl.delete(123L);
        verify(this.lessonRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link LessonServiceImpl#delete(Long)}
     */
    @Test
    void testDelete2() {
        doThrow(new SystemException("An error occurred", ErrorCode.INTERNAL_SERVER_ERROR)).when(this.lessonRepository)
                .deleteById((Long) any());
        assertThrows(SystemException.class, () -> this.lessonServiceImpl.delete(123L));
        verify(this.lessonRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link LessonServiceImpl#calculateFinalMark(StudentCourseDTO)}
     */
    @Test
    void testCalculateFinalMark() {
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
        when(this.lessonRepository.findByStudentIdAndCourseId((Long) any(), (Long) any())).thenReturn(new ArrayList<>());

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

        StudentCourseDTO studentCourseDTO = new StudentCourseDTO();
        studentCourseDTO.setCourseId(123L);
        studentCourseDTO.setStudentId(123L);
        assertThrows(SystemException.class, () -> this.lessonServiceImpl.calculateFinalMark(studentCourseDTO));
        verify(this.studentService).getStudentOrThrow((Long) any());
        verify(this.lessonRepository).findByStudentIdAndCourseId((Long) any(), (Long) any());
        verify(this.courseService).findByIdOrThrow((Long) any());
    }

    /**
     * Method under test: {@link LessonServiceImpl#calculateFinalMark(StudentCourseDTO)}
     */
    @Test
    void testCalculateFinalMark2() {
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
        when(this.lessonRepository.findByStudentIdAndCourseId((Long) any(), (Long) any()))
                .thenThrow(new SystemException("An error occurred", ErrorCode.INTERNAL_SERVER_ERROR));

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

        StudentCourseDTO studentCourseDTO = new StudentCourseDTO();
        studentCourseDTO.setCourseId(123L);
        studentCourseDTO.setStudentId(123L);
        assertThrows(SystemException.class, () -> this.lessonServiceImpl.calculateFinalMark(studentCourseDTO));
        verify(this.studentService).getStudentOrThrow((Long) any());
        verify(this.lessonRepository).findByStudentIdAndCourseId((Long) any(), (Long) any());
        verify(this.courseService).findByIdOrThrow((Long) any());
    }

    /**
     * Method under test: {@link LessonServiceImpl#calculateFinalMark(StudentCourseDTO)}
     */
    @Test
    void testCalculateFinalMark3() {
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
        when(this.lessonRepository.findByStudentIdAndCourseId((Long) any(), (Long) any())).thenReturn(new ArrayList<>());
        CourseDTO courseDTO = mock(CourseDTO.class);
        when(courseDTO.getName()).thenThrow(new SystemException("An error occurred", ErrorCode.INTERNAL_SERVER_ERROR));
        when(courseDTO.getId()).thenReturn(123L);
        when(courseDTO.getNumberOfLessons()).thenReturn((short) 1);
        doNothing().when(courseDTO).setCreatedBy((String) any());
        doNothing().when(courseDTO).setCreatedDate((LocalDateTime) any());
        doNothing().when(courseDTO).setModifiedBy((String) any());
        doNothing().when(courseDTO).setModifiedDate((LocalDateTime) any());
        doNothing().when(courseDTO).setId((Long) any());
        doNothing().when(courseDTO).setInstructorIds((java.util.Set<Long>) any());
        doNothing().when(courseDTO).setName((String) any());
        doNothing().when(courseDTO).setNumberOfLessons((Short) any());
        doNothing().when(courseDTO).setStudentIds((java.util.Set<Long>) any());
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

        StudentCourseDTO studentCourseDTO = new StudentCourseDTO();
        studentCourseDTO.setCourseId(123L);
        studentCourseDTO.setStudentId(123L);
        assertThrows(SystemException.class, () -> this.lessonServiceImpl.calculateFinalMark(studentCourseDTO));
        verify(this.studentService).getStudentOrThrow((Long) any());
        verify(this.lessonRepository).findByStudentIdAndCourseId((Long) any(), (Long) any());
        verify(this.courseService).findByIdOrThrow((Long) any());
        verify(courseDTO).getId();
        verify(courseDTO).getNumberOfLessons();
        verify(courseDTO).getName();
        verify(courseDTO).setCreatedBy((String) any());
        verify(courseDTO).setCreatedDate((LocalDateTime) any());
        verify(courseDTO).setModifiedBy((String) any());
        verify(courseDTO).setModifiedDate((LocalDateTime) any());
        verify(courseDTO).setId((Long) any());
        verify(courseDTO).setInstructorIds((java.util.Set<Long>) any());
        verify(courseDTO).setName((String) any());
        verify(courseDTO).setNumberOfLessons((Short) any());
        verify(courseDTO).setStudentIds((java.util.Set<Long>) any());
    }

    /**
     * Method under test: {@link LessonServiceImpl#calculateFinalMark(StudentCourseDTO)}
     */
    @Test
    void testCalculateFinalMark4() {
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

        Course course = new Course();
        course.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        course.setCreatedDate(LocalDateTime.of(4, 4, 4, 4, 1));
        course.setId(123L);
        course.setInstructors(new HashSet<>());
        course.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        course.setModifiedDate(LocalDateTime.of(4, 4, 4, 4, 1));
        course.setName(
                "Not able to calculate final mark for course: %s and student: %s, because student should have %f lessons"
                        + " but it is %d");
        course.setNumberOfLessons((short) 4);
        course.setStudents(new HashSet<>());

        User user = new User();
        user.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        user.setCreatedDate(LocalDateTime.of(4, 4, 4, 4, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        user.setModifiedDate(LocalDateTime.of(4, 4, 4, 4, 1));
        user.setPassword("iloveyou");
        user.setSecondName(
                "Not able to calculate final mark for course: %s and student: %s, because student should have %f lessons"
                        + " but it is %d");
        user.setUserRoles(
                "Not able to calculate final mark for course: %s and student: %s, because student should have %f lessons"
                        + " but it is %d");

        Student student = new Student();
        student.setCourses(new HashSet<>());
        student.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        student.setCreatedDate(LocalDateTime.of(4, 4, 4, 4, 1));
        student.setId(123L);
        student.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        student.setModifiedDate(LocalDateTime.of(4, 4, 4, 4, 1));
        student.setUser(user);

        Lesson lesson = new Lesson();
        lesson.setCourse(course);
        lesson.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        lesson.setCreatedDate(LocalDateTime.of(4, 4, 4, 4, 1));
        lesson.setId(123L);
        lesson.setLessonNumber((short) 4);
        lesson.setMark((short) 4);
        lesson.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        lesson.setModifiedDate(LocalDateTime.of(4, 4, 4, 4, 1));
        lesson.setStudent(student);
        lesson.setStudentAttachments(new HashSet<>());

        ArrayList<Lesson> lessonList = new ArrayList<>();
        lessonList.add(lesson);
        when(this.lessonRepository.findByStudentIdAndCourseId((Long) any(), (Long) any())).thenReturn(lessonList);
        CourseDTO courseDTO = mock(CourseDTO.class);
        when(courseDTO.getName()).thenThrow(new SystemException("An error occurred", ErrorCode.INTERNAL_SERVER_ERROR));
        when(courseDTO.getId()).thenReturn(123L);
        when(courseDTO.getNumberOfLessons()).thenReturn((short) 1);
        doNothing().when(courseDTO).setCreatedBy((String) any());
        doNothing().when(courseDTO).setCreatedDate((LocalDateTime) any());
        doNothing().when(courseDTO).setModifiedBy((String) any());
        doNothing().when(courseDTO).setModifiedDate((LocalDateTime) any());
        doNothing().when(courseDTO).setId((Long) any());
        doNothing().when(courseDTO).setInstructorIds((java.util.Set<Long>) any());
        doNothing().when(courseDTO).setName((String) any());
        doNothing().when(courseDTO).setNumberOfLessons((Short) any());
        doNothing().when(courseDTO).setStudentIds((java.util.Set<Long>) any());
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

        StudentCourseDTO studentCourseDTO = new StudentCourseDTO();
        studentCourseDTO.setCourseId(123L);
        studentCourseDTO.setStudentId(123L);
        assertEquals(4.0d, this.lessonServiceImpl.calculateFinalMark(studentCourseDTO).getFinalMark().doubleValue());
        verify(this.studentService).getStudentOrThrow((Long) any());
        verify(this.lessonRepository).findByStudentIdAndCourseId((Long) any(), (Long) any());
        verify(this.courseService).findByIdOrThrow((Long) any());
        verify(courseDTO).getId();
        verify(courseDTO).getNumberOfLessons();
        verify(courseDTO).setCreatedBy((String) any());
        verify(courseDTO).setCreatedDate((LocalDateTime) any());
        verify(courseDTO).setModifiedBy((String) any());
        verify(courseDTO).setModifiedDate((LocalDateTime) any());
        verify(courseDTO).setId((Long) any());
        verify(courseDTO).setInstructorIds((java.util.Set<Long>) any());
        verify(courseDTO).setName((String) any());
        verify(courseDTO).setNumberOfLessons((Short) any());
        verify(courseDTO).setStudentIds((java.util.Set<Long>) any());
    }

    /**
     * Method under test: {@link LessonServiceImpl#findByIdOrThrow(Long)}
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

        Lesson lesson = new Lesson();
        lesson.setCourse(course);
        lesson.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        lesson.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lesson.setId(123L);
        lesson.setLessonNumber((short) 1);
        lesson.setMark((short) 1);
        lesson.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        lesson.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lesson.setStudent(student);
        lesson.setStudentAttachments(new HashSet<>());
        Optional<Lesson> ofResult = Optional.of(lesson);
        when(this.lessonRepository.findById((Long) any())).thenReturn(ofResult);

        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setCourseId(123L);
        lessonDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        lessonDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lessonDTO.setId(123L);
        lessonDTO.setLessonNumber((short) 1);
        lessonDTO.setMark((short) 1);
        lessonDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        lessonDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lessonDTO.setStudentAttachmentIds(new HashSet<>());
        lessonDTO.setStudentId(123L);
        when(this.lessonMapper.toDto((Lesson) any())).thenReturn(lessonDTO);
        assertSame(lessonDTO, this.lessonServiceImpl.findByIdOrThrow(123L));
        verify(this.lessonRepository).findById((Long) any());
        verify(this.lessonMapper).toDto((Lesson) any());
    }
}

