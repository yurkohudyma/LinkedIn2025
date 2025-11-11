package ua.hudyma.dto;

import java.util.List;

public record UserRespDto(
        String userCode,
        String email,
        String fullName,
        String profileUrl,
        String address,
        String birthDate,
        List<UserPhoneReqDto> phoneList,
        List<UserEducationReqDto> educationList,
        List<UserMessengerReqDto> messengerList,
        List<UserWebsiteReqDto> websiteList,
        List<UserPositionReqDto> positionList
) {
}
