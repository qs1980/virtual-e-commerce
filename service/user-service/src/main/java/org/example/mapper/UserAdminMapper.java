package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.cursor.Cursor;
import org.example.entity.UserEntity;

@Mapper
public interface UserAdminMapper extends BaseMapper<UserEntity> {
    Cursor<UserEntity> scanAll();
}
