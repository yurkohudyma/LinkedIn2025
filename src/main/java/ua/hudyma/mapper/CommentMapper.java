package ua.hudyma.mapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.content.Comment;
import ua.hudyma.dto.CommentRespDto;
import ua.hudyma.enums.CommentStatus;

@Component
public class CommentMapper extends BaseMapper <CommentRespDto, Comment> {
    @Override
    protected CommentRespDto toDto(Comment comment) {
        return new CommentRespDto(
                comment.getCommentCode(),
                comment.getUser().getFullName(),
                comment.getPost().getUser().getFullName(),
                comment.getSentOn(),
                comment.getUpdatedOn(),
                comment.getText(),
                comment.getStatus(),
                wasEdited (comment)
                );
    }

    private boolean wasEdited(Comment comment) {
        return !comment.getSentOn().equals(comment.getUpdatedOn()) && comment.getStatus() == CommentStatus.EDITED;
    }

    @Override
    protected Comment toEntity(CommentRespDto dto) {
        return null;
    }
}
