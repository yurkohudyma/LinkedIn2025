package ua.hudyma.domain.profile;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ua.hudyma.enums.EmploymentType;

@Embeddable
@Data
public class Position {
    private String positionName;
    @Enumerated(value = EnumType.STRING)
    private EmploymentType employmentType;
    private String organisationName;
}
