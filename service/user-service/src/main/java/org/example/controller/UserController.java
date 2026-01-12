package org.example.controller;

import jakarta.validation.Valid;
import org.example.common.Result;
import org.example.dto.UserLoginDTO;
import org.example.dto.UserRegisterDTO;
import org.example.dto.UserUpdateDTO;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result<String> register(@RequestBody @Valid UserRegisterDTO userDto) {
        return userService.register(userDto);
    }
    @PostMapping("/login")
    public Result<String> login(@RequestBody @Valid UserLoginDTO userDto) {
        return userService.login(userDto);
    }
    @PostMapping("/updateUser")
    public Result<String> updateUser(@RequestBody @Valid UserUpdateDTO userDto) {
        return userService.updateUser(userDto);
    }
}
