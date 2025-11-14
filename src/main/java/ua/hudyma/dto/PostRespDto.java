package ua.hudyma.dto;

import ua.hudyma.enums.PostStatus;

import java.time.LocalDateTime;

public record PostRespDto(
        String postCode,
        String postAuthor,
        LocalDateTime sentOn,
        LocalDateTime updatedOn,
        PostStatus status,
        boolean wasEdited,
        String text
) {
}
