package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.content.Emotion;

import java.util.List;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {
    List<Emotion> findAllByComment_CommentCode(String commentCode);

    List<Emotion> findAllByPost_PostCode(String postCode);

    List<Emotion> findAllByUser_UserCode(String userCode);
}
