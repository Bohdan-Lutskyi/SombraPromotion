package com.sombra.promotion.web.rest.dto;

import com.sombra.promotion.domain.enumeration.UserRole;
import com.sombra.promotion.dto.UserDTO;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * View Model object for storing a user's credentials.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(exclude = {"password"})
@NoArgsConstructor
public class RegistrationDTO extends UserDTO {

    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    public RegistrationDTO(final Long id, final String email, final String firstName, final String secondName, final Set<UserRole> userRoles, final String password) {
        super(id, email, firstName, secondName, userRoles);
        this.password = password;
    }
}
