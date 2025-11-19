package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.learning.*;
import ua.hudyma.domain.profile.Skill;
import ua.hudyma.dto.*;
import ua.hudyma.enums.AccessibilityType;
import ua.hudyma.exception.DtoObligatoryFieldsAreMissingException;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CourseMapper extends BaseMapper<CourseRespDto, Course, CourseReqDto> {
    private final UserSkillMapper skillMapper;
    private final ReviewMapper reviewMapper;
    private final CertificateMapper certificateMapper;
    private final TopicMapper topicMapper;
    private final QuizMapper quizMapper;

    @Override
    public CourseRespDto toDto(Course course) {
        return new CourseRespDto(
                course.getCourseCode(),
                course.getCourseName(),
                course.getDescription(),
                course.getAccessibilityTypeSet(),
                course.getInstructor(),
                course.getComplexityLevel(),
                mapSkillListToDto(course.getSkillList()),
                mapQuizListToDtoList(course.getQuizList()),
                mapTopicListToDtoList(course.getTopicList()),
                mapCertListToDtoList(course.getCertificateList()),
                mapReviewListToDtoList(course.getReviewList())
        );
    }

    private List<ReviewRespDto> mapReviewListToDtoList(List<Review> reviewList) {
        return reviewMapper.toDtoList(reviewList);
    }

    private List<CertificateRespDto> mapCertListToDtoList(List<Certificate> certificateList) {
        return certificateMapper.toDtoList(certificateList);
    }

    private List<TopicRespDto> mapTopicListToDtoList(List<Topic> topicList) {
        return topicMapper.toDtoList(topicList);
    }

    private List<QuizRespDto> mapQuizListToDtoList(List<Quiz> quizList) {
        return quizMapper.toDtoList(quizList);
    }

    public List<UserSkillRespDto> mapSkillListToDto (List<Skill> skillList){
        return skillMapper.toDtoList(skillList);
    }

    public List<Skill> mapEntityListToSkillDtoList(List<UserSkillReqDto> skillReqDtoList){
        return skillMapper.toEntityList(skillReqDtoList);
    }

    @Override
    public Course toEntity(CourseReqDto dto) {
        var course = new Course();
        course.setCourseName(dto.courseName());
        course.setDescription(dto.description());
        course.setInstructor(dto.instructor());
        course.setAccessibilityTypeSet(toEnumSet(
                dto.accessibilityTypeSet(),
                AccessibilityType.class));
        course.setComplexityLevel(dto.complexityLevel());
        return course;
    }

    private <E extends Enum<E>> Set<E> toEnumSet(Set<E> dtoSet,
                                                 Class<E> enumClass) {
        if (dtoSet == null) throw new DtoObligatoryFieldsAreMissingException
                ("Dto Set is NULL, cannot proceed");
        return dtoSet.stream()
                .collect(Collectors.toCollection(
                        () -> EnumSet
                                .noneOf(enumClass)));
    }
}
