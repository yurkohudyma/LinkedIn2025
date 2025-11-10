package ua.hudyma.mapper;

import ua.hudyma.domain.Education;
import ua.hudyma.domain.User;
import ua.hudyma.dto.UserReqDto;
import ua.hudyma.enums.UserEducationReqDto;

import java.util.List;

public interface UserMapper extends EntityMapper <UserReqDto, User> {
    List<Education> mapEducationDtoListToEntity(List<UserEducationReqDto> dtoList);
}
