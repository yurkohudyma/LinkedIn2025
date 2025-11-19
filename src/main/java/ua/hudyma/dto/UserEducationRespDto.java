package ua.hudyma.dto;

import ua.hudyma.enums.DegreeType;

public record UserEducationRespDto(
        String institutionName,
        String address,
        DegreeType degreeType
) {
}
