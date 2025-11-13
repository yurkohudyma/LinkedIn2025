package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.hudyma.domain.content.Comment;
import ua.hudyma.dto.CommentReqDto;
import ua.hudyma.dto.PostReqDto;
import ua.hudyma.service.ContentService;

@RestController
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;
    @PostMapping("/post")
    public ResponseEntity<String> createPost (@RequestBody PostReqDto dto){
        return ResponseEntity.ok(contentService.createPost(dto));
    }
    @PostMapping("/comment")
    public ResponseEntity<String> createComment (@RequestBody CommentReqDto dto){
        return ResponseEntity.ok(contentService.commentPost(dto));
    }
}
