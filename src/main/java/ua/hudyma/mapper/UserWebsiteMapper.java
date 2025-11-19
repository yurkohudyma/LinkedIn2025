package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.profile.Website;
import ua.hudyma.dto.UserWebsiteReqDto;
import ua.hudyma.dto.UserWebsiteRespDto;

@Component
public class UserWebsiteMapper extends BaseMapper <UserWebsiteRespDto, Website, UserWebsiteReqDto> {
    @Override
    public UserWebsiteRespDto toDto(Website website) {
        return new UserWebsiteRespDto(
                website.getWebsiteUrl(),
                website.getWebsiteType()
        );
    }

    @Override
    public Website toEntity(UserWebsiteReqDto dto) {
        var website = new Website();
        website.setWebsiteType(dto.websiteType());
        website.setWebsiteUrl(dto.websiteUrl());
        return website;
    }
}
