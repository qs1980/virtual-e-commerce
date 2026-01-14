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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.concurrent.Executors;

@Service
public class UserAdminServiceImpl implements UserAdminService {

    @Autowired
    private UserAdminMapper userAdminMapper;

    @Override
    @Transactional(readOnly = true)
    public ResponseBodyEmitter selectAllUser() {
        ResponseBodyEmitter responseBodyEmitter = new ResponseBodyEmitter();
        try (Cursor<UserEntity> cursor = userAdminMapper.scanAll()) {
            for (UserEntity u : cursor) {
                responseBodyEmitter.send(u);
            }
            responseBodyEmitter.complete();
        } catch (Exception e) {
            responseBodyEmitter.completeWithError(e);
        }

        return responseBodyEmitter;
    }
}

