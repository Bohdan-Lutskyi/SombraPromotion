package com.sombra.promotion.e2e;

import com.sombra.promotion.domain.enumeration.UserRole;
import com.sombra.promotion.e2e.abstraction.E2ETest;
import com.sombra.promotion.util.TestUtil;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public class RegistrationE2ETest extends E2ETest {

    @Test
    void must_register_user() {

        given().contentType("application/json")
                .body(TestUtil.getTestAdminUserDTO())
                .when()
                .post(testURL("/register"))
                .then()
                .statusCode(200)
                .assertThat()
                .body("user.email", is(TestUtil.DEFAULT_ADMIN_EMAIL))
                .body("user.firstName", is(TestUtil.DEFAULT_FIRST_NAME))
                .body("user.secondName", is(TestUtil.DEFAULT_SECOND_ADMIN_NAME))
                .body("user.userRoles", contains(UserRole.ADMIN));

    }

}
