package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.enums.MessageReqDto;
import ua.hudyma.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<String> createMessage (@RequestBody MessageReqDto dto){
        return ResponseEntity.ok(messageService.createMessage(dto));
    }

    @GetMapping("/outcoming")
    public ResponseEntity<List<MessageReqDto>> getAllOutcominMessages (@RequestParam String userCode){
        return ResponseEntity.ok(messageService
                .getAllOutcomingMessages(userCode));
    }

    @GetMapping("/incoming")
    public ResponseEntity<List<MessageReqDto>> getAllIncomingMessages (@RequestParam String userCode){
        return ResponseEntity.ok(messageService
                .getAllIncomingMessages(userCode));
    }
}
