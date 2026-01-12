package org.example.service;

import jakarta.validation.Valid;
import org.example.common.Result;
import org.example.dto.UserLoginDTO;
import org.example.dto.UserRegisterDTO;
import org.example.dto.UserUpdateDTO;

public interface UserService {
    Result<String> register(UserRegisterDTO userDto);

    Result<String> login(@Valid UserLoginDTO userDto);

    Result<String> updateUser(@Valid UserUpdateDTO userDto);
}
