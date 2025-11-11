package ua.hudyma.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ua.hudyma.enums.SkillCategory;

@Embeddable
@Data
public class Skill {
    private String skillName;
    @Enumerated(EnumType.STRING)
    private SkillCategory skillCategory;
    private String proficiencyLevel;

}
