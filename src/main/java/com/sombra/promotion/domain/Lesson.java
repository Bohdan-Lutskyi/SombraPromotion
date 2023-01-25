package com.sombra.promotion.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "lesson")
@EqualsAndHashCode(of = "id", callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lesson extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "lesson_number", nullable = false)
    private Short lessonNumber;

    @NotNull
    @Column(name = "mark")
    private Short mark;

    @OneToOne(fetch = FetchType.LAZY)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Set<StudentAttachment> studentAttachments = new HashSet<>();

}
