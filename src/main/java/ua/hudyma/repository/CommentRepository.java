package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.content.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
