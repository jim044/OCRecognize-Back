package com.ocrecognize.service;

import com.ocrecognize.model.dto.UserDto;

public interface IUserService {

    UserDto findByUsername(String username);
}
