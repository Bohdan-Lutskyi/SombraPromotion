package com.sombra.promotion.e2e.abstraction;

import com.sombra.promotion.PromotionApplication;
import com.sombra.promotion.repository.*;
import com.sombra.promotion.service.SecurityService;
import com.sombra.promotion.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {PromotionApplication.class, TestUtil.class})
abstract public class E2ETest {

    @LocalServerPort
    protected int port;
    @Autowired
    private CourseFeedbackRepository courseFeedbackRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private StudentAttachmentRepository studentAttachmentRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    protected TestUtil testUtil;
    @Autowired
    protected SecurityService securityService;

    protected String testURL(String path) {
        return "http://localhost:" + port + "/api" + path;
    }

    @BeforeEach
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void setUp() {
        deleteWholeDB();
    }

    private void deleteWholeDB() {

        studentAttachmentRepository.deleteAll();
        courseFeedbackRepository.deleteAll();
        lessonRepository.deleteAll();
        courseRepository.deleteAll();
        instructorRepository.deleteAll();
        studentRepository.deleteAll();
        userRepository.deleteAll();

        testUtil.createTestAdmin();
    }
}
