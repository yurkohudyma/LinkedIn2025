package ua.hudyma.domain.learning;

import jakarta.persistence.*;
import lombok.Data;
import ua.hudyma.domain.profile.User;

@Data
@Entity
@Table(name = "course_certificates")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
