package ua.hudyma.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.content.Message;
import ua.hudyma.domain.profile.User;
import ua.hudyma.dto.MessageRespDto;
import ua.hudyma.enums.MessageReqDto;
import ua.hudyma.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class MessageMapper extends BaseMapper<MessageRespDto, Message, MessageReqDto> {
    private final UserRepository userRepository;

    @Override
    public MessageRespDto toDto(Message message) {
        return new MessageRespDto(
                            message.getMessageCode(),
                message.getFromUser().getFullName(),
                message.getToUser().getFullName(),
                message.getText(),
                message.getSentOn(),
                message.getUpdatedOn(),
                message.getStatus()
        );
    }

    @Override
    public Message toEntity(MessageReqDto dto) {
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
