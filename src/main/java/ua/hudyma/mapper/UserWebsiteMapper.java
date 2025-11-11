package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.Website;
import ua.hudyma.dto.UserWebsiteReqDto;

@Component
public class UserWebsiteMapper extends BaseMapper <UserWebsiteReqDto, Website> {
    @Override
    protected UserWebsiteReqDto toDto(Website website) {
        return new UserWebsiteReqDto(
                website.getWebsiteUrl(),
                website.getWebsiteType()
        );
    }

    @Override
    protected Website toEntity(UserWebsiteReqDto dto) {
        var website = new Website();
        website.setWebsiteType(dto.websiteType());
        website.setWebsiteUrl(dto.websiteUrl());
        return website;
    }
}
