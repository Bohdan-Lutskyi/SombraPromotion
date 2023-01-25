package com.sombra.promotion.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user")
@EqualsAndHashCode(of = "id", callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Wrong entered email address.")
    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @NotNull
    @Column(name = "first_name", length = 40, nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "second_name", length = 40, nullable = false)
    private String secondName;

    @Column(name = "user_roles", length = 100)
    private String userRoles;
}
