package ua.hudyma.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.User;
import ua.hudyma.dto.*;
import ua.hudyma.exception.DtoObligatoryFieldsAreMissingException;
import ua.hudyma.mapper.UserReqMapper;
import ua.hudyma.mapper.UserRespMapper;
import ua.hudyma.repository.UserRepository;
import ua.hudyma.util.IdGenerator;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {
    private final UserRepository userRepository;
    private final UserReqMapper userReqMapper;
    private final UserRespMapper userRespMapper;

    @Transactional
    public void addMessengers(String userCode, List<UserMessengerReqDto> dtoList) {
        avoidDtoNullity(dtoList);
        var user = getUser(userCode);
        var messengerList = userReqMapper.mapMessengerDtoListToEntityList(dtoList);
        if (user.getMessengerList().isEmpty()){
            user.setMessengerList(messengerList);
        }
        else {
            user.getMessengerList().addAll(messengerList);
        }
        log.info("::: User {} " + user.getFullName() + " messagers List HAS BEEN UPDATED");
    }

    @Transactional
    public void addWebsites(String userCode, List<UserWebsiteReqDto> dtoList) {
        avoidDtoNullity(dtoList);
        var user = getUser(userCode);
        var websiteList = userReqMapper.mapWebsiteDtoListToEntityList(dtoList);
        if (user.getWebsiteList().isEmpty()){
            user.setWebsiteList(websiteList);
        }
        else {
            user.getWebsiteList().addAll(websiteList);
        }
        log.info("::: User {} " + user.getFullName() + " websites List HAS BEEN UPDATED");
    }
    
    @Transactional
    public void addPhones(String userCode, List<UserPhoneReqDto> dtoList) {
        avoidDtoNullity(dtoList);
        var user = getUser(userCode);
        var phoneList = userReqMapper.mapPhoneDtoListToEntityList(dtoList);
        if (user.getPhoneList().isEmpty()){
            user.setPhoneList(phoneList);
        }
        else {
            user.getPhoneList().addAll(phoneList);
        }
        log.info("::: User {} " + user.getFullName() + " phonesList HAS BEEN UPDATED");
    }

    @Transactional
    public void addPositions(String userCode, List<UserPositionReqDto> dtoList) {
        avoidDtoNullity(dtoList);
        var user = getUser(userCode);
        var positionList = userReqMapper.mapPositionDtoListToEntityList(dtoList);
        if (user.getPositionList().isEmpty()){
            user.setPositionList(positionList);
        }
        else {
            user.getPositionList().addAll(positionList);
        }
        log.info("::: User {} " + user.getFullName() + " positionList HAS BEEN UPDATED");
    }

    @Transactional
    public void addEducation(String userCode, List<UserEducationReqDto> dtoList) {
        avoidDtoNullity(dtoList);
        User user = getUser(userCode);
        //var educationList = userReqMapper.mapEducationDtoListToEntityList(dtoList);
        var educationList = userReqMapper.mapEducationDtoListToEntityList(dtoList);
        if (user.getEducationList().isEmpty()){
            user.setEducationList(educationList);
        }
        else {
            user.getEducationList().addAll(educationList);
        }
        log.info("::: User {} " + user.getFullName() + " educationList HAS BEEN UPDATED");
    }

    @Transactional(readOnly = true)
    public UserRespDto fetchUser(String userCode) {
        var user = getUser(userCode);
        return userRespMapper.toDto(user);
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

    private static <D> void avoidDtoNullity (D dtoList){
        Objects.requireNonNull(dtoList, () -> "Required DtoList IS NULL");
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

    private User getUser(String userCode) {
        return userRepository
                .findByUserCode(userCode).orElseThrow(
                        () -> new EntityNotFoundException
                                (" User "+ userCode + " does NOT exist"));
    }
}
