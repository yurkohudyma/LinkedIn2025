package ua.hudyma.dto;

public record QuizReqDto(
        String courseCode,
        Integer numberOfQuestions
) {
}
