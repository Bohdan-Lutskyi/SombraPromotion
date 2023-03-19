package com.sombra.promotion.unit.rest;

import com.sombra.promotion.config.error.SystemException;
import com.sombra.promotion.domain.Course;
import com.sombra.promotion.domain.Instructor;
import com.sombra.promotion.domain.Student;
import com.sombra.promotion.domain.User;
import com.sombra.promotion.dto.CourseDTO;
import com.sombra.promotion.repository.CourseRepository;
import com.sombra.promotion.repository.InstructorRepository;
import com.sombra.promotion.repository.StudentRepository;
import com.sombra.promotion.service.CourseService;
import com.sombra.promotion.service.impl.CourseServiceImpl;
import com.sombra.promotion.service.mapper.CourseMapperImpl;
import com.sombra.promotion.service.mapper.StudentMapperImpl;
import com.sombra.promotion.web.rest.CourseResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CourseResource.class})
@ExtendWith(SpringExtension.class)
class CourseResourceTest {
    @MockBean
    private CourseRepository courseRepository;

    @Autowired
    private CourseResource courseResource;

    @MockBean
    private CourseService courseService;

    /**
     * Method under test: {@link CourseResource#addInstructorToCourse(Long, java.util.Set)}
     */
    @Test
    void testAddInstructorToCourse() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/course/{courseId}/add-instructor",
                "Uri Vars", "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CourseResource#createCourse(CourseDTO)}
     */
    @Test
    void testCreateCourse() throws SystemException, URISyntaxException {
        CourseRepository courseRepository = mock(CourseRepository.class);
        CourseMapperImpl courseMapper = new CourseMapperImpl();
        StudentRepository studentRepository = mock(StudentRepository.class);
        InstructorRepository instructorRepository = mock(InstructorRepository.class);
        CourseResource courseResource = new CourseResource(new CourseServiceImpl(courseRepository, courseMapper,
                studentRepository, instructorRepository), mock(CourseRepository.class));

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
        assertThrows(SystemException.class, () -> courseResource.createCourse(courseDTO));
    }

    /**
     * Method under test: {@link CourseResource#createCourse(CourseDTO)}
     */
    @Test
    void testCreateCourse2() throws SystemException, URISyntaxException {
        Course course = mock(Course.class);
        when(course.getStudents()).thenReturn(new HashSet<>());
        doNothing().when(course).setCreatedBy((String) any());
        doNothing().when(course).setCreatedDate((LocalDateTime) any());
        doNothing().when(course).setModifiedBy((String) any());
        doNothing().when(course).setModifiedDate((LocalDateTime) any());
        doNothing().when(course).setId((Long) any());
        doNothing().when(course).setInstructors((Set<Instructor>) any());
        doNothing().when(course).setName((String) any());
        doNothing().when(course).setNumberOfLessons((Short) any());
        doNothing().when(course).setStudents((Set<Student>) any());
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
        CourseRepository courseRepository = mock(CourseRepository.class);
        when(courseRepository.save((Course) any())).thenReturn(course1);
        when(courseRepository.findById((Long) any())).thenReturn(ofResult);

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
        user.setSecondName("Second Name");
        user.setUserRoles("User Roles");

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
        Course course2 = mock(Course.class);
        when(course2.getStudents()).thenReturn(new HashSet<>());
        when(course2.getInstructors()).thenReturn(instructorSet);
        doNothing().when(course2).setCreatedBy((String) any());
        doNothing().when(course2).setCreatedDate((LocalDateTime) any());
        doNothing().when(course2).setModifiedBy((String) any());
        doNothing().when(course2).setModifiedDate((LocalDateTime) any());
        doNothing().when(course2).setId((Long) any());
        doNothing().when(course2).setInstructors((Set<Instructor>) any());
        doNothing().when(course2).setName((String) any());
        doNothing().when(course2).setNumberOfLessons((Short) any());
        doNothing().when(course2).setStudents((Set<Student>) any());
        course2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        course2.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course2.setId(123L);
        course2.setInstructors(new HashSet<>());
        course2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        course2.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course2.setName("Name");
        course2.setNumberOfLessons((short) 1);
        course2.setStudents(new HashSet<>());
        CourseMapperImpl courseMapperImpl = mock(CourseMapperImpl.class);
        when(courseMapperImpl.toEntity((CourseDTO) any())).thenReturn(course2);
        when(courseMapperImpl.toDto((Course) any())).thenReturn(courseDTO);
        InstructorRepository instructorRepository = mock(InstructorRepository.class);
        when(instructorRepository.existsById((Long) any())).thenReturn(true);
        StudentRepository studentRepository = mock(StudentRepository.class);

        CourseResource courseResource = new CourseResource(new CourseServiceImpl(courseRepository, courseMapperImpl,
                studentRepository, instructorRepository), mock(CourseRepository.class));
        courseResource.addStudentToCourse(123L, new HashSet<>());

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
        assertThrows(SystemException.class, () -> courseResource.createCourse(courseDTO1));
        verify(courseRepository).save((Course) any());
        verify(courseRepository).findById((Long) any());
        verify(course).setCreatedBy((String) any());
        verify(course).setCreatedDate((LocalDateTime) any());
        verify(course).setModifiedBy((String) any());
        verify(course).setModifiedDate((LocalDateTime) any());
        verify(course).setId((Long) any());
        verify(course).setInstructors((Set<Instructor>) any());
        verify(course).setName((String) any());
        verify(course).setNumberOfLessons((Short) any());
        verify(course).setStudents((Set<Student>) any());
        verify(courseMapperImpl).toEntity((CourseDTO) any());
        verify(courseMapperImpl, atLeast(1)).toDto((Course) any());
        verify(course2).getInstructors();
        verify(course2).getStudents();
        verify(course2).setCreatedBy((String) any());
        verify(course2).setCreatedDate((LocalDateTime) any());
        verify(course2).setModifiedBy((String) any());
        verify(course2).setModifiedDate((LocalDateTime) any());
        verify(course2).setId((Long) any());
        verify(course2).setInstructors((Set<Instructor>) any());
        verify(course2).setName((String) any());
        verify(course2).setNumberOfLessons((Short) any());
        verify(course2).setStudents((Set<Student>) any());
        verify(instructorRepository).existsById((Long) any());
    }

