package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.Position;
import ua.hudyma.dto.UserPositionReqDto;

@Component
public class UserPositionMapper extends BaseMapper <UserPositionReqDto, Position> {
    @Override
    protected UserPositionReqDto toDto(Position position) {
        return new UserPositionReqDto(
                position.getPositionName(),
                position.getEmploymentType(),
                position.getOrganisationName()
        );
    }

    @Override
    protected Position toEntity(UserPositionReqDto position) {
        var pos = new Position();
        pos.setPositionName(position.positionName());
        pos.setEmploymentType(position.employmentType());
        pos.setOrganisationName(position.organisationName());
        return pos;
    }
}
