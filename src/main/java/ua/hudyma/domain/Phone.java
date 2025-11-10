package ua.hudyma.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ua.hudyma.enums.PhoneType;

@Embeddable
@Data
public class Phone {
    private String phoneNumber;
    @Enumerated(value = EnumType.STRING)
    private PhoneType phoneType;
}