    /**
     * Method under test: {@link CourseResource#updateCourse(Long, CourseDTO)}
     */
    @Test
    void testUpdateCourse() {
        CourseRepository courseRepository = mock(CourseRepository.class);
        CourseMapperImpl courseMapper = new CourseMapperImpl();
        StudentRepository studentRepository = mock(StudentRepository.class);
        InstructorRepository instructorRepository = mock(InstructorRepository.class);
        CourseResource courseResource = new CourseResource(new CourseServiceImpl(courseRepository, courseMapper,
                studentRepository, instructorRepository), mock(CourseRepository.class));

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
        assertThrows(SystemException.class, () -> courseResource.updateCourse(1L, courseDTO));
    }

    /**
     * Method under test: {@link CourseResource#updateCourse(Long, CourseDTO)}
     */
    @Test
    void testUpdateCourse5() {
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
        CourseService courseService = mock(CourseService.class);
        when(courseService.save((CourseDTO) any())).thenReturn(courseDTO);
        CourseRepository courseRepository = mock(CourseRepository.class);
        when(courseRepository.existsById((Long) any())).thenReturn(true);
        CourseResource courseResource = new CourseResource(courseService, courseRepository);

        CourseDTO courseDTO1 = new CourseDTO();
        courseDTO1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseDTO1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDTO1.setId(1L);
        courseDTO1.setInstructorIds(new HashSet<>());
        courseDTO1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseDTO1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDTO1.setName("Name");
        courseDTO1.setNumberOfLessons((short) 1);
        courseDTO1.setStudentIds(new HashSet<>());
        ResponseEntity<CourseDTO> actualUpdateCourseResult = courseResource.updateCourse(1L, courseDTO1);
        assertTrue(actualUpdateCourseResult.hasBody());
        assertEquals(2, actualUpdateCourseResult.getHeaders().size());
        assertEquals(HttpStatus.OK, actualUpdateCourseResult.getStatusCode());
        verify(courseService).save((CourseDTO) any());
        verify(courseRepository).existsById((Long) any());
    }

