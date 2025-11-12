package ua.hudyma.enums;

import java.time.LocalDateTime;

public record MessageReqDto (
        String messageCode,
        String fromUserCode,
        String toUserCode,
        String messageText,
        LocalDateTime sentOn,
        LocalDateTime updatedOn,
        MessageStatus status
) {}
