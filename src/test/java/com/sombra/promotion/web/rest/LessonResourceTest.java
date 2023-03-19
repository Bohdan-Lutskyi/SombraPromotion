package com.sombra.promotion.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sombra.promotion.config.SystemProperties;
import com.sombra.promotion.config.error.SystemException;
import com.sombra.promotion.domain.Course;
import com.sombra.promotion.domain.Lesson;
import com.sombra.promotion.domain.Student;
import com.sombra.promotion.domain.User;
import com.sombra.promotion.dto.CoursePassDTO;
import com.sombra.promotion.dto.LessonDTO;
import com.sombra.promotion.dto.StudentCourseDTO;
import com.sombra.promotion.repository.*;
import com.sombra.promotion.service.LessonService;
import com.sombra.promotion.service.impl.CourseServiceImpl;
import com.sombra.promotion.service.impl.LessonServiceImpl;
import com.sombra.promotion.service.impl.StudentServiceImpl;
import com.sombra.promotion.service.impl.UserServiceImpl;
import com.sombra.promotion.service.mapper.CourseMapperImpl;
import com.sombra.promotion.service.mapper.LessonMapperImpl;
import com.sombra.promotion.service.mapper.StudentMapperImpl;
import com.sombra.promotion.service.mapper.UserMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ContextConfiguration(classes = {LessonResource.class})
@ExtendWith(SpringExtension.class)
class LessonResourceTest {
    @MockBean
    private LessonRepository lessonRepository;

    @Autowired
    private LessonResource lessonResource;

    @MockBean
    private LessonService lessonService;

    /**
     * Method under test: {@link LessonResource#createLesson(LessonDTO)}
     */
    @Test
    void testCreateLesson() throws URISyntaxException {
        LessonRepository lessonRepository = mock(LessonRepository.class);
        LessonMapperImpl lessonMapper = new LessonMapperImpl();
        StudentRepository studentRepository = mock(StudentRepository.class);
        StudentMapperImpl studentMapper = new StudentMapperImpl();
        UserRepository userRepository = mock(UserRepository.class);
        UserMapperImpl userMapper = new UserMapperImpl();
        UserServiceImpl userService = new UserServiceImpl(userRepository, userMapper, new Argon2PasswordEncoder());

        StudentServiceImpl studentService = new StudentServiceImpl(studentRepository, studentMapper, userService,
                new UserMapperImpl());

        CourseRepository courseRepository = mock(CourseRepository.class);
        CourseMapperImpl courseMapper = new CourseMapperImpl();
        StudentRepository studentRepository1 = mock(StudentRepository.class);
        InstructorRepository instructorRepository = mock(InstructorRepository.class);
        SystemProperties systemProperties = mock(SystemProperties.class);
        LessonResource lessonResource = new LessonResource(
                new LessonServiceImpl(lessonRepository, lessonMapper, studentService, new CourseServiceImpl(courseRepository,
                        courseMapper, studentRepository1, instructorRepository), systemProperties),
                mock(LessonRepository.class));

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
        assertThrows(SystemException.class, () -> lessonResource.createLesson(lessonDTO));
    }

