package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.profile.Education;
import ua.hudyma.domain.profile.User;
import ua.hudyma.dto.UserEducationReqDto;
import ua.hudyma.dto.UserReqDto;
import ua.hudyma.dto.UserRespDto;
import ua.hudyma.util.IdGenerator;

@Component
@RequiredArgsConstructor
public class UserMapper extends BaseMapper <UserRespDto, User, UserReqDto> {
    private final UserEducationMapper educationMapper;
    private final UserPositionMapper positionMapper;
    private final UserPhoneMapper phoneMapper;
    private final UserMessengerMapper messengerMapper;
    private final UserWebsiteMapper websiteMapper;
    private final UserSkillMapper skillMapper;
    @Override
    public UserRespDto toDto(User user) {
        return new UserRespDto(
                user.getUserCode(),
                user.getEmail(),
                user.getFullName(),
                user.getProfileUrl(),
                user.getAddress(),
                String.format("%d/%d", user.getDayOfBirth(), user.getMonthOfBirth()),
                phoneMapper.toDtoList(user.getPhoneList()),
                educationMapper.toDtoList(user.getEducationList()),
                messengerMapper.toDtoList(user.getMessengerList()),
                websiteMapper.toDtoList(user.getWebsiteList()),
                positionMapper.toDtoList(user.getPositionList()),
                skillMapper.toDtoList(user.getSkillList())
        );
    }

   @Override
    public User toEntity(UserReqDto dto) {
       var user = new User();
       var name = dto.fullName();
       var userCode = user.getUserCode();
       if (userCode != null && !userCode.isEmpty()){
           user.setProfileUrl(IdGenerator
                   .generateLinkedProfileUrl(name, userCode));
       }
       user.setEmail(dto.email());
       user.setFullName(name);
       user.setAddress(dto.address());
       user.setDayOfBirth(dto.dayOfBirth());
       user.setMonthOfBirth(dto.monthOfBirth());
       return user;
    }
}
