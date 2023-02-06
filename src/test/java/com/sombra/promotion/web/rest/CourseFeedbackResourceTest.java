package com.sombra.promotion.web.rest;

import com.sombra.promotion.config.error.SystemException;
import com.sombra.promotion.domain.Course;
import com.sombra.promotion.domain.Instructor;
import com.sombra.promotion.dto.CourseFeedbackDTO;
import com.sombra.promotion.repository.*;
import com.sombra.promotion.service.CourseFeedbackService;
import com.sombra.promotion.service.impl.CourseFeedbackServiceImpl;
import com.sombra.promotion.service.impl.CourseServiceImpl;
import com.sombra.promotion.service.impl.StudentServiceImpl;
import com.sombra.promotion.service.impl.UserServiceImpl;
import com.sombra.promotion.service.mapper.CourseFeedbackMapperImpl;
import com.sombra.promotion.service.mapper.CourseMapperImpl;
import com.sombra.promotion.service.mapper.StudentMapperImpl;
import com.sombra.promotion.service.mapper.UserMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CourseFeedbackResource.class})
@ExtendWith(SpringExtension.class)
class CourseFeedbackResourceTest {
    @MockBean
    private CourseFeedbackRepository courseFeedbackRepository;

    @Autowired
    private CourseFeedbackResource courseFeedbackResource;

    @MockBean
    private CourseFeedbackService courseFeedbackService;

    /**
     * Method under test: {@link CourseFeedbackResource#createCourseFeedback(CourseFeedbackDTO)}
     */
    @Test
    void testCreateCourseFeedback() throws SystemException, URISyntaxException {


        CourseFeedbackRepository courseFeedbackRepository = mock(CourseFeedbackRepository.class);
        CourseFeedbackMapperImpl courseFeedbackMapper = new CourseFeedbackMapperImpl();
        CourseRepository courseRepository = mock(CourseRepository.class);
        CourseMapperImpl courseMapper = new CourseMapperImpl();
        StudentRepository studentRepository = mock(StudentRepository.class);
        InstructorRepository instructorRepository = mock(InstructorRepository.class);
        CourseServiceImpl courseService = new CourseServiceImpl(courseRepository, courseMapper, studentRepository,
                instructorRepository);

        StudentRepository studentRepository1 = mock(StudentRepository.class);
        StudentMapperImpl studentMapper = new StudentMapperImpl();
        UserRepository userRepository = mock(UserRepository.class);
        UserMapperImpl userMapper = new UserMapperImpl();
        UserServiceImpl userService = new UserServiceImpl(userRepository, userMapper, new Argon2PasswordEncoder());

        CourseFeedbackResource courseFeedbackResource = new CourseFeedbackResource(
                new CourseFeedbackServiceImpl(courseFeedbackRepository, courseFeedbackMapper, courseService,
                        new StudentServiceImpl(studentRepository1, studentMapper, userService, new UserMapperImpl())),
                mock(CourseFeedbackRepository.class));

        CourseFeedbackDTO courseFeedbackDTO = new CourseFeedbackDTO();
        courseFeedbackDTO.setCourseId(123L);
        courseFeedbackDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseFeedbackDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO.setFeedback("Feedback");
        courseFeedbackDTO.setId(123L);
        courseFeedbackDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseFeedbackDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO.setStudentId(123L);
        assertThrows(SystemException.class, () -> courseFeedbackResource.createCourseFeedback(courseFeedbackDTO));
    }

