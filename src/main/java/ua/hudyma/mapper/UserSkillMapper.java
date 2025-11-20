package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.profile.Skill;
import ua.hudyma.dto.UserSkillReqDto;
import ua.hudyma.dto.UserSkillRespDto;

@Component
public class UserSkillMapper extends BaseMapper <UserSkillRespDto, Skill, UserSkillReqDto>{
    @Override
    public UserSkillRespDto toDto(Skill skill) {
        return new UserSkillRespDto(
                skill.getSkillName(),
                skill.getSkillCategory(),
                "NA"
        );
    }

    @Override
    public Skill toEntity(UserSkillReqDto dto) {
        var skill = new Skill();
        skill.setSkillCategory(dto.skillCategory());
        skill.setSkillName(dto.skillName());
        skill.setProficiencyLevel(dto.proficiencyLevel());
        return skill;
    }
}
