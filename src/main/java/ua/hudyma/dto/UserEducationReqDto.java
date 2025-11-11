package ua.hudyma.dto;

import ua.hudyma.enums.DegreeType;

public record UserEducationReqDto(
        String institutionName,
        String address,
        DegreeType degreeType
) {
}
