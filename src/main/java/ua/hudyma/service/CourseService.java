package ua.hudyma.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.learning.Course;
import ua.hudyma.dto.CourseReqDto;
import ua.hudyma.dto.CourseRespDto;
import ua.hudyma.dto.QuizReqDto;
import ua.hudyma.dto.UserSkillReqDto;
import ua.hudyma.mapper.CourseMapper;
import ua.hudyma.repository.CourseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public String addCourse(CourseReqDto dto) {
        var course = courseMapper.toEntity(dto);
        courseRepository.save(course);
        var msg = String.format("Course %s has BEEN CREATED", course.getCourseName());
        log.info(msg);
        return msg;
    }

    @Transactional
    public String editQuizzes(String courseCode, List<QuizReqDto> dtoList) {
        var course = getCourse(courseCode);
        course.getQuizList().addAll(courseMapper.mapDtoListToQuizList(dtoList));
        var msg = String.format("Skill list for Course %s has been UPDATED",
                course.getCourseName());
        log.info(msg);
        return msg;
    }

    @Transactional
    public String editSkills(String courseCode, List<UserSkillReqDto> dtoList) {
        var course = getCourse(courseCode);
        course.getSkillList().addAll(courseMapper.mapDtoListToSkillList(dtoList));
        var msg = String.format("Skill list for Course %s has been UPDATED",
                course.getCourseName());
        log.info(msg);
        return msg;
    }

    @Transactional
    public CourseRespDto fetchCourseDto(String courseCode) {
        return courseMapper.toDto(getCourse(courseCode));
    }

    public Course getCourse(String courseCode) {
        return courseRepository.findByCourseCode(courseCode)
                .orElseThrow( () -> new EntityNotFoundException("Course " + courseCode + " NOT FOUND"));
    }
}
