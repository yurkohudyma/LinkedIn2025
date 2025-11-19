package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.profile.Position;
import ua.hudyma.dto.UserPositionReqDto;
import ua.hudyma.dto.UserPositionRespDto;

@Component
public class UserPositionMapper extends BaseMapper <UserPositionRespDto, Position, UserPositionReqDto> {
    @Override
    public UserPositionRespDto toDto(Position position) {
        return new UserPositionRespDto(
                position.getPositionName(),
                position.getEmploymentType(),
                position.getOrganisationName()
        );
    }

    @Override
     public Position toEntity(UserPositionReqDto position) {
        var pos = new Position();
        pos.setPositionName(position.positionName());
        pos.setEmploymentType(position.employmentType());
        pos.setOrganisationName(position.organisationName());
        return pos;
    }
}
