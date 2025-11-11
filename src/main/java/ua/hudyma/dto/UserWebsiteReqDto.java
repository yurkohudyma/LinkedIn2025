package ua.hudyma.dto;

import ua.hudyma.enums.WebsiteType;

public record UserWebsiteReqDto(
        String websiteUrl,
        WebsiteType websiteType
) {
}
