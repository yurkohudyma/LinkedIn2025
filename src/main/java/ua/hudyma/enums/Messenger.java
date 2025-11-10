package ua.hudyma.enums;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Embeddable
@Data
public class Messenger {
    private String messengerName;
    @Enumerated(value = EnumType.STRING)
    private MessengerType messengerType;
}
