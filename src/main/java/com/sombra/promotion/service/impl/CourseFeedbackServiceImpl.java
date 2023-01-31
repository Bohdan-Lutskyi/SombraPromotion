package com.sombra.promotion.service.impl;

import com.sombra.promotion.domain.CourseFeedback;
import com.sombra.promotion.dto.CourseFeedbackDTO;
import com.sombra.promotion.repository.CourseFeedbackRepository;
import com.sombra.promotion.service.CourseFeedbackService;
import com.sombra.promotion.service.CourseService;
import com.sombra.promotion.service.StudentService;
import com.sombra.promotion.service.mapper.CourseFeedbackMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseFeedbackServiceImpl implements CourseFeedbackService {

    private final Logger log = LoggerFactory.getLogger(CourseFeedbackServiceImpl.class);

    private final CourseFeedbackRepository courseFeedbackRepository;
    private final CourseFeedbackMapper courseFeedbackMapper;
    private final CourseService courseService;
    private final StudentService studentService;

    public CourseFeedbackServiceImpl(final CourseFeedbackRepository courseFeedbackRepository,
                                     final CourseFeedbackMapper courseFeedbackMapper,
                                     final CourseService courseService,
                                     final StudentService studentService) {
        this.courseFeedbackRepository = courseFeedbackRepository;
        this.courseFeedbackMapper = courseFeedbackMapper;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Override
    public CourseFeedbackDTO save(final CourseFeedbackDTO courseFeedbackDTO) {
        log.debug("Request to save CourseFeedback : {}", courseFeedbackDTO);
        verifyCourseFeedback(courseFeedbackDTO);
        CourseFeedback courseFeedback = courseFeedbackMapper.toEntity(courseFeedbackDTO);
        courseFeedback = courseFeedbackRepository.save(courseFeedback);
        return courseFeedbackMapper.toDto(courseFeedback);
    }

    private void verifyCourseFeedback(final CourseFeedbackDTO courseFeedbackDTO) {
        courseService.findByIdOrThrow(courseFeedbackDTO.getCourseId());
        studentService.getStudentOrThrow(courseFeedbackDTO.getStudentId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseFeedbackDTO> findAll() {
        log.debug("Request to get all CourseFeedbacks");
        return courseFeedbackRepository.findAll().stream().map(courseFeedbackMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CourseFeedbackDTO> findOne(final Long id) {
        log.debug("Request to get CourseFeedback : {}", id);
        return courseFeedbackRepository.findById(id).map(courseFeedbackMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CourseFeedback : {}", id);
        courseFeedbackRepository.deleteById(id);
    }

}