    /**
     * Method under test: {@link CourseFeedbackResource#createCourseFeedback(CourseFeedbackDTO)}
     */
    @Test
    void testCreateCourseFeedback2() throws SystemException, URISyntaxException {


        CourseFeedbackRepository courseFeedbackRepository = mock(CourseFeedbackRepository.class);
        CourseFeedbackMapperImpl courseFeedbackMapper = new CourseFeedbackMapperImpl();
        CourseRepository courseRepository = mock(CourseRepository.class);
        CourseMapperImpl courseMapper = new CourseMapperImpl();
        StudentRepository studentRepository = mock(StudentRepository.class);
        InstructorRepository instructorRepository = mock(InstructorRepository.class);
        CourseServiceImpl courseService = new CourseServiceImpl(courseRepository, courseMapper, studentRepository,
                instructorRepository);

        StudentRepository studentRepository1 = mock(StudentRepository.class);
        StudentMapperImpl studentMapper = new StudentMapperImpl();
        UserRepository userRepository = mock(UserRepository.class);
        UserMapperImpl userMapper = new UserMapperImpl();
        UserServiceImpl userService = new UserServiceImpl(userRepository, userMapper, new Argon2PasswordEncoder());

        CourseFeedbackResource courseFeedbackResource = new CourseFeedbackResource(
                new CourseFeedbackServiceImpl(courseFeedbackRepository, courseFeedbackMapper, courseService,
                        new StudentServiceImpl(studentRepository1, studentMapper, userService, new UserMapperImpl())),
                mock(CourseFeedbackRepository.class));
        CourseFeedbackDTO courseFeedbackDTO = mock(CourseFeedbackDTO.class);
        when(courseFeedbackDTO.getId()).thenReturn(123L);
        doNothing().when(courseFeedbackDTO).setCreatedBy((String) any());
        doNothing().when(courseFeedbackDTO).setCreatedDate((LocalDateTime) any());
        doNothing().when(courseFeedbackDTO).setModifiedBy((String) any());
        doNothing().when(courseFeedbackDTO).setModifiedDate((LocalDateTime) any());
        doNothing().when(courseFeedbackDTO).setCourseId((Long) any());
        doNothing().when(courseFeedbackDTO).setFeedback((String) any());
        doNothing().when(courseFeedbackDTO).setId((Long) any());
        doNothing().when(courseFeedbackDTO).setStudentId((Long) any());
        courseFeedbackDTO.setCourseId(123L);
        courseFeedbackDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseFeedbackDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO.setFeedback("Feedback");
        courseFeedbackDTO.setId(123L);
        courseFeedbackDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseFeedbackDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO.setStudentId(123L);
        assertThrows(SystemException.class, () -> courseFeedbackResource.createCourseFeedback(courseFeedbackDTO));
        verify(courseFeedbackDTO).getId();
        verify(courseFeedbackDTO).setCreatedBy((String) any());
        verify(courseFeedbackDTO).setCreatedDate((LocalDateTime) any());
        verify(courseFeedbackDTO).setModifiedBy((String) any());
        verify(courseFeedbackDTO).setModifiedDate((LocalDateTime) any());
        verify(courseFeedbackDTO).setCourseId((Long) any());
        verify(courseFeedbackDTO).setFeedback((String) any());
        verify(courseFeedbackDTO).setId((Long) any());
        verify(courseFeedbackDTO).setStudentId((Long) any());
    }

    /**
     * Method under test: {@link CourseFeedbackResource#createCourseFeedback(CourseFeedbackDTO)}
     */
    @Test
    void testCreateCourseFeedback4() throws SystemException, URISyntaxException {


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
        CourseRepository courseRepository = mock(CourseRepository.class);
        when(courseRepository.findById((Long) any())).thenReturn(Optional.of(course));
        CourseMapperImpl courseMapper = new CourseMapperImpl();
        StudentRepository studentRepository = mock(StudentRepository.class);
        InstructorRepository instructorRepository = mock(InstructorRepository.class);
        new CourseServiceImpl(courseRepository, courseMapper, studentRepository, instructorRepository);

        CourseFeedbackDTO courseFeedbackDTO = new CourseFeedbackDTO();
        courseFeedbackDTO.setCourseId(123L);
        courseFeedbackDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseFeedbackDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO.setFeedback("Feedback");
        courseFeedbackDTO.setId(123L);
        courseFeedbackDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseFeedbackDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO.setStudentId(123L);
        CourseFeedbackService courseFeedbackService = mock(CourseFeedbackService.class);
        when(courseFeedbackService.save((CourseFeedbackDTO) any())).thenReturn(courseFeedbackDTO);
        CourseFeedbackResource courseFeedbackResource = new CourseFeedbackResource(courseFeedbackService,
                mock(CourseFeedbackRepository.class));
        CourseFeedbackDTO courseFeedbackDTO1 = mock(CourseFeedbackDTO.class);
        when(courseFeedbackDTO1.getCourseId()).thenReturn(123L);
        when(courseFeedbackDTO1.getId()).thenReturn(null);
        doNothing().when(courseFeedbackDTO1).setCreatedBy((String) any());
        doNothing().when(courseFeedbackDTO1).setCreatedDate((LocalDateTime) any());
        doNothing().when(courseFeedbackDTO1).setModifiedBy((String) any());
        doNothing().when(courseFeedbackDTO1).setModifiedDate((LocalDateTime) any());
        doNothing().when(courseFeedbackDTO1).setCourseId((Long) any());
        doNothing().when(courseFeedbackDTO1).setFeedback((String) any());
        doNothing().when(courseFeedbackDTO1).setId((Long) any());
        doNothing().when(courseFeedbackDTO1).setStudentId((Long) any());
        courseFeedbackDTO1.setCourseId(123L);
        courseFeedbackDTO1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseFeedbackDTO1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO1.setFeedback("Feedback");
        courseFeedbackDTO1.setId(123L);
        courseFeedbackDTO1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseFeedbackDTO1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO1.setStudentId(123L);
        ResponseEntity<CourseFeedbackDTO> actualCreateCourseFeedbackResult = courseFeedbackResource
                .createCourseFeedback(courseFeedbackDTO1);
        assertTrue(actualCreateCourseFeedbackResult.hasBody());
        assertEquals(3, actualCreateCourseFeedbackResult.getHeaders().size());
        assertEquals(HttpStatus.CREATED, actualCreateCourseFeedbackResult.getStatusCode());
        verify(courseFeedbackService).save((CourseFeedbackDTO) any());
        verify(courseFeedbackDTO1).getId();
        verify(courseFeedbackDTO1).setCreatedBy((String) any());
        verify(courseFeedbackDTO1).setCreatedDate((LocalDateTime) any());
        verify(courseFeedbackDTO1).setModifiedBy((String) any());
        verify(courseFeedbackDTO1).setModifiedDate((LocalDateTime) any());
        verify(courseFeedbackDTO1).setCourseId((Long) any());
        verify(courseFeedbackDTO1).setFeedback((String) any());
        verify(courseFeedbackDTO1).setId((Long) any());
        verify(courseFeedbackDTO1).setStudentId((Long) any());
    }

