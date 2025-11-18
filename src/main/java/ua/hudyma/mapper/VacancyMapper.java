package ua.hudyma.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.job.Company;
import ua.hudyma.domain.job.Vacancy;
import ua.hudyma.dto.VacancyMatcherDto;
import ua.hudyma.dto.VacancyReqDto;
import ua.hudyma.dto.VacancyRespDto;
import ua.hudyma.exception.DtoObligatoryFieldsAreMissingException;
import ua.hudyma.repository.CompanyRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VacancyMapper extends BaseMapper<VacancyRespDto, Vacancy> {
    private final CompanyRepository companyRepository;

    public List<VacancyMatcherDto> mapEntityListToVacancyMatcherDtoList(
            List<Vacancy> list) {
        return list.stream().map(vacancy ->
                new VacancyMatcherDto(
                        vacancy.getCompany().getCompanyName(),
                        vacancy.getPosition(),
                        vacancy.getEmploymentType().getLabel(),
                        vacancy.getRecruiter(),
                        vacancy.getVacancyCode())
        ).toList();
    }

    public Vacancy mapReqDtoToEntity(VacancyReqDto dto) {
        var companyCode = dto.companyCode();
        if (companyCode == null || companyCode.isEmpty()) {
            throw new DtoObligatoryFieldsAreMissingException("CompanyCode is Null or Empty, fail-safing");
        }
        var company = getCompany(companyCode);
        var vacancy = new Vacancy();
        vacancy.setEmploymentType(dto.employmentType());
        vacancy.setPosition(dto.position());
        vacancy.setDescription(dto.description());
        vacancy.setRequirements(dto.requirements());
        vacancy.setNiceToHave(dto.niceToHave());
        vacancy.setResponsibilities(dto.responsibilities());
        vacancy.setOffer(dto.offer());
        vacancy.setRecruiter(dto.recruiter());
        vacancy.setCompany(company);
        return vacancy;
    }

    private Company getCompany(String companyCode) {
        return companyRepository.findByCompanyCode(companyCode)
                .orElseThrow(() ->
                        new EntityNotFoundException("Company " + companyCode + " NOT found"));
    }

    @Override
    public VacancyRespDto toDto(Vacancy vacancy) {
        return new VacancyRespDto(
                vacancy.getVacancyCode(),
                vacancy.getCompany().getCompanyName(),
                vacancy.getEmploymentType(),
                vacancy.getPosition(),
                vacancy.getDescription(),
                vacancy.getRequirements(),
                vacancy.getNiceToHave(),
                vacancy.getOffer(),
                vacancy.getResponsibilities(),
                vacancy.getRecruiter()
        );
    }

    @Override
    public Vacancy toEntity(VacancyRespDto dto) {
        return null;
    }
}
