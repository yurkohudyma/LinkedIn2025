package ua.hudyma.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.learning.Course;
import ua.hudyma.domain.learning.Topic;
import ua.hudyma.dto.TopicReqDto;
import ua.hudyma.dto.TopicRespDto;
import ua.hudyma.exception.DtoObligatoryFieldsAreMissingException;
import ua.hudyma.repository.CourseRepository;
import ua.hudyma.service.CourseService;

@Component
@RequiredArgsConstructor
public class TopicMapper extends BaseMapper<TopicRespDto, Topic, TopicReqDto> {
    private final CourseRepository courseRepository;
    @Override
    public TopicRespDto toDto(Topic topic) {
        return new TopicRespDto(
                topic.getTopicName(),
                topic.getCourse().getCourseCode(),
                topic.getTopicCode()
        );
    }

    @Override
    public Topic toEntity(TopicReqDto dto) {
        var courseCode = dto.courseCode();
        if (courseCode == null || courseCode.isEmpty()){
            throw new DtoObligatoryFieldsAreMissingException("Course code for Topic is VOID");
        }
        var course = getCourse(courseCode);
        var topic = new Topic();
        topic.setCourse(course);
        topic.setTopicName(dto.topicName());
        return topic;
    }

    private Course getCourse(String courseCode) {
        return courseRepository.findByCourseCode(courseCode)
                .orElseThrow( () -> new EntityNotFoundException
                        ("Course " + courseCode + " NOT FOUND"));
    }
}
