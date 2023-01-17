package com.sombra.promotion.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "course")
@EqualsAndHashCode(of = "id", callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"instructors", "students"})
public class Course extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "number_of_lessons")
    private Short numberOfLessons;

    @ManyToMany
    @JoinTable(
            name = "course_instructors",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "instructors_id")}
    )
    @JsonIgnoreProperties(value = {"students", "instructors", "courses"}, allowSetters = true)
    private Set<Instructor> instructors = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "course_students",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "students_id")}
    )
    @JsonIgnoreProperties(value = {"students", "instructors", "courses"}, allowSetters = true)
    private Set<Student> students = new HashSet<>();

}


