package com.ocrecognize.mapper;


import com.ocrecognize.model.dto.RoleDto;
import com.ocrecognize.model.dto.UserDto;
import com.ocrecognize.model.entity.RoleEntity;
import com.ocrecognize.model.entity.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @InjectMocks
    private UserMapperImpl userMapper;

    @Mock
    private RoleMapperImpl roleMapper;

    private UserEntity userEntity;

    private UserDto userDto;

    private List<UserEntity> listUserEntity;

    private List<UserDto> listUserDto;

    private RoleEntity roleEntity;

    private RoleDto roleDto;

    private List<RoleEntity> roleEntityList;

    private List<RoleDto> roleDtoList;

    @Before
    public void setUpBeforeClass(){

        userEntity = new UserEntity();
        userDto = new UserDto();
        listUserEntity = new ArrayList<>();
        listUserDto = new ArrayList<>();
        roleEntity = new RoleEntity();
        roleDto = new RoleDto();
        roleEntityList = new ArrayList<>();
        roleDtoList = new ArrayList<>();

        roleEntity.setId(10L);
        roleEntity.setRoleName("role_name");
        roleEntityList.add(roleEntity);

        roleDto.setId(10L);
        roleDto.setRoleName("role_name");
        roleDtoList.add(roleDto);

        userEntity.setId(10L);
        userEntity.setFirstname("Jimmy");
        userEntity.setUsername("jimmy.villossel@gmail.com");
        userEntity.setLastname("Villossel");
        userEntity.setPassword("Test");
        userEntity.setRoleEntity(roleEntity);
        listUserEntity.add(userEntity);

        userDto.setId(10L);
        userDto.setFirstname("Jimmy");
        userDto.setUsername("jimmy.villossel@gmail.com");
        userDto.setLastname("Villossel");
        userDto.setPassword("Test");
        userDto.setRoleDto(roleDto);
        listUserDto.add(userDto);
    }

    @Test
    public void testDtoToEntity(){
        Mockito.when(roleMapper.dtoToEntity(Mockito.any())).thenReturn(roleEntity);
        assertEquals(userEntity, userMapper.dtoToEntity(userDto));
    }

    @Test
    public void testDtoToEntityNull(){
        assertEquals(null, userMapper.dtoToEntity(null));
    }

    @Test
    public void testEntityToDto(){
        Mockito.when(roleMapper.entityToDto(Mockito.any())).thenReturn(roleDto);
        assertEquals(userDto, userMapper.entityToDto(userEntity));
    }

    @Test
    public void testEntityToDtoNull(){
        assertEquals(null, userMapper.entityToDto(null));
    }

    @Test
    public void testListDtoToEntity(){
        Mockito.when(roleMapper.dtoToEntity(Mockito.any())).thenReturn(roleEntity);
        assertEquals(listUserEntity, userMapper.listDtoToEntity(listUserDto));
    }

    @Test
    public void testListDtoToEntityNull(){
        assertEquals(null, userMapper.listDtoToEntity(null));
    }

    @Test
    public void testListEntityToDto(){
        Mockito.when(roleMapper.entityToDto(Mockito.any())).thenReturn(roleDto);
        assertEquals(listUserDto, userMapper.listEntityToDto(listUserEntity));
    }

    @Test
    public void testListEntityToDtoNull(){
        assertEquals(null, userMapper.listEntityToDto(null));
    }
}
