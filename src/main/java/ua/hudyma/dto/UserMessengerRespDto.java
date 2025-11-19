package ua.hudyma.dto;

import ua.hudyma.enums.MessengerType;

public record UserMessengerRespDto(
        String messengerUserName,
        MessengerType messengerType
) {
}
