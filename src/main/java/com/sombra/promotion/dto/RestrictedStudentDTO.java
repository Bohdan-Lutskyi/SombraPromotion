package com.sombra.promotion.dto;

import com.sombra.promotion.domain.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
public class RestrictedStudentDTO extends BaseDTO implements Serializable {

    public RestrictedStudentDTO(Student student) {
        super(student.getModifiedDate(), student.getModifiedBy(), student.getCreatedDate(), student.getCreatedBy());
        this.id = student.getId();
        this.email = student.getUser().getEmail();
        this.firstName = student.getUser().getFirstName();
        this.secondName = student.getUser().getSecondName();
    }

    private Long id;

    private String email;

    @Size(min = 0, max = 40)
    private String firstName;

    @Size(min = 0, max = 40)
    private String secondName;

}
