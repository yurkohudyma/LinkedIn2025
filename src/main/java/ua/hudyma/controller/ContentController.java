package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.domain.content.Comment;
import ua.hudyma.domain.content.Post;
import ua.hudyma.dto.CommentReqDto;
import ua.hudyma.dto.CommentRespDto;
import ua.hudyma.dto.PostReqDto;
import ua.hudyma.dto.PostRespDto;
import ua.hudyma.service.ContentService;

import java.util.List;

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

    @GetMapping("/allComments")
    public ResponseEntity<List<CommentRespDto>> fetchAllPostComments (@RequestParam String postCode){
        return ResponseEntity.ok(contentService.getAllPostComments (postCode));
    }

    @GetMapping("/allPosts")
    public ResponseEntity<List<PostRespDto>> fetchAllUserPosts (@RequestParam String userCode){
        return ResponseEntity.ok(contentService.getAllUserPosts (userCode));
    }

    @PatchMapping("/editComment")
    public ResponseEntity<CommentRespDto> editComment (@RequestBody CommentReqDto dto, @RequestParam String commentCode){
        return ResponseEntity.ok(contentService.editComment(dto, commentCode));
    }
    @PatchMapping("/editPost")
    public ResponseEntity<PostRespDto> editPost (@RequestBody PostReqDto dto, @RequestParam String postCode){
        return ResponseEntity.ok(contentService.editPost(dto, postCode));
    }
    @DeleteMapping("/deletePost")
    public ResponseEntity<String> deletePost (@RequestParam String postCode){
        return ResponseEntity.ok(contentService.deletePost(postCode));
    }
    @DeleteMapping("/deleteComment")
    public ResponseEntity<String> deleteComment (@RequestParam String commentCode){
        return ResponseEntity.ok(contentService.deleteComment(commentCode));
    }
}
