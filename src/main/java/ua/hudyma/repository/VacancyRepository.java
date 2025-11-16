package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.job.Vacancy;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
}
