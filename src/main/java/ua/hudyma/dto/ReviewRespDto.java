package ua.hudyma.dto;

public record ReviewRespDto(
        Integer rating,
        String reviewerName,
        String courseCode
) {
}
