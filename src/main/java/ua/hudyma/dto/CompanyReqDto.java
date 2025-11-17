package ua.hudyma.dto;

import ua.hudyma.enums.CompanyActivityType;
import ua.hudyma.enums.CompanySize;
import ua.hudyma.enums.CompanySpecialtyType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public record CompanyReqDto(
        String companyName,
        Set<CompanySpecialtyType> companySpecialtyTypes,
        String companyDescription,
        String website,
        String phone,
        LocalDateTime accountVerifiedOn,
        CompanySize companySize,
        Set<CompanyActivityType> companyActivityTypes,
        LocalDate yearEstablished) {
}
