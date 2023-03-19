package com.sombra.promotion.unit.service.impl;

import com.sombra.promotion.config.security.TokenProvider;
import com.sombra.promotion.dto.UserDTO;
import com.sombra.promotion.service.UserService;
import com.sombra.promotion.service.impl.SecurityServiceImpl;
import com.sombra.promotion.web.rest.dto.LoginDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {SecurityServiceImpl.class})
@ExtendWith(SpringExtension.class)
class SecurityServiceImplTest {
    @Autowired
    private SecurityServiceImpl securityServiceImpl;

    @MockBean
    private TokenProvider tokenProvider;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link SecurityServiceImpl#login(LoginDTO, HttpHeaders)}
     */
    @Test
    void testLogin() {
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
        when(this.userService.findByEmailAndPassword((String) any(), (String) any())).thenReturn(userDTO);
        when(this.tokenProvider.generateToken((String) any())).thenReturn("ABC123");

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("jane.doe@example.org");
        loginDTO.setPassword("iloveyou");
        HttpHeaders httpHeaders = new HttpHeaders();
        assertEquals("Bearer ABC123", this.securityServiceImpl.login(loginDTO, httpHeaders));
        verify(this.userService).findByEmailAndPassword((String) any(), (String) any());
        verify(this.tokenProvider).generateToken((String) any());
        List<String> getResult = httpHeaders.get(HttpHeaders.AUTHORIZATION);
        assertEquals(1, getResult.size());
        assertEquals("Bearer ABC123", getResult.get(0));
    }
}

