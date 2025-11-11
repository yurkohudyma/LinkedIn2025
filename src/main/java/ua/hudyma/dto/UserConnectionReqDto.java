package ua.hudyma.dto;

public record UserConnectionReqDto(
        String initUserCode,
        String connectingUserCode,
        String connectingNote
) {
}
