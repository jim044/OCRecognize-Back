package com.ocrecognize.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @GetMapping("/message")
    public String test() {
        return "Hello JavaInUse Called in First Service";
    }
}
