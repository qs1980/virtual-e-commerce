package org.example.dto;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateDTO {
    Long id;
    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    private String username;
    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    private String password;
    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    @Email(message = "邮箱格式不正确")
    private String email;
}
