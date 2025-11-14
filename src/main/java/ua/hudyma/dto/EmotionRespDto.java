package ua.hudyma.dto;

import ua.hudyma.enums.EmotionType;

public record EmotionRespDto(
        String emotionCode,
        EmotionType emotionType,
        String emotionAuthor,
        String postHeading,
        String commentHeading
) {
}
