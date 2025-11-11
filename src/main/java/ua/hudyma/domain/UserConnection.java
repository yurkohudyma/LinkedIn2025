package ua.hudyma.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
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

    @Enumerated(EnumType.STRING)
    private ConnectionStatus status;

    private String note;

    private LocalDateTime createdAt = LocalDateTime.now();
}

