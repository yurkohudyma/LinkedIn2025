package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.profile.Messenger;
import ua.hudyma.dto.UserMessengerReqDto;

@Component
public class UserMessengerMapper extends BaseMapper<UserMessengerReqDto, Messenger> {
    @Override
    protected UserMessengerReqDto toDto(Messenger messenger) {
        return new UserMessengerReqDto(
                messenger.getMessengerUserName(),
                messenger.getMessengerType()
        );
    }

    @Override
    protected Messenger toEntity(UserMessengerReqDto dto) {
        var messenger = new Messenger();
        messenger.setMessengerUserName(dto.messengerUserName());
        messenger.setMessengerType(dto.messengerType());
        return messenger;
    }
}