    /**
     * Method under test: {@link CourseFeedbackResource#createCourseFeedback(CourseFeedbackDTO)}
     */
    @Test
    void testCreateCourseFeedback5() throws SystemException, URISyntaxException {


        HashSet<Instructor> instructors = new HashSet<>();

        Course course = new Course(123L, "REST request to save CourseFeedback : {}", (short) 4, instructors,
                new HashSet<>());
        course.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        course.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course.setId(123L);
        course.setInstructors(new HashSet<>());
        course.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        course.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course.setName("Name");
        course.setNumberOfLessons((short) 1);
        course.setStudents(new HashSet<>());
        CourseRepository courseRepository = mock(CourseRepository.class);
        when(courseRepository.findById((Long) any())).thenReturn(Optional.of(course));
        CourseMapperImpl courseMapper = new CourseMapperImpl();
        StudentRepository studentRepository = mock(StudentRepository.class);
        InstructorRepository instructorRepository = mock(InstructorRepository.class);
        new CourseServiceImpl(courseRepository, courseMapper, studentRepository, instructorRepository);

        CourseFeedbackDTO courseFeedbackDTO = new CourseFeedbackDTO();
        courseFeedbackDTO.setCourseId(123L);
        courseFeedbackDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseFeedbackDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO.setFeedback("Feedback");
        courseFeedbackDTO.setId(123L);
        courseFeedbackDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseFeedbackDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO.setStudentId(123L);
        CourseFeedbackService courseFeedbackService = mock(CourseFeedbackService.class);
        when(courseFeedbackService.save((CourseFeedbackDTO) any())).thenReturn(courseFeedbackDTO);
        CourseFeedbackResource courseFeedbackResource = new CourseFeedbackResource(courseFeedbackService,
                mock(CourseFeedbackRepository.class));
        CourseFeedbackDTO courseFeedbackDTO1 = mock(CourseFeedbackDTO.class);
        when(courseFeedbackDTO1.getCourseId()).thenReturn(123L);
        when(courseFeedbackDTO1.getId()).thenReturn(null);
        doNothing().when(courseFeedbackDTO1).setCreatedBy((String) any());
        doNothing().when(courseFeedbackDTO1).setCreatedDate((LocalDateTime) any());
        doNothing().when(courseFeedbackDTO1).setModifiedBy((String) any());
        doNothing().when(courseFeedbackDTO1).setModifiedDate((LocalDateTime) any());
        doNothing().when(courseFeedbackDTO1).setCourseId((Long) any());
        doNothing().when(courseFeedbackDTO1).setFeedback((String) any());
        doNothing().when(courseFeedbackDTO1).setId((Long) any());
        doNothing().when(courseFeedbackDTO1).setStudentId((Long) any());
        courseFeedbackDTO1.setCourseId(123L);
        courseFeedbackDTO1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseFeedbackDTO1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO1.setFeedback("Feedback");
        courseFeedbackDTO1.setId(123L);
        courseFeedbackDTO1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseFeedbackDTO1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO1.setStudentId(123L);
        ResponseEntity<CourseFeedbackDTO> actualCreateCourseFeedbackResult = courseFeedbackResource
                .createCourseFeedback(courseFeedbackDTO1);
        assertTrue(actualCreateCourseFeedbackResult.hasBody());
        assertEquals(3, actualCreateCourseFeedbackResult.getHeaders().size());
        assertEquals(HttpStatus.CREATED, actualCreateCourseFeedbackResult.getStatusCode());
        verify(courseFeedbackService).save((CourseFeedbackDTO) any());
        verify(courseFeedbackDTO1).getId();
        verify(courseFeedbackDTO1).setCreatedBy((String) any());
        verify(courseFeedbackDTO1).setCreatedDate((LocalDateTime) any());
        verify(courseFeedbackDTO1).setModifiedBy((String) any());
        verify(courseFeedbackDTO1).setModifiedDate((LocalDateTime) any());
        verify(courseFeedbackDTO1).setCourseId((Long) any());
        verify(courseFeedbackDTO1).setFeedback((String) any());
        verify(courseFeedbackDTO1).setId((Long) any());
        verify(courseFeedbackDTO1).setStudentId((Long) any());
    }

