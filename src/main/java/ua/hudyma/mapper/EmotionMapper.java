package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.content.Emotion;
import ua.hudyma.dto.EmotionRespDto;
import ua.hudyma.service.EmotionService;

@Component
@RequiredArgsConstructor
public class EmotionMapper extends BaseMapper<EmotionRespDto, Emotion> {
    @Override
    protected EmotionRespDto toDto(Emotion emotion) {
        var comment = emotion.getComment();
        var post = emotion.getPost();
        var postHeading = post == null ? null : compileHeading(post.getText());
        var commentHeading = comment == null ? null : compileHeading(comment.getText());
        return new EmotionRespDto(
                emotion.getEmotionCode(),
                emotion.getEmotionType(),
                emotion.getUser().getFullName(),
                postHeading,
                commentHeading
        );
    }

    protected String compileHeading(String text) {
        var textArray = text.split("\\s");
        return textArray.length > 1 ? String.format("%s %s", textArray[0], textArray[1]) : textArray[0];
    }

    @Override
    protected Emotion toEntity(EmotionRespDto dto) {
        return null;
    }
}
