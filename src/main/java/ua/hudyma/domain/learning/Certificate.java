package ua.hudyma.domain.learning;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;
import ua.hudyma.domain.profile.User;
import ua.hudyma.util.IdGenerator;

@Data
@Entity
@Table(name = "course_certificates")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    private String certificateCode = IdGenerator.generateId(2, 5);
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