    /**
     * Method under test: {@link CourseResource#updateCourse(Long, CourseDTO)}
     */
    @Test
    void testUpdateCourse6() {
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
        CourseService courseService = mock(CourseService.class);
        when(courseService.save((CourseDTO) any())).thenReturn(courseDTO);
        CourseRepository courseRepository = mock(CourseRepository.class);
        when(courseRepository.existsById((Long) any())).thenReturn(false);
        CourseResource courseResource = new CourseResource(courseService, courseRepository);

        CourseDTO courseDTO1 = new CourseDTO();
        courseDTO1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseDTO1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDTO1.setId(1L);
        courseDTO1.setInstructorIds(new HashSet<>());
        courseDTO1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseDTO1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDTO1.setName("Name");
        courseDTO1.setNumberOfLessons((short) 1);
        courseDTO1.setStudentIds(new HashSet<>());
        assertThrows(SystemException.class, () -> courseResource.updateCourse(1L, courseDTO1));
        verify(courseRepository).existsById((Long) any());
    }

    /**
     * Method under test: {@link CourseResource#updateCourse(Long, CourseDTO)}
     */
    @Test
    void testUpdateCourse7() {
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
        CourseService courseService = mock(CourseService.class);
        when(courseService.save((CourseDTO) any())).thenReturn(courseDTO);
        CourseRepository courseRepository = mock(CourseRepository.class);
        when(courseRepository.existsById((Long) any())).thenReturn(true);
        CourseResource courseResource = new CourseResource(courseService, courseRepository);

        CourseDTO courseDTO1 = new CourseDTO();
        courseDTO1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseDTO1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDTO1.setId(null);
        courseDTO1.setInstructorIds(new HashSet<>());
        courseDTO1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseDTO1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDTO1.setName("Name");
        courseDTO1.setNumberOfLessons((short) 1);
        courseDTO1.setStudentIds(new HashSet<>());
        assertThrows(SystemException.class, () -> courseResource.updateCourse(1L, courseDTO1));
    }

    /**
     * Method under test: {@link CourseResource#addInstructorToCourse(Long, java.util.Set)}
     */
    @Test
    void testAddInstructorToCourse2() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CourseResource#addStudentToCourse(Long, java.util.Set)}
     */
    @Test
    void testAddStudentToCourse() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/course/{courseId}/add-student",
                "Uri Vars", "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CourseResource#deleteCourse(Long)}
     */
    @Test
    void testDeleteCourse() throws Exception {
        doNothing().when(this.courseService).delete((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/courses/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link CourseResource#deleteCourse(Long)}
     */
    @Test
    void testDeleteCourse2() throws Exception {
        doNothing().when(this.courseService).delete((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/courses/{id}", 123L);
        deleteResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseResource)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link CourseResource#getAllCourses()}
     */
    @Test
    void testGetAllCourses() throws Exception {
        when(this.courseService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/courses");
        MockMvcBuilders.standaloneSetup(this.courseResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CourseResource#getAllCourses()}
     */
    @Test
    void testGetAllCourses2() throws Exception {
        when(this.courseService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/courses");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.courseResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CourseResource#getAllStudentsPerCourse(java.util.Collection)}
     */
    @Test
    void testGetAllStudentsPerCourse() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/courses/students");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CourseResource#getCourse(Long)}
     */
    @Test
    void testGetCourse() throws Exception {
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
        Optional<CourseDTO> ofResult = Optional.of(courseDTO);
        when(this.courseService.findOne((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/courses/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.courseResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"modifiedDate\":[1,1,1,1,1],\"modifiedBy\":\"Jan 1, 2020 9:00am GMT+0100\",\"createdDate\":[1,1,1,1,1],"
                                        + "\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"id\":123,\"name\":\"Name\",\"numberOfLessons\":1,\"instructorIds\""
                                        + ":[],\"studentIds\":[]}"));
    }

    /**
     * Method under test: {@link CourseResource#getCourse(Long)}
     */
    @Test
    void testGetCourse2() throws Exception {
        when(this.courseService.findOne((Long) any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/courses/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CourseResource#getCourse(Long)}
     */
    @Test
    void testGetCourse3() throws Exception {
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
        Optional<CourseDTO> ofResult = Optional.of(courseDTO);
        when(this.courseService.findOne((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/courses/{id}", 123L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.courseResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"modifiedDate\":[1,1,1,1,1],\"modifiedBy\":\"Jan 1, 2020 9:00am GMT+0100\",\"createdDate\":[1,1,1,1,1],"
                                        + "\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"id\":123,\"name\":\"Name\",\"numberOfLessons\":1,\"instructorIds\""
                                        + ":[],\"studentIds\":[]}"));
    }
}

