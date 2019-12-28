package com.test.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethod {
    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)//RequestMethod.GET为get方法
    @ApiOperation(value = "通过这个方法可以获取到Cookies",httpMethod = "GET")//里面value值为描述
    public String getCookies(HttpServletResponse response){
        //HttpServerletRequest 装请求信息的类
        //HttpServerletResponse 装响应信息的类
        Cookie cookie=new Cookie("login","true");//cookie添加
        response.addCookie(cookie);//返回cookie信息
        return "恭喜你获得cookies信息成功！！！";
    }

    /**
     * 要求客户端携带cookies访问
     * 这是一个需要携带cookies信息才能访问的get请求
     */
    @RequestMapping(value ="/get/cookies",method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带cookies访问",httpMethod = "GET")//里面value值为描述
    public String getCookies(HttpServletRequest request){
        Cookie[] cookie=request.getCookies();//获取cookie信息
        //判断cookie是否为null
        if(Objects.isNull(cookie)){
            return "你必须携带cookies信息！！！";
        }
        for(Cookie cookie1:cookie){
            //判断cookies的key和value是否正确
            if(cookie1.getName().equals("login") && cookie1.getValue().equals("true")){
                return "这是一个需要携带cookies信息才能访问的get请求";
            }
        }

        return "你必须携带cookies信息！！！";
    }

    /*
    * 开发一个需要携带参数才能访问的get请求
    * 第一种实现方式 url: key=value&key=value
    * 我们来模拟获取商品列表
    * */
    //例如翻页，一个是你需要获取的开始位置，另一个需要获取的结束的位置的两个参数，一页20条
    @RequestMapping(value = "/get/param",method = RequestMethod.GET)
    @ApiOperation(value = "发一个需要携带参数才能访问的get请求,第一种",httpMethod = "GET")//里面value值为描述
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
        //存放商品列表
        Map<String,Integer> myList=new HashMap<>();
        myList.put("鞋",400);
        myList.put("饼干",3);
        myList.put("上衣",200);

        return myList;
    }
    /**
     * 第二种需要携带参数访问的get请求
     * url: ip:port/get/param/10/20
     * */
    @RequestMapping(value = "/get/param/{start}/{end}")
    @ApiOperation(value = "发一个需要携带参数才能访问的get请求,第二种",httpMethod = "GET")//里面value值为描述
    public Map myGetList(@PathVariable Integer start,
                         @PathVariable Integer end){
        //存放商品列表
        Map<String,Integer> myList=new HashMap<>();
        myList.put("鞋",400);
        myList.put("饼干",3);
        myList.put("上衣",200);

        return myList;
    }


}
