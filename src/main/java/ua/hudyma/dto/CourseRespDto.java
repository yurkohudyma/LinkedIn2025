package ua.hudyma.dto;

import ua.hudyma.domain.learning.ComplexityLevel;
import ua.hudyma.enums.AccessibilityType;

import java.util.List;
import java.util.Set;

public record CourseRespDto(
        String courseCode,
        String courseName,
        String description,
        Set<AccessibilityType> accessibilityTypeSet,
        String instructor,
        ComplexityLevel complexityLevel,
        List<UserSkillRespDto> skillDtoList

        // todo add all lists

) {
}
