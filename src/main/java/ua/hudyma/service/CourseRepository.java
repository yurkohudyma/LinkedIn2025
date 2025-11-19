package ua.hudyma.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.learning.Course;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCourseCode(String courseCode);
}
