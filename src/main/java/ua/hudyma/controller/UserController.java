package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.dto.UserReqDto;
import ua.hudyma.enums.UserEducationReqDto;
import ua.hudyma.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public void createUser (@RequestBody UserReqDto dto){
        userService.createUser (dto);
    }

    @PatchMapping("/education")
    public void addEducation (@RequestParam String userCode,
                              @RequestBody List<UserEducationReqDto> dtoList){
        userService.addEducation(userCode, dtoList);
    }
}
