package com.sombra.promotion.web.rest;

import com.sombra.promotion.config.error.ErrorCode;
import com.sombra.promotion.config.error.SystemException;
import com.sombra.promotion.dto.UserDTO;
import com.sombra.promotion.repository.UserRepository;
import com.sombra.promotion.service.SecurityService;
import com.sombra.promotion.service.UserService;
import com.sombra.promotion.util.HeaderUtil;
import com.sombra.promotion.util.ResponseUtil;
import com.sombra.promotion.web.rest.dto.RegistrationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


/**
 * REST controller for managing {@link com.sombra.promotion.domain.User}.
 */
@RestController
@RequestMapping("/api")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private static final String ENTITY_NAME = "user";

    private final UserService userService;
    private final UserRepository userRepository;
    private final SecurityService securityService;

    public UserResource(final UserService userService,
                        final UserRepository userRepository,
                        final SecurityService securityService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.securityService = securityService;
    }

    /**
     * {@code POST  /users} : Create a new user.
     *
     * @param registrationDTO the userDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userDTO, or with status {@code 400 (Bad Request)} if the user has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDTO> registerAccount(@Valid @RequestBody RegistrationDTO registrationDTO) throws URISyntaxException {
        log.debug("REST request to register User : {}", registrationDTO);
        if (registrationDTO.getId() != null) {
            throw new SystemException("A new user cannot already have an ID", ErrorCode.BAD_REQUEST);
        }
        UserDTO result = userService.registerUser(registrationDTO, registrationDTO.getPassword());
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert("applicationName", true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/users/{userId}/role")
    public ResponseEntity<Void> assignRoleForUser(@PathVariable Long userId, @RequestBody String role) {
        userService.setUserRole(userId, role);
        return ResponseEntity.ok().build();
    }

    /**
     * {@code PUT  /users/:id} : Updates an existing user.
     *
     * @param id      the id of the userDTO to save.
     * @param userDTO the userDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userDTO,
     * or with status {@code 400 (Bad Request)} if the userDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userDTO couldn't be updated.
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody UserDTO userDTO
    ) {
        log.debug("REST request to update User : {}, {}", id, userDTO);
        if (userDTO.getId() == null) {
            throw new SystemException("Invalid id", ErrorCode.BAD_REQUEST);
        }
        if (!Objects.equals(id, userDTO.getId())) {
            throw new SystemException("Invalid ID", ErrorCode.BAD_REQUEST);
        }

        if (!userRepository.existsById(id)) {
            throw new SystemException("Entity not found", ErrorCode.BAD_REQUEST);
        }

        UserDTO result = userService.update(userDTO);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert("applicationName", true, ENTITY_NAME, userDTO.getId().toString()))
                .body(result);
    }


    /**
     * {@code GET  /users} : get all the users.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of users in body.
     */
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDTO> getAllUsers() {
        log.debug("REST request to get all Users");
        return userService.findAll();
    }

    /**
     * {@code GET  /users/:id} : get the "id" user.
     *
     * @param id the id of the userDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        log.debug("REST request to get User : {}", id);
        Optional<UserDTO> userDTO = userService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userDTO);
    }

    /**
     * {@code DELETE  /users/:id} : delete the "id" user.
     *
     * @param id the id of the userDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.debug("REST request to delete User : {}", id);
        userService.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert("applicationName", true, ENTITY_NAME, id.toString()))
                .build();
    }
}
