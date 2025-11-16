package ua.hudyma.mapper;

import jakarta.persistence.Column;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.job.Company;
import ua.hudyma.dto.CompanyReqDto;
import ua.hudyma.dto.CompanyRespDto;
import ua.hudyma.enums.CompanySpecialtyType;

import java.util.Set;

@Component
public class CompanyMapper extends BaseMapper<CompanyRespDto, Company> {
    public Company mapReqDtoToEntity(CompanyReqDto dto) {
        //todo introduce dto fields checkup
        var company = new Company();
        company.setCompanyName(dto.companyName());
        company.setCompanySpecialtyTypes(mapToEnumSet(dto.companySpecialtyTypes()));
        company.setCompanyDescription(dto.companyDescription());
        company.setPhone(dto.phone());
        company.setAccountVerifiedOn(dto.accountVerifiedOn());
        company.setCompanySize(dto.companySize());
        company.setCompanyActivityType(mapToEnumSet(dto.companyActivityTypes()));
        company.setYearEstablished(dto.yearEstablished());
        return company;
    }

    private <E, I> Set<E> mapToEnumSet(Set<I> companySpecialtyTypes) {
        return null;
    }

    @Override
    protected CompanyRespDto toDto(Company company) {
        return null;
    }

    @Override
    protected Company toEntity(CompanyRespDto dto) {
        return null;
    }
}
