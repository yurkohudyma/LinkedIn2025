package ua.hudyma.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.Message;
import ua.hudyma.domain.User;
import ua.hudyma.enums.MessageReqDto;
import ua.hudyma.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class MessageMapper extends BaseMapper<MessageReqDto, Message> {
    private final UserRepository userRepository;

    @Override
    protected MessageReqDto toDto(Message message) {
        return new MessageReqDto(
                            message.getMessageCode(),
                message.getFromUser().getFullName(),
                message.getToUser().getFullName(),
                message.getText()
        );
    }

    @Override
    protected Message toEntity(MessageReqDto dto) {
        var message = new Message();
        message.setFromUser(getUser(dto.fromUserCode()));
        message.setToUser(getUser(dto.toUserCode()));
        message.setText(dto.messageText());
        return message;
    }

    private User getUser(String userCode) {
        return userRepository
                .findByUserCode(userCode)
                .orElseThrow(()
                        -> new EntityNotFoundException(" User " + userCode +
                        " DOES NOT EXIST"));
    }
}
