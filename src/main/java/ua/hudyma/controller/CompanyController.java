package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.hudyma.dto.CompanyReqDto;
import ua.hudyma.dto.VacancyReqDto;
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
}
