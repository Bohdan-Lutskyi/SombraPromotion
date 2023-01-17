package com.sombra.promotion.web.rest;

import com.sombra.promotion.dto.UserDTO;
import com.sombra.promotion.service.SecurityService;
import com.sombra.promotion.web.rest.dto.LoginDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class JWTLoginController {

    private final SecurityService securityService;

    public JWTLoginController(final SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authorize(@Valid @RequestBody final LoginDTO loginDTO) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final String token = securityService.login(loginDTO, httpHeaders);
        return new ResponseEntity<>(token, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> currentUser() {

        final HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok(securityService.getAuthenticatedUser(headers));
    }

}
