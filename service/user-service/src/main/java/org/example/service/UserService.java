package org.example.service;

import org.example.common.Result;
import org.example.dto.UserRegisterDTO;

public interface UserService {
    Result<String> register(UserRegisterDTO userDto);
}
