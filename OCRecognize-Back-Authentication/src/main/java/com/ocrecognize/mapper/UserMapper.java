package com.ocrecognize.mapper;

import com.ocrecognize.model.dto.UserDto;
import com.ocrecognize.model.entity.UserEntity;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
@Mapper(componentModel = "spring",  uses = {RoleMapper.class})
public interface UserMapper {

    @Named("UserEntityToDto")
    @Mapping( source = "userEntity.roleEntity", target = "roleDto", qualifiedByName="RoleEntityToDto")
    UserDto entityToDto(UserEntity userEntity);

    @Named("UserDtoToEntity")
    @Mapping( source = "userDto.roleDto", target = "roleEntity", qualifiedByName="RoleDtoToEntity")
    UserEntity dtoToEntity(UserDto userDto);

    @BeanMapping(resultType = HashSet.class)
    @IterableMapping(qualifiedByName = "UserEntityToDto")
    List<UserDto> listEntityToDto(List<UserEntity> listUserEntity);

    @BeanMapping(resultType = HashSet.class)
    @IterableMapping(qualifiedByName = "UserDtoToEntity")
    List<UserEntity> listDtoToEntity(List<UserDto> listUserDto);
}
