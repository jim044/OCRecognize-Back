package com.ocrecognize.mapper;

import com.ocrecognize.model.dto.RoleDto;
import com.ocrecognize.model.entity.RoleEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class RoleMapperTest {

    @InjectMocks
    private RoleMapperImpl roleMapper;

    private RoleEntity roleEntity;

    private RoleDto roleDto;

    private List<RoleEntity> listRoleEntity;

    private List<RoleDto> listRoleDto;

    @Before
    public void setUpBeforeClass(){

        roleEntity = new RoleEntity();
        roleDto = new RoleDto();
        listRoleEntity = new ArrayList<>();
        listRoleDto = new ArrayList<>();

        roleEntity.setId(10L);
        roleEntity.setRoleName("role_name");
        listRoleEntity.add(roleEntity);

        roleDto.setId(10L);
        roleDto.setRoleName("role_name");
        listRoleDto.add(roleDto);
    }

    @Test
    public void testDtoToEntity(){
       assertEquals(roleEntity, roleMapper.dtoToEntity(roleDto));
    }

    @Test
    public void testDtoToEntityNull(){
        assertEquals(null, roleMapper.dtoToEntity(null));
    }

    @Test
    public void testEntityToDto(){
        assertEquals(roleDto, roleMapper.entityToDto(roleEntity));
    }

    @Test
    public void testEntityToDtoNull(){
        assertEquals(null, roleMapper.entityToDto(null));
    }

    @Test
    public void testListDtoToEntity(){
        assertEquals(listRoleEntity, roleMapper.listDtoToEntity(listRoleDto));
    }

    @Test
    public void testListDtoToEntityNull(){
        assertEquals(null, roleMapper.listDtoToEntity(null));
    }

    @Test
    public void testListEntityToDto(){
        assertEquals(listRoleDto, roleMapper.listEntityToDto(listRoleEntity));
    }

    @Test
    public void testListEntityToDtoNull(){
        assertEquals(null, roleMapper.listEntityToDto(null));
    }
}
