package org.example.service.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.common.Result;
import org.example.entity.UserEntity;
import org.example.mapper.UserAdminMapper;
import org.example.service.UserAdminService;
import org.example.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Service
public class UserAdminServiceImpl implements UserAdminService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public StreamingResponseBody selectAllUser() {

        return outputStream -> {

            ObjectMapper mapper = new ObjectMapper();
            JsonGenerator generator = mapper.getFactory().createGenerator(outputStream);

            generator.writeStartArray();

            try (SqlSession session = sqlSessionFactory.openSession(ExecutorType.SIMPLE)) {

                UserAdminMapper mapperProxy = session.getMapper(UserAdminMapper.class);

                try (Cursor<UserEntity> cursor = mapperProxy.scanAll()) {
                    for (UserEntity entity : cursor) {
                        UserVO vo = convert(entity);
                        mapper.writeValue(generator, vo);
                    }
                }
            }

            generator.writeEndArray();
            generator.flush();
        };
    }

    private UserVO convert(UserEntity entity) {
        UserVO vo = new UserVO();
        vo.setId(entity.getId());
        vo.setUsername(entity.getUsername());
        vo.setEmail(entity.getEmail());
        return vo;
    }
}

