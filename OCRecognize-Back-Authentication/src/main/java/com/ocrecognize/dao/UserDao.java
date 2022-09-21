package com.ocrecognize.dao;

import com.ocrecognize.model.dto.UserDto;
import com.ocrecognize.mapper.UserMapper;
import com.ocrecognize.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    public UserDto findByUsername(String username){
        return userMapper.entityToDto(userRepository.findByUsername(username));
    }
}
