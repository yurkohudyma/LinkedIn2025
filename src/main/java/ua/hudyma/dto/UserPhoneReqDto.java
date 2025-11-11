package ua.hudyma.dto;

import ua.hudyma.enums.PhoneType;

public record UserPhoneReqDto(
        String phoneNumber,
        PhoneType phoneType
) {
}
