package ua.hudyma.dto;

public record VacancyMatcherDto(
        String companyName,
        String position,
        String employmentTypeLabel,
        String recruiterName,
        String vacancyCode
) {
}
