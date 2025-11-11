package ua.hudyma.dto;

import ua.hudyma.enums.MessengerType;

public record UserMessengerReqDto(
        String messengerUserName,
        MessengerType messengerType
) {
}
