package com.ocrecognize.mapper;


import com.ocrecognize.dto.UserDto;
import com.ocrecognize.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @InjectMocks
    private UserMapperImpl userMapper;

    private User user;

    private UserDto userDto;

    private List<User> listUser;

    private List<UserDto> listUserDto;

    @Before
    public void setUpBeforeClass(){

        user = new User();
        userDto = new UserDto();
        listUser = new ArrayList<>();
        listUserDto = new ArrayList<>();

        user.setId(10L);
        user.setFirstname("James");
        user.setUsername("jimmy.villossel@gmail.com");
        user.setLastname("Villossel");
        user.setPassword("Test");
        listUser.add(user);

        userDto.setId(10L);
        userDto.setFirstname("James");
        userDto.setUsername("jimmy.villossel@gmail.com");
        userDto.setLastname("Villossel");
        userDto.setPassword("Test");
        listUserDto.add(userDto);
    }

    @Test
    public void testDtoToEntity(){
       assertEquals(user, userMapper.dtoToEntity(userDto));
    }

    @Test
    public void testDtoToEntityNull(){
        assertEquals(null, userMapper.dtoToEntity(null));
    }

    @Test
    public void testEntityToDto(){
        assertEquals(userDto, userMapper.entityToDto(user));
    }

    @Test
    public void testEntityToDtoNull(){
        assertEquals(null, userMapper.entityToDto(null));
    }

    @Test
    public void testListDtoToEntity(){
        assertEquals(listUser, userMapper.listDtoToEntity(listUserDto));
    }

    @Test
    public void testListDtoToEntityNull(){
        assertEquals(null, userMapper.listDtoToEntity(null));
    }

    @Test
    public void testListEntityToDto(){
        assertEquals(listUserDto, userMapper.listEntityToDto(listUser));
    }

    @Test
    public void testListEntityToDtoNull(){
        assertEquals(null, userMapper.listEntityToDto(null));
    }
}
