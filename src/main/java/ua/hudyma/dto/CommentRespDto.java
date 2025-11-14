package ua.hudyma.dto;

import ua.hudyma.enums.CommentStatus;

import java.time.LocalDateTime;

public record CommentRespDto(
        String commentCode,
        String commentAuthor,
        String postAuthor,
        LocalDateTime publishedOn,
        Boolean wasEdited,
        String text,
        CommentStatus status

) {


}
