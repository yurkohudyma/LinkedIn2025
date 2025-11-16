package ua.hudyma.domain.content;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ua.hudyma.domain.job.Company;
import ua.hudyma.domain.profile.User;
import ua.hudyma.enums.PostStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String postCode = UUID.randomUUID().toString();
    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @CreationTimestamp
    private LocalDateTime sentOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;
    @Enumerated(value = EnumType.STRING)
    private PostStatus status;
    @Column(columnDefinition = "text")
    private String text;
    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Emotion> emotionList = new ArrayList<>();
}
