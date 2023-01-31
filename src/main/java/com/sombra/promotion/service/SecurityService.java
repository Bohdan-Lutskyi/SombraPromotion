package com.sombra.promotion.service;

import com.sombra.promotion.dto.UserDTO;
import com.sombra.promotion.web.rest.dto.LoginDTO;
import org.springframework.http.HttpHeaders;

public interface SecurityService {

    String login(final LoginDTO loginDTO, final HttpHeaders httpHeaders);

    UserDTO getAuthenticatedUser(HttpHeaders httpHeaders);

}
