package ua.hudyma.domain.job;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;
import ua.hudyma.domain.content.Comment;
import ua.hudyma.domain.content.Emotion;
import ua.hudyma.domain.content.Post;
import ua.hudyma.domain.profile.User;
import ua.hudyma.enums.CompanyActivityType;
import ua.hudyma.enums.CompanySize;
import ua.hudyma.enums.CompanySpecialtyType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    @Column(unique = true)
    private String companyCode = UUID.randomUUID().toString();
    private String companyName;
    @ElementCollection(targetClass = CompanySpecialtyType.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "company_specialties",
            joinColumns = @JoinColumn(name = "company_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "specialty")
    private Set<CompanySpecialtyType> companySpecialtyTypes = EnumSet
            .noneOf(CompanySpecialtyType.class);
    @Column(columnDefinition = "text")
    private String companyDescription;
    private String website;
    private String phone;
    private LocalDate accountVerifiedOn;
    @Enumerated(EnumType.STRING)
    private CompanySize companySize;
    @ElementCollection(targetClass = CompanyActivityType.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "company_activities",
            joinColumns = @JoinColumn(name = "company_id"))
    @Enumerated(EnumType.STRING)
    private Set<CompanyActivityType> companyActivityType = EnumSet
            .noneOf(CompanyActivityType.class);
    private LocalDate yearEstablished;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;
    @OneToMany(mappedBy = "company",
            cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Post> postList = new ArrayList<>();
    @OneToMany(mappedBy = "company",
            cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Comment> commentList = new ArrayList<>();
    @OneToMany(mappedBy = "company",
            cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Emotion> emotionList = new ArrayList<>();
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Vacancy> vacancyList = new ArrayList<>();
    @ManyToMany(mappedBy = "trackableCompanyList")
    @ToString.Exclude
    private List<User> trackingUsersList = new ArrayList<>();

}
