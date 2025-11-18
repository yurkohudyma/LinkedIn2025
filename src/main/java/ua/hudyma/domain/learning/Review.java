package ua.hudyma.domain.learning;

import jakarta.persistence.*;
import lombok.Data;
import ua.hudyma.domain.profile.User;

@Data
@Entity
@Table(name = "course_reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer rating;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User reviewAuthor;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
