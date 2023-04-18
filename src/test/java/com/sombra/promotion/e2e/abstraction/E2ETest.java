package com.sombra.promotion.e2e.abstraction;

import com.sombra.promotion.PromotionApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {PromotionApplication.class})
@Transactional
abstract public class E2ETest {

    @LocalServerPort protected int port;

    protected String testURL(String path) {
        return "http://localhost:" + port + "/api" + path;
    }

}
