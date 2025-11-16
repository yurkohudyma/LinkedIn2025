package ua.hudyma.dto;

import ua.hudyma.enums.EmploymentType;

public record VacancyReqDto(
        String companyCode,
        EmploymentType employmentType,
        String position,
        String description,
        String requirements,
        String niceToHave,
        String offer
) {
}
