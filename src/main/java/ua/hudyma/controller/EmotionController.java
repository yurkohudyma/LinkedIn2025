package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.dto.EmotionReqDto;
import ua.hudyma.dto.EmotionRespDto;
import ua.hudyma.service.EmotionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmotionController {
    private final EmotionService emotionService;
    @PostMapping("/post/emotion")
    public ResponseEntity<String> addEmotionToPost (@RequestBody EmotionReqDto dto){
        return ResponseEntity.ok(emotionService.addEmotionToPost(dto));
    }

    @PostMapping("/comment/emotion")
    public ResponseEntity<String> addEmotionToComment (@RequestBody EmotionReqDto dto){
        return ResponseEntity.ok(emotionService.addEmotionToComment(dto));
    }

    @GetMapping("/user/emotions")
    public ResponseEntity<List<EmotionRespDto>> fetchAllUsersEmotions (@RequestParam String userCode){
        return ResponseEntity.ok(emotionService.fetchAllUserEmotions(userCode));
    }
    @GetMapping("/post/emotions")
    public ResponseEntity<List<EmotionRespDto>> fetchAllPostEmotions (@RequestParam String postCode){
        return ResponseEntity.ok(emotionService.fetchAllPostEmotions(postCode));
    }
    @GetMapping("/comment/emotions")
    public ResponseEntity<List<EmotionRespDto>> fetchAllCommentEmotions (@RequestParam String commentCode){
        return ResponseEntity.ok(emotionService.fetchAllCommentEmotions(commentCode));
    }
}
