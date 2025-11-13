package ua.hudyma.domain.content;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ua.hudyma.domain.profile.User;
import ua.hudyma.enums.CommentStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String commentCode = UUID.randomUUID().toString();
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "commenting_user_id")
    private User user;
    @CreationTimestamp
    private LocalDateTime sentOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;
    @Enumerated(value = EnumType.STRING)
    private CommentStatus status;
    @Column(columnDefinition = "text")
    private String text;

    //todo add Emotions
}
