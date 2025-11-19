package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.profile.Education;
import ua.hudyma.domain.profile.Messenger;
import ua.hudyma.dto.UserEducationReqDto;
import ua.hudyma.dto.UserMessengerReqDto;
import ua.hudyma.dto.UserMessengerRespDto;

@Component
public class UserMessengerMapper extends BaseMapper<UserMessengerRespDto, Messenger, UserMessengerReqDto> {
    @Override
    public UserMessengerRespDto toDto(Messenger messenger) {
        return new UserMessengerRespDto(
                messenger.getMessengerUserName(),
                messenger.getMessengerType()
        );
    }
    @Override
    public Messenger toEntity(UserMessengerReqDto dto) {
        var messenger = new Messenger();
        messenger.setMessengerUserName(dto.messengerUserName());
        messenger.setMessengerType(dto.messengerType());
        return messenger;
    }
}
