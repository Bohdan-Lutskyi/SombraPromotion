package com.sombra.promotion.service;

import com.sombra.promotion.config.error.SystemException;
import com.sombra.promotion.dto.StudentAttachmentDTO;
import org.springframework.web.multipart.MultipartFile;

public interface StudentAttachmentService {
    String uploadFile(MultipartFile file, final Long courseId);

    byte[] downloadFile(StudentAttachmentDTO attachmentDTO);

    String deleteFile(StudentAttachmentDTO fileName);

    StudentAttachmentDTO findByLessonIdOrThrow(Long id) throws SystemException;
}
