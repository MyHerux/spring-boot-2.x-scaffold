package com.xu.scaffold.controller;

import com.alibaba.fastjson.JSONObject;
import com.xu.scaffold.common.bean.ExceptionType;
import com.xu.scaffold.common.bean.Level;
import com.xu.scaffold.common.exception.BusinessException;
import com.xu.scaffold.entity.Info;
import com.xu.scaffold.entity.User;
import com.xu.scaffold.repository.primary.UserMapper;
import com.xu.scaffold.repository.second.InfoMapper;
import com.xu.scaffold.service.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "TEST")
@RequestMapping(value = "/api", produces = "application/json;charset=UTF-8")
@RestController()
public class TestController {

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private InfoMapper infoMapper;


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
            throw new BusinessException(20002, "测试自定义异常，输入数据：" + json.toJSONString(), Level.ignore);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", "test");
        return jsonObject;
    }

    @ApiOperation(value = "测试Redis")
    @GetMapping("/test5")
    public String test5() {
        template.opsForValue().set("aaa", "111");

        return template.opsForValue().get("aaa");
    }

    @ApiOperation(value = "测试 MongoDB Repositories")
    @GetMapping("/test6")
    public List<User> test6() {
        User user = User.builder().name("test").password("123").build();
        userRepository.save(user);

        return userRepository.findAll();
    }

    @ApiOperation(value = "测试 MongoTemplate")
    @GetMapping("/test7")
    public List<User> test7() {
        User user = User.builder().name("test").password("123").build();
        mongoTemplate.save(user);
        return mongoTemplate.findAll(User.class);
    }

    @ApiOperation(value = "测试 Mybatis")
    @GetMapping("/test8")
    public User test8(){
       return userMapper.SelectUserById(1);
    }

    @ApiOperation(value = "测试 Mybatis")
    @GetMapping("/test9")
    public Info test9(){
        return infoMapper.SelectInfoById(1);
    }

    private final static String test = "{\"input\":\"test\",\"input2\":\"test2\"}";
}
