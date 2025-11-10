package ua.hudyma.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.Education;
import ua.hudyma.domain.User;
import ua.hudyma.dto.UserReqDto;
import ua.hudyma.enums.UserEducationReqDto;
import ua.hudyma.exception.DtoObligatoryFieldsAreMissingException;
import ua.hudyma.mapper.UserMapper;
import ua.hudyma.repository.UserRepository;
import ua.hudyma.util.IdGenerator;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public void addEducation(String userCode, List<UserEducationReqDto> dtoList) {
        var user = userRepository
                .findByUserCode(userCode).orElseThrow(
                        () -> new EntityNotFoundException
                                (" User "+ userCode + " does NOT exist"));
        var educationList = userMapper.mapEducationDtoListToEntity(dtoList);
        if (user.getEducationList().isEmpty()){
            user.setEducationList(educationList);
        }
        else {
            user.getEducationList().addAll(educationList);
        }
        log.info("::: User {} " + user.getFullName() + " educationList HAS BEEN UPDATED");
    }

    public void createUser(UserReqDto dto) {
        var user = new User();
        checkObligatoryFields(dto);
        var name = dto.fullName();
        String userCode = user.getUserCode();
        if (userCode != null && !userCode.isEmpty()){
            user.setProfileUrl(IdGenerator
                    .generateLinkedProfileUrl(name, userCode));
        }
        user.setEmail(dto.email());
        user.setFullName(name);
        String address = dto.address();
        if (address != null && !address.isEmpty()){
            user.setAddress(address);
        }
        if (dto.dayOfBirth() != null){
            user.setDayOfBirth(dto.dayOfBirth());
        }
        if (dto.monthOfBirth() != null){
            user.setMonthOfBirth(dto.monthOfBirth());
        }
        userRepository.save(user);
        log.info("::: User {} CREATED", name);
    }

    private static void checkObligatoryFields(UserReqDto dto) {
        if (dto == null ||
                dto.fullName() == null ||
                dto.fullName().isEmpty() ||
                dto.email() == null || dto.email().isEmpty()) {
            throw new DtoObligatoryFieldsAreMissingException
                    ("User Req Dto or Compulsory fields are null or Missing");
        }
    }
}
