package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.job.Vacancy;
import ua.hudyma.domain.profile.User;
import ua.hudyma.dto.VacancyMatcherDto;
import ua.hudyma.mapper.VacancyMapper;
import ua.hudyma.repository.VacancyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
@Log4j2
public class VacancyMatcherService {
    private final VacancyRepository vacancyRepository;
    private final UserService userService;
    private final VacancyMapper vacancyMapper;

    @Transactional
    public List<VacancyMatcherDto> matchVacanciesForUser(String userCode) {
        var list = new ArrayList<Vacancy>();
        var user = userService.getUser(userCode);
        var skillNamesHeaders = extractSkillNameFirstWordToSet(user);
        log.info("::: SKILLNAMESHEADER : {}", skillNamesHeaders);
        var vacancies = vacancyRepository.findAll();
        for (Vacancy vacancy : vacancies) {
            var vacancyPositionKeywords = getVacancyPositionKeywords(vacancy.getPosition());
            log.info(":::: VacancyPositionKeywords : {}", vacancyPositionKeywords);
            if (matchUserSkillsWithvacancyPositionKeywords(skillNamesHeaders, vacancyPositionKeywords)) {
                list.add(vacancy);
            }
        }
        return vacancyMapper.mapEntityListToVacancyMatcherDtoList(list);
    }

    private static Set<String> getVacancyPositionKeywords(String positionName) {
        return Stream
                .of(positionName
                        .split("\\W+"))
                .collect(toSet());
    }

    private boolean matchUserSkillsWithvacancyPositionKeywords(
            Set<String> skillNamesHeaders,
                      Set<String> vacancyPositionKeywords) {
        for (String skillNameHead : skillNamesHeaders){
            if (vacancyPositionKeywords.stream()
                    .anyMatch(vacancy -> vacancy.equals(skillNameHead))){
                log.info(":::: Detected MATCH of {} ", skillNameHead);
                return true;
            }
        }
        log.error(" :::: Detected MATCHES: ZERO");
        return false;
    }

    private static Set<String> extractSkillNameFirstWordToSet(User user) {
        return user
                .getSkillList()
                .stream()
                .map(skill -> skill
                        .getSkillName()
                        .split("\\s")[0])
                .collect(toSet());
    }
}
