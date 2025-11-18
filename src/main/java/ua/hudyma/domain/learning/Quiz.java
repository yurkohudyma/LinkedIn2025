package ua.hudyma.domain.learning;

import jakarta.persistence.*;
import lombok.Data;

import static ua.hudyma.util.IdGenerator.generateId;

@Data
@Entity
@Table(name = "course_quizzes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String quizCode = generateId(2,7);
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
