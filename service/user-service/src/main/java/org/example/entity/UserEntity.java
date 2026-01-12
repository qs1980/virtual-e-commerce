package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class UserEntity {
    private Long id;
    private String username;
    private String password; // 存加密后的
    private String email;
    private Date createTime;
}
