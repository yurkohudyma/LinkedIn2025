package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.Message;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByFromUser_UserCode(String userCode);
    List<Message> findAllByToUser_UserCode(String userCode);

    Optional<Message> findByMessageCode(String msgCode);
}
