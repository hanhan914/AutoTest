package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "v1",description = "这是我的第一个版本的demo")
@RequestMapping("v1")//api中的v1和这里的是对应的，Api力的v1是这里的说明
public class demo {

    //首先获取一个执行sql语句的对象

    @Autowired //启动及加载
    private SqlSessionTemplate template;

    @RequestMapping(value = "/getUserCount",method = RequestMethod.GET)
    @ApiOperation(value = "查询获取到用户数",httpMethod = "GET")
    public int getUserCount(){
        return  template.selectOne("getUserCount");//里面填写mysql.xml中的id
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ApiOperation(value = "往数据库添加数据",httpMethod = "POST")
    public int addUser(@RequestBody User user){
        return template.insert("addUser",user);
    }
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    @ApiOperation(value = "更改数据库数据",httpMethod = "POST")
    public int updateUser(@RequestBody User user){
        return template.update("updateUser",user);
    }

    @RequestMapping(value = "/delectUser",method = RequestMethod.GET)
    @ApiOperation(value = "删除数据库数据",httpMethod = "GET")
    public int delectUser(@RequestParam int id){
        return template.delete("deleteUser",id);
    }

}
