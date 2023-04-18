package com.sombra.promotion.e2e;

import com.sombra.promotion.e2e.abstraction.E2ETest;
import com.sombra.promotion.util.TestUtil;
import com.sombra.promotion.web.rest.dto.RegistrationDTO;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public class RegistrationE2ETest extends E2ETest {

    @Test
    void must_register_user() {
        final RegistrationDTO registrationDTO = TestUtil.getTestInstructorRegistrationDTO();

        given().contentType("application/json")
                .body(registrationDTO)
                .when()
                .post(testURL("/register"))
                .then()
                .statusCode(200)
                .assertThat()
                .body("user.email", is(registrationDTO.getEmail()))
                .body("user.firstName", is(registrationDTO.getFirstName()))
                .body("user.secondName", is(registrationDTO.getSecondName()))
                .body("user.userRoles", contains(registrationDTO.getUserRoles()));


//        restUserMockMvc
//                .perform(post("/api/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(JacksonUtil.serialize(registrationDTO)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(jsonPath("$.email").value(hasItem(DEFAULT_EMAIL)))
//                .andExpect(jsonPath("$.firstName").value(hasItem(DEFAULT_FIRST_NAME)))
//                .andExpect(jsonPath("$.secondName").value(hasItem(DEFAULT_SECOND_NAME)));

    }

}
