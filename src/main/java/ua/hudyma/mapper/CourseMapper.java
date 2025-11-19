package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.learning.Course;
import ua.hudyma.domain.profile.Skill;
import ua.hudyma.dto.CourseReqDto;
import ua.hudyma.dto.CourseRespDto;
import ua.hudyma.dto.UserSkillReqDto;
import ua.hudyma.dto.UserSkillRespDto;
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
    @Override
    public CourseRespDto toDto(Course course) {
        return new CourseRespDto(
                course.getCourseCode(),
                course.getCourseName(),
                course.getDescription(),
                course.getAccessibilityTypeSet(),
                course.getInstructor(),
                course.getComplexityLevel(),
                mapSkillListToDto(course.getSkillList())
                //todo add all lists
        );
    }

    public List<UserSkillRespDto> mapSkillListToDto (List<Skill> skillList){
        return skillMapper.toDtoList(skillList);
    }

    public List<Skill> mapEntityListToDto (List<UserSkillReqDto> skillReqDtoList){
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
