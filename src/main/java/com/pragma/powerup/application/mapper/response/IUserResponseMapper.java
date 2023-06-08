package com.pragma.powerup.application.mapper.response;

import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {
    @Mapping(source = "roles", target = "roles", qualifiedByName = "mapRolesToStrings")
    UserResponseDto toUserResponse(UserModel userModel);

    @Named("mapRolesToStrings")
    default List<String> mapRolesToStrings(List<RoleModel> roles) {
        return roles.stream()
                .map(RoleModel::getName)
                .collect(Collectors.toList());
    }
}
