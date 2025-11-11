package ua.hudyma.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ua.hudyma.enums.WebsiteType;

@Embeddable
@Data
public class Website {
    private String websiteUrl;
    @Enumerated(value = EnumType.STRING)
    private WebsiteType websiteType;
}
