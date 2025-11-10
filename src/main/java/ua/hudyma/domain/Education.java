package ua.hudyma.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ua.hudyma.enums.DegreeType;

@Embeddable
@Data
public class Education {
    private String institutionName;
    private String address;
    @Enumerated(value = EnumType.STRING)
    private DegreeType degreeType;
}
