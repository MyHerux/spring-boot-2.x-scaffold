package com.xu.scaffold.controller;

import com.alibaba.fastjson.JSONObject;
import com.xu.scaffold.common.bean.ExceptionType;
import com.xu.scaffold.common.bean.Level;
import com.xu.scaffold.common.exception.BusinessException;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @ApiOperation(value = "测试 Swagger ", notes = "示例数据：\n" + test)
    @PostMapping("/test2")
    public JSONObject test2(@RequestBody JSONObject json) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", "test");
        return jsonObject;
    }

    @ApiOperation(value = "测试异常的抛出", notes = "示例数据：\n" + test)
    @PostMapping("/test3")
    public JSONObject test3(@RequestBody JSONObject json) throws BusinessException {
        if (!json.containsKey("data")) {
            throw new BusinessException(ExceptionType.INIT_CLIENT_ERROR);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", "test");
        return jsonObject;
    }

    @ApiOperation(value = "测试异常的抛出", notes = "示例数据：\n" + test)
    @PostMapping("/test4")
    public JSONObject test4(@RequestBody JSONObject json) throws BusinessException {
        if (!json.containsKey("data")) {
            throw new BusinessException(20002,"测试自定义异常，输入数据："+json.toJSONString(),Level.ignore);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", "test");
        return jsonObject;
    }

    private final static String test = "{\"input\":\"test\",\"input2\":\"test2\"}";
}
