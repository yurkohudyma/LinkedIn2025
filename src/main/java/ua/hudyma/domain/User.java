package ua.hudyma.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;
import ua.hudyma.enums.Messenger;
import ua.hudyma.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

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
    private String fullName;
    private String profileUrl;
    private String address;
    private Integer monthOfBirth;
    private Integer dayOfBirth;
    @ElementCollection
    @CollectionTable(
            name = "user_phones",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<Phone> phoneList = new ArrayList<>();
    @ElementCollection
    @CollectionTable(
            name = "user_websites",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<Website> websiteUrlList = new ArrayList<>();
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
}
