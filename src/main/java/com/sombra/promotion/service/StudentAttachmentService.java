package com.sombra.promotion.service;

import com.sombra.promotion.config.error.SystemException;
import com.sombra.promotion.dto.StudentAttachmentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface StudentAttachmentService {
    String uploadFile(MultipartFile file, final Long courseId);

    Optional<StudentAttachmentDTO> findOne(Long id);

    byte[] downloadFile(StudentAttachmentDTO attachmentDTO);

    String deleteFile(StudentAttachmentDTO fileName);

    StudentAttachmentDTO findByLessonIdOrThrow(Long id) throws SystemException;
}
