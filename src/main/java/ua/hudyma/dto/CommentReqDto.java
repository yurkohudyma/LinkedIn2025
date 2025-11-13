package ua.hudyma.dto;

public record CommentReqDto(
        String postCode,
        String authorUserCode,
        String text
) {
}
