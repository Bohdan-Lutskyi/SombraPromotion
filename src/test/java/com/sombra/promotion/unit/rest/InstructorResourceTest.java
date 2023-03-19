package com.sombra.promotion.unit.rest;

import com.sombra.promotion.config.error.SystemException;
import com.sombra.promotion.domain.User;
import com.sombra.promotion.dto.InstructorDTO;
import com.sombra.promotion.dto.RestrictedCourseDTO;
import com.sombra.promotion.repository.InstructorRepository;
import com.sombra.promotion.repository.UserRepository;
import com.sombra.promotion.service.InstructorService;
import com.sombra.promotion.service.impl.InstructorServiceImpl;
import com.sombra.promotion.service.impl.UserServiceImpl;
import com.sombra.promotion.service.mapper.InstructorMapperImpl;
import com.sombra.promotion.service.mapper.UserMapperImpl;
import com.sombra.promotion.web.rest.InstructorResource;
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

@ContextConfiguration(classes = {InstructorResource.class})
@ExtendWith(SpringExtension.class)
class InstructorResourceTest {
    @MockBean
    private InstructorRepository instructorRepository;

    @Autowired
    private InstructorResource instructorResource;

    @MockBean
    private InstructorService instructorService;

    /**
     * Method under test: {@link InstructorResource#createInstructor(InstructorDTO)}
     */
    @Test
    void testCreateInstructor() throws URISyntaxException {
        InstructorRepository instructorRepository = mock(InstructorRepository.class);
        InstructorMapperImpl instructorMapper = new InstructorMapperImpl();
        UserRepository userRepository = mock(UserRepository.class);
        UserMapperImpl userMapper = new UserMapperImpl();
        UserServiceImpl userService = new UserServiceImpl(userRepository, userMapper, new Argon2PasswordEncoder());

        InstructorResource instructorResource = new InstructorResource(
                new InstructorServiceImpl(instructorRepository, instructorMapper, userService, new UserMapperImpl()),
                mock(InstructorRepository.class));

        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setCourses(new HashSet<>());
        instructorDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        instructorDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructorDTO.setEmail("jane.doe@example.org");
        instructorDTO.setFirstName("Jane");
        instructorDTO.setId(123L);
        instructorDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        instructorDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructorDTO.setSecondName("Second Name");
        instructorDTO.setUserId(123L);
        assertThrows(SystemException.class, () -> instructorResource.createInstructor(instructorDTO));
    }

    /**
     * Method under test: {@link InstructorResource#createInstructor(InstructorDTO)}
     */
    @Test
    void testCreateInstructor2() throws URISyntaxException {
        InstructorRepository instructorRepository = mock(InstructorRepository.class);
        InstructorMapperImpl instructorMapper = new InstructorMapperImpl();
        UserRepository userRepository = mock(UserRepository.class);
        UserMapperImpl userMapper = new UserMapperImpl();
        UserServiceImpl userService = new UserServiceImpl(userRepository, userMapper, new Argon2PasswordEncoder());

        InstructorResource instructorResource = new InstructorResource(
                new InstructorServiceImpl(instructorRepository, instructorMapper, userService, new UserMapperImpl()),
                mock(InstructorRepository.class));
        InstructorDTO instructorDTO = mock(InstructorDTO.class);
        when(instructorDTO.getId()).thenReturn(123L);
        doNothing().when(instructorDTO).setCreatedBy((String) any());
        doNothing().when(instructorDTO).setCreatedDate((LocalDateTime) any());
        doNothing().when(instructorDTO).setModifiedBy((String) any());
        doNothing().when(instructorDTO).setModifiedDate((LocalDateTime) any());
        doNothing().when(instructorDTO).setCourses((java.util.Set<RestrictedCourseDTO>) any());
        doNothing().when(instructorDTO).setEmail((String) any());
        doNothing().when(instructorDTO).setFirstName((String) any());
        doNothing().when(instructorDTO).setId((Long) any());
        doNothing().when(instructorDTO).setSecondName((String) any());
        doNothing().when(instructorDTO).setUserId((Long) any());
        instructorDTO.setCourses(new HashSet<>());
        instructorDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        instructorDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructorDTO.setEmail("jane.doe@example.org");
        instructorDTO.setFirstName("Jane");
        instructorDTO.setId(123L);
        instructorDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        instructorDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructorDTO.setSecondName("Second Name");
        instructorDTO.setUserId(123L);
        assertThrows(SystemException.class, () -> instructorResource.createInstructor(instructorDTO));
        verify(instructorDTO).getId();
        verify(instructorDTO).setCreatedBy((String) any());
        verify(instructorDTO).setCreatedDate((LocalDateTime) any());
        verify(instructorDTO).setModifiedBy((String) any());
        verify(instructorDTO).setModifiedDate((LocalDateTime) any());
        verify(instructorDTO).setCourses((java.util.Set<RestrictedCourseDTO>) any());
        verify(instructorDTO).setEmail((String) any());
        verify(instructorDTO).setFirstName((String) any());
        verify(instructorDTO).setId((Long) any());
        verify(instructorDTO).setSecondName((String) any());
        verify(instructorDTO).setUserId((Long) any());
    }

