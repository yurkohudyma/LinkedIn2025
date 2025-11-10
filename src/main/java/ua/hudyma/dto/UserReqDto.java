package ua.hudyma.dto;

public record UserReqDto(
        String email,
        String fullName,
        String address,
        Integer monthOfBirth,
        Integer dayOfBirth
) {
}
