package org.example.service;

import org.example.common.Result;
import org.example.vo.UserVO;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public interface UserAdminService {
    StreamingResponseBody selectAllUser();
}
