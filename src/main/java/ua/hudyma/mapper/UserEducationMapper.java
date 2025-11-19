package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.profile.Education;
import ua.hudyma.dto.UserEducationReqDto;
import ua.hudyma.dto.UserEducationRespDto;

@Component
public class UserEducationMapper extends BaseMapper<UserEducationRespDto, Education, UserEducationReqDto> {
    @Override
    public UserEducationRespDto toDto(Education education) {
        return new UserEducationRespDto(
                education.getInstitutionName(),
                education.getAddress(),
                education.getDegreeType()
        );
    }

    @Override
    public Education toEntity(UserEducationReqDto dto) {
        var edu = new Education();
        edu.setInstitutionName(dto.institutionName());
        edu.setAddress(dto.address());
        edu.setDegreeType(dto.degreeType());
        return edu;
    }
}
