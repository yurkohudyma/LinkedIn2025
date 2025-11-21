package ua.hudyma.dto;

public record ReviewReqDto(
        Integer rating,
        String reviewerUserCode,
        String courseCode,
        String reviewComment
) {
}
