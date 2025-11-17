package ua.hudyma.dto;

import ua.hudyma.enums.EmploymentType;

public record VacancyRespDto(
        String vacancyCode,
        String companyName,
        EmploymentType employmentType,
        String position,
        String description,
        String requirements,
        String niceToHave,
        String offer,
        String responsibilities,
        String recruiter
) {
}
