package ua.hudyma.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ua.hudyma.enums.ConnectionStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_connections")
@Data
public class UserConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;
    @ManyToOne
    @JoinColumn(name = "contact_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User contact;
    @OneToOne
    private User rejectedBy;
    @Enumerated(EnumType.STRING)
    private ConnectionStatus status;
    private String note;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;
}

