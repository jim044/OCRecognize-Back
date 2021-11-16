package com.ocrecognize.service.impl;

import com.ocrecognize.dao.UserDao;
import com.ocrecognize.model.dto.UserDto;
import com.ocrecognize.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDto findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