    /**
     * Method under test: {@link LessonResource#createLesson(LessonDTO)}
     */
    @Test
    void testCreateLesson2() throws URISyntaxException {
        LessonRepository lessonRepository = mock(LessonRepository.class);
        LessonMapperImpl lessonMapper = new LessonMapperImpl();
        StudentRepository studentRepository = mock(StudentRepository.class);
        StudentMapperImpl studentMapper = new StudentMapperImpl();
        UserRepository userRepository = mock(UserRepository.class);
        UserMapperImpl userMapper = new UserMapperImpl();
        UserServiceImpl userService = new UserServiceImpl(userRepository, userMapper, new Argon2PasswordEncoder());

        StudentServiceImpl studentService = new StudentServiceImpl(studentRepository, studentMapper, userService,
                new UserMapperImpl());

        CourseRepository courseRepository = mock(CourseRepository.class);
        CourseMapperImpl courseMapper = new CourseMapperImpl();
        StudentRepository studentRepository1 = mock(StudentRepository.class);
        InstructorRepository instructorRepository = mock(InstructorRepository.class);
        SystemProperties systemProperties = mock(SystemProperties.class);
        LessonResource lessonResource = new LessonResource(
                new LessonServiceImpl(lessonRepository, lessonMapper, studentService, new CourseServiceImpl(courseRepository,
                        courseMapper, studentRepository1, instructorRepository), systemProperties),
                mock(LessonRepository.class));
        LessonDTO lessonDTO = mock(LessonDTO.class);
        when(lessonDTO.getId()).thenReturn(123L);
        doNothing().when(lessonDTO).setCreatedBy((String) any());
        doNothing().when(lessonDTO).setCreatedDate((LocalDateTime) any());
        doNothing().when(lessonDTO).setModifiedBy((String) any());
        doNothing().when(lessonDTO).setModifiedDate((LocalDateTime) any());
        doNothing().when(lessonDTO).setCourseId((Long) any());
        doNothing().when(lessonDTO).setId((Long) any());
        doNothing().when(lessonDTO).setLessonNumber((Short) any());
        doNothing().when(lessonDTO).setMark((Short) any());
        doNothing().when(lessonDTO).setStudentAttachmentIds((java.util.Set<Long>) any());
        doNothing().when(lessonDTO).setStudentId((Long) any());
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
        assertThrows(SystemException.class, () -> lessonResource.createLesson(lessonDTO));
        verify(lessonDTO).getId();
        verify(lessonDTO).setCreatedBy((String) any());
        verify(lessonDTO).setCreatedDate((LocalDateTime) any());
        verify(lessonDTO).setModifiedBy((String) any());
        verify(lessonDTO).setModifiedDate((LocalDateTime) any());
        verify(lessonDTO).setCourseId((Long) any());
        verify(lessonDTO).setId((Long) any());
        verify(lessonDTO).setLessonNumber((Short) any());
        verify(lessonDTO).setMark((Short) any());
        verify(lessonDTO).setStudentAttachmentIds((java.util.Set<Long>) any());
        verify(lessonDTO).setStudentId((Long) any());
    }

