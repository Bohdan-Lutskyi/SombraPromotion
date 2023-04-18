package com.sombra.promotion.e2e;

import com.sombra.promotion.e2e.abstraction.E2ETest;
import com.sombra.promotion.util.TestUtil;
import com.sombra.promotion.web.rest.dto.RegistrationDTO;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

public class RegistrationE2ETest extends E2ETest {

    @Test
    void must_register_user() {
        final RegistrationDTO registrationDTO = TestUtil.getTestInstructorRegistrationDTO();

        given().header(CONTENT_TYPE, "application/json")
                .body(registrationDTO)
                .when()
                .post(testURL("/register"))
                .then()
                .statusCode(200)
                .assertThat()
                .body("email", is(registrationDTO.getEmail()))
                .body("firstName", is(registrationDTO.getFirstName()))
                .body("secondName", is(registrationDTO.getSecondName()));
    }

}
