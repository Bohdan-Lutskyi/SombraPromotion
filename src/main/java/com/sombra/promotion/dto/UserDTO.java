package com.sombra.promotion.dto;

import com.sombra.promotion.domain.enumeration.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
public class UserDTO extends BaseDTO implements Serializable {

    private Long id;

    @NotNull
    private String email;

    @NotNull
    @Size(max = 40)
    private String firstName;

    @NotNull
    @Size(max = 40)
    private String secondName;

    private Set<UserRole> userRoles;
}
