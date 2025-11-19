package ua.hudyma.domain.learning;

import jakarta.persistence.*;
import lombok.Data;
import ua.hudyma.domain.profile.Skill;
import ua.hudyma.enums.AccessibilityType;
import ua.hudyma.enums.CompanyActivityType;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static ua.hudyma.util.IdGenerator.generateId;

@Data
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseCode = generateId(5,5);
    private String courseName;
    @Column(columnDefinition = "text")
    private String description;
    @OneToMany(mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Quiz> quizList = new ArrayList<>();
    @OneToMany(mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Topic> topicList = new ArrayList<>();

    @ElementCollection(targetClass = AccessibilityType.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "course_accessibility",
            joinColumns = @JoinColumn(name = "course_id"))
    @Enumerated(EnumType.STRING)
    private Set<AccessibilityType> accessibilityTypeSet = EnumSet
            .noneOf(AccessibilityType.class);
    @ElementCollection
    @CollectionTable(
            name = "course_skills",
            joinColumns = @JoinColumn(name = "course_id"))
    private List<Skill> skillList = new ArrayList<>();
    @OneToMany(mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Certificate> certificateList = new ArrayList<>();
    private String instructor;
    @OneToMany(mappedBy = "course")
    private List<Review> reviewList = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private ComplexityLevel complexityLevel;

}
