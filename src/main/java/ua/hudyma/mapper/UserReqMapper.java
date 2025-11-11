package ua.hudyma.mapper;

import ua.hudyma.domain.*;
import ua.hudyma.dto.*;

import java.util.List;

public interface UserReqMapper {

    List<Education> mapEducationDtoListToEntityList(List<UserEducationReqDto> dtoList);
    List<Phone> mapPhoneDtoListToEntityList(List<UserPhoneReqDto> dtoList);
    List<Position> mapPositionDtoListToEntityList(List<UserPositionReqDto> dtoList);
    List<Messenger> mapMessengerDtoListToEntityList(List<UserMessengerReqDto> dtoList);
    List<Website> mapWebsiteDtoListToEntityList(List<UserWebsiteReqDto> dtoList);

    List<UserEducationReqDto> mapEducationListToDtoList (List<Education>educationList);
    List<UserPhoneReqDto> mapPhoneListToDtoList (List<Phone>phoneList);
    List<UserMessengerReqDto> mapMessengerListToDtoList (List<Messenger> messengerList);
    List<UserWebsiteReqDto> mapWebsiteListToDtoList (List<Website> websiteList);
    List<UserPositionReqDto> mapPositionListToDtoList (List<Position> positionList);

}
