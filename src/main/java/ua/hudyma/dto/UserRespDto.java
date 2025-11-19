package ua.hudyma.dto;

import java.util.List;

public record UserRespDto(
        String userCode,
        String email,
        String fullName,
        String profileUrl,
        String address,
        String birthDate,
        List<UserPhoneRespDto> phoneList,
        List<UserEducationRespDto> educationList,
        List<UserMessengerRespDto> messengerList,
        List<UserWebsiteRespDto> websiteList,
        List<UserPositionRespDto> positionList,
        List<UserSkillRespDto> userSkillReqDtos) {
}
