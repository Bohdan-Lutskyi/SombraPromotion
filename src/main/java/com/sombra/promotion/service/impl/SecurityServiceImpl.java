package com.sombra.promotion.service.impl;

import com.sombra.promotion.config.security.JWTFilter;
import com.sombra.promotion.config.security.TokenProvider;
import com.sombra.promotion.dto.UserDTO;
import com.sombra.promotion.service.SecurityService;
import com.sombra.promotion.service.UserService;
import com.sombra.promotion.web.rest.dto.LoginDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {


    private final TokenProvider tokenProvider;
    private final UserService userService;


    public SecurityServiceImpl(final TokenProvider tokenProvider, final UserService userService) {
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @Override
    public String login(final LoginDTO loginDTO, final HttpHeaders httpHeaders) {
        final UserDTO userDTO = userService.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
        final String token = "Bearer " + tokenProvider.generateToken(userDTO.getEmail());
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, token);
        return token;
    }

    @Override
    public UserDTO getAuthenticatedUser(HttpHeaders httpHeaders) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final UserDTO userDTO = userService.findByEmail(authentication.getPrincipal().toString());
        return userDTO;
    }
}
