package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.profile.Phone;
import ua.hudyma.dto.UserPhoneReqDto;

@Component
public class UserPhoneMapper extends BaseMapper<UserPhoneReqDto, Phone> {
    @Override
    protected UserPhoneReqDto toDto(Phone phone) {
        return new UserPhoneReqDto(
                phone.getPhoneNumber(),
                phone.getPhoneType()
        );
    }

    @Override
    protected Phone toEntity(UserPhoneReqDto dto) {
        var phone = new Phone();
        phone.setPhoneNumber(dto.phoneNumber());
        phone.setPhoneType(dto.phoneType());
        return phone;
    }
}
