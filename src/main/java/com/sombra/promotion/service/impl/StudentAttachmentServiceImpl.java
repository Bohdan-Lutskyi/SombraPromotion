package com.sombra.promotion.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.sombra.promotion.config.error.ErrorCode;
import com.sombra.promotion.config.error.SystemException;
import com.sombra.promotion.domain.StudentAttachment;
import com.sombra.promotion.dto.LessonDTO;
import com.sombra.promotion.dto.StudentAttachmentDTO;
import com.sombra.promotion.repository.StudentAttachmentRepository;
import com.sombra.promotion.service.LessonService;
import com.sombra.promotion.service.StudentAttachmentService;
import com.sombra.promotion.service.mapper.LessonMapper;
import com.sombra.promotion.service.mapper.StudentAttachmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Transactional
public class StudentAttachmentServiceImpl implements StudentAttachmentService {

    private final Logger log = LoggerFactory.getLogger(StudentAttachmentServiceImpl.class);

    @Value("${application.bucket.name}")
    private String bucketName;

    private final AmazonS3 s3Client;
    private final LessonService lessonService;
    private final LessonMapper lessonMapper;
    private final StudentAttachmentMapper studentAttachmentMapper;
    private final StudentAttachmentRepository studentAttachmentRepository;

    public StudentAttachmentServiceImpl(final AmazonS3 s3Client,
                                        final LessonService lessonService,
                                        final LessonMapper lessonMapper,
                                        final StudentAttachmentMapper studentAttachmentMapper,
                                        final StudentAttachmentRepository studentAttachmentRepository) {
        this.s3Client = s3Client;
        this.lessonService = lessonService;
        this.lessonMapper = lessonMapper;
        this.studentAttachmentMapper = studentAttachmentMapper;
        this.studentAttachmentRepository = studentAttachmentRepository;
    }

    @Override
    public String uploadFile(final MultipartFile file, final Long lessonId) {
        final LessonDTO lessonDTO = lessonService.findByIdOrThrow(lessonId);
        final File fileObj = convertMultiPartFileToFile(file);
        final String fullBucketName = bucketName + "/" + lessonId;
        final String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(fullBucketName, fileName, fileObj));
        saveStudentAttachment(file.getOriginalFilename(), fileName, lessonDTO);
        fileObj.delete();
        return "File uploaded : " + fileName;
    }

    private void saveStudentAttachment(final String name, final String fileName, final LessonDTO lessonDTO) {
        final StudentAttachment studentAttachment = new StudentAttachment();
        studentAttachment.setOriginalFileName(name);
        studentAttachment.setStoredFileName(fileName);
        studentAttachment.setLesson(lessonMapper.toEntity(lessonDTO));
        studentAttachmentRepository.save(studentAttachment);
    }


    @Override
    public byte[] downloadFile(final StudentAttachmentDTO attachmentDTO) {
        final StudentAttachmentDTO studentAttachmentDTO = findByLessonIdOrThrow(attachmentDTO.getLessonId());
        final String fullBucketName = bucketName + "/" + studentAttachmentDTO.getLessonId();
        S3Object s3Object = s3Client.getObject(fullBucketName, studentAttachmentDTO.getStoredFileName());
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String deleteFile(final StudentAttachmentDTO studentAttachmentDTO) {
        final String fullBucketName = bucketName + "/" + studentAttachmentDTO.getLessonId();
        s3Client.deleteObject(fullBucketName, studentAttachmentDTO.getStoredFileName());
        return studentAttachmentDTO.getOriginalFileName() + " removed ...";
    }

    private File convertMultiPartFileToFile(final MultipartFile file) {
        final File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }

    @Override
    @Transactional(readOnly = true)
    public StudentAttachmentDTO findByLessonIdOrThrow(final Long id) throws SystemException {
        log.debug("Request to get Course : {}", id);
        return studentAttachmentRepository.findByLessonId(id)
                .map(studentAttachmentMapper::toDto)
                .orElseThrow(() -> new SystemException(String.format("StudentAttachment with id: %d doesn't exist.", id), ErrorCode.BAD_REQUEST));
    }


}
