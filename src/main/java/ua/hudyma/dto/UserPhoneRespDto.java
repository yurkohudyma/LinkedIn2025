package ua.hudyma.dto;

import ua.hudyma.enums.PhoneType;

public record UserPhoneRespDto(
        String phoneNumber,
        PhoneType phoneType
) {
}
