package ua.hudyma.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.learning.Course;
import ua.hudyma.domain.learning.Quiz;
import ua.hudyma.dto.QuizReqDto;
import ua.hudyma.dto.QuizRespDto;
import ua.hudyma.exception.DtoObligatoryFieldsAreMissingException;
import ua.hudyma.repository.CourseRepository;
import ua.hudyma.service.CourseService;

@Component
@RequiredArgsConstructor
public class QuizMapper extends BaseMapper<QuizRespDto, Quiz, QuizReqDto> {
    private final CourseRepository courseRepository;
    @Override
    public QuizRespDto toDto(Quiz quiz) {
        return new QuizRespDto(
                quiz.getQuizCode(),
                quiz.getCourse().getCourseName()
        );
    }

    @Override
    public Quiz toEntity(QuizReqDto dto) {
        var courseCode = dto.courseCode();
        if (courseCode == null || courseCode.isEmpty()){
            throw new DtoObligatoryFieldsAreMissingException("Course code for Topic is VOID");
        }
        var course = getCourse(courseCode);
        var quiz = new Quiz();
        quiz.setCourse(course);
        return quiz;
    }

    private Course getCourse(String courseCode) {
        return courseRepository.findByCourseCode(courseCode)
                .orElseThrow( () -> new EntityNotFoundException
                        ("Course " + courseCode + " NOT FOUND"));
    }
}
