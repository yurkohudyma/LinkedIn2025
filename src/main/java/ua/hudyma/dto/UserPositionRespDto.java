package ua.hudyma.dto;

import ua.hudyma.enums.EmploymentType;

public record UserPositionRespDto(
        String positionName,
        EmploymentType employmentType,
        String organisationName
) {
}
