package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.profile.Phone;
import ua.hudyma.dto.UserPhoneReqDto;
import ua.hudyma.dto.UserPhoneRespDto;

@Component
public class UserPhoneMapper extends BaseMapper<UserPhoneRespDto, Phone, UserPhoneReqDto> {
    @Override
    public UserPhoneRespDto toDto(Phone phone) {
        return new UserPhoneRespDto(
                phone.getPhoneNumber(),
                phone.getPhoneType()
        );
    }

    @Override
    public Phone toEntity(UserPhoneReqDto dto) {
        var phone = new Phone();
        phone.setPhoneNumber(dto.phoneNumber());
        phone.setPhoneType(dto.phoneType());
        return phone;
    }
}
