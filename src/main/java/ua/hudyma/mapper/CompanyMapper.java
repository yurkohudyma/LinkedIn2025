package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.job.Company;
import ua.hudyma.dto.CompanyReqDto;
import ua.hudyma.dto.CompanyRespDto;
import ua.hudyma.enums.CompanyActivityType;
import ua.hudyma.enums.CompanySpecialtyType;
import ua.hudyma.enums.LabeledEnum;
import ua.hudyma.exception.DtoObligatoryFieldsAreMissingException;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CompanyMapper extends BaseMapper<CompanyRespDto, Company> {

    public Company mapReqDtoToEntity(CompanyReqDto dto) {
        var company = new Company();
        company.setCompanyName(dto.companyName());
        company.setCompanySpecialtyTypes(
                toEnumSet(dto.companySpecialtyTypes(),
                        CompanySpecialtyType.class));
        company.setCompanyDescription(dto.companyDescription());
        company.setPhone(dto.phone());
        company.setAccountVerifiedOn(dto.accountVerifiedOn());
        company.setCompanySize(dto.companySize());
        company.setCompanyActivityType(
                toEnumSet(dto.companyActivityTypes(),
                        CompanyActivityType.class));
        company.setYearEstablished(dto.yearEstablished());
        company.setWebsite(dto.website());
        return company;
    }

    private <E extends Enum<E>> Set<E> toEnumSet(Set<E> dtoSet,
                                                 Class<E> enumClass) {
        if (dtoSet == null) throw new DtoObligatoryFieldsAreMissingException
                ("Dto Set is NULL, cannot proceed");
        return dtoSet.stream()
                .collect(Collectors.toCollection(
                        () -> EnumSet
                                .noneOf(enumClass)));
    }

    @Override
    protected CompanyRespDto toDto(Company company) {
        return new CompanyRespDto(
                company.getCompanyCode(),
                company.getCompanyName(),
                getEnumValueList(company
                        .getCompanySpecialtyTypes()),
                company.getCompanyDescription(),
                company.getWebsite(),
                company.getPhone(),
                company.getAccountVerifiedOn().toString(),
                company.getCompanySize().getLabel(),
                getEnumValueList(company
                        .getCompanyActivityType()),
                company.getYearEstablished().getYear(),
                company.getVacancyList().size(),
                company.getCommentList().size(),
                company.getEmotionList().size(),
                getTrackingUsersCount(),
                getWorkingUserCount()
        );
    }

    private int getWorkingUserCount() {
        return 0;
    }

    private int getTrackingUsersCount() {
        return 0;
    }

    private <E extends LabeledEnum> List<String> getEnumValueList(
            Set<E> types) {
        return types
                .stream()
                .map(E::getLabel)
                .toList();
    }

    @Override
    protected Company toEntity(CompanyRespDto dto) {
        return null;
    }
}
