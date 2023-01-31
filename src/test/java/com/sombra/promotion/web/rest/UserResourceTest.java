package com.sombra.promotion.web.rest;

import com.sombra.promotion.config.error.SystemException;
import com.sombra.promotion.config.security.TokenProvider;
import com.sombra.promotion.domain.enumeration.UserRole;
import com.sombra.promotion.dto.UserDTO;
import com.sombra.promotion.repository.UserRepository;
import com.sombra.promotion.service.SecurityService;
import com.sombra.promotion.service.UserService;
import com.sombra.promotion.service.impl.SecurityServiceImpl;
import com.sombra.promotion.service.impl.UserServiceImpl;
import com.sombra.promotion.service.mapper.UserMapperImpl;
import com.sombra.promotion.web.rest.dto.RegistrationDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserResource.class})
@ExtendWith(SpringExtension.class)
class UserResourceTest {
    @MockBean
    private SecurityService securityService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserResource userResource;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserResource#deleteUser(Long)}
     */
    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(this.userService).delete((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/users/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link UserResource#registerAccount(RegistrationDTO)}
     */
    @Test
    void testRegisterAccount() throws URISyntaxException {
        UserRepository userRepository = mock(UserRepository.class);
        UserMapperImpl userMapper = new UserMapperImpl();
        UserServiceImpl userService = new UserServiceImpl(userRepository, userMapper, new Argon2PasswordEncoder());

        UserRepository userRepository1 = mock(UserRepository.class);
        TokenProvider tokenProvider = new TokenProvider();
        UserRepository userRepository2 = mock(UserRepository.class);
        UserMapperImpl userMapper1 = new UserMapperImpl();
        UserResource userResource = new UserResource(userService, userRepository1, new SecurityServiceImpl(tokenProvider,
                new UserServiceImpl(userRepository2, userMapper1, new Argon2PasswordEncoder())));

        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        registrationDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        registrationDTO.setEmail("jane.doe@example.org");
        registrationDTO.setFirstName("Jane");
        registrationDTO.setId(123L);
        registrationDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        registrationDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        registrationDTO.setPassword("iloveyou");
        registrationDTO.setSecondName("Second Name");
        registrationDTO.setUserRoles(new HashSet<>());
        assertThrows(SystemException.class, () -> userResource.registerAccount(registrationDTO));
    }

    /**
     * Method under test: {@link UserResource#registerAccount(RegistrationDTO)}
     */
    @Test
    void testRegisterAccount2() throws URISyntaxException {
        UserRepository userRepository = mock(UserRepository.class);
        UserMapperImpl userMapper = new UserMapperImpl();
        UserServiceImpl userService = new UserServiceImpl(userRepository, userMapper, new Argon2PasswordEncoder());

        UserRepository userRepository1 = mock(UserRepository.class);
        TokenProvider tokenProvider = new TokenProvider();
        UserRepository userRepository2 = mock(UserRepository.class);
        UserMapperImpl userMapper1 = new UserMapperImpl();
        UserResource userResource = new UserResource(userService, userRepository1, new SecurityServiceImpl(tokenProvider,
                new UserServiceImpl(userRepository2, userMapper1, new Argon2PasswordEncoder())));
        RegistrationDTO registrationDTO = mock(RegistrationDTO.class);
        when(registrationDTO.getId()).thenReturn(123L);
        doNothing().when(registrationDTO).setCreatedBy((String) any());
        doNothing().when(registrationDTO).setCreatedDate((LocalDateTime) any());
        doNothing().when(registrationDTO).setModifiedBy((String) any());
        doNothing().when(registrationDTO).setModifiedDate((LocalDateTime) any());
        doNothing().when(registrationDTO).setEmail((String) any());
        doNothing().when(registrationDTO).setFirstName((String) any());
        doNothing().when(registrationDTO).setId((Long) any());
        doNothing().when(registrationDTO).setSecondName((String) any());
        doNothing().when(registrationDTO).setUserRoles((java.util.Set<UserRole>) any());
        doNothing().when(registrationDTO).setPassword((String) any());
        registrationDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        registrationDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        registrationDTO.setEmail("jane.doe@example.org");
        registrationDTO.setFirstName("Jane");
        registrationDTO.setId(123L);
        registrationDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        registrationDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        registrationDTO.setPassword("iloveyou");
        registrationDTO.setSecondName("Second Name");
        registrationDTO.setUserRoles(new HashSet<>());
        assertThrows(SystemException.class, () -> userResource.registerAccount(registrationDTO));
        verify(registrationDTO).getId();
        verify(registrationDTO).setCreatedBy((String) any());
        verify(registrationDTO).setCreatedDate((LocalDateTime) any());
        verify(registrationDTO).setModifiedBy((String) any());
        verify(registrationDTO).setModifiedDate((LocalDateTime) any());
        verify(registrationDTO).setEmail((String) any());
        verify(registrationDTO).setFirstName((String) any());
        verify(registrationDTO).setId((Long) any());
        verify(registrationDTO).setSecondName((String) any());
        verify(registrationDTO).setUserRoles((java.util.Set<UserRole>) any());
        verify(registrationDTO).setPassword((String) any());
    }

    /**
     * Method under test: {@link UserResource#updateUser(Long, UserDTO)}
     */
    @Test
    void testUpdateUser() {
        UserRepository userRepository = mock(UserRepository.class);
        UserMapperImpl userMapper = new UserMapperImpl();
        UserServiceImpl userService = new UserServiceImpl(userRepository, userMapper, new Argon2PasswordEncoder());

        UserRepository userRepository1 = mock(UserRepository.class);
        TokenProvider tokenProvider = new TokenProvider();
        UserRepository userRepository2 = mock(UserRepository.class);
        UserMapperImpl userMapper1 = new UserMapperImpl();
        UserResource userResource = new UserResource(userService, userRepository1, new SecurityServiceImpl(tokenProvider,
                new UserServiceImpl(userRepository2, userMapper1, new Argon2PasswordEncoder())));

        UserDTO userDTO = new UserDTO();
        userDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        userDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        userDTO.setEmail("jane.doe@example.org");
        userDTO.setFirstName("Jane");
        userDTO.setId(123L);
        userDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        userDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        userDTO.setSecondName("Second Name");
        userDTO.setUserRoles(new HashSet<>());
        assertThrows(SystemException.class, () -> userResource.updateUser(1L, userDTO));
    }

    /**
     * Method under test: {@link UserResource#deleteUser(Long)}
     */
    @Test
    void testDeleteUser2() throws Exception {
        doNothing().when(this.userService).delete((Long) any());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link UserResource#deleteUser(Long)}
     */
    @Test
    void testDeleteUser3() throws Exception {
        doNothing().when(this.userService).delete((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/users/{id}", 123L);
        deleteResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userResource)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link UserResource#getAllUsers()}
     */
    @Test
    void testGetAllUsers() throws Exception {
        when(this.userService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users");
        MockMvcBuilders.standaloneSetup(this.userResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UserResource#getAllUsers()}
     */
    @Test
    void testGetAllUsers2() throws Exception {
        when(this.userService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/users");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.userResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UserResource#getUser(Long)}
     */
    @Test
    void testGetUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        userDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        userDTO.setEmail("jane.doe@example.org");
        userDTO.setFirstName("Jane");
        userDTO.setId(123L);
        userDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        userDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        userDTO.setSecondName("Second Name");
        userDTO.setUserRoles(new HashSet<>());
        Optional<UserDTO> ofResult = Optional.of(userDTO);
        when(this.userService.findOne((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.userResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"modifiedDate\":[1,1,1,1,1],\"modifiedBy\":\"Jan 1, 2020 9:00am GMT+0100\",\"createdDate\":[1,1,1,1,1],"
                                        + "\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"id\":123,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\""
                                        + ",\"secondName\":\"Second Name\",\"userRoles\":[]}"));
    }

    /**
     * Method under test: {@link UserResource#getUser(Long)}
     */
    @Test
    void testGetUser2() throws Exception {
        when(this.userService.findOne((Long) any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link UserResource#getUser(Long)}
     */
    @Test
    void testGetUser3() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        userDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        userDTO.setEmail("jane.doe@example.org");
        userDTO.setFirstName("Jane");
        userDTO.setId(123L);
        userDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        userDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        userDTO.setSecondName("Second Name");
        userDTO.setUserRoles(new HashSet<>());
        Optional<UserDTO> ofResult = Optional.of(userDTO);
        when(this.userService.findOne((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/users/{id}", 123L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.userResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"modifiedDate\":[1,1,1,1,1],\"modifiedBy\":\"Jan 1, 2020 9:00am GMT+0100\",\"createdDate\":[1,1,1,1,1],"
                                        + "\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"id\":123,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\""
                                        + ",\"secondName\":\"Second Name\",\"userRoles\":[]}"));
    }
}

