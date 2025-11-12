package ua.hudyma.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ua.hudyma.enums.MessageStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "messages")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String messageCode = UUID.randomUUID().toString();
    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private User fromUser;
    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private User toUser;
    @CreationTimestamp
    private LocalDateTime sentOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;
    @Enumerated(value = EnumType.STRING)
    private MessageStatus status;
    @Column(columnDefinition = "text")
    private String text;

}
