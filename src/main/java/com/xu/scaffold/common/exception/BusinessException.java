
package com.xu.scaffold.common.exception;


import com.xu.scaffold.common.bean.ExceptionType;
import com.xu.scaffold.common.bean.Level;

/**
 * 业务异常
 *
 * @author xuhua
 * @since 1.0.0
 */
public class BusinessException extends Exception {

    private Integer code;

    private String message;

    private int level;

    public BusinessException(ExceptionType exceptionType) {
        this.code = exceptionType.getCode();
        this.message = exceptionType.getMessage();
        this.level = exceptionType.getLevel();
    }

    public BusinessException(Integer code, String message, Level level) {
        this.code = code;
        this.message = message;
        this.level = level.getLevel();
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", level=" + level +
                '}';
    }
}
