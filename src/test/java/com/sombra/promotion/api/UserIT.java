package com.sombra.promotion.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sombra.promotion.domain.Student;
import com.sombra.promotion.domain.User;
import com.sombra.promotion.domain.enumeration.UserRole;
import com.sombra.promotion.dto.UserDTO;
import com.sombra.promotion.repository.StudentRepository;
import com.sombra.promotion.repository.UserRepository;
import com.sombra.promotion.service.mapper.UserMapper;
import com.sombra.promotion.util.ApiTestConfiguration;
import com.sombra.promotion.util.JacksonUtil;
import com.sombra.promotion.util.TestUtil;
import com.sombra.promotion.web.rest.JWTLoginController;
import com.sombra.promotion.web.rest.dto.RegistrationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import static com.sombra.promotion.util.TestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UserResource} REST controller.
 */
@ApiTestConfiguration
@WithMockUser(roles = {"ADMIN"})
class UserIT {

    public static final String UPDATED_FIRST_NAME = "Test";
    public static final String UPDATED_SECOND_NAME = "Test";
    public static final String UPDATED_EMAIL = "test.test@test.com";

    private static final String ENTITY_API_URL = "/api/users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserMockMvc;

    private User user;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestUtil testUtil;

    @Autowired
    private JWTLoginController loginController;

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public User createEntity(EntityManager em) {
        User user = testUtil.createUserEntity();
        return user;
    }

    @BeforeEach
    public void initTest() {
        user = createEntity(em);
    }

