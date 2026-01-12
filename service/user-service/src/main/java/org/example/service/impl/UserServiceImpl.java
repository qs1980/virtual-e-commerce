package org.example.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.example.common.Result;
import org.example.converter.UserConverter;
import org.example.dto.UserLoginDTO;
import org.example.dto.UserRegisterDTO;
import org.example.dto.UserUpdateDTO;
import org.example.entity.UserEntity;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import org.example.unti.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserConverter converter;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
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
        Integer userName = userMapper.getByUserName(user.getUsername());
        if(ObjectUtils.isNotEmpty(userName)){
            return Result.fail("此用户名已注册");
        }
        // 插入数据库
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        try {
            userMapper.insert(user);
        } catch (Exception e) {
            // 捕获唯一索引异常，避免高并发重复注册
            return Result.fail("注册失败，请稍后重试");
        }
        return Result.ok("注册成功");
    }

    public Result<String> login(UserLoginDTO userLoginDTO) {
        // 校验用户名
        UserEntity user = userMapper.getUserByUsername(userLoginDTO.getUsername());
        if (user == null) {
            return Result.fail("用户名不存在");
        }

        // 校验密码
        if (!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            return Result.fail("密码错误");
        }

        // 生成 Token
        String token = jwtTokenProvider.createToken(user);
        return Result.ok("登录成功", token);
    }

    @Override
    public Result<String> updateUser(UserUpdateDTO userDto) {
        UserEntity user = converter.dtoToEntity(userDto);
        //校验邮箱
        Integer email = userMapper.getByEmail(user.getEmail());
        if(ObjectUtils.isNotEmpty(email)){
            return Result.fail("此邮箱已注册");
        }
        //校验用户名
        Integer userName = userMapper.getByUserName(user.getUsername());
        if(ObjectUtils.isNotEmpty(userName)){
            return Result.fail("此用户名已注册");
        }
        // 插入数据库
        if (ObjectUtils.isNotEmpty(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        try {
            userMapper.updateById(user);
        } catch (Exception e) {
            // 捕获唯一索引异常，避免高并发重复注册
            return Result.fail("修改失败，请稍后重试");
        }
        String token = jwtTokenProvider.createToken(user);
        return Result.ok("修改成功",token);
    }
}
