package com.xu.scaffold.common.exception;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * 统一错误码异常处理
 *
 * @author xu
 */
@RestControllerAdvice()
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = BusinessException.class)
    public JSONObject businessExceptionHandler(HttpServletRequest request, BusinessException exception) throws IOException {
        logger.info(exception.toString());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("response_code", exception.getCode());
        jsonObject.put("message", exception.getMessage());
        return jsonObject;
    }

    @ExceptionHandler(value = Exception.class)
    public JSONObject otherExceptionHandler(HttpServletRequest request, Exception e) throws IOException {
        e.printStackTrace();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("response_code", "30003");
        jsonObject.put("message", "传入数据错误");
        return jsonObject;
    }
}
