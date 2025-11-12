package ua.hudyma.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.enums.MessageReqDto;
import ua.hudyma.enums.MessageStatus;
import ua.hudyma.mapper.MessageMapper;
import ua.hudyma.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Transactional
    public String unsendMessage (String msgCode){
        var msg = messageRepository
                .findByMessageCode(msgCode)
                .orElseThrow( () ->
                        new EntityNotFoundException("Msg " + msgCode + " NOT found"));
        msg.setStatus(MessageStatus.UNSENT);
        msg.setUpdatedOn(LocalDateTime.now());
        return "::: Message " + msgCode + " HAS been UNSENT";
    }

    @Transactional
    public String createMessage(MessageReqDto dto) {
        var message = messageMapper.mapToEntity(dto);
        message.setStatus(MessageStatus.SENT);
        messageRepository.save(message);
        message.getFromUser().getOutcomingMessageList().add(message);
        message.getToUser().getIncomingMessageList().add(message);
        var msg = String.format("::: Message from %s -> %s SUCC created and sent",
                message.getFromUser().getFullName(), message.getToUser().getFullName());
        log.info(msg);
        return msg;
    }

    public List<MessageReqDto> getAllIncomingMessages(String userCode) {
        return messageMapper.toDtoList(
                messageRepository.findAllByToUser_UserCode(userCode));
    }

    public List<MessageReqDto> getAllOutcomingMessages(String userCode) {
        return messageMapper.toDtoList(
                messageRepository.findAllByFromUser_UserCode(userCode));
    }
}
