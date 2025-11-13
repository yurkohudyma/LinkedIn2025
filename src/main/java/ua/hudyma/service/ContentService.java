package ua.hudyma.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.content.Comment;
import ua.hudyma.domain.content.Post;
import ua.hudyma.domain.profile.User;
import ua.hudyma.dto.CommentReqDto;
import ua.hudyma.dto.PostReqDto;
import ua.hudyma.enums.CommentStatus;
import ua.hudyma.enums.PostStatus;
import ua.hudyma.exception.DtoObligatoryFieldsAreMissingException;
import ua.hudyma.repository.CommentRepository;
import ua.hudyma.repository.PostRepository;
import ua.hudyma.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class ContentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public String commentPost(CommentReqDto dto) {
        checkObligatoryFields (dto);
        var comment = new Comment();
        comment.setText(dto.text());
        var post = getPost(dto.postCode());
        comment.setPost(post);
        var user = getUser(dto.authorUserCode());
        comment.setUser(user);
        comment.setStatus(CommentStatus.PUBLISHED);
        commentRepository.save(comment);
        user.getCommentList().add(comment);
        post.getCommentList().add(comment);
        var msg = String.format(":::: Comment for post of %s SUCC published", user.getFullName());
        log.info(msg);
        return msg;
    }

    public String createPost(PostReqDto dto) {
        checkObligatoryFields(dto);
        var post = new Post();
        post.setStatus(PostStatus.PUBLISHED);
        post.setText(dto.text());
        var user = getUser(dto.authorUserCode());
        postRepository.save(post);
        user.getPostList().add(post);
        var msg = String.format(":::: Post of %s SUCC published", user.getFullName());
        log.info(msg);
        return msg;
    }

    private User getUser(String userCode) {
        return userRepository
                .findByUserCode(userCode).orElseThrow(
                        () -> new EntityNotFoundException
                                (" User " + userCode + " does NOT exist"));
    }

    private Post getPost(String postCode) {
        return postRepository.findByPostCode(postCode)
                .orElseThrow( () -> new EntityNotFoundException("Post " + postCode + " NOT EXISTENT"));
    }

    private static void checkObligatoryFields(CommentReqDto dto) {
        if (dto == null || dto.authorUserCode() == null || dto.authorUserCode().isEmpty() ||
            dto.postCode() == null || dto.postCode().isEmpty() ||
            dto.text() == null || dto.text().isEmpty()){
            throw new DtoObligatoryFieldsAreMissingException("Comment dto fields are EMPTY or NULL");
        }
    }

    private static void checkObligatoryFields(PostReqDto dto) {
        if (dto == null || dto.authorUserCode() == null || dto.authorUserCode().isEmpty()) {
            throw new DtoObligatoryFieldsAreMissingException("Post dto fields are EMPTY or NULL");
        }
    }
}
