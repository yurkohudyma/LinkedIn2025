package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import ua.hudyma.domain.content.Emotion;
import ua.hudyma.dto.EmotionReqDto;
import ua.hudyma.dto.EmotionRespDto;
import ua.hudyma.exception.DtoObligatoryFieldsAreMissingException;
import ua.hudyma.mapper.EmotionMapper;
import ua.hudyma.repository.EmotionRepository;
import ua.hudyma.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmotionService {
    private final EmotionRepository emotionRepository;
    private final ContentService contentService;
    private final EmotionMapper emotionMapper;

    @Transactional
    public String addEmotionToComment(EmotionReqDto dto) {
        checkObligatoryFields(dto);
        var commentCode = dto.commentCode();
        if (commentCode == null || commentCode.isEmpty()) {
            throw new DtoObligatoryFieldsAreMissingException("CommentCode is necessary for adding Emotion");
        }
        var comment = contentService.getComment(commentCode);
        var user = contentService.getUser(dto.userCode());
        var emotion = new Emotion();
        emotion.setEmotionType(dto.emotionType());
        emotion.setComment(comment);
        emotion.setUser(user);
        emotionRepository.save(emotion);
        user.getEmotionList().add(emotion);
        comment.getEmotionList().add(emotion);
        var commentHeading = compileHeading(comment.getText());
        String msg = String.format(":::: Emotion of %s to comment %s has been APPLIED", user.getFullName(), commentHeading);
        log.info(msg);
        return msg;
    }

    @Transactional
    public String addEmotionToPost(@RequestBody EmotionReqDto dto) {
        checkObligatoryFields(dto);
        var postCode = dto.postCode();
        if (postCode == null || postCode.isEmpty()) {
            throw new DtoObligatoryFieldsAreMissingException("PostCode is necessary for adding Emotion");
        }
        var post = contentService.getPost(postCode);
        var user = contentService.getUser(dto.userCode());
        var emotion = new Emotion();
        emotion.setEmotionType(dto.emotionType());
        emotion.setPost(post);
        emotion.setUser(user);
        emotionRepository.save(emotion);
        user.getEmotionList().add(emotion);
        post.getEmotionList().add(emotion);
        var postHeading = compileHeading(post.getText());
        String msg = String.format(":::: Emotion of %s to post %s has been APPLIED", user.getFullName(), postHeading);
        log.info(msg);
        return msg;
    }

    public List<EmotionRespDto> fetchAllCommentEmotions(String commentCode) {
        var comment = contentService.getComment(commentCode);
        return emotionMapper.toDtoList(emotionRepository.findAllByComment_CommentCode(commentCode));
    }

    public List<EmotionRespDto> fetchAllPostEmotions(String postCode) {
        var post = contentService.getPost(postCode);
        return emotionMapper.toDtoList(emotionRepository.findAllByPost_PostCode(postCode));
    }

    public List<EmotionRespDto> fetchAllUserEmotions(String userCode) {
        return emotionMapper.toDtoList(emotionRepository.findAllByUser_UserCode(userCode));
    }

    protected String compileHeading(String text) {
        var textArray = text.split("\\s");
        return textArray.length > 1 ? String.format("%s %s", textArray[0], textArray[1]) : textArray[0];
    }

    private static void checkObligatoryFields(EmotionReqDto dto) {
        if (dto.emotionType() == null ||
                dto.userCode() == null ||
                dto.userCode().isEmpty()) {
            throw new DtoObligatoryFieldsAreMissingException
                    (" Emotion Req dto field are either null or empty");
        }
    }
}