package com.sombra.promotion.unit.rest;

import com.sombra.promotion.config.error.SystemException;
import com.sombra.promotion.domain.User;
import com.sombra.promotion.dto.RestrictedCourseDTO;
import com.sombra.promotion.dto.StudentDTO;
import com.sombra.promotion.repository.StudentRepository;
import com.sombra.promotion.repository.UserRepository;
import com.sombra.promotion.service.StudentService;
import com.sombra.promotion.service.impl.StudentServiceImpl;
import com.sombra.promotion.service.impl.UserServiceImpl;
import com.sombra.promotion.service.mapper.StudentMapperImpl;
import com.sombra.promotion.service.mapper.UserMapperImpl;
import com.sombra.promotion.web.rest.StudentResource;
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

@ContextConfiguration(classes = {StudentResource.class})
@ExtendWith(SpringExtension.class)
class StudentResourceTest {
    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentResource studentResource;

    @MockBean
    private StudentService studentService;

    /**
     * Method under test: {@link StudentResource#createStudent(StudentDTO)}
     */
    @Test
    void testCreateStudent() throws URISyntaxException {
        StudentRepository studentRepository = mock(StudentRepository.class);
        StudentMapperImpl studentMapper = new StudentMapperImpl();
        UserRepository userRepository = mock(UserRepository.class);
        UserMapperImpl userMapper = new UserMapperImpl();
        UserServiceImpl userService = new UserServiceImpl(userRepository, userMapper, new Argon2PasswordEncoder());

        StudentResource studentResource = new StudentResource(
                new StudentServiceImpl(studentRepository, studentMapper, userService, new UserMapperImpl()),
                mock(StudentRepository.class));

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
        assertThrows(SystemException.class, () -> studentResource.createStudent(studentDTO));
    }

    /**
     * Method under test: {@link StudentResource#createStudent(StudentDTO)}
     */
    @Test
    void testCreateStudent2() throws URISyntaxException {
        StudentRepository studentRepository = mock(StudentRepository.class);
        StudentMapperImpl studentMapper = new StudentMapperImpl();
        UserRepository userRepository = mock(UserRepository.class);
        UserMapperImpl userMapper = new UserMapperImpl();
        UserServiceImpl userService = new UserServiceImpl(userRepository, userMapper, new Argon2PasswordEncoder());

        StudentResource studentResource = new StudentResource(
                new StudentServiceImpl(studentRepository, studentMapper, userService, new UserMapperImpl()),
                mock(StudentRepository.class));
        StudentDTO studentDTO = mock(StudentDTO.class);
        when(studentDTO.getId()).thenReturn(123L);
        doNothing().when(studentDTO).setCreatedBy((String) any());
        doNothing().when(studentDTO).setCreatedDate((LocalDateTime) any());
        doNothing().when(studentDTO).setModifiedBy((String) any());
        doNothing().when(studentDTO).setModifiedDate((LocalDateTime) any());
        doNothing().when(studentDTO).setCourses((java.util.Set<RestrictedCourseDTO>) any());
        doNothing().when(studentDTO).setEmail((String) any());
        doNothing().when(studentDTO).setFirstName((String) any());
        doNothing().when(studentDTO).setId((Long) any());
        doNothing().when(studentDTO).setSecondName((String) any());
        doNothing().when(studentDTO).setUserId((Long) any());
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
        assertThrows(SystemException.class, () -> studentResource.createStudent(studentDTO));
        verify(studentDTO).getId();
        verify(studentDTO).setCreatedBy((String) any());
        verify(studentDTO).setCreatedDate((LocalDateTime) any());
        verify(studentDTO).setModifiedBy((String) any());
        verify(studentDTO).setModifiedDate((LocalDateTime) any());
        verify(studentDTO).setCourses((java.util.Set<RestrictedCourseDTO>) any());
        verify(studentDTO).setEmail((String) any());
        verify(studentDTO).setFirstName((String) any());
        verify(studentDTO).setId((Long) any());
        verify(studentDTO).setSecondName((String) any());
        verify(studentDTO).setUserId((Long) any());
    }

