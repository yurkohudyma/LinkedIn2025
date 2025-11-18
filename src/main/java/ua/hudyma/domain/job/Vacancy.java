package ua.hudyma.domain.job;

import jakarta.persistence.*;
import lombok.Data;
import ua.hudyma.enums.EmploymentType;

import static ua.hudyma.util.IdGenerator.generateId;

@Data
@Entity
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vacancyCode = generateId(4,7);
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;
    private String position;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "text")
    private String requirements;
    @Column(columnDefinition = "text")
    private String niceToHave;
    @Column(columnDefinition = "text")
    private String responsibilities;
    @Column(columnDefinition = "text")
    private String offer;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    private String recruiter;
}
