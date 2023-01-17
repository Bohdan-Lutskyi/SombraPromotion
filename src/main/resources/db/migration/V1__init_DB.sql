create table course
(
    id                bigint not null auto_increment primary key,
    name              varchar(255),
    number_of_lessons smallint,
    created_by        varchar(255),
    created_date      datetime(6),
    modified_by       varchar(255),
    modified_date     datetime(6)
);

create table user
(
    id            bigint not null auto_increment primary key,
    email         varchar(255) unique,
    first_name    varchar(255),
    second_name   varchar(255),
    user_role     varchar(20),
    created_by    varchar(255),
    created_date  datetime(6),
    modified_by   varchar(255),
    modified_date datetime(6)
);

create table instructor
(
    id            bigint not null auto_increment primary key,
    user_id       bigint unique,
    created_by    varchar(255),
    created_date  datetime(6),
    modified_by   varchar(255),
    modified_date datetime(6),
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES user (id)
);

create table student
(
    id            bigint not null auto_increment primary key,
    user_id       bigint not null unique,
    created_by    varchar(255),
    created_date  datetime(6),
    modified_by   varchar(255),
    modified_date datetime(6),
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES user (id)
);

create table lesson
(
    id            bigint not null auto_increment primary key,
    lesson_number smallint,
    mark          smallint,
    course_id     bigint,
    student_id    bigint,
    created_by    varchar(255),
    created_date  datetime(6),
    modified_by   varchar(255),
    modified_date datetime(6),
    CONSTRAINT FOREIGN KEY (course_id) REFERENCES course (id),
    CONSTRAINT FOREIGN KEY (student_id) REFERENCES student (id)

);

create table course_instructors
(
    course_id      bigint NOT NULL,
    instructors_id bigint NOT NULL,
    created_by     varchar(255),
    created_date   datetime(6),
    modified_by    varchar(255),
    modified_date  datetime(6),
    PRIMARY KEY (course_id, instructors_id),
    CONSTRAINT FOREIGN KEY (instructors_id) REFERENCES instructor (id),
    CONSTRAINT FOREIGN KEY (course_id) REFERENCES course (id)
);

create table course_students
(
    course_id     bigint NOT NULL,
    students_id   bigint NOT NULL,
    created_by    varchar(255),
    created_date  datetime(6),
    modified_by   varchar(255),
    modified_date datetime(6),
    PRIMARY KEY (course_id, students_id),
    CONSTRAINT FOREIGN KEY (students_id) REFERENCES student (id),
    CONSTRAINT FOREIGN KEY (course_id) REFERENCES course (id)
);
