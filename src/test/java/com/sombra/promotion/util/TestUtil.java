package com.sombra.promotion.util;

import com.sombra.promotion.domain.*;
import com.sombra.promotion.domain.enumeration.UserRole;
import com.sombra.promotion.dto.UserDTO;
import com.sombra.promotion.repository.*;
import com.sombra.promotion.service.SecurityService;
import com.sombra.promotion.util.JacksonUtil;
import com.sombra.promotion.web.rest.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
public class TestUtil {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseFeedbackRepository courseFeedbackRepository;

    @Autowired
    private CourseRepository courseRepository;

    public static final String DEFAULT_FIRST_NAME = "Test";
    public static final String DEFAULT_SECOND_NAME = "Test";
    public static final String DEFAULT_EMAIL = "test.test@test.com";
    public static final UserRole DEFAULT_USER_ROLE = null;
    public static final String DEFAULT_PASSWORD = "0000";

    public static final String DEFAULT_SECOND_STUDENT_NAME = "Student";
    public static final String DEFAULT_STUDENT_EMAIL = "test.student@test.com";
    public static final String DEFAULT_SECOND_INSTRUCTOR_NAME = "Instructor";
    public static final String DEFAULT_INSTRUCTOR_EMAIL = "test.instructor@test.com";
    public static final String DEFAULT_SECOND_ADMIN_NAME = "Admin";
    public static final String DEFAULT_ADMIN_EMAIL = "test.admin@test.com";

    private static final Short DEFAULT_LESSON_NUMBER = 1;
    private static final Short DEFAULT_MARK = 2;

    private static final String DEFAULT_FEEDBACK = "AAAAAAAAAA";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final Short DEFAULT_NUMBER_OF_LESSONS = 10;

    public String getAuthToken(User user) {
        return securityService.login(new LoginDTO(user.getEmail(), DEFAULT_PASSWORD), new HttpHeaders());
    }

    public Student createTestStudent(Set<Course> courses) {
        User user = createUser(Collections.singleton(UserRole.STUDENT), DEFAULT_FIRST_NAME, DEFAULT_SECOND_STUDENT_NAME, DEFAULT_STUDENT_EMAIL);
        final Student student = new Student();
        student.setUser(user);
        student.setCourses(courses);
        studentRepository.save(student);
        return student;
    }

    public Student createTestStudent() {
        User user = createUser(Collections.singleton(UserRole.STUDENT), DEFAULT_FIRST_NAME, DEFAULT_SECOND_STUDENT_NAME, DEFAULT_STUDENT_EMAIL);
        final Student student = new Student();
        student.setUser(user);
        student.setCourses(Collections.singleton(createCourse()));
        studentRepository.save(student);
        return student;
    }

    public Student createTestStudentEntity() {
        User user = createUser(Collections.singleton(UserRole.STUDENT), DEFAULT_FIRST_NAME, DEFAULT_SECOND_STUDENT_NAME, DEFAULT_STUDENT_EMAIL);
        final Student student = new Student();
        student.setUser(user);
        return student;
    }

    public Instructor createTestInstructor() {
        User user = createUser(Collections.singleton(UserRole.INSTRUCTOR), DEFAULT_FIRST_NAME, DEFAULT_SECOND_INSTRUCTOR_NAME, DEFAULT_INSTRUCTOR_EMAIL);
        final Instructor instructor = new Instructor();
        instructor.setUser(user);
        instructorRepository.save(instructor);
        return instructor;
    }

    public Instructor createTestInstructorEntity() {
        User user = createUser(Collections.singleton(UserRole.INSTRUCTOR), DEFAULT_FIRST_NAME, DEFAULT_SECOND_INSTRUCTOR_NAME, DEFAULT_INSTRUCTOR_EMAIL);
        final Instructor instructor = new Instructor();
        instructor.setUser(user);
        return instructor;
    }

    public static UserDTO getTestAdminUserDTO() {
        return new UserDTO(null, DEFAULT_ADMIN_EMAIL, DEFAULT_FIRST_NAME, DEFAULT_SECOND_ADMIN_NAME, Collections.singleton(UserRole.ADMIN));
    }

    public User createTestAdmin() {
        User admin = createUser(Collections.singleton(UserRole.ADMIN), DEFAULT_FIRST_NAME, DEFAULT_SECOND_ADMIN_NAME, DEFAULT_ADMIN_EMAIL);
        return admin;
    }

    public User createTestUser() {
        User admin = createUser(Collections.singleton(DEFAULT_USER_ROLE), DEFAULT_FIRST_NAME, DEFAULT_SECOND_NAME, DEFAULT_EMAIL);
        return admin;
    }

    public User createUser(Set<UserRole> userRoles, String firstName, String secondName, String email) {
        final User user = new User();
        user.setUserRoles(JacksonUtil.serialize(userRoles));
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setEmail(email);
        user.setPassword(encode(DEFAULT_PASSWORD));
        userRepository.save(user);

        return user;
    }

    public User createUserEntity() {
        final User user = new User();
        user.setUserRoles(JacksonUtil.serialize(Collections.singletonList(DEFAULT_USER_ROLE)));
        user.setFirstName(DEFAULT_FIRST_NAME);
        user.setSecondName(DEFAULT_SECOND_NAME);
        user.setEmail(DEFAULT_EMAIL);
        user.setPassword(encode(DEFAULT_PASSWORD));
        return user;
    }

    private String encode(String input) {
        return passwordEncoder.encode(input);
    }

    public Lesson createLesson() {
        final Student student = createTestStudent();
        Lesson lesson = Lesson.builder()
                .lessonNumber(DEFAULT_LESSON_NUMBER)
                .mark(DEFAULT_MARK)
                .course(student.getCourses().stream().findAny().get())
                .student(student)
                .build();
        final Lesson savedLesson = lessonRepository.save(lesson);
        return savedLesson;
    }

    public Course createCourse() {
        Course course = Course.builder()
                .name(DEFAULT_NAME)
                .numberOfLessons(DEFAULT_NUMBER_OF_LESSONS)
                .instructors(Collections.singleton(createTestInstructor()))
                .build();
        final Course save = courseRepository.save(course);
        return save;
    }

    public CourseFeedback createCourseFeedbackEntity() {
        final Course course = createCourse();
        CourseFeedback courseFeedback = CourseFeedback.builder()
                .feedback(DEFAULT_FEEDBACK)
                .course(course)
                .student(createTestStudent(Collections.singleton(course)))
                .build();

        return courseFeedback;
    }


}
