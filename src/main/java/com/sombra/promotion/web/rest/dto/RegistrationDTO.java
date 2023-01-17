package com.sombra.promotion.web.rest.dto;

import com.sombra.promotion.dto.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * View Model object for storing a user's credentials.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(exclude = {"password"})
public class RegistrationDTO extends UserDTO {

    @NotNull
    @Size(min = 4, max = 100)
    private String password;
}
