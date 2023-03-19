package com.sombra.promotion.unit.service.impl;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.StringInputStream;
import com.sombra.promotion.config.error.ErrorCode;
import com.sombra.promotion.config.error.SystemException;
import com.sombra.promotion.domain.*;
import com.sombra.promotion.dto.StudentAttachmentDTO;
import com.sombra.promotion.repository.StudentAttachmentRepository;
import com.sombra.promotion.service.LessonService;
import com.sombra.promotion.service.impl.StudentAttachmentServiceImpl;
import com.sombra.promotion.service.mapper.LessonMapper;
import com.sombra.promotion.service.mapper.StudentAttachmentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {StudentAttachmentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class StudentAttachmentServiceImplTest {
    @MockBean
    private AmazonS3 amazonS3;

    @MockBean
    private LessonMapper lessonMapper;

    @MockBean
    private LessonService lessonService;

    @MockBean
    private StudentAttachmentMapper studentAttachmentMapper;

    @MockBean
    private StudentAttachmentRepository studentAttachmentRepository;

    @Autowired
    private StudentAttachmentServiceImpl studentAttachmentServiceImpl;

    /**
     * Method under test: {@link StudentAttachmentServiceImpl#downloadFile(StudentAttachmentDTO)}
     */
    @Test
    void testDownloadFile() throws SdkClientException, UnsupportedEncodingException {
        Course course = new Course();
        course.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        course.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course.setId(123L);
        course.setInstructors(new HashSet<>());
        course.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        course.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course.setName("Name");
        course.setNumberOfLessons((short) 1);
        course.setStudents(new HashSet<>());

        User user = new User();
        user.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        user.setCreatedDate(null);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        user.setModifiedDate(null);
        user.setPassword("iloveyou");
        user.setSecondName("Second Name");
        user.setUserRoles("User Roles");

        Student student = new Student();
        student.setCourses(new HashSet<>());
        student.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        student.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        student.setId(123L);
        student.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        student.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        student.setUser(user);

        Lesson lesson = new Lesson();
        lesson.setCourse(course);
        lesson.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        lesson.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lesson.setId(123L);
        lesson.setLessonNumber((short) 1);
        lesson.setMark((short) 1);
        lesson.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        lesson.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lesson.setStudent(student);
        lesson.setStudentAttachments(new HashSet<>());

        StudentAttachment studentAttachment = new StudentAttachment();
        studentAttachment.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        studentAttachment.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachment.setId(123L);
        studentAttachment.setLesson(lesson);
        studentAttachment.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        studentAttachment.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachment.setOriginalFileName("foo.txt");
        studentAttachment.setStoredFileName("foo.txt");
        Optional<StudentAttachment> ofResult = Optional.of(studentAttachment);
        when(this.studentAttachmentRepository.findByLessonId((Long) any())).thenReturn(ofResult);

        StudentAttachmentDTO studentAttachmentDTO = new StudentAttachmentDTO();
        studentAttachmentDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        studentAttachmentDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachmentDTO.setId(123L);
        studentAttachmentDTO.setLessonId(123L);
        studentAttachmentDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        studentAttachmentDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachmentDTO.setOriginalFileName("foo.txt");
        studentAttachmentDTO.setStoredFileName("foo.txt");
        when(this.studentAttachmentMapper.toDto((StudentAttachment) any())).thenReturn(studentAttachmentDTO);

        S3Object s3Object = new S3Object();
        s3Object.setObjectContent(new StringInputStream("Lorem ipsum dolor sit amet."));
        when(this.amazonS3.getObject((String) any(), (String) any())).thenReturn(s3Object);

        StudentAttachmentDTO studentAttachmentDTO1 = new StudentAttachmentDTO();
        studentAttachmentDTO1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        studentAttachmentDTO1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachmentDTO1.setId(123L);
        studentAttachmentDTO1.setLessonId(123L);
        studentAttachmentDTO1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        studentAttachmentDTO1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachmentDTO1.setOriginalFileName("foo.txt");
        studentAttachmentDTO1.setStoredFileName("foo.txt");
        assertEquals(27, this.studentAttachmentServiceImpl.downloadFile(studentAttachmentDTO1).length);
        verify(this.studentAttachmentRepository).findByLessonId((Long) any());
        verify(this.studentAttachmentMapper).toDto((StudentAttachment) any());
        verify(this.amazonS3).getObject((String) any(), (String) any());
    }

    /**
     * Method under test: {@link StudentAttachmentServiceImpl#downloadFile(StudentAttachmentDTO)}
     */
    @Test
    void testDownloadFile2() throws SdkClientException {
        Course course = new Course();
        course.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        course.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course.setId(123L);
        course.setInstructors(new HashSet<>());
        course.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        course.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course.setName("Name");
        course.setNumberOfLessons((short) 1);
        course.setStudents(new HashSet<>());

        User user = new User();
        user.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        user.setCreatedDate(null);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        user.setModifiedDate(null);
        user.setPassword("iloveyou");
        user.setSecondName("Second Name");
        user.setUserRoles("User Roles");

        Student student = new Student();
        student.setCourses(new HashSet<>());
        student.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        student.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        student.setId(123L);
        student.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        student.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        student.setUser(user);

        Lesson lesson = new Lesson();
        lesson.setCourse(course);
        lesson.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        lesson.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lesson.setId(123L);
        lesson.setLessonNumber((short) 1);
        lesson.setMark((short) 1);
        lesson.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        lesson.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lesson.setStudent(student);
        lesson.setStudentAttachments(new HashSet<>());

        StudentAttachment studentAttachment = new StudentAttachment();
        studentAttachment.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        studentAttachment.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachment.setId(123L);
        studentAttachment.setLesson(lesson);
        studentAttachment.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        studentAttachment.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachment.setOriginalFileName("foo.txt");
        studentAttachment.setStoredFileName("foo.txt");
        Optional<StudentAttachment> ofResult = Optional.of(studentAttachment);
        when(this.studentAttachmentRepository.findByLessonId((Long) any())).thenReturn(ofResult);

        StudentAttachmentDTO studentAttachmentDTO = new StudentAttachmentDTO();
        studentAttachmentDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        studentAttachmentDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachmentDTO.setId(123L);
        studentAttachmentDTO.setLessonId(123L);
        studentAttachmentDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        studentAttachmentDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachmentDTO.setOriginalFileName("foo.txt");
        studentAttachmentDTO.setStoredFileName("foo.txt");
        when(this.studentAttachmentMapper.toDto((StudentAttachment) any())).thenReturn(studentAttachmentDTO);
        when(this.amazonS3.getObject((String) any(), (String) any()))
                .thenThrow(new SystemException("An error occurred", ErrorCode.INTERNAL_SERVER_ERROR));

        StudentAttachmentDTO studentAttachmentDTO1 = new StudentAttachmentDTO();
        studentAttachmentDTO1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        studentAttachmentDTO1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachmentDTO1.setId(123L);
        studentAttachmentDTO1.setLessonId(123L);
        studentAttachmentDTO1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        studentAttachmentDTO1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachmentDTO1.setOriginalFileName("foo.txt");
        studentAttachmentDTO1.setStoredFileName("foo.txt");
        assertThrows(SystemException.class, () -> this.studentAttachmentServiceImpl.downloadFile(studentAttachmentDTO1));
        verify(this.studentAttachmentRepository).findByLessonId((Long) any());
        verify(this.studentAttachmentMapper).toDto((StudentAttachment) any());
        verify(this.amazonS3).getObject((String) any(), (String) any());
    }

    /**
     * Method under test: {@link StudentAttachmentServiceImpl#deleteFile(StudentAttachmentDTO)}
     */
    @Test
    void testDeleteFile() throws SdkClientException {
        doNothing().when(this.amazonS3).deleteObject((String) any(), (String) any());

        StudentAttachmentDTO studentAttachmentDTO = new StudentAttachmentDTO();
        studentAttachmentDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        studentAttachmentDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachmentDTO.setId(123L);
        studentAttachmentDTO.setLessonId(123L);
        studentAttachmentDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        studentAttachmentDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachmentDTO.setOriginalFileName("foo.txt");
        studentAttachmentDTO.setStoredFileName("foo.txt");
        assertEquals("foo.txt removed ...", this.studentAttachmentServiceImpl.deleteFile(studentAttachmentDTO));
        verify(this.amazonS3).deleteObject((String) any(), (String) any());
    }

    /**
     * Method under test: {@link StudentAttachmentServiceImpl#deleteFile(StudentAttachmentDTO)}
     */
    @Test
    void testDeleteFile2() throws SdkClientException {
        doThrow(new SystemException("An error occurred", ErrorCode.INTERNAL_SERVER_ERROR)).when(this.amazonS3)
                .deleteObject((String) any(), (String) any());

        StudentAttachmentDTO studentAttachmentDTO = new StudentAttachmentDTO();
        studentAttachmentDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        studentAttachmentDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachmentDTO.setId(123L);
        studentAttachmentDTO.setLessonId(123L);
        studentAttachmentDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        studentAttachmentDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachmentDTO.setOriginalFileName("foo.txt");
        studentAttachmentDTO.setStoredFileName("foo.txt");
        assertThrows(SystemException.class, () -> this.studentAttachmentServiceImpl.deleteFile(studentAttachmentDTO));
        verify(this.amazonS3).deleteObject((String) any(), (String) any());
    }

    /**
     * Method under test: {@link StudentAttachmentServiceImpl#findByLessonIdOrThrow(Long)}
     */
    @Test
    void testFindByLessonIdOrThrow() throws SystemException {
        Course course = new Course();
        course.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        course.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course.setId(123L);
        course.setInstructors(new HashSet<>());
        course.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        course.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        course.setName("Name");
        course.setNumberOfLessons((short) 1);
        course.setStudents(new HashSet<>());

        User user = new User();
        user.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        user.setCreatedDate(null);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        user.setModifiedDate(null);
        user.setPassword("iloveyou");
        user.setSecondName("Second Name");
        user.setUserRoles("User Roles");

        Student student = new Student();
        student.setCourses(new HashSet<>());
        student.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        student.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        student.setId(123L);
        student.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        student.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        student.setUser(user);

        Lesson lesson = new Lesson();
        lesson.setCourse(course);
        lesson.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        lesson.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lesson.setId(123L);
        lesson.setLessonNumber((short) 1);
        lesson.setMark((short) 1);
        lesson.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        lesson.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        lesson.setStudent(student);
        lesson.setStudentAttachments(new HashSet<>());

        StudentAttachment studentAttachment = new StudentAttachment();
        studentAttachment.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        studentAttachment.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachment.setId(123L);
        studentAttachment.setLesson(lesson);
        studentAttachment.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        studentAttachment.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachment.setOriginalFileName("foo.txt");
        studentAttachment.setStoredFileName("foo.txt");
        Optional<StudentAttachment> ofResult = Optional.of(studentAttachment);
        when(this.studentAttachmentRepository.findByLessonId((Long) any())).thenReturn(ofResult);

        StudentAttachmentDTO studentAttachmentDTO = new StudentAttachmentDTO();
        studentAttachmentDTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        studentAttachmentDTO.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachmentDTO.setId(123L);
        studentAttachmentDTO.setLessonId(123L);
        studentAttachmentDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        studentAttachmentDTO.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        studentAttachmentDTO.setOriginalFileName("foo.txt");
        studentAttachmentDTO.setStoredFileName("foo.txt");
        when(this.studentAttachmentMapper.toDto((StudentAttachment) any())).thenReturn(studentAttachmentDTO);
        assertSame(studentAttachmentDTO, this.studentAttachmentServiceImpl.findByLessonIdOrThrow(123L));
        verify(this.studentAttachmentRepository).findByLessonId((Long) any());
        verify(this.studentAttachmentMapper).toDto((StudentAttachment) any());
    }
}

