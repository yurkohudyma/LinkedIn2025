package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.Education;
import ua.hudyma.dto.UserEducationReqDto;

@Component
public class UserEducationMapper extends BaseMapper<UserEducationReqDto, Education> {
    @Override
    protected UserEducationReqDto toDto(Education education) {
        return new UserEducationReqDto(
                education.getInstitutionName(),
                education.getAddress(),
                education.getDegreeType()
        );
    }

    @Override
    protected Education toEntity(UserEducationReqDto dto) {
        var edu = new Education();
        edu.setInstitutionName(dto.institutionName());
        edu.setAddress(dto.address());
        edu.setDegreeType(dto.degreeType());
        return edu;
    }
}
