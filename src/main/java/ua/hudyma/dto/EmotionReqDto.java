package ua.hudyma.dto;

import ua.hudyma.enums.EmotionType;

public record EmotionReqDto(
        EmotionType emotionType,
        String userCode,
        String postCode,
        String commentCode
) {
}
