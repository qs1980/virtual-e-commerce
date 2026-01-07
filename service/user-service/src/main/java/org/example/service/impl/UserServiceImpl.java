package org.example.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.example.common.Result;
import org.example.converter.UserConverter;
import org.example.dto.UserRegisterDTO;
import org.example.entity.UserEntity;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserConverter converter;
    @Override
    @Transactional
    public Result<String> register(UserRegisterDTO userDto) {
        UserEntity user = converter.dtoToEntity(userDto);
        //校验邮箱
        Integer email = userMapper.getByEmail(user.getEmail());
        if(ObjectUtils.isNotEmpty(email)){
            return Result.fail("此邮箱已注册");
        }
        //校验用户名
        Integer userName = userMapper.getByUserName(user.getUserName());
        if(ObjectUtils.isNotEmpty(userName)){
            return Result.fail("此用户名已注册");
        }
        // 插入数据库
        user.setPassWord(BCrypt.hashpw(user.getPassWord(), BCrypt.gensalt()));
        try {
            userMapper.insert(user);
        } catch (Exception e) {
            // 捕获唯一索引异常，避免高并发重复注册
            return Result.fail("注册失败，用户名或邮箱已存在");
        }
        return Result.ok("注册成功");
    }
}
