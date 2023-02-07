package com.sombra.promotion.web.rest;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.sombra.promotion.config.SystemProperties;
import com.sombra.promotion.dto.StudentAttachmentDTO;
import com.sombra.promotion.repository.*;
import com.sombra.promotion.service.StudentAttachmentService;
import com.sombra.promotion.service.impl.*;
import com.sombra.promotion.service.mapper.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {StudentAttachmentResource.class})
@ExtendWith(SpringExtension.class)
class StudentAttachmentResourceTest {
    @Autowired
    private StudentAttachmentResource studentAttachmentResource;

    @MockBean
    private StudentAttachmentService studentAttachmentService;

    /**
     * Method under test: {@link StudentAttachmentResource#deleteFile(StudentAttachmentDTO)}
     */
    @Test
    void testDeleteFile2() throws SdkClientException {
        AmazonS3Client amazonS3Client = mock(AmazonS3Client.class);
        doNothing().when(amazonS3Client).deleteObject((String) any(), (String) any());
        LessonRepository lessonRepository = mock(LessonRepository.class);
        LessonMapperImpl lessonMapper = new LessonMapperImpl();
        StudentRepository studentRepository = mock(StudentRepository.class);
        StudentMapperImpl studentMapper = new StudentMapperImpl();
        UserRepository userRepository = mock(UserRepository.class);
        UserMapperImpl userMapper = new UserMapperImpl();
        UserServiceImpl userService = new UserServiceImpl(userRepository, userMapper, new Argon2PasswordEncoder());

        StudentServiceImpl studentService = new StudentServiceImpl(studentRepository, studentMapper, userService,
                new UserMapperImpl());

        CourseRepository courseRepository = mock(CourseRepository.class);
        CourseMapperImpl courseMapper = new CourseMapperImpl();
        StudentRepository studentRepository1 = mock(StudentRepository.class);
        InstructorRepository instructorRepository = mock(InstructorRepository.class);
        SystemProperties systemProperties = mock(SystemProperties.class);
        LessonServiceImpl lessonService = new LessonServiceImpl(lessonRepository, lessonMapper, studentService,
                new CourseServiceImpl(courseRepository, courseMapper, studentRepository1, instructorRepository), systemProperties);

        LessonMapperImpl lessonMapper1 = new LessonMapperImpl();
        StudentAttachmentResource studentAttachmentResource = new StudentAttachmentResource(
                new StudentAttachmentServiceImpl(amazonS3Client, lessonService, lessonMapper1,
                        new StudentAttachmentMapperImpl(), mock(StudentAttachmentRepository.class)));

        StudentAttachmentDTO studentAttachmentDTO = new StudentAttachmentDTO();
        studentAttachmentDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        studentAttachmentDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachmentDTO.setId(123L);
        studentAttachmentDTO.setLessonId(123L);
        studentAttachmentDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        studentAttachmentDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachmentDTO.setOriginalFileName("foo.txt");
        studentAttachmentDTO.setStoredFileName("foo.txt");
        ResponseEntity<String> actualDeleteFileResult = studentAttachmentResource.deleteFile(studentAttachmentDTO);
        assertEquals("foo.txt removed ...", actualDeleteFileResult.getBody());
        assertEquals(HttpStatus.OK, actualDeleteFileResult.getStatusCode());
        assertTrue(actualDeleteFileResult.getHeaders().isEmpty());
        verify(amazonS3Client).deleteObject((String) any(), (String) any());
    }

    /**
     * Method under test: {@link StudentAttachmentResource#uploadFile(Long, org.springframework.web.multipart.MultipartFile)}
     */
    @Test
    void testUploadFile() throws Exception {
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/attachment/upload/{lessonId}",
                "Uri Vars", "Uri Vars");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("file", String.valueOf((Object) null));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.studentAttachmentResource)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

