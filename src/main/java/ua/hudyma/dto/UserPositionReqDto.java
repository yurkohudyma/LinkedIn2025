package ua.hudyma.dto;

import ua.hudyma.enums.EmploymentType;

public record UserPositionReqDto(
        String positionName,
        EmploymentType employmentType,
        String organisationName
) {
}
