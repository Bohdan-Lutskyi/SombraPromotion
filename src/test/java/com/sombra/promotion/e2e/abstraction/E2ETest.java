package com.sombra.promotion.e2e.abstraction;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Import({DatabaseTestConfiguration.class, JwtTokenTestConfiguration.class, JsonTestConfiguration.class})
@Transactional
abstract public class E2ETest {

//    @Autowired protected JsonBuilderUtils jsonBuilderUtils;
//    @Autowired protected JwtTokenUtil jwtTokenUtil;
//    @Autowired protected InsertUtils insert;
//    @Autowired protected SelectUtils select;
//    @Autowired protected DeleteUtils delete;
    @LocalServerPort protected int port;

    protected String testURL(String path) {
        return "http://localhost:" + port + path;
    }

//    @BeforeEach
//    void setUp() {
//        deleteWholeDB();
//    }

//    private void deleteWholeDB() {
//
//        // tables delete should be in the right order
//
//        delete.userRole();
//        delete.instructorCourse();
//        delete.studentCourse();
//
//        delete.feedback();
//        delete.role();
//        delete.courseMark();
//        delete.homework();
//        delete.mark();
//
//        delete.lesson();
//        delete.course();
//        delete.user();
//
//    }

}
