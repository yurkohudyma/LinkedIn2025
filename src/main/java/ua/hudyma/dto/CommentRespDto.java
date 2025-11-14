package ua.hudyma.dto;

import ua.hudyma.enums.CommentStatus;

import java.time.LocalDateTime;

public record CommentRespDto(
        String commentCode,
        String commentAuthor,
        String postAuthor,
        LocalDateTime publishedOn,
        LocalDateTime updatedOn,
        String text,
        CommentStatus status,
        boolean wasEdited

) {


}
