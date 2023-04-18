package com.sombra.promotion.e2e;


import com.sombra.promotion.domain.Course;
import com.sombra.promotion.domain.Instructor;
import com.sombra.promotion.domain.Student;
import com.sombra.promotion.dto.CourseDTO;
import com.sombra.promotion.dto.LessonDTO;
import com.sombra.promotion.dto.StudentCourseDTO;
import com.sombra.promotion.e2e.abstraction.E2ETest;
import com.sombra.promotion.util.TestUtil;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

import static com.sombra.promotion.util.TestUtil.DEFAULT_NAME;
import static com.sombra.promotion.util.TestUtil.DEFAULT_NUMBER_OF_LESSONS;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

public class LessonE2ETest extends E2ETest {

    @Test
    void must_create_lesson() {
        final Course course = testUtil.createCourseWithInstructorAndStudent();
        final Student student = course.getStudents().stream().findFirst().get();
        final LessonDTO lessonDTO = testUtil.createLessonDTO(student.getId(), course.getId());

        given().header(CONTENT_TYPE, "application/json")
                .header(AUTHORIZATION, testUtil.generateToken(TestUtil.DEFAULT_ADMIN_EMAIL, TestUtil.DEFAULT_PASSWORD))
                .body(lessonDTO)
                .when()
                .post(testURL("/lessons"))
                .then()
                .statusCode(201)
                .assertThat()
                .body("lessonNumber", is(lessonDTO.getLessonNumber().intValue()))
                .body("mark", is(lessonDTO.getMark().intValue()))
                .body("studentId", is(student.getId().intValue()))
                .body("courseId", is(course.getId().intValue()));
    }

    @Test
    void must_pass_course() {
        final Student testStudent = testUtil.createTestStudent();
        final Course course = testUtil.createCourse();
        final StudentCourseDTO studentCourseDTO = new StudentCourseDTO(testStudent.getId(), course.getId());
        for (int i = 1; i <= 10; i++) {
            testUtil.createLesson(testStudent, course, 10, i);
        }

        given().header(CONTENT_TYPE, "application/json")
                .header(AUTHORIZATION, testUtil.generateToken(TestUtil.DEFAULT_ADMIN_EMAIL, TestUtil.DEFAULT_PASSWORD))
                .body(studentCourseDTO)
                .when()
                .post(testURL("/lessons/final-mark"))
                .then()
                .statusCode(200)
                .log().body()
                .assertThat()
                .body("coursePassed", equalTo(true))
                .body("message", equalTo("You passed course, congratulations."));
    }

    @Test
    void must_fail_course() {
        final Student testStudent = testUtil.createTestStudent();
        final Course course = testUtil.createCourse();
        final StudentCourseDTO studentCourseDTO = new StudentCourseDTO(testStudent.getId(), course.getId());
        for (int i = 1; i <= 10; i++) {
            testUtil.createLesson(testStudent, course, 7, i);
        }

        given().header(CONTENT_TYPE, "application/json")
                .header(AUTHORIZATION, testUtil.generateToken(TestUtil.DEFAULT_ADMIN_EMAIL, TestUtil.DEFAULT_PASSWORD))
                .body(studentCourseDTO)
                .when()
                .post(testURL("/lessons/final-mark"))
                .then()
                .statusCode(200)
                .assertThat()
                .body("coursePassed", equalTo(false))
                .body("message", equalTo("You didn't pass course, average mark should be above 8."));
    }
}
