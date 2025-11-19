package ua.hudyma.dto;

import ua.hudyma.enums.MessageStatus;

import java.time.LocalDateTime;

public record MessageRespDto(
        String messageCode,
        String fromUserCode,
        String toUserCode,
        String messageText,
        LocalDateTime sentOn,
        LocalDateTime updatedOn,
        MessageStatus status
) {
}
