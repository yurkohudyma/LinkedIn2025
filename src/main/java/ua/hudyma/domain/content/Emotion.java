package ua.hudyma.domain.content;

import jakarta.persistence.*;
import lombok.Data;
import ua.hudyma.domain.profile.User;
import ua.hudyma.enums.EmotionType;
import ua.hudyma.util.IdGenerator;

@Data
@Entity
@Table(name = "emotions")
public class Emotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emotionCode = IdGenerator.generateId(3,10);
    @Enumerated(EnumType.STRING)
    private EmotionType emotionType;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

}
