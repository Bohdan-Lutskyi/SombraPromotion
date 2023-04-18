package com.sombra.promotion.e2e;


import com.sombra.promotion.domain.Course;
import com.sombra.promotion.domain.Instructor;
import com.sombra.promotion.domain.Student;
import com.sombra.promotion.dto.CourseDTO;
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

public class CourseE2ETest extends E2ETest {

    @Test
    void must_create_course() {
        final Student testStudent = testUtil.createTestStudent();
        final Instructor testInstructor = testUtil.createTestInstructor();
        final CourseDTO courseDTO = testUtil.createRequestCourseDTO(testStudent, testInstructor);

        given().header(CONTENT_TYPE, "application/json")
                .header(AUTHORIZATION, testUtil.generateToken(TestUtil.DEFAULT_ADMIN_EMAIL, TestUtil.DEFAULT_PASSWORD))
                .body(courseDTO)
                .when()
                .post(testURL("/courses"))
                .then()
                .statusCode(201)
                .assertThat()
                .body("name", is(DEFAULT_NAME))
                .body("numberOfLessons", equalTo(DEFAULT_NUMBER_OF_LESSONS.intValue()))
                .body("instructorIds", hasItems(testInstructor.getId().intValue()))
                .body("studentIds", hasItems(testStudent.getId().intValue()));
    }

    @Test
    void must_add_student_to_course() {
        final Student testStudent = testUtil.createTestStudent();
        final Course course = testUtil.createCourse();

        given().header(CONTENT_TYPE, "application/json")
                .header(AUTHORIZATION, testUtil.generateToken(TestUtil.DEFAULT_ADMIN_EMAIL, TestUtil.DEFAULT_PASSWORD))
                .queryParam("studentIds", Collections.singleton(testStudent.getId()))
                .when()
                .post(testURL("/course/" + course.getId() + "/add-student"))
                .then()
                .statusCode(201)
                .assertThat()
                .body("name", is(DEFAULT_NAME))
                .body("numberOfLessons", equalTo(DEFAULT_NUMBER_OF_LESSONS.intValue()))
                .body("instructorIds", hasItems(course.getInstructors().stream().findFirst().get().getId().intValue()))
                .body("studentIds", hasItems(testStudent.getId().intValue()));
    }

    @Test
    void must_add_instructor_to_course() {
        final Course course = testUtil.createCourseWithoutInstructor();
        final Instructor testInstructor = testUtil.createTestInstructor();

        given().header(CONTENT_TYPE, "application/json")
                .header(AUTHORIZATION, testUtil.generateToken(TestUtil.DEFAULT_ADMIN_EMAIL, TestUtil.DEFAULT_PASSWORD))
                .queryParam("instructorIds", Collections.singleton(testInstructor.getId()))
                .when()
                .post(testURL("/course/" + course.getId() + "/add-instructor"))
                .then()
                .statusCode(201)
                .assertThat()
                .body("name", is(DEFAULT_NAME))
                .body("numberOfLessons", equalTo(DEFAULT_NUMBER_OF_LESSONS.intValue()))
                .body("instructorIds", hasItems(testInstructor.getId().intValue()))
                .body("studentIds", is(Collections.emptyList()));
    }

    @Test
    void must_get_all_students_in_course() {
        final Student testStudent1 = testUtil.createTestStudent();
        final Student testStudent2 = testUtil.createTestStudent("second-test.student@test.com");
        final Course course = testUtil.createCourse();

        course.setStudents(Set.of(testStudent1, testStudent2));
        courseRepository.saveAndFlush(course);

        given().header(CONTENT_TYPE, "application/json")
                .header(AUTHORIZATION, testUtil.generateToken(TestUtil.DEFAULT_ADMIN_EMAIL, TestUtil.DEFAULT_PASSWORD))
                .queryParam("courseIds", Collections.singleton(course.getId()))
                .when()
                .get(testURL("/courses/students"))
                .then()
                .log().body()
                .statusCode(200)
                .assertThat()
                .body(course.getId().toString(), hasSize(2))
                .body(course.getId() + ".email", hasItems(is(testStudent1.getUser().getEmail()), is(testStudent2.getUser().getEmail())))
                .body(course.getId() + ".id", hasItems(is(testStudent1.getId().intValue()), is(testStudent2.getId().intValue())));
    }

}
