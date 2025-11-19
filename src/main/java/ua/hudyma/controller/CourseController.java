package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.domain.profile.Skill;
import ua.hudyma.dto.CourseReqDto;
import ua.hudyma.dto.CourseRespDto;
import ua.hudyma.dto.UserSkillReqDto;
import ua.hudyma.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<String> addCourse (@RequestBody CourseReqDto dto){
        return ResponseEntity.ok(courseService.addCourse(dto));
    }
    @GetMapping
    public ResponseEntity<CourseRespDto> addCourse (@RequestParam String courseCode){
        return ResponseEntity.ok(courseService.fetchCourse(courseCode));
    }
    @PatchMapping("/skills")
    public ResponseEntity<String> editSkills (@RequestParam String courseCode,
                                              @RequestBody List<UserSkillReqDto> dtoList){
        return ResponseEntity.ok(courseService.editSkills(courseCode, dtoList));
    }
}
