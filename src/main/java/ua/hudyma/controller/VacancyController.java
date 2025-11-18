package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.hudyma.dto.VacancyMatcherDto;
import ua.hudyma.service.VacancyMatcherService;

import java.util.List;

@RestController
@RequestMapping("/vacancy")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyMatcherService vacancyMatcherService;

    @GetMapping("matchUser")
    public ResponseEntity<List<VacancyMatcherDto>> matchSuitableVacanciesForUser (
            @RequestParam String userCode){
        return ResponseEntity.ok(vacancyMatcherService
                .matchVacanciesForUser(userCode));

    }
}
