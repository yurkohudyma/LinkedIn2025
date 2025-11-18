package ua.hudyma.domain.learning;

import jakarta.persistence.*;
import lombok.Data;

import static ua.hudyma.util.IdGenerator.generateId;

@Data
@Entity
@Table(name = "course_topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String topicCode = generateId(3,2);
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
