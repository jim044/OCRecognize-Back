package com.ocrecognize.controller;

import com.ocrecognize.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/message")
    public String test() {
        return "Hello JavaInUse Called in First Service";
    }
}
