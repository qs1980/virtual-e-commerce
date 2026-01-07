package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.entity.UserEntity;
import org.springframework.web.bind.annotation.Mapping;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    Integer getByEmail(String email);

    Integer getByUserName(String username);
}
