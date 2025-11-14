package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.content.Post;
import ua.hudyma.dto.PostRespDto;
import ua.hudyma.enums.PostStatus;

@Component
public class PostMapper extends BaseMapper<PostRespDto, Post> {
    @Override
    protected PostRespDto toDto(Post post) {
        return new PostRespDto(
                post.getPostCode(),
                post.getUser().getFullName(),
                post.getSentOn(),
                post.getStatus(),
                wasEdited(post),
                post.getText()
        );
    }

    private boolean wasEdited(Post post) {
        return !post.getSentOn().equals(post.getUpdatedOn())
                && post.getStatus() == PostStatus.EDITED;
    }

    @Override
    protected Post toEntity(PostRespDto dto) {
        return null;
    }
}
