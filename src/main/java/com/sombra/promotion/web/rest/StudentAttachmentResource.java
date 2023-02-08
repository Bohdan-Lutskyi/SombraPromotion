package com.sombra.promotion.web.rest;


import com.sombra.promotion.dto.StudentAttachmentDTO;
import com.sombra.promotion.service.StudentAttachmentService;
import com.sombra.promotion.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentAttachmentResource {

    private final Logger log = LoggerFactory.getLogger(CourseResource.class);

    private final StudentAttachmentService studentAttachmentService;

    public StudentAttachmentResource(final StudentAttachmentService studentAttachmentService) {
        this.studentAttachmentService = studentAttachmentService;
    }

    @PostMapping("/attachment/upload/{lessonId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT')")
    public ResponseEntity<String> uploadFile(@PathVariable(value = "lessonId") final Long lessonId,
                                             @RequestParam(value = "file") final MultipartFile file) {
        log.debug("REST request to upload file with name: {}", file.getOriginalFilename());
        return new ResponseEntity<>(studentAttachmentService.uploadFile(file, lessonId), HttpStatus.OK);
    }

    @GetMapping("/attachment/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR', 'STUDENT')")
    public ResponseEntity<StudentAttachmentDTO> getStudentAttachment(@PathVariable Long id) {
        log.debug("REST request to get Course : {}", id);
        Optional<StudentAttachmentDTO> studentAttachmentDTO = studentAttachmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(studentAttachmentDTO);
    }

    @PostMapping("/attachment/download")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ByteArrayResource> downloadFile(@RequestBody final StudentAttachmentDTO attachmentDTO) {
        log.debug("REST request to get file with name: {}", attachmentDTO.getOriginalFileName());
        byte[] data = studentAttachmentService.downloadFile(attachmentDTO);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; fileName=\"" + attachmentDTO.getOriginalFileName() + "\"")
                .body(resource);
    }

    @DeleteMapping("/attachment/delete")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT')")
    public ResponseEntity<String> deleteFile(@RequestBody final StudentAttachmentDTO attachmentDTO) {
        log.debug("REST request to delete file with name: {}", attachmentDTO.getOriginalFileName());
        return new ResponseEntity<>(studentAttachmentService.deleteFile(attachmentDTO), HttpStatus.OK);
    }
}
