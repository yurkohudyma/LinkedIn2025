package ua.hudyma.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.learning.Course;
import ua.hudyma.domain.learning.Review;
import ua.hudyma.dto.ReviewReqDto;
import ua.hudyma.dto.ReviewRespDto;
import ua.hudyma.exception.DtoObligatoryFieldsAreMissingException;
import ua.hudyma.repository.CourseRepository;
import ua.hudyma.service.UserService;

@Component
@RequiredArgsConstructor
public class ReviewMapper extends BaseMapper<ReviewRespDto, Review, ReviewReqDto> {
    private final UserService userService;
    private final CourseRepository courseRepository;
    @Override
    public ReviewRespDto toDto(Review review) {
        return new ReviewRespDto(
                review.getRating(),
                review.getReviewAuthor().getFullName(),
                review.getCourse().getCourseCode()
        );
    }

    @Override
    public Review toEntity(ReviewReqDto dto) {
        var userCode = dto.reviewerUserCode();
        if (userCode == null || userCode.isEmpty()){
            throw new DtoObligatoryFieldsAreMissingException
                    ("Reviewer user code is NULL or EMPTY");
        }
        var courseCode = dto.courseCode();
        if (courseCode == null || courseCode.isEmpty()){
            throw new DtoObligatoryFieldsAreMissingException
                    ("Course Code is NULL or EMPTY");
        }
        var review = new Review();
        var user = userService.getUser(userCode);
        review.setReviewAuthor(user);
        var course = getCourse(courseCode);
        review.setCourse(course);
        review.setRating(dto.rating());
        return review;
    }

    private Course getCourse(String courseCode) {
        return courseRepository.findByCourseCode(courseCode)
                .orElseThrow( () -> new EntityNotFoundException
                        ("Course " + courseCode + " NOT FOUND"));
    }
}
