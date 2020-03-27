package com.syh.concurrent.common.Response;

/**
 * Created by Administrator on 2017/8/7.
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    SUCCESS(200, "成功"),
    FAIl(404,"失败"),
    USERNAME_OR_PASSWORD_ERROR(1001, "用户名或密码错误"),
    USER_NOT_LOGIN(1002, "用户未登录"),
    TOKEN_EXPIRE(1003, "token已过期"),
    USER_DISABLE(1004,"用户已被禁用"),
    USER_EXIT(1005,"该用户名已被注册"),
    Validated(1006,"参数验证失败"),
    ;

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;


    ResultCode(int code,String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