    /**
     * Method under test: {@link StudentResource#createStudent(StudentDTO)}
     */
    @Test
    void testCreateStudent4() throws URISyntaxException {
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
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.getById((Long) any())).thenReturn(user);
        UserMapperImpl userMapper = new UserMapperImpl();
        new UserServiceImpl(userRepository, userMapper, new Argon2PasswordEncoder());

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
        StudentService studentService = mock(StudentService.class);
        when(studentService.save((StudentDTO) any())).thenReturn(studentDTO);
        StudentResource studentResource = new StudentResource(studentService, mock(StudentRepository.class));
        StudentDTO studentDTO1 = mock(StudentDTO.class);
        when(studentDTO1.getUserId()).thenReturn(123L);
        when(studentDTO1.getCreatedBy()).thenReturn("Jan 1, 2020 8:00am GMT+0100");
        when(studentDTO1.getModifiedBy()).thenReturn("Jan 1, 2020 9:00am GMT+0100");
        when(studentDTO1.getCreatedDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(studentDTO1.getModifiedDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(studentDTO1.getId()).thenReturn(null);
        doNothing().when(studentDTO1).setCreatedBy((String) any());
        doNothing().when(studentDTO1).setCreatedDate((LocalDateTime) any());
        doNothing().when(studentDTO1).setModifiedBy((String) any());
        doNothing().when(studentDTO1).setModifiedDate((LocalDateTime) any());
        doNothing().when(studentDTO1).setCourses((java.util.Set<RestrictedCourseDTO>) any());
        doNothing().when(studentDTO1).setEmail((String) any());
        doNothing().when(studentDTO1).setFirstName((String) any());
        doNothing().when(studentDTO1).setId((Long) any());
        doNothing().when(studentDTO1).setSecondName((String) any());
        doNothing().when(studentDTO1).setUserId((Long) any());
        studentDTO1.setCourses(new HashSet<>());
        studentDTO1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        studentDTO1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentDTO1.setEmail("jane.doe@example.org");
        studentDTO1.setFirstName("Jane");
        studentDTO1.setId(123L);
        studentDTO1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        studentDTO1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentDTO1.setSecondName("Second Name");
        studentDTO1.setUserId(123L);
        ResponseEntity<StudentDTO> actualCreateStudentResult = studentResource.createStudent(studentDTO1);
        assertTrue(actualCreateStudentResult.hasBody());
        assertEquals(3, actualCreateStudentResult.getHeaders().size());
        assertEquals(HttpStatus.CREATED, actualCreateStudentResult.getStatusCode());
        verify(studentService).save((StudentDTO) any());
        verify(studentDTO1).getId();
        verify(studentDTO1).setCreatedBy((String) any());
        verify(studentDTO1).setCreatedDate((LocalDateTime) any());
        verify(studentDTO1).setModifiedBy((String) any());
        verify(studentDTO1).setModifiedDate((LocalDateTime) any());
        verify(studentDTO1).setCourses((java.util.Set<RestrictedCourseDTO>) any());
        verify(studentDTO1).setEmail((String) any());
        verify(studentDTO1).setFirstName((String) any());
        verify(studentDTO1).setId((Long) any());
        verify(studentDTO1).setSecondName((String) any());
        verify(studentDTO1).setUserId((Long) any());
    }

    /**
     * Method under test: {@link StudentResource#createStudent(StudentDTO)}
     */
    @Test
    void testCreateStudent5() throws URISyntaxException {
        User user = new User(123L, "jane.doe@example.org", "iloveyou", "Jane", "REST request to save Student : {}",
                "REST request to save Student : {}");
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
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.getById((Long) any())).thenReturn(user);
        UserMapperImpl userMapper = new UserMapperImpl();
        new UserServiceImpl(userRepository, userMapper, new Argon2PasswordEncoder());

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
        StudentService studentService = mock(StudentService.class);
        when(studentService.save((StudentDTO) any())).thenReturn(studentDTO);
        StudentResource studentResource = new StudentResource(studentService, mock(StudentRepository.class));
        StudentDTO studentDTO1 = mock(StudentDTO.class);
        when(studentDTO1.getUserId()).thenReturn(123L);
        when(studentDTO1.getCreatedBy()).thenReturn("Jan 1, 2020 8:00am GMT+0100");
        when(studentDTO1.getModifiedBy()).thenReturn("Jan 1, 2020 9:00am GMT+0100");
        when(studentDTO1.getCreatedDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(studentDTO1.getModifiedDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(studentDTO1.getId()).thenReturn(null);
        doNothing().when(studentDTO1).setCreatedBy((String) any());
        doNothing().when(studentDTO1).setCreatedDate((LocalDateTime) any());
        doNothing().when(studentDTO1).setModifiedBy((String) any());
        doNothing().when(studentDTO1).setModifiedDate((LocalDateTime) any());
        doNothing().when(studentDTO1).setCourses((java.util.Set<RestrictedCourseDTO>) any());
        doNothing().when(studentDTO1).setEmail((String) any());
        doNothing().when(studentDTO1).setFirstName((String) any());
        doNothing().when(studentDTO1).setId((Long) any());
        doNothing().when(studentDTO1).setSecondName((String) any());
        doNothing().when(studentDTO1).setUserId((Long) any());
        studentDTO1.setCourses(new HashSet<>());
        studentDTO1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        studentDTO1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentDTO1.setEmail("jane.doe@example.org");
        studentDTO1.setFirstName("Jane");
        studentDTO1.setId(123L);
        studentDTO1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        studentDTO1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentDTO1.setSecondName("Second Name");
        studentDTO1.setUserId(123L);
        ResponseEntity<StudentDTO> actualCreateStudentResult = studentResource.createStudent(studentDTO1);
        assertTrue(actualCreateStudentResult.hasBody());
        assertEquals(3, actualCreateStudentResult.getHeaders().size());
        assertEquals(HttpStatus.CREATED, actualCreateStudentResult.getStatusCode());
        verify(studentService).save((StudentDTO) any());
        verify(studentDTO1).getId();
        verify(studentDTO1).setCreatedBy((String) any());
        verify(studentDTO1).setCreatedDate((LocalDateTime) any());
        verify(studentDTO1).setModifiedBy((String) any());
        verify(studentDTO1).setModifiedDate((LocalDateTime) any());
        verify(studentDTO1).setCourses((java.util.Set<RestrictedCourseDTO>) any());
        verify(studentDTO1).setEmail((String) any());
        verify(studentDTO1).setFirstName((String) any());
        verify(studentDTO1).setId((Long) any());
        verify(studentDTO1).setSecondName((String) any());
        verify(studentDTO1).setUserId((Long) any());
    }

    /**
     * Method under test: {@link StudentResource#updateStudent(Long, StudentDTO)}
     */
    @Test
    void testUpdateStudent() throws URISyntaxException {
        StudentRepository studentRepository = mock(StudentRepository.class);
        StudentMapperImpl studentMapper = new StudentMapperImpl();
        UserRepository userRepository = mock(UserRepository.class);
        UserMapperImpl userMapper = new UserMapperImpl();
        UserServiceImpl userService = new UserServiceImpl(userRepository, userMapper, new Argon2PasswordEncoder());

        StudentResource studentResource = new StudentResource(
                new StudentServiceImpl(studentRepository, studentMapper, userService, new UserMapperImpl()),
                mock(StudentRepository.class));

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
        assertThrows(SystemException.class, () -> studentResource.updateStudent(1L, studentDTO));
    }

    /**
     * Method under test: {@link StudentResource#deleteStudent(Long)}
     */
    @Test
    void testDeleteStudent() throws Exception {
        doNothing().when(this.studentService).delete((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/students/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.studentResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link StudentResource#deleteStudent(Long)}
     */
    @Test
    void testDeleteStudent2() throws Exception {
        doNothing().when(this.studentService).delete((Long) any());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.studentResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link StudentResource#deleteStudent(Long)}
     */
    @Test
    void testDeleteStudent3() throws Exception {
        doNothing().when(this.studentService).delete((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/students/{id}", 123L);
        deleteResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.studentResource)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link StudentResource#getAllStudents()}
     */
    @Test
    void testGetAllStudents() throws Exception {
        when(this.studentService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/students");
        MockMvcBuilders.standaloneSetup(this.studentResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link StudentResource#getAllStudents()}
     */
    @Test
    void testGetAllStudents2() throws Exception {
        when(this.studentService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/students");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.studentResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link StudentResource#getStudent(Long)}
     */
    @Test
    void testGetStudent() throws Exception {
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
        Optional<StudentDTO> ofResult = Optional.of(studentDTO);
        when(this.studentService.findOne((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/students/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.studentResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"modifiedDate\":[1,1,1,1,1],\"modifiedBy\":\"Jan 1, 2020 9:00am GMT+0100\",\"createdDate\":[1,1,1,1,1],"
                                        + "\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"id\":123,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\""
                                        + ",\"secondName\":\"Second Name\",\"userId\":123,\"courses\":[]}"));
    }

    /**
     * Method under test: {@link StudentResource#getStudent(Long)}
     */
    @Test
    void testGetStudent2() throws Exception {
        when(this.studentService.findOne((Long) any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/students/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.studentResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link StudentResource#getStudent(Long)}
     */
    @Test
    void testGetStudent3() throws Exception {
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
        Optional<StudentDTO> ofResult = Optional.of(studentDTO);
        when(this.studentService.findOne((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/students/{id}", 123L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.studentResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"modifiedDate\":[1,1,1,1,1],\"modifiedBy\":\"Jan 1, 2020 9:00am GMT+0100\",\"createdDate\":[1,1,1,1,1],"
                                        + "\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"id\":123,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\""
                                        + ",\"secondName\":\"Second Name\",\"userId\":123,\"courses\":[]}"));
    }
}

