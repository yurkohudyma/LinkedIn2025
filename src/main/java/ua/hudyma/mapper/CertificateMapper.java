package ua.hudyma.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.learning.Certificate;
import ua.hudyma.domain.learning.Course;
import ua.hudyma.dto.CertificateReqDto;
import ua.hudyma.dto.CertificateRespDto;
import ua.hudyma.exception.DtoObligatoryFieldsAreMissingException;
import ua.hudyma.repository.CourseRepository;
import ua.hudyma.service.UserService;

@Component
@RequiredArgsConstructor
public class CertificateMapper extends BaseMapper<CertificateRespDto, Certificate, CertificateReqDto> {
    private final UserService userService;
    private final CourseRepository courseRepository;
    public CertificateRespDto toDto(Certificate cert) {
        return new CertificateRespDto(
                cert.getUser().getFullName(),
                cert.getCourse().getCourseName(),
                cert.getCertificateCode()
        );
    }

    @Override
    public Certificate toEntity(CertificateReqDto dto) {
        var userCode = dto.userCode();
        var courseCode = dto.courseCode();
        if (userCode == null || courseCode == null || userCode.isEmpty() || courseCode.isEmpty()){
            throw new DtoObligatoryFieldsAreMissingException("Cert obligatory fields are VOID");
        }
        var user = userService.getUser(userCode);
        var course = getCourse(courseCode);
        var cert = new Certificate();
        cert.setUser(user);
        cert.setCourse(course);
        return cert;
    }

    private Course getCourse(String courseCode) {
        return courseRepository.findByCourseCode(courseCode)
                .orElseThrow( () -> new EntityNotFoundException
                        ("Course " + courseCode + " NOT FOUND"));
    }
}
