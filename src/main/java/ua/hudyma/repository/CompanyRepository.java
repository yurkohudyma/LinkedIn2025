package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.job.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
