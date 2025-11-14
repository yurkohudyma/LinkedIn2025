package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.content.Emotion;
import ua.hudyma.domain.content.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser_UserCode(String userCode);
    Optional<Post> findByPostCode(String postCode);
}