    /**
     * Method under test: {@link CourseFeedbackResource#updateCourseFeedback(Long, CourseFeedbackDTO)}
     */
    @Test
    void testUpdateCourseFeedback() {


        CourseFeedbackRepository courseFeedbackRepository = mock(CourseFeedbackRepository.class);
        CourseFeedbackMapperImpl courseFeedbackMapper = new CourseFeedbackMapperImpl();
        CourseRepository courseRepository = mock(CourseRepository.class);
        CourseMapperImpl courseMapper = new CourseMapperImpl();
        StudentRepository studentRepository = mock(StudentRepository.class);
        InstructorRepository instructorRepository = mock(InstructorRepository.class);
        CourseServiceImpl courseService = new CourseServiceImpl(courseRepository, courseMapper, studentRepository,
                instructorRepository);

        StudentRepository studentRepository1 = mock(StudentRepository.class);
        StudentMapperImpl studentMapper = new StudentMapperImpl();
        UserRepository userRepository = mock(UserRepository.class);
        UserMapperImpl userMapper = new UserMapperImpl();
        UserServiceImpl userService = new UserServiceImpl(userRepository, userMapper, new Argon2PasswordEncoder());

        CourseFeedbackResource courseFeedbackResource = new CourseFeedbackResource(
                new CourseFeedbackServiceImpl(courseFeedbackRepository, courseFeedbackMapper, courseService,
                        new StudentServiceImpl(studentRepository1, studentMapper, userService, new UserMapperImpl())),
                mock(CourseFeedbackRepository.class));

        CourseFeedbackDTO courseFeedbackDTO = new CourseFeedbackDTO();
        courseFeedbackDTO.setCourseId(123L);
        courseFeedbackDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseFeedbackDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO.setFeedback("Feedback");
        courseFeedbackDTO.setId(123L);
        courseFeedbackDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseFeedbackDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO.setStudentId(123L);
        assertThrows(SystemException.class, () -> courseFeedbackResource.updateCourseFeedback(1L, courseFeedbackDTO));
    }

    /**
     * Method under test: {@link CourseFeedbackResource#updateCourseFeedback(Long, CourseFeedbackDTO)}
     */
    @Test
    void testUpdateCourseFeedback5() {


        CourseFeedbackDTO courseFeedbackDTO = new CourseFeedbackDTO();
        courseFeedbackDTO.setCourseId(123L);
        courseFeedbackDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseFeedbackDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO.setFeedback("Feedback");
        courseFeedbackDTO.setId(123L);
        courseFeedbackDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseFeedbackDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO.setStudentId(123L);
        CourseFeedbackServiceImpl courseFeedbackServiceImpl = mock(CourseFeedbackServiceImpl.class);
        when(courseFeedbackServiceImpl.save((CourseFeedbackDTO) any())).thenReturn(courseFeedbackDTO);
        CourseFeedbackRepository courseFeedbackRepository = mock(CourseFeedbackRepository.class);
        when(courseFeedbackRepository.existsById((Long) any())).thenReturn(true);
        CourseFeedbackResource courseFeedbackResource = new CourseFeedbackResource(courseFeedbackServiceImpl,
                courseFeedbackRepository);

        CourseFeedbackDTO courseFeedbackDTO1 = new CourseFeedbackDTO();
        courseFeedbackDTO1.setCourseId(123L);
        courseFeedbackDTO1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseFeedbackDTO1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO1.setFeedback("Feedback");
        courseFeedbackDTO1.setId(1L);
        courseFeedbackDTO1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseFeedbackDTO1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO1.setStudentId(123L);
        ResponseEntity<CourseFeedbackDTO> actualUpdateCourseFeedbackResult = courseFeedbackResource.updateCourseFeedback(1L,
                courseFeedbackDTO1);
        assertTrue(actualUpdateCourseFeedbackResult.hasBody());
        assertEquals(2, actualUpdateCourseFeedbackResult.getHeaders().size());
        assertEquals(HttpStatus.OK, actualUpdateCourseFeedbackResult.getStatusCode());
        verify(courseFeedbackServiceImpl).save((CourseFeedbackDTO) any());
        verify(courseFeedbackRepository).existsById((Long) any());
    }

