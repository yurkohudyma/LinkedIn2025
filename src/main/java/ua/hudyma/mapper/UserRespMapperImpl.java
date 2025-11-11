package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.User;
import ua.hudyma.dto.UserRespDto;

@Component
@RequiredArgsConstructor
public class UserRespMapperImpl implements UserRespMapper {
    private final UserReqMapper userReqMapper;
    public UserRespDto toDto(User user) {
        return new UserRespDto(
                user.getUserCode(),
                user.getEmail(),
                user.getFullName(),
                user.getProfileUrl(),
                user.getAddress(),
                String.format("%d/%d", user.getDayOfBirth(), user.getMonthOfBirth()),
                userReqMapper.mapPhoneListToDtoList(user.getPhoneList()),
                userReqMapper.mapEducationListToDtoList(user.getEducationList()),
                        userReqMapper.mapMessengerListToDtoList(user.getMessengerList()),
                userReqMapper.mapWebsiteListToDtoList(user.getWebsiteList()),
                userReqMapper.mapPositionListToDtoList(user.getPositionList())
        );
    }

    @Override
    public User toEntity(UserRespDto dto) {
        return null;
    }
}
