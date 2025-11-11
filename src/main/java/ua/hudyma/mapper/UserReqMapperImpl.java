package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.*;
import ua.hudyma.dto.*;

import java.util.List;

@Component
public class UserReqMapperImpl implements UserReqMapper {

    @Override
    public List<Education> mapEducationDtoListToEntityList(List<UserEducationReqDto> dtoList) {
        return dtoList
                .stream()
                .map(dto -> {
                    var edu = new Education();
                    edu.setInstitutionName(dto.institutionName());
                    edu.setAddress(dto.address());
                    edu.setDegreeType(dto.degreeType());
                    return edu;
                })
                .toList();
    }

    @Override
    public List<Position> mapPositionDtoListToEntityList(List<UserPositionReqDto> dtoList) {
        return dtoList.stream().map(position -> {
            var pos = new Position();
            pos.setPositionName(position.positionName());
            pos.setEmploymentType(position.employmentType());
            pos.setOrganisationName(position.organisationName());
            return pos;
        }).toList();
    }

    @Override
    public List<Messenger> mapMessengerDtoListToEntityList(List<UserMessengerReqDto> dtoList) {
        return dtoList.stream().map(dto -> {
            var messenger = new Messenger();
            messenger.setMessengerUserName(dto.messengerUserName());
            messenger.setMessengerType(dto.messengerType());
            return messenger;
        }).toList();
    }

    @Override
    public List<Website> mapWebsiteDtoListToEntityList(List<UserWebsiteReqDto> dtoList) {
        return dtoList.stream().map(dto -> {
            var website = new Website();
            website.setWebsiteType(dto.websiteType());
            website.setWebsiteUrl(dto.websiteUrl());
            return website;
        }).toList();
    }

    @Override
    public List<Phone> mapPhoneDtoListToEntityList(List<UserPhoneReqDto> dtoList) {
        return dtoList.stream().map(dto -> {
            var phone = new Phone();
            phone.setPhoneNumber(dto.phoneNumber());
            phone.setPhoneType(dto.phoneType());
            return phone;
        }).toList();
    }

    @Override
    public List<UserEducationReqDto> mapEducationListToDtoList(List<Education> educationList) {
        return educationList.stream().map(education ->
            new UserEducationReqDto(
                    education.getInstitutionName(),
                    education.getAddress(),
                    education.getDegreeType()
        )).toList();
    }

    @Override
    public List<UserPhoneReqDto> mapPhoneListToDtoList(List<Phone> phoneList) {
        return phoneList.stream().map(phone ->
                new UserPhoneReqDto(
                        phone.getPhoneNumber(),
                        phone.getPhoneType()
                )).toList();
    }

    @Override
    public List<UserMessengerReqDto> mapMessengerListToDtoList(List<Messenger> messengerList) {
        return messengerList.stream().map(messenger ->
                new UserMessengerReqDto(
                        messenger.getMessengerUserName(),
                        messenger.getMessengerType()
                )).toList();
    }

    @Override
    public List<UserWebsiteReqDto> mapWebsiteListToDtoList(List<Website> websiteList) {
        return websiteList.stream().map(website ->
                new UserWebsiteReqDto(
                        website.getWebsiteUrl(),
                        website.getWebsiteType()
                )).toList();
    }

    @Override
    public List<UserPositionReqDto> mapPositionListToDtoList(List<Position> positionList) {
        return positionList.stream().map(position ->
                new UserPositionReqDto(
                        position.getPositionName(),
                        position.getEmploymentType(),
                        position.getOrganisationName()
                )).toList();
    }
}
