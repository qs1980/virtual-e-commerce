package org.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一接口返回类
 * @param <T> 返回的数据类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    /**
     * 状态码（200成功，非200失败）
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    // ------------------ 静态方法，方便链式调用 ------------------

    /**
     * 成功，无数据
     */
    public static <T> Result<T> ok() {
        return new Result<>(200, "success", null);
    }

    /**
     * 成功，有数据
     */
    public static <T> Result<T> ok(T data) {
        return new Result<>(200, "success", data);
    }

    /**
     * 成功，有数据 + 自定义消息
     */
    public static <T> Result<T> ok(T data, String message) {
        return new Result<>(200, message, data);
    }

    /**
     * 失败，默认消息
     */
    public static <T> Result<T> fail() {
        return new Result<>(500, "fail", null);
    }

    /**
     * 失败，自定义消息
     */
    public static <T> Result<T> fail(String message) {
        return new Result<>(500, message, null);
    }

    /**
     * 失败，自定义 code + 消息
     */
    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<>(code, message, null);
    }

}
