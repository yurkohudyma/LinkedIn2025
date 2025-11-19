package ua.hudyma.dto;

import ua.hudyma.enums.SkillCategory;

public record UserSkillRespDto(
        String skillName,
        SkillCategory skillCategory,
        String proficiencyLevel
) {
}
