package com.ocrecognize.mapper;

import com.ocrecognize.model.dto.RoleDto;
import com.ocrecognize.model.entity.RoleEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Named("RoleEntityToDto")
    RoleDto entityToDto(RoleEntity RoleEntity);

    @Named("RoleDtoToEntity")
    RoleEntity dtoToEntity(RoleDto RoleDto);

    @BeanMapping(resultType = HashSet.class)
    @IterableMapping(qualifiedByName = "RoleEntityToDto")
    List<RoleDto> listEntityToDto(List<RoleEntity> listRoleEntity);

    @BeanMapping(resultType = HashSet.class)
    @IterableMapping(qualifiedByName = "RoleDtoToEntity")
    List<RoleEntity> listDtoToEntity(List<RoleDto> listRoleDto);
}
