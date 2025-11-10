package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.Education;
import ua.hudyma.domain.User;
import ua.hudyma.dto.UserReqDto;
import ua.hudyma.enums.UserEducationReqDto;

import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public List<Education> mapEducationDtoListToEntity(List<UserEducationReqDto> dtoList) {
        return dtoList
                .stream()
                .map(dto -> {
                    var edu = new Education();
                    edu.setInstitutionName(dto.institutionName());
                    edu.setAddress(dto.address());
                    edu.setDegreeType(dto.degreeType());
                    return edu;
                })
                .toList();
    }

    @Override
    public UserReqDto toDto(User user) {
        return null;
    }

    @Override
    public List<UserReqDto> toDtoList(List<User> list) {
        return null;
    }
}
