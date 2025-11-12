package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.dto.*;
import ua.hudyma.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    private ResponseEntity<UserRespDto> fetchUser (@RequestParam String userCode){
        return ResponseEntity.ok(userService.fetchUser (userCode));
    }

    @PostMapping
    public void createUser (@RequestBody UserReqDto dto){
        userService.createUser (dto);
    }

    @PatchMapping("/education")
    public void addEducation (@RequestParam String userCode,
                              @RequestBody List<UserEducationReqDto> dtoList){
        userService.addEducation(userCode, dtoList);
    }

    @PatchMapping("/phones")
    public void addPhones (@RequestParam String userCode,
                           @RequestBody List<UserPhoneReqDto> dtoList){
        userService.addPhones(userCode, dtoList);
    }
    @PatchMapping("/positions")
    public void addPositions (@RequestParam String userCode,
                              @RequestBody List<UserPositionReqDto> dtoList){
        userService.addPositions (userCode, dtoList);
    }
    @PatchMapping("/messengers")
    public void addMessangers (@RequestParam String userCode,
                              @RequestBody List<UserMessengerReqDto> dtoList){
        userService.addMessengers(userCode, dtoList);
    }

    @PatchMapping("/websites")
    public void addWebsites (@RequestParam String userCode,
                              @RequestBody List<UserWebsiteReqDto> dtoList){
        userService.addWebsites(userCode, dtoList);
    }
    @PatchMapping("/skills")
    public void addSkills (@RequestParam String userCode,
                             @RequestBody List<UserSkillReqDto> dtoList){
        userService.addSkills(userCode, dtoList);
    }

    @PostMapping("/connect")
    public ResponseEntity<String> connectWithUser (@RequestBody UserConnectionReqDto dto){
        return ResponseEntity.ok(userService.createConnectionWithUser(dto));
    }

    @PostMapping("/disconnect")
    public ResponseEntity<String> disconnectWithUser (@RequestBody UserConnectionReqDto dto){
        return ResponseEntity.ok(userService.cancelConnection(dto));
    }
}