    /**
     * Method under test: {@link LessonResource#createLesson(LessonDTO)}
     */
    @Test
    void testCreateLesson3() throws URISyntaxException {
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
        LessonRepository lessonRepository = mock(LessonRepository.class);
        when(lessonRepository.save((Lesson) any())).thenReturn(lesson);
        LessonMapperImpl lessonMapper = new LessonMapperImpl();
        StudentRepository studentRepository = mock(StudentRepository.class);
        StudentMapperImpl studentMapper = new StudentMapperImpl();
        UserRepository userRepository = mock(UserRepository.class);
        UserMapperImpl userMapper = new UserMapperImpl();
        UserServiceImpl userService = new UserServiceImpl(userRepository, userMapper, new Argon2PasswordEncoder());

        StudentServiceImpl studentService = new StudentServiceImpl(studentRepository, studentMapper, userService,
                new UserMapperImpl());

        CourseRepository courseRepository = mock(CourseRepository.class);
        CourseMapperImpl courseMapper = new CourseMapperImpl();
        StudentRepository studentRepository1 = mock(StudentRepository.class);
        InstructorRepository instructorRepository = mock(InstructorRepository.class);
        SystemProperties systemProperties = mock(SystemProperties.class);
        LessonResource lessonResource = new LessonResource(
                new LessonServiceImpl(lessonRepository, lessonMapper, studentService, new CourseServiceImpl(courseRepository,
                        courseMapper, studentRepository1, instructorRepository), systemProperties),
                mock(LessonRepository.class));
        LessonDTO lessonDTO = mock(LessonDTO.class);
        when(lessonDTO.getCourseId()).thenReturn(123L);
        when(lessonDTO.getStudentId()).thenReturn(123L);
        when(lessonDTO.getLessonNumber()).thenReturn((short) 1);
        when(lessonDTO.getMark()).thenReturn((short) 1);
        when(lessonDTO.getCreatedBy()).thenReturn("Jan 1, 2020 8:00am GMT+0100");
        when(lessonDTO.getModifiedBy()).thenReturn("Jan 1, 2020 9:00am GMT+0100");
        when(lessonDTO.getCreatedDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(lessonDTO.getModifiedDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(lessonDTO.getId()).thenReturn(null);
        doNothing().when(lessonDTO).setCreatedBy((String) any());
        doNothing().when(lessonDTO).setCreatedDate((LocalDateTime) any());
        doNothing().when(lessonDTO).setModifiedBy((String) any());
        doNothing().when(lessonDTO).setModifiedDate((LocalDateTime) any());
        doNothing().when(lessonDTO).setCourseId((Long) any());
        doNothing().when(lessonDTO).setId((Long) any());
        doNothing().when(lessonDTO).setLessonNumber((Short) any());
        doNothing().when(lessonDTO).setMark((Short) any());
        doNothing().when(lessonDTO).setStudentAttachmentIds((Set<Long>) any());
        doNothing().when(lessonDTO).setStudentId((Long) any());
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
        ResponseEntity<LessonDTO> actualCreateLessonResult = lessonResource.createLesson(lessonDTO);
        assertTrue(actualCreateLessonResult.hasBody());
        assertEquals(3, actualCreateLessonResult.getHeaders().size());
        assertEquals(HttpStatus.CREATED, actualCreateLessonResult.getStatusCode());
        LessonDTO body = actualCreateLessonResult.getBody();
        assertEquals("Jan 1, 2020 9:00am GMT+0100", body.getModifiedBy());
        assertEquals("01:01", body.getModifiedDate().toLocalTime().toString());
        assertEquals((short) 1, body.getMark().shortValue());
        assertEquals((short) 1, body.getLessonNumber().shortValue());
        assertEquals(123L, body.getId().longValue());
        assertEquals("Jan 1, 2020 8:00am GMT+0100", body.getCreatedBy());
        assertEquals("01:01", body.getCreatedDate().toLocalTime().toString());
        assertEquals(123L, body.getCourseId().longValue());
        assertEquals(123L, body.getStudentId().longValue());
        assertTrue(body.getStudentAttachmentIds().isEmpty());
        verify(lessonRepository).save((Lesson) any());
        verify(lessonDTO, atLeast(2)).getCourseId();
        verify(lessonDTO, atLeast(1)).getId();
        verify(lessonDTO, atLeast(2)).getStudentId();
        verify(lessonDTO, atLeast(2)).getLessonNumber();
        verify(lessonDTO).getMark();
        verify(lessonDTO).getCreatedBy();
        verify(lessonDTO).getModifiedBy();
        verify(lessonDTO).getCreatedDate();
        verify(lessonDTO).getModifiedDate();
        verify(lessonDTO).setCreatedBy((String) any());
        verify(lessonDTO).setCreatedDate((LocalDateTime) any());
        verify(lessonDTO).setModifiedBy((String) any());
        verify(lessonDTO).setModifiedDate((LocalDateTime) any());
        verify(lessonDTO).setCourseId((Long) any());
        verify(lessonDTO).setId((Long) any());
        verify(lessonDTO).setLessonNumber((Short) any());
        verify(lessonDTO).setMark((Short) any());
        verify(lessonDTO).setStudentAttachmentIds((Set<Long>) any());
        verify(lessonDTO).setStudentId((Long) any());
    }

    /**
     * Method under test: {@link LessonResource#updateLesson(Long, LessonDTO)}
     */
    @Test
    void testUpdateLesson() throws URISyntaxException {
        LessonRepository lessonRepository = mock(LessonRepository.class);
        LessonMapperImpl lessonMapper = new LessonMapperImpl();
        StudentRepository studentRepository = mock(StudentRepository.class);
        StudentMapperImpl studentMapper = new StudentMapperImpl();
        UserRepository userRepository = mock(UserRepository.class);
        UserMapperImpl userMapper = new UserMapperImpl();
        UserServiceImpl userService = new UserServiceImpl(userRepository, userMapper, new Argon2PasswordEncoder());

        StudentServiceImpl studentService = new StudentServiceImpl(studentRepository, studentMapper, userService,
                new UserMapperImpl());

        CourseRepository courseRepository = mock(CourseRepository.class);
        CourseMapperImpl courseMapper = new CourseMapperImpl();
        StudentRepository studentRepository1 = mock(StudentRepository.class);
        InstructorRepository instructorRepository = mock(InstructorRepository.class);
        SystemProperties systemProperties = mock(SystemProperties.class);
        LessonResource lessonResource = new LessonResource(
                new LessonServiceImpl(lessonRepository, lessonMapper, studentService, new CourseServiceImpl(courseRepository,
                        courseMapper, studentRepository1, instructorRepository), systemProperties),
                mock(LessonRepository.class));

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
        assertThrows(SystemException.class, () -> lessonResource.updateLesson(1L, lessonDTO));
    }

    /**
     * Method under test: {@link LessonResource#deleteLesson(Long)}
     */
    @Test
    void testDeleteLesson() throws Exception {
        doNothing().when(this.lessonService).delete((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/lessons/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.lessonResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link LessonResource#deleteLesson(Long)}
     */
    @Test
    void testDeleteLesson2() throws Exception {
        doNothing().when(this.lessonService).delete((Long) any());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.lessonResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link LessonResource#deleteLesson(Long)}
     */
    @Test
    void testDeleteLesson3() throws Exception {
        doNothing().when(this.lessonService).delete((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/lessons/{id}", 123L);
        deleteResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.lessonResource)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link LessonResource#getAllLessons()}
     */
    @Test
    void testGetAllLessons() throws Exception {
        when(this.lessonService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/lessons");
        MockMvcBuilders.standaloneSetup(this.lessonResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link LessonResource#getAllLessons()}
     */
    @Test
    void testGetAllLessons2() throws Exception {
        when(this.lessonService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/lessons");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.lessonResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link LessonResource#getAllLessonsForStudent(Long)}
     */
    @Test
    void testGetAllLessonsForStudent() throws Exception {
        when(this.lessonService.getAllLessonsByStudentId((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/lessons/student/{studentId}", 123L);
        MockMvcBuilders.standaloneSetup(this.lessonResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link LessonResource#getAllLessonsForStudent(Long)}
     */
    @Test
    void testGetAllLessonsForStudent2() throws Exception {
        when(this.lessonService.getAllLessonsByStudentId((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/lessons/student/{studentId}", 123L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.lessonResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link LessonResource#getFinalMarkForLesson(StudentCourseDTO)}
     */
    @Test
    void testGetFinalMarkForLesson() throws Exception {
        CoursePassDTO coursePassDTO = new CoursePassDTO(10.0d, true, "You passed");
        when(this.lessonService.calculateFinalMark((StudentCourseDTO) any())).thenReturn(coursePassDTO);

        StudentCourseDTO studentCourseDTO = new StudentCourseDTO();
        studentCourseDTO.setCourseId(123L);
        studentCourseDTO.setStudentId(123L);
        String content = (new ObjectMapper()).writeValueAsString(studentCourseDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/lessons/final-mark")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.lessonResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.finalMark").value(coursePassDTO.getFinalMark()))
                .andExpect(jsonPath("$.message").value(coursePassDTO.getMessage()))
                .andExpect(jsonPath("$.coursePassed").value(coursePassDTO.isCoursePassed()));
    }

    /**
     * Method under test: {@link LessonResource#getLesson(Long)}
     */
    @Test
    void testGetLesson() throws Exception {
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
        Optional<LessonDTO> ofResult = Optional.of(lessonDTO);
        when(this.lessonService.findOne((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/lessons/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.lessonResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"modifiedDate\":[1,1,1,1,1],\"modifiedBy\":\"Jan 1, 2020 9:00am GMT+0100\",\"createdDate\":[1,1,1,1,1],"
                                        + "\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"id\":123,\"lessonNumber\":1,\"mark\":1,\"studentId\":123,\"courseId"
                                        + "\":123,\"studentAttachmentIds\":[]}"));
    }

    /**
     * Method under test: {@link LessonResource#getLesson(Long)}
     */
    @Test
    void testGetLesson2() throws Exception {
        when(this.lessonService.findOne((Long) any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/lessons/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.lessonResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link LessonResource#getLesson(Long)}
     */
    @Test
    void testGetLesson3() throws Exception {
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
        Optional<LessonDTO> ofResult = Optional.of(lessonDTO);
        when(this.lessonService.findOne((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/lessons/{id}", 123L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.lessonResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"modifiedDate\":[1,1,1,1,1],\"modifiedBy\":\"Jan 1, 2020 9:00am GMT+0100\",\"createdDate\":[1,1,1,1,1],"
                                        + "\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"id\":123,\"lessonNumber\":1,\"mark\":1,\"studentId\":123,\"courseId"
                                        + "\":123,\"studentAttachmentIds\":[]}"));
    }
}

