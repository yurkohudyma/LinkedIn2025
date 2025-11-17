package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.job.Vacancy;

import java.util.Optional;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    Optional<Vacancy> findByVacancyCode(String vacancyCode);
}