    /**
     * Method under test: {@link InstructorResource#createInstructor(InstructorDTO)}
     */
    @Test
    void testCreateInstructor4() throws URISyntaxException {
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

        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setCourses(new HashSet<>());
        instructorDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        instructorDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructorDTO.setEmail("jane.doe@example.org");
        instructorDTO.setFirstName("Jane");
        instructorDTO.setId(123L);
        instructorDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        instructorDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructorDTO.setSecondName("Second Name");
        instructorDTO.setUserId(123L);
        InstructorService instructorService = mock(InstructorService.class);
        when(instructorService.save((InstructorDTO) any())).thenReturn(instructorDTO);
        InstructorResource instructorResource = new InstructorResource(instructorService, mock(InstructorRepository.class));
        InstructorDTO instructorDTO1 = mock(InstructorDTO.class);
        when(instructorDTO1.getUserId()).thenReturn(123L);
        when(instructorDTO1.getCreatedBy()).thenReturn("Jan 1, 2020 8:00am GMT+0100");
        when(instructorDTO1.getModifiedBy()).thenReturn("Jan 1, 2020 9:00am GMT+0100");
        when(instructorDTO1.getCreatedDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(instructorDTO1.getModifiedDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(instructorDTO1.getId()).thenReturn(null);
        doNothing().when(instructorDTO1).setCreatedBy((String) any());
        doNothing().when(instructorDTO1).setCreatedDate((LocalDateTime) any());
        doNothing().when(instructorDTO1).setModifiedBy((String) any());
        doNothing().when(instructorDTO1).setModifiedDate((LocalDateTime) any());
        doNothing().when(instructorDTO1).setCourses((java.util.Set<RestrictedCourseDTO>) any());
        doNothing().when(instructorDTO1).setEmail((String) any());
        doNothing().when(instructorDTO1).setFirstName((String) any());
        doNothing().when(instructorDTO1).setId((Long) any());
        doNothing().when(instructorDTO1).setSecondName((String) any());
        doNothing().when(instructorDTO1).setUserId((Long) any());
        instructorDTO1.setCourses(new HashSet<>());
        instructorDTO1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        instructorDTO1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructorDTO1.setEmail("jane.doe@example.org");
        instructorDTO1.setFirstName("Jane");
        instructorDTO1.setId(123L);
        instructorDTO1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        instructorDTO1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructorDTO1.setSecondName("Second Name");
        instructorDTO1.setUserId(123L);
        ResponseEntity<InstructorDTO> actualCreateInstructorResult = instructorResource.createInstructor(instructorDTO1);
        assertTrue(actualCreateInstructorResult.hasBody());
        assertEquals(3, actualCreateInstructorResult.getHeaders().size());
        assertEquals(HttpStatus.CREATED, actualCreateInstructorResult.getStatusCode());
        verify(instructorService).save((InstructorDTO) any());
        verify(instructorDTO1).getId();
        verify(instructorDTO1).setCreatedBy((String) any());
        verify(instructorDTO1).setCreatedDate((LocalDateTime) any());
        verify(instructorDTO1).setModifiedBy((String) any());
        verify(instructorDTO1).setModifiedDate((LocalDateTime) any());
        verify(instructorDTO1).setCourses((java.util.Set<RestrictedCourseDTO>) any());
        verify(instructorDTO1).setEmail((String) any());
        verify(instructorDTO1).setFirstName((String) any());
        verify(instructorDTO1).setId((Long) any());
        verify(instructorDTO1).setSecondName((String) any());
        verify(instructorDTO1).setUserId((Long) any());
    }

    /**
     * Method under test: {@link InstructorResource#createInstructor(InstructorDTO)}
     */
    @Test
    void testCreateInstructor5() throws URISyntaxException {
        User user = new User(123L, "jane.doe@example.org", "iloveyou", "Jane", "REST request to save Instructor : {}",
                "REST request to save Instructor : {}");
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

        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setCourses(new HashSet<>());
        instructorDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        instructorDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructorDTO.setEmail("jane.doe@example.org");
        instructorDTO.setFirstName("Jane");
        instructorDTO.setId(123L);
        instructorDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        instructorDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructorDTO.setSecondName("Second Name");
        instructorDTO.setUserId(123L);
        InstructorService instructorService = mock(InstructorService.class);
        when(instructorService.save((InstructorDTO) any())).thenReturn(instructorDTO);
        InstructorResource instructorResource = new InstructorResource(instructorService, mock(InstructorRepository.class));
        InstructorDTO instructorDTO1 = mock(InstructorDTO.class);
        when(instructorDTO1.getUserId()).thenReturn(123L);
        when(instructorDTO1.getCreatedBy()).thenReturn("Jan 1, 2020 8:00am GMT+0100");
        when(instructorDTO1.getModifiedBy()).thenReturn("Jan 1, 2020 9:00am GMT+0100");
        when(instructorDTO1.getCreatedDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(instructorDTO1.getModifiedDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(instructorDTO1.getId()).thenReturn(null);
        doNothing().when(instructorDTO1).setCreatedBy((String) any());
        doNothing().when(instructorDTO1).setCreatedDate((LocalDateTime) any());
        doNothing().when(instructorDTO1).setModifiedBy((String) any());
        doNothing().when(instructorDTO1).setModifiedDate((LocalDateTime) any());
        doNothing().when(instructorDTO1).setCourses((java.util.Set<RestrictedCourseDTO>) any());
        doNothing().when(instructorDTO1).setEmail((String) any());
        doNothing().when(instructorDTO1).setFirstName((String) any());
        doNothing().when(instructorDTO1).setId((Long) any());
        doNothing().when(instructorDTO1).setSecondName((String) any());
        doNothing().when(instructorDTO1).setUserId((Long) any());
        instructorDTO1.setCourses(new HashSet<>());
        instructorDTO1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        instructorDTO1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructorDTO1.setEmail("jane.doe@example.org");
        instructorDTO1.setFirstName("Jane");
        instructorDTO1.setId(123L);
        instructorDTO1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        instructorDTO1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructorDTO1.setSecondName("Second Name");
        instructorDTO1.setUserId(123L);
        ResponseEntity<InstructorDTO> actualCreateInstructorResult = instructorResource.createInstructor(instructorDTO1);
        assertTrue(actualCreateInstructorResult.hasBody());
        assertEquals(3, actualCreateInstructorResult.getHeaders().size());
        assertEquals(HttpStatus.CREATED, actualCreateInstructorResult.getStatusCode());
        verify(instructorService).save((InstructorDTO) any());
        verify(instructorDTO1).getId();
        verify(instructorDTO1).setCreatedBy((String) any());
        verify(instructorDTO1).setCreatedDate((LocalDateTime) any());
        verify(instructorDTO1).setModifiedBy((String) any());
        verify(instructorDTO1).setModifiedDate((LocalDateTime) any());
        verify(instructorDTO1).setCourses((java.util.Set<RestrictedCourseDTO>) any());
        verify(instructorDTO1).setEmail((String) any());
        verify(instructorDTO1).setFirstName((String) any());
        verify(instructorDTO1).setId((Long) any());
        verify(instructorDTO1).setSecondName((String) any());
        verify(instructorDTO1).setUserId((Long) any());
    }

    /**
     * Method under test: {@link InstructorResource#updateInstructor(Long, InstructorDTO)}
     */
    @Test
    void testUpdateInstructor() throws URISyntaxException {
        InstructorRepository instructorRepository = mock(InstructorRepository.class);
        InstructorMapperImpl instructorMapper = new InstructorMapperImpl();
        UserRepository userRepository = mock(UserRepository.class);
        UserMapperImpl userMapper = new UserMapperImpl();
        UserServiceImpl userService = new UserServiceImpl(userRepository, userMapper, new Argon2PasswordEncoder());

        InstructorResource instructorResource = new InstructorResource(
                new InstructorServiceImpl(instructorRepository, instructorMapper, userService, new UserMapperImpl()),
                mock(InstructorRepository.class));

        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setCourses(new HashSet<>());
        instructorDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        instructorDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructorDTO.setEmail("jane.doe@example.org");
        instructorDTO.setFirstName("Jane");
        instructorDTO.setId(123L);
        instructorDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        instructorDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructorDTO.setSecondName("Second Name");
        instructorDTO.setUserId(123L);
        assertThrows(SystemException.class, () -> instructorResource.updateInstructor(1L, instructorDTO));
    }

    /**
     * Method under test: {@link InstructorResource#deleteInstructor(Long)}
     */
    @Test
    void testDeleteInstructor() throws Exception {
        doNothing().when(this.instructorService).delete((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/instructors/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.instructorResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link InstructorResource#deleteInstructor(Long)}
     */
    @Test
    void testDeleteInstructor2() throws Exception {
        doNothing().when(this.instructorService).delete((Long) any());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.instructorResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link InstructorResource#deleteInstructor(Long)}
     */
    @Test
    void testDeleteInstructor3() throws Exception {
        doNothing().when(this.instructorService).delete((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/instructors/{id}", 123L);
        deleteResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.instructorResource)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link InstructorResource#getAllInstructors()}
     */
    @Test
    void testGetAllInstructors() throws Exception {
        when(this.instructorService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/instructors");
        MockMvcBuilders.standaloneSetup(this.instructorResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link InstructorResource#getAllInstructors()}
     */
    @Test
    void testGetAllInstructors2() throws Exception {
        when(this.instructorService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/instructors");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.instructorResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link InstructorResource#getInstructor(Long)}
     */
    @Test
    void testGetInstructor() throws Exception {
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setCourses(new HashSet<>());
        instructorDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        instructorDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructorDTO.setEmail("jane.doe@example.org");
        instructorDTO.setFirstName("Jane");
        instructorDTO.setId(123L);
        instructorDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        instructorDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructorDTO.setSecondName("Second Name");
        instructorDTO.setUserId(123L);
        Optional<InstructorDTO> ofResult = Optional.of(instructorDTO);
        when(this.instructorService.findOne((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/instructors/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.instructorResource)
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
     * Method under test: {@link InstructorResource#getInstructor(Long)}
     */
    @Test
    void testGetInstructor2() throws Exception {
        when(this.instructorService.findOne((Long) any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/instructors/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.instructorResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link InstructorResource#getInstructor(Long)}
     */
    @Test
    void testGetInstructor3() throws Exception {
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setCourses(new HashSet<>());
        instructorDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        instructorDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructorDTO.setEmail("jane.doe@example.org");
        instructorDTO.setFirstName("Jane");
        instructorDTO.setId(123L);
        instructorDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        instructorDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        instructorDTO.setSecondName("Second Name");
        instructorDTO.setUserId(123L);
        Optional<InstructorDTO> ofResult = Optional.of(instructorDTO);
        when(this.instructorService.findOne((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/instructors/{id}", 123L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.instructorResource)
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

