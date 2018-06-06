package com.xu.scaffold.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @ApiOperation(value = "测试接口", notes = "示例数据：\n" + test)
    @PostMapping("/test2")
    public JSONObject test2() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", "test");
        return jsonObject;
    }

    private final static String test="{\n" +
            "    \"input\":\"test\",\n" +
            "    \"input2\":\"test2\"\n" +
            "}";
}
