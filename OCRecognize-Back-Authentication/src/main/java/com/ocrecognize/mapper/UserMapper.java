package com.ocrecognize.mapper;

import com.ocrecognize.dto.UserDto;
import com.ocrecognize.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Named("UserEntityToDto")
    UserDto entityToDto(User user);

    @Named("UserDtoToEntity")
    User dtoToEntity(UserDto userDto);

    @BeanMapping(resultType = HashSet.class)
    @IterableMapping(qualifiedByName = "UserEntityToDto")
    List<UserDto> listEntityToDto(List<User> listUser);

    @BeanMapping(resultType = HashSet.class)
    @IterableMapping(qualifiedByName = "UserDtoToEntity")
    List<User> listDtoToEntity(List<UserDto> listUserDto);
}
