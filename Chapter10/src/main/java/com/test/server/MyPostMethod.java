package com.test.server;


import com.test.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是我全部的post请求")
@RequestMapping("/v1")
public class MyPostMethod {
    //这个变量是用来装我们cookies信息的
    private static Cookie cookie;

    //用户登录成功获取到cookies，然后再访问其他接口获取到列表
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，成功后获取cookies信息",httpMethod = "POST")
    //userName为参数是否必须传，true为必须
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName",required = true) String userName,
                        @RequestParam(value = "passwoer",required = true) String passwoer){//response将cookies信息添加进去
        if(userName.equals("wang")&&passwoer.equals("123456")){
            cookie=new Cookie("login","true");//设置cookies
            response.addCookie(cookie);//添加cookies
            return "恭喜你登录成功！！！";
        }
        return "用户名或者密码错误";
    }

    //获取用户列表
    @RequestMapping(value = "/getUserList",method =RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                            @RequestBody User u){
        User user;
        //获取cookies
        Cookie[] cookies=request.getCookies();
        //验证cookies是否合法
        for(Cookie c:cookies){
            if(c.getName().equals("login")
                    &&c.getValue().equals("true")
                    &&u.getUserName().equals("wang")
                    &&u.getPasswoer().equals("123456")
                    ){
                //将用户列表返回
                user=new User();
                user.setName("niu");
                user.setAge("30");
                user.setSex("man");

                return user.toString();
            }
        }
        return "参数不合法";

    }
}
