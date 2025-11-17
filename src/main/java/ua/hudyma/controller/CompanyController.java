package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.dto.CompanyReqDto;
import ua.hudyma.dto.CompanyRespDto;
import ua.hudyma.dto.VacancyReqDto;
import ua.hudyma.dto.VacancyRespDto;
import ua.hudyma.service.CompanyService;

@RestController
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    @PostMapping("/company")
    public ResponseEntity<String> createCompany (@RequestBody CompanyReqDto dto){
        return ResponseEntity.ok(companyService.createCompany(dto));
    }

    @PostMapping("/vacancy")
    public ResponseEntity<String> createVacancy (@RequestBody VacancyReqDto dto){
        return ResponseEntity.ok(companyService.createVacancy(dto));
    }
    @GetMapping("/company")
    public ResponseEntity<CompanyRespDto> fetchCompany (@RequestParam String companyCode){
        return ResponseEntity.ok(companyService.fetchCompany(companyCode));
    }

    @GetMapping("/vacancy")
    public ResponseEntity<VacancyRespDto> fetchVacancy (@RequestParam String vacancyCode){
        return ResponseEntity.ok(companyService.fetchVacancy(vacancyCode));
    }
}
