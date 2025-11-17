package ua.hudyma.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CompanyRespDto(
        String companyCode,
        String companyName,
        List<String> companySpecialtyList,
        String companyDescription,
        String website,
        String phone,
        String accountVerifiedOn,
        String companySize,
        List<String> companyActivityList,
        int yearEstablished,
        int vacanciesPublished,
        int commentsPublished,
        int emotionsExpressed,
        int trackingUsers,
        int workingUsers
) {
}
