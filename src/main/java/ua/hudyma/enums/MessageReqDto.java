package ua.hudyma.enums;

public record MessageReqDto (
        String messageCode,
        String fromUserCode,
        String toUserCode,
        String messageText
) {}
