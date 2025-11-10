package ua.hudyma.enums;

public record UserEducationReqDto(
        String institutionName,
        String address,
        DegreeType degreeType
) {
}
