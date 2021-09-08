package com.ocrecognize.dao;

import com.ocrecognize.dto.RoleDto;
import com.ocrecognize.mapper.RoleMapper;
import com.ocrecognize.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDao {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleRepository roleRepository;
}
