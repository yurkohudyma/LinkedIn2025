package ua.hudyma.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.content.Comment;
import ua.hudyma.domain.content.Post;
import ua.hudyma.domain.profile.User;
import ua.hudyma.dto.CommentReqDto;
import ua.hudyma.dto.CommentRespDto;
import ua.hudyma.dto.PostReqDto;
import ua.hudyma.dto.PostRespDto;
import ua.hudyma.enums.CommentStatus;
import ua.hudyma.enums.PostStatus;
import ua.hudyma.exception.DtoObligatoryFieldsAreMissingException;
import ua.hudyma.mapper.CommentMapper;
import ua.hudyma.mapper.PostMapper;
import ua.hudyma.repository.CommentRepository;
import ua.hudyma.repository.PostRepository;
import ua.hudyma.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ContentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;
    private final PostMapper postMapper;
    private final EntityManager em;

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
        var msg = String.format(":::: Comment for post of %s #%s SUCC published",
                user.getFullName(), comment.getCommentCode());
        log.info(msg);
        return msg;
    }

    @Transactional
    public String createPost(PostReqDto dto) {
        checkObligatoryFields(dto);
        var post = new Post();
        post.setStatus(PostStatus.PUBLISHED);
        post.setText(dto.text());
        var user = getUser(dto.authorUserCode());
        post.setUser(user);
        postRepository.save(post);
        user.getPostList().add(post);
        var msg = String.format(":::: Post of %s SUCC #%s published",
                user.getFullName(), post.getPostCode());
        log.info(msg);
        return msg;
    }

    @Transactional
    public String deleteComment(String commentCode) {
        var comment = getComment(commentCode);
        comment.setStatus(CommentStatus.DELETED);
        var msg = String.format("Comment %s has BEEN DELETED", commentCode);
        log.info(msg);
        return msg;
    }

    @Transactional
    public String deletePost(String postCode) {
        var post = getPost(postCode);
        post.setStatus(PostStatus.DELETED);
        var msg = String.format("Post %s has BEEN DELETED", postCode);
        log.info(msg);
        return msg;
    }

    @Transactional
    public CommentRespDto editComment(CommentReqDto dto, String commentCode) {
        checkTextChangesAvailability(dto.text());
        var comment = getComment(commentCode);
        comment.setText(dto.text());
        comment.setStatus(CommentStatus.EDITED);
        var msg = String.format("Comment %s has BEEN UPDATED", commentCode);
        log.info(msg);
        em.flush();
        return commentMapper.mapToDto(comment);
    }

    @Transactional
    public PostRespDto editPost(PostReqDto dto, String postCode) {
        checkTextChangesAvailability(dto.text());
        var post = getPost(postCode);
        post.setStatus(PostStatus.EDITED);
        post.setText(dto.text());
        var msg = String.format("Post %s has BEEN UPDATED", postCode);
        log.info(msg);
        em.flush();
        return postMapper.mapToDto(post);
    }

    public List<CommentRespDto> getAllPostComments(String postCode) {
        return commentMapper.toDtoList(commentRepository
                .findAllByPost_PostCode(postCode));
    }

    public List<PostRespDto> getAllUserPosts(String userCode) {
        return postMapper.toDtoList(postRepository
                .findAllByUser_UserCode(userCode));
    }

    protected User getUser(String userCode) {
        return userRepository
                .findByUserCode(userCode).orElseThrow(
                        () -> new EntityNotFoundException
                                (" User " + userCode + " does NOT exist"));
    }

    protected Post getPost(String postCode) {
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

    protected Comment getComment(String commentCode) {
        return commentRepository.findByCommentCode(commentCode).orElseThrow();
    }

    private static void checkTextChangesAvailability(String text) {
        if (text == null || text.isEmpty()) {
            throw new DtoObligatoryFieldsAreMissingException("no changes applied, new text is null or empty");
        }
    }
}
