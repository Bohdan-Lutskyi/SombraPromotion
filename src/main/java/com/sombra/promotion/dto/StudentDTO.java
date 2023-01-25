package com.sombra.promotion.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link com.sombra.promotion.domain.Student} entity.
 */

@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
public class StudentDTO extends BaseDTO implements Serializable {

    private Long id;

    private String email;

    @Size(min = 0, max = 40)
    private String firstName;

    @Size(min = 0, max = 40)
    private String secondName;

    private Long userId;
    private Set<RestrictedCourseDTO> courses;
}
