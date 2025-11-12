package ua.hudyma.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;
import ua.hudyma.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    @Column(unique = true)
    private String userCode = IdGenerator.generateLinkedIdUserCode();
    private String email;
    @Column(nullable = false)
    private String fullName;
    private String profileUrl;
    private String address;
    private Integer monthOfBirth;
    private Integer dayOfBirth;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;
    @ElementCollection
    @CollectionTable(
            name = "user_phones",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<Phone> phoneList = new ArrayList<>();
    @ElementCollection
    @CollectionTable(
            name = "user_websites",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<Website> websiteList = new ArrayList<>();
    @ElementCollection
    @CollectionTable(
            name = "user_messengers",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<Messenger> messengerList = new ArrayList<>();
    @ElementCollection
    @CollectionTable(
            name = "user_education",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<Education> educationList = new ArrayList<>();
    @ElementCollection
    @CollectionTable(
            name = "user_positions",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<Position> positionList = new ArrayList<>();
    @ElementCollection
    @CollectionTable(
            name = "user_skills",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<Skill> skillList = new ArrayList<>();
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    //@EqualsAndHashCode.Exclude
    private Set<UserConnection> connections = new HashSet<>();

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    //@EqualsAndHashCode.Exclude
    private Set<UserConnection> connectedWithMe = new HashSet<>();

    @OneToMany(mappedBy = "fromUser",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private List<Message> outcomingMessageList = new ArrayList<>();
    @OneToMany(mappedBy = "toUser",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private List<Message> incomingMessageList = new ArrayList<>();


}
