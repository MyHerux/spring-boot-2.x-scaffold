package com.xu.scaffold.common.bean;

/**
 * Enum 响应类型
 *
 * @author xu
 */
public enum ExceptionType {
    SUCCESS(200, "success", Level.ignore),
    INIT_CLIENT_ERROR(10002, "平台初始化失败", Level.high);

    private Integer code;
    private String message;
    private int level;

    /**
     * @param code    异常码
     * @param message 对外输出信息
     * @param level   异常等级
     */
    ExceptionType(Integer code, String message, Level level) {
        this.code = code;
        this.message = message;
        this.level = level.getLevel();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}


