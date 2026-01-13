package org.example.controller;

import jakarta.validation.Valid;
import org.example.common.Result;
import org.example.dto.UserUpdateDTO;
import org.example.service.UserAdminService;
import org.example.service.UserService;
import org.example.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/user/admin")
public class UserAdminController {
    @Autowired
    private UserAdminService userAdminService;
    @GetMapping("/selectAllUser")
    public StreamingResponseBody selectAllUser() {
        return userAdminService.selectAllUser();
    }
}
