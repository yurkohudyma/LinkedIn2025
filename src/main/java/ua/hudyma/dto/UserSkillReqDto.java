package ua.hudyma.dto;

import ua.hudyma.enums.SkillCategory;

public record UserSkillReqDto(
        String skillName,
        SkillCategory skillCategory,
        String proficiencyLevel
) {
}
