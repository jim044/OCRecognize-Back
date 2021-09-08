package com.ocrecognize.service;

import com.ocrecognize.dto.RoleDto;
import com.ocrecognize.dto.UserDto;

import java.util.List;

public interface IUserService {

    UserDto findByUsername(String username);
}