    @Test
    @Transactional
    void createUser() throws Exception {
        int databaseSizeBeforeCreate = userRepository.findAll().size();
        // Create the User
        final RegistrationDTO registrationDTO = getRegistrationDTO(user);
        restUserMockMvc
                .perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JacksonUtil.serialize(registrationDTO)))
                .andExpect(status().isOk());

        // Validate the User in the database
        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeCreate + 1);
        User testUser = userList.get(userList.size() - 1);
        assertThat(testUser.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testUser.getSecondName()).isEqualTo(DEFAULT_SECOND_NAME);
        assertThat(testUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUser.getUserRoles()).isEqualTo(JacksonUtil.serialize(Collections.singletonList(DEFAULT_USER_ROLE)));
    }

    @Test
    @Transactional
    void createUserWithExistingId() throws Exception {
        // Create the User with an existing ID
        final RegistrationDTO registrationDTO = getRegistrationDTO(user);
        registrationDTO.setId(1L);

        int databaseSizeBeforeCreate = userRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserMockMvc
                .perform(post("/api/register").contentType(MediaType.APPLICATION_JSON).content(JacksonUtil.serialize(registrationDTO)))
                .andExpect(status().isBadRequest());

        // Validate the User in the database
        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = userRepository.findAll().size();
        // set the field null
        user.setEmail(null);

        // Create the User, which fails.
        final RegistrationDTO registrationDTO = getRegistrationDTO(user);

        restUserMockMvc
                .perform(post("/api/register").contentType(MediaType.APPLICATION_JSON).content(JacksonUtil.serialize(registrationDTO)))
                .andExpect(status().isBadRequest());

        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPasswordIsRequired() throws Exception {
        int databaseSizeBeforeTest = userRepository.findAll().size();
        final RegistrationDTO registrationDTO = getRegistrationDTO(user);
        // set the field null
        registrationDTO.setPassword(null);

        restUserMockMvc
                .perform(post("/api/register").contentType(MediaType.APPLICATION_JSON).content(JacksonUtil.serialize(registrationDTO)))
                .andExpect(status().isBadRequest());

        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = userRepository.findAll().size();
        // set the field null
        user.setFirstName(null);

        // Create the User, which fails.
        final RegistrationDTO registrationDTO = getRegistrationDTO(user);
        restUserMockMvc
                .perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JacksonUtil.serialize(registrationDTO)))
                .andExpect(status().isBadRequest());

        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSecondNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = userRepository.findAll().size();
        // set the field null
        user.setSecondName(null);

        // Create the User, which fails.
        final RegistrationDTO registrationDTO = getRegistrationDTO(user);

        restUserMockMvc
                .perform(post("/api/register").contentType(MediaType.APPLICATION_JSON).content(JacksonUtil.serialize(registrationDTO)))
                .andExpect(status().isBadRequest());

        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllUsers() throws Exception {
        // Initialize the database
        userRepository.saveAndFlush(user);

        // Get all the userList
        restUserMockMvc
                .perform(get(ENTITY_API_URL + "?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(user.getId().intValue())))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
                .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
                .andExpect(jsonPath("$.[*].secondName").value(hasItem(DEFAULT_SECOND_NAME)));
    }

    @Test
    @Transactional
    void getUser() throws Exception {
        // Initialize the database
        userRepository.saveAndFlush(user);

        // Get the user
        restUserMockMvc
                .perform(get(ENTITY_API_URL_ID, user.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(user.getId().intValue()))
                .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
                .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
                .andExpect(jsonPath("$.secondName").value(DEFAULT_SECOND_NAME));
    }

    @Test
    @Transactional
    void getNonExistingUser() throws Exception {
        // Get the user
        restUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewUser() throws Exception {
        // Initialize the database
        userRepository.saveAndFlush(user);

        int databaseSizeBeforeUpdate = userRepository.findAll().size();

        // Update the user
        User updatedUser = userRepository.findById(user.getId()).get();
        // Disconnect from session so that the updates on updatedUser are not directly saved in db
        em.detach(updatedUser);
        updatedUser.setFirstName(UPDATED_FIRST_NAME);
        updatedUser.setSecondName(UPDATED_SECOND_NAME);
        updatedUser.setEmail(UPDATED_EMAIL);
        UserDTO userDTO = userMapper.toDto(updatedUser);

        restUserMockMvc
                .perform(
                        put(ENTITY_API_URL_ID, userDTO.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JacksonUtil.serialize(userDTO))
                )
                .andExpect(status().isOk());

        // Validate the User in the database
        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeUpdate);
        User testUser = userList.get(userList.size() - 1);
        assertThat(testUser.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testUser.getSecondName()).isEqualTo(UPDATED_SECOND_NAME);
        assertThat(testUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUser.getUserRoles()).isEqualTo(JacksonUtil.serialize(Collections.singletonList(DEFAULT_USER_ROLE)));
    }

    @Test
    @Transactional
    void putNonExistingUser() throws Exception {
        int databaseSizeBeforeUpdate = userRepository.findAll().size();
        user.setId(count.incrementAndGet());

        // Create the User
        UserDTO userDTO = userMapper.toDto(user);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserMockMvc
                .perform(
                        put(ENTITY_API_URL_ID, userDTO.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JacksonUtil.serialize(userDTO))
                )
                .andExpect(status().isBadRequest());

        // Validate the User in the database
        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUser() throws Exception {
        int databaseSizeBeforeUpdate = userRepository.findAll().size();
        user.setId(count.incrementAndGet());

        // Create the User
        UserDTO userDTO = userMapper.toDto(user);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserMockMvc
                .perform(
                        put(ENTITY_API_URL_ID, count.incrementAndGet())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JacksonUtil.serialize(userDTO))
                )
                .andExpect(status().isBadRequest());

        // Validate the User in the database
        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUser() throws Exception {
        int databaseSizeBeforeUpdate = userRepository.findAll().size();
        user.setId(count.incrementAndGet());

        // Create the User
        UserDTO userDTO = userMapper.toDto(user);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserMockMvc
                .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(JacksonUtil.serialize(userDTO)))
                .andExpect(status().isMethodNotAllowed());

        // Validate the User in the database
        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUser() throws Exception {
        // Initialize the database
        userRepository.saveAndFlush(user);

        int databaseSizeBeforeDelete = userRepository.findAll().size();

        // Delete the user
        restUserMockMvc
                .perform(delete(ENTITY_API_URL_ID, user.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    void adminCanAssignRoleToUser() throws Exception {
        User user = testUtil.createTestUser();

        ResultActions resultActions = restUserMockMvc.perform(put("/api/users/{userId}/role", user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(UserRole.STUDENT.name()));

        // THEN role is set for user
        resultActions.andExpect(status().isOk());
    }

    @Test
    void adminCantAssignRoleToUser_whenRoleAlreadySet() throws Exception {
        Student student = testUtil.createTestStudent();

        ResultActions resultActions = restUserMockMvc.perform(put("/api/users/" + student.getId() + "/role")
                .contentType(MediaType.APPLICATION_JSON)
                .content(UserRole.STUDENT.name()));

        // THEN response is error
        resultActions.andExpect(status().is4xxClientError());

        Student savedStudent = studentRepository.getById(student.getId());
        assertNotNull(savedStudent);
        assertTrue(savedStudent.getUser().getUserRoles().contains(UserRole.STUDENT.name()));
    }

    @Test
    @WithMockUser(roles = {"STUDENT"})
    void nonUserUserCantAssignRoleToUser() throws Exception {
        Student student = testUtil.createTestStudent();

        // WHEN admin setts role to a user with role
        ResultActions resultActions = restUserMockMvc.perform(put("/api/users/" + student.getUser().getId() + "/role")
                .contentType(MediaType.APPLICATION_JSON)
                .content(UserRole.STUDENT.name()));

        // THEN response is error
        resultActions.andExpect(status().isForbidden());
    }

    private RegistrationDTO getRegistrationDTO(User user) {
        final RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setEmail(user.getEmail());
        registrationDTO.setPassword(DEFAULT_PASSWORD);
        registrationDTO.setFirstName(user.getFirstName());
        registrationDTO.setSecondName(user.getSecondName());
        registrationDTO.setUserRoles(JacksonUtil.deserialize(user.getUserRoles(), new TypeReference<Set<UserRole>>() {
        }));
        return registrationDTO;
    }

}
