package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.dto.*;
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
        return ResponseEntity.ok(courseService.fetchCourseDto(courseCode));
    }
    @PatchMapping("/skills")
    public ResponseEntity<String> editSkills (@RequestParam String courseCode,
                                              @RequestBody List<UserSkillReqDto> dtoList){
        return ResponseEntity.ok(courseService.editSkills(courseCode, dtoList));
    }
    @PatchMapping("/quizzes")
    public ResponseEntity<String> editQuizzes (@RequestParam String courseCode,
                                             @RequestBody List<QuizReqDto> dtoList){
        return ResponseEntity.ok(courseService.editQuizzes(courseCode, dtoList));
    }
    @PatchMapping("/topics")
    public ResponseEntity<String> editTopics (@RequestParam String courseCode,
                                               @RequestBody List<TopicReqDto> dtoList){
        return ResponseEntity.ok(courseService.editTopics(courseCode, dtoList));
    }
    @PatchMapping("/reviews")
    public ResponseEntity<String> editReviews (@RequestParam String courseCode,
                                              @RequestBody List<ReviewReqDto> dtoList){
        return ResponseEntity.ok(courseService.editReviews(courseCode, dtoList));
    }
    @PatchMapping("/certs")
    public ResponseEntity<String> editCerts (@RequestParam String courseCode,
                                               @RequestBody List<CertificateReqDto> dtoList){
        return ResponseEntity.ok(courseService.editCerts(courseCode, dtoList));
    }
}
