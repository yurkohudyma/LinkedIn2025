package ua.hudyma.dto;

import ua.hudyma.enums.DegreeType;

public record UserReqDto(
        String email,
        String fullName,
        String address,
        Integer monthOfBirth,
        Integer dayOfBirth
) {}