    /**
     * Method under test: {@link CourseFeedbackResource#deleteCourseFeedback(Long)}
     */
    @Test
    void testDeleteCourseFeedback() throws Exception {
        doNothing().when(this.courseFeedbackService).delete((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/course-feedback/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseFeedbackResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link CourseFeedbackResource#deleteCourseFeedback(Long)}
     */
    @Test
    void testDeleteCourseFeedback2() throws Exception {
        doNothing().when(this.courseFeedbackService).delete((Long) any());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseFeedbackResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CourseFeedbackResource#deleteCourseFeedback(Long)}
     */
    @Test
    void testDeleteCourseFeedback3() throws Exception {
        doNothing().when(this.courseFeedbackService).delete((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/course-feedback/{id}", 123L);
        deleteResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseFeedbackResource)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link CourseFeedbackResource#getAllCourseFeedbacks()}
     */
    @Test
    void testGetAllCourseFeedbacks() throws Exception {
        when(this.courseFeedbackService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/course-feedbacks");
        MockMvcBuilders.standaloneSetup(this.courseFeedbackResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CourseFeedbackResource#getAllCourseFeedbacks()}
     */
    @Test
    void testGetAllCourseFeedbacks2() throws Exception {
        when(this.courseFeedbackService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/course-feedbacks");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.courseFeedbackResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CourseFeedbackResource#getCourseFeedback(Long)}
     */
    @Test
    void testGetCourseFeedback() throws Exception {
        CourseFeedbackDTO courseFeedbackDTO = new CourseFeedbackDTO();
        courseFeedbackDTO.setCourseId(123L);
        courseFeedbackDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseFeedbackDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO.setFeedback("Feedback");
        courseFeedbackDTO.setId(123L);
        courseFeedbackDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseFeedbackDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO.setStudentId(123L);
        Optional<CourseFeedbackDTO> ofResult = Optional.of(courseFeedbackDTO);
        when(this.courseFeedbackService.findOne((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/course-feedback/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.courseFeedbackResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"modifiedDate\":[1,1,1,1,1],\"modifiedBy\":\"Jan 1, 2020 9:00am GMT+0100\",\"createdDate\":[1,1,1,1,1"
                                        + "],\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"id\":123,\"feedback\":\"Feedback\",\"courseId\":123,\"studentId"
                                        + "\":123}"));
    }

    /**
     * Method under test: {@link CourseFeedbackResource#getCourseFeedback(Long)}
     */
    @Test
    void testGetCourseFeedback2() throws Exception {
        when(this.courseFeedbackService.findOne((Long) any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/course-feedback/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseFeedbackResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CourseFeedbackResource#getCourseFeedback(Long)}
     */
    @Test
    void testGetCourseFeedback3() throws Exception {
        CourseFeedbackDTO courseFeedbackDTO = new CourseFeedbackDTO();
        courseFeedbackDTO.setCourseId(123L);
        courseFeedbackDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseFeedbackDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO.setFeedback("Feedback");
        courseFeedbackDTO.setId(123L);
        courseFeedbackDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseFeedbackDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseFeedbackDTO.setStudentId(123L);
        Optional<CourseFeedbackDTO> ofResult = Optional.of(courseFeedbackDTO);
        when(this.courseFeedbackService.findOne((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/course-feedback/{id}", 123L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.courseFeedbackResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"modifiedDate\":[1,1,1,1,1],\"modifiedBy\":\"Jan 1, 2020 9:00am GMT+0100\",\"createdDate\":[1,1,1,1,1"
                                        + "],\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"id\":123,\"feedback\":\"Feedback\",\"courseId\":123,\"studentId"
                                        + "\":123}"));
    }
}

