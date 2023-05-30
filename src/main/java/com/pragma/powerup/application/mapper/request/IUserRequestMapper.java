package com.pragma.powerup.application.mapper.request;

import com.pragma.powerup.application.dto.request.SaveUserRequestDto;
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
public interface IUserRequestMapper {

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRoles")
    UserModel toUserModel(SaveUserRequestDto saveUserRequestDto);

    @Named("mapRoles")
    default List<RoleModel> mapRoles(List<String> roles) {
        return roles.stream()
                .map(role -> {
                    RoleModel roleModel = new RoleModel();
                    roleModel.setName(role);
                    return roleModel;
                })
                .collect(Collectors.toList());
    }
}
