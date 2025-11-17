package ua.hudyma.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.job.Company;
import ua.hudyma.domain.job.Vacancy;
import ua.hudyma.dto.CompanyReqDto;
import ua.hudyma.dto.CompanyRespDto;
import ua.hudyma.dto.VacancyReqDto;
import ua.hudyma.dto.VacancyRespDto;
import ua.hudyma.mapper.CompanyMapper;
import ua.hudyma.mapper.VacancyMapper;
import ua.hudyma.repository.CompanyRepository;
import ua.hudyma.repository.VacancyRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final VacancyRepository vacancyRepository;
    private final CompanyMapper companyMapper;
    private final VacancyMapper vacancyMapper;
    private final UserService userService;

    @Transactional
    public String addTrackingUser(String companyCode, String userCode) {
        var company = getCompany(companyCode);
        var user = userService.getUser(userCode);
        user.getTrackableCompanyList().add(company);
        company.getTrackingUsersList().add(user);
        var msg = String.format("User %s now tracks Company %s",
                user.getFullName(), company.getCompanyName());
        log.info(msg);
        return msg;
    }

    public String createCompany(CompanyReqDto dto) {
        var company = companyMapper.mapReqDtoToEntity (dto);
        companyRepository.save(company);
        var msg = String.format("::: Company %s has been created", company.getCompanyName());
        log.info(msg);
        return msg;
    }

    public String createVacancy(VacancyReqDto dto) {
        var vacancy = vacancyMapper.mapReqDtoToEntity(dto);
        vacancyRepository.save(vacancy);
        var msg = String.format("::: Vacancy %s has BEEN SAVED", vacancy.getPosition());
        log.info(msg);
        return msg;
    }

    @Transactional
    public CompanyRespDto fetchCompany(String companyCode) {
        var company = getCompany(companyCode);
        return companyMapper.mapToDto(company);
    }

    private Company getCompany(String companyCode) {
        return companyRepository.findByCompanyCode(companyCode)
                .orElseThrow(
                        () -> new EntityNotFoundException
                                ("::: Company " + companyCode + " NOT EXISTENT"));
    }

    public VacancyRespDto fetchVacancy (String vacancyCode){
        return vacancyMapper.toDto(getVacancy(vacancyCode));
    }

    private Vacancy getVacancy(String vacancyCode) {
        return vacancyRepository.findByVacancyCode(vacancyCode)
                .orElseThrow(() ->
                        new EntityNotFoundException(" :: Vacancy " + vacancyCode + " NOT FOUND"));
    }
}
