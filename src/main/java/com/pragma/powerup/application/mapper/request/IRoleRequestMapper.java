package com.pragma.powerup.application.mapper.request;

import com.pragma.powerup.application.dto.request.SaveRoleRequestDto;
import com.pragma.powerup.domain.model.RoleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleRequestMapper {
    @Mapping(target = "id", ignore = true)
    RoleModel toRoleModel(SaveRoleRequestDto roleRequestDto);
}
