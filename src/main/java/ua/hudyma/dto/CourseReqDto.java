package ua.hudyma.dto;

import ua.hudyma.domain.learning.ComplexityLevel;
import ua.hudyma.enums.AccessibilityType;

import java.util.Set;

public record CourseReqDto(
        String courseName,
        String description,
        Set<AccessibilityType> accessibilityTypeSet,
        String instructor,
        ComplexityLevel complexityLevel

) {
}
