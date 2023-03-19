package com.sombra.promotion.service.impl;

import com.sombra.promotion.config.SystemProperties;
import com.sombra.promotion.config.error.ErrorCode;
import com.sombra.promotion.config.error.SystemException;
import com.sombra.promotion.domain.Lesson;
import com.sombra.promotion.dto.*;
import com.sombra.promotion.repository.LessonRepository;
import com.sombra.promotion.service.CourseService;
import com.sombra.promotion.service.LessonService;
import com.sombra.promotion.service.StudentService;
import com.sombra.promotion.service.mapper.LessonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService {

    private final Logger log = LoggerFactory.getLogger(LessonServiceImpl.class);

    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;
    private final StudentService studentService;
    private final CourseService courseService;
    private final SystemProperties systemProperties;

    public LessonServiceImpl(final LessonRepository lessonRepository, final LessonMapper lessonMapper,
                             final StudentService studentService, final CourseService courseService,
                             final SystemProperties systemProperties) {
        this.lessonRepository = lessonRepository;
        this.lessonMapper = lessonMapper;
        this.studentService = studentService;
        this.courseService = courseService;
        this.systemProperties = systemProperties;
    }

    @Override
    public LessonDTO save(LessonDTO lessonDTO) {
        log.debug("Request to save Lesson : {}", lessonDTO);
        Optional<Lesson> lessonFromDb = lessonRepository.findByStudentIdAndCourseIdAndLessonNumber(lessonDTO.getStudentId(), lessonDTO.getCourseId(), lessonDTO.getLessonNumber());
        if (lessonFromDb.isPresent()) {
            throw new SystemException(String.format("Lesson with number %s, for student %s and course %s already exist.",
                    lessonFromDb.get().getLessonNumber(), lessonFromDb.get().getStudent().getUser().getEmail(),
                    lessonFromDb.get().getCourse().getName()),
                    ErrorCode.BAD_REQUEST);
        }
        Lesson lesson = lessonMapper.toEntity(lessonDTO);
        lesson = lessonRepository.save(lesson);
        return lessonMapper.toDto(lesson);
    }

    @Override
    public LessonDTO update(final LessonDTO lessonDTO) {
        log.debug("Request to save Lesson : {}", lessonDTO);
        Optional<Lesson> lessonFromDb = lessonRepository.findByStudentIdAndCourseIdAndLessonNumber(lessonDTO.getStudentId(), lessonDTO.getCourseId(), lessonDTO.getLessonNumber());
        if (lessonFromDb.isEmpty()) {
            throw new SystemException(String.format("Lesson with number %s, for student %s and course %s not exist.",
                    lessonFromDb.get().getLessonNumber(), lessonFromDb.get().getStudent().getUser().getEmail(),
                    lessonFromDb.get().getCourse().getName()),
                    ErrorCode.BAD_REQUEST);
        }
        Lesson lesson = lessonMapper.toEntity(lessonDTO);
        lesson = lessonRepository.save(lesson);
        return lessonMapper.toDto(lesson);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonDTO> findAll() {
        log.debug("Request to get all Students");
        return lessonRepository.findAll().stream().map(lessonMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LessonDTO> findOne(Long id) {
        log.debug("Request to get Lesson : {}", id);
        return lessonRepository.findById(id).map(lessonMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonDTO> getAllLessonsByStudentId(final Long studentId) {
        log.debug("Request to get all Lessons for student with id: {}", studentId);
        studentService.getStudentOrThrow(studentId);
        return lessonMapper.toDto(lessonRepository.findByStudentId(studentId));
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Lesson : {}", id);
        lessonRepository.deleteById(id);
    }

    @Override
    public CoursePassDTO calculateFinalMark(StudentCourseDTO studentCourseDTO) {
        final CourseDTO courseDTO = courseService.findByIdOrThrow(studentCourseDTO.getCourseId());
        final StudentDTO studentDTO = studentService.getStudentOrThrow(studentCourseDTO.getStudentId());
        final List<Lesson> lessons = lessonRepository.findByStudentIdAndCourseId(studentDTO.getId(), courseDTO.getId());
        final Double numberOfLessons = Double.valueOf(courseDTO.getNumberOfLessons());
        if (lessons.size() != numberOfLessons) {
            throw new SystemException(String.format("Not able to calculate final mark for course: %s and student: %s, because student should have %d lessons but it is %d",
                    courseDTO.getName(), studentDTO.getFirstName() + studentDTO.getSecondName(), numberOfLessons.intValue(), lessons.size()),
                    ErrorCode.BAD_REQUEST);
        }
        return getStudentPassedCourseInfo(lessons);
    }

    private CoursePassDTO getStudentPassedCourseInfo(final List<Lesson> lessons) {
        final CoursePassDTO coursePassDTO = new CoursePassDTO();
        final Double finalMark = lessons.stream()
                .mapToDouble(lessonDTO -> Double.valueOf(lessonDTO.getMark()))
                .average()
                .getAsDouble();
        coursePassDTO.setFinalMark(finalMark);
        if (finalMark.compareTo(systemProperties.getPassCourseThresholdValue()) < 0){
            coursePassDTO.setCoursePassed(false);
            coursePassDTO.setMessage("You didn't pass course, average mark should be above 8.");
        } else {
            coursePassDTO.setCoursePassed(true);
            coursePassDTO.setMessage("You passed course, congratulations.");
        }
        return coursePassDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public LessonDTO findByIdOrThrow(final Long id) throws SystemException {
        log.debug("Request to get Course : {}", id);
        return lessonRepository.findById(id)
                .map(lessonMapper::toDto)
                .orElseThrow(() -> new SystemException(String.format("Lesson with id: %d doesn't exist.", id), ErrorCode.BAD_REQUEST));
    }
}
