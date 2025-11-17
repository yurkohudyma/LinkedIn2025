package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.hudyma.domain.job.Company;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByCompanyCode(String companyCode);

    @Query(value = """
        SELECT count(*)
        FROM user_positions up
        JOIN users u ON up.user_id = u.id
        WHERE organisation_name LIKE CONCAT('%', :companyName, '%')
        """, nativeQuery = true)
    int findUsersNumberHavingJobConnectionWithCompany(
            @Param("companyName") String companyName);

}
