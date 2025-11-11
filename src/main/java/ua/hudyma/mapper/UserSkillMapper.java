package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.Skill;
import ua.hudyma.dto.UserSkillReqDto;

@Component
public class UserSkillMapper extends BaseMapper <UserSkillReqDto, Skill>{
    @Override
    protected UserSkillReqDto toDto(Skill skill) {
        return new UserSkillReqDto(
                skill.getSkillName(),
                skill.getSkillCategory(),
                skill.getProficiencyLevel()
        );
    }

    @Override
    protected Skill toEntity(UserSkillReqDto dto) {
        var skill = new Skill();
        skill.setSkillCategory(dto.skillCategory());
        skill.setSkillName(dto.skillName());
        skill.setProficiencyLevel(dto.proficiencyLevel());
        return skill;
    }
}
