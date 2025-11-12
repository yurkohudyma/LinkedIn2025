package ua.hudyma.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.User;
import ua.hudyma.domain.UserConnection;
import ua.hudyma.dto.*;
import ua.hudyma.exception.DtoObligatoryFieldsAreMissingException;
import ua.hudyma.exception.EntityAlreadyExistsException;
import ua.hudyma.mapper.*;
import ua.hudyma.repository.UserConnectionRepository;
import ua.hudyma.repository.UserRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ua.hudyma.enums.ConnectionStatus.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {
    private final UserConnectionRepository userConnectionRepository;
    private final UserRepository userRepository;
    private final UserEducationMapper educationMapper;
    private final UserPositionMapper positionMapper;
    private final UserPhoneMapper phoneMapper;
    private final UserMessengerMapper messengerMapper;
    private final UserMapper userMapper;
    private final UserWebsiteMapper websiteMapper;
    private final UserSkillMapper skillMapper;

    @Transactional
    public void addMessengers(String userCode, List<UserMessengerReqDto> dtoList) {
        avoidDtoNullity(dtoList);
        var user = getUser(userCode);
        var messengerList = messengerMapper.toEntityList(dtoList);
        if (user.getMessengerList().isEmpty()) {
            user.setMessengerList(messengerList);
        } else {
            user.getMessengerList().addAll(messengerList);
        }
        log.info("::: User {} " + user.getFullName() + " messagers List HAS BEEN UPDATED");
    }

    @Transactional
    public void addSkills(String userCode, List<UserSkillReqDto> dtoList) {
        avoidDtoNullity(dtoList);
        var user = getUser(userCode);
        var skillList = skillMapper.toEntityList(dtoList);
        if (user.getSkillList().isEmpty()) {
            user.setSkillList(skillList);
        } else {
            user.getSkillList().addAll(skillList);
        }
        log.info("::: User {} " + user.getFullName() + " skill List HAS BEEN UPDATED");
    }

    @Transactional
    public void addWebsites(String userCode, List<UserWebsiteReqDto> dtoList) {
        avoidDtoNullity(dtoList);
        var user = getUser(userCode);
        var websiteList = websiteMapper.toEntityList(dtoList);
        if (user.getWebsiteList().isEmpty()) {
            user.setWebsiteList(websiteList);
        } else {
            user.getWebsiteList().addAll(websiteList);
        }
        log.info("::: User {} " + user.getFullName() + " websites List HAS BEEN UPDATED");
    }

    @Transactional
    public void addPhones(String userCode, List<UserPhoneReqDto> dtoList) {
        avoidDtoNullity(dtoList);
        var user = getUser(userCode);
        var phoneList = phoneMapper.toEntityList(dtoList);
        if (user.getPhoneList().isEmpty()) {
            user.setPhoneList(phoneList);
        } else {
            user.getPhoneList().addAll(phoneList);
        }
        log.info("::: User {} " + user.getFullName() + " phonesList HAS BEEN UPDATED");
    }

    @Transactional
    public void addPositions(String userCode, List<UserPositionReqDto> dtoList) {
        avoidDtoNullity(dtoList);
        var user = getUser(userCode);
        var positionList = positionMapper.toEntityList(dtoList);
        if (user.getPositionList().isEmpty()) {
            user.setPositionList(positionList);
        } else {
            user.getPositionList().addAll(positionList);
        }
        log.info("::: User {} " + user.getFullName() + " positionList HAS BEEN UPDATED");
    }

    @Transactional
    public void addEducation(String userCode, List<UserEducationReqDto> dtoList) {
        avoidDtoNullity(dtoList);
        User user = getUser(userCode);
        var educationList = educationMapper.toEntityList(dtoList);
        if (user.getEducationList().isEmpty()) {
            user.setEducationList(educationList);
        } else {
            user.getEducationList().addAll(educationList);
        }
        log.info("::: User {} " + user.getFullName() + " educationList HAS BEEN UPDATED");
    }

    //todo introduce messager

    @Transactional
    public String cancelConnection(UserConnectionReqDto dto) {
        checkObligatoryFields(dto);
        var initUser = getUser(dto.initUserCode());
        var connectingUser = getUser(dto.connectingUserCode());
        var connectionOpt = userConnectionRepository
                .findByUserAndContactOrContactAndUser(initUser, connectingUser, initUser, connectingUser);
        var initUserFullName = initUser.getFullName();
        var connectingUserFullName = connectingUser.getFullName();
        if (connectionOpt.isEmpty()) {
            throw new EntityAlreadyExistsException("Connection " + initUserFullName + " <-> " + connectingUserFullName + " NOT EXISTENT");
        }
        var connection = connectionOpt.get();
        if (connection.getStatus() == REJECTED) {
            String rejectedByName = connection.getRejectedBy() == null
                    ? "UNKNOWN"
                    : Objects.toString(connection.getRejectedBy().getFullName(), "UNKNOWN");

            throw new EntityAlreadyExistsException(
                    "Connection " + initUserFullName + " <-> " + connectingUserFullName +
                            " EXISTS and rejected by " + rejectedByName
            );

        } else {
            connection.setStatus(REJECTED);
            connection.setRejectedBy(initUser);
            log.info("::: Connection {} <-> {} HAS been Rejected", initUserFullName, connectingUserFullName);
        }
        return "ok";
    }

    @Transactional
    public String createConnectionWithUser(UserConnectionReqDto dto) {
        checkObligatoryFields(dto);
        var initUser = getUser(dto.initUserCode());
        var connectingUser = getUser(dto.connectingUserCode());
        var existingConnectionOpt = userConnectionRepository
                .findByUserAndContactOrContactAndUser(initUser, connectingUser, initUser, connectingUser);
        var initUserFullName = initUser.getFullName();
        var connectingUserFullName = connectingUser.getFullName();
        if (existingConnectionOpt.isPresent()) {
            var existingConnection = existingConnectionOpt.get();
            //conn EXISTS As PENDING
            if (existingConnection.getStatus() == PENDING && existingConnection.getUser().equals(connectingUser)) {
                existingConnection.setStatus(ACCEPTED);
                if (dto.connectingNote() != null && !dto.connectingNote().isBlank()) {
                    existingConnection.setNote(dto.connectingNote());
                }
                userConnectionRepository.save(existingConnection);
                String message = String.format("::: User %s accepted connection from %s",
                        initUserFullName, connectingUserFullName);
                log.info(message);
                return message;
            } else if (existingConnection.getStatus() == REJECTED && connRejectedByInitUser(initUser, existingConnection) == 0) { //conn REJECTED BY CONTACTING USER
                throw new EntityAlreadyExistsException("Connection " + initUserFullName + " <-> "
                        + connectingUserFullName + " EXISTS and rejected by " + connectingUserFullName);
            } else if (existingConnection.getStatus() == REJECTED && connRejectedByInitUser(initUser, existingConnection) == 1) { //conn REJECTED BY INIT USER, RECONNECT
                existingConnection.setStatus(ACCEPTED);
                existingConnection.setRejectedBy(null);
                log.info(":::: Refused connection by initUser {} has been RECONNECTED", initUserFullName);
            } else {
                var warning = String.format("Connection already exists between %s and %s",
                        initUserFullName, connectingUserFullName);
                log.warn(warning);
                throw new EntityAlreadyExistsException(warning);
            }
        }
        else {
            var connection = new UserConnection();
            connection.setUser(initUser);
            connection.setContact(connectingUser);
            connection.setStatus(PENDING);
            Optional.ofNullable(dto.connectingNote())
                    .filter(s -> !s.isBlank())
                    .ifPresent(connection::setNote);
            initUser.getConnections().add(connection);
            userRepository.save(initUser);
            log.info("::: User {} requested connection with {}",
                    initUserFullName, connectingUserFullName);
            return "created connection";
        }
        return String.format("Refused connection by initUser %s has been RECONNECTED", initUserFullName);
    }

    private static int connRejectedByInitUser(User initUser, UserConnection existingConnection) {
        if (existingConnection.getRejectedBy() == null) {
            log.error(":::: RejectedByField is NULL, discard");
            return -1;
        }
        return existingConnection.getRejectedBy().equals(initUser) ? 1 : 0;
    }


    @Transactional(readOnly = true)
    public UserRespDto fetchUser(String userCode) {
        var user = getUser(userCode);
        return userMapper.mapToDto(user);
    }

    public void createUser(UserReqDto dto) {
        checkObligatoryFields(dto);
        var user = userMapper.mapToEntity(dto);
        userRepository.save(user);
        log.info("::: User {} CREATED", user.getFullName());
    }

    private static <D> void avoidDtoNullity(List<D> dtoList) {
        Objects.requireNonNull(dtoList, () -> "Required DtoList IS NULL");
        if (dtoList.isEmpty()) {
            throw new IllegalArgumentException("Dto List is Empty");
        }
        checkDtoFieldsIfNull(dtoList);
    }

    private static <D> void checkDtoFieldsIfNull(List<D> dtoList) {
        for (D dto : dtoList) {
            if (dto == null) continue;
            boolean allFieldsNull = true;
            for (Field field : dto.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(dto);
                    if (value != null) {
                        allFieldsNull = false;
                        break;
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            if (allFieldsNull) {
                throw new IllegalArgumentException("DTO has all fields null: "
                        + dto.getClass().getSimpleName());
            }
        }
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

    private static void checkObligatoryFields(UserConnectionReqDto dto) {
        if (dto == null ||
                dto.initUserCode() == null ||
                dto.initUserCode().isEmpty() ||
                dto.connectingUserCode() == null ||
                dto.connectingUserCode().isEmpty()) {
            throw new DtoObligatoryFieldsAreMissingException
                    ("User Req Dto or Compulsory fields are null or Missing");
        }
    }

    private User getUser(String userCode) {
        return userRepository
                .findByUserCode(userCode).orElseThrow(
                        () -> new EntityNotFoundException
                                (" User " + userCode + " does NOT exist"));
    }
}