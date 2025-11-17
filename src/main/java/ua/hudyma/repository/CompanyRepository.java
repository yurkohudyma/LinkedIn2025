package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.job.Company;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByCompanyCode(String companyCode);
}
