package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.cursor.Cursor;
import org.example.entity.UserEntity;

@Mapper
public interface UserAdminMapper extends BaseMapper<UserEntity> {
    @Options(fetchSize = 100)
    Cursor<UserEntity> scanAll();
}
