package ua.hudyma.dto;

import ua.hudyma.enums.WebsiteType;

public record UserWebsiteRespDto(
        String websiteUrl,
        WebsiteType websiteType
) {
}
