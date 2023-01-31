package com.sombra.promotion.config.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sombra.promotion.domain.User;
import com.sombra.promotion.domain.enumeration.UserRole;
import com.sombra.promotion.repository.UserRepository;
import com.sombra.promotion.util.JacksonUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Filters incoming requests and installs a Spring Security principal if a header corresponding to a valid user is
 * found.
 */
@Component
public class JWTFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    public JWTFilter(final TokenProvider tokenProvider, final UserRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        if (token != null && tokenProvider.validateToken(token)) {
            String userEmail = tokenProvider.getEmailFromToken(token);
            final User user = userRepository.findByEmail(userEmail);
            final Set<SimpleGrantedAuthority> authorities = getSimpleGrantedAuthorities(user);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private Set<SimpleGrantedAuthority> getSimpleGrantedAuthorities(final User user) {
        final Set<UserRole> userRoles = JacksonUtil.deserialize(user.getUserRoles(), new TypeReference<Set<UserRole>>() {
        });
        return userRoles.stream().map(userRole -> new SimpleGrantedAuthority(userRole.name())).collect(Collectors.toSet());
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
