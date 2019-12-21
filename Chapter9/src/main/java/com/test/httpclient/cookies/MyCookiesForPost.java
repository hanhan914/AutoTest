package com.test.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    private String url;
    private ResourceBundle bundle;//读取application.properties配置文件
    //存储cookies信息
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){
        //测试之前需要先执行的，添加配置文件

        bundle=ResourceBundle.getBundle("application",Locale.CHINA);//这里只写前缀前面，自动识别resources下的.properties文件
        url=bundle.getString("test.url");//获取配置文件中的key
    }
    @Test
    public void testGetCookies() throws IOException {
//        从配置文件中 拼接测试的url
        String result;
        String uri=bundle.getString("getCookies.uri");
        String testUrl=this.url+uri;

//        测试逻辑代码书写
        HttpGet get=new HttpGet(testUrl);
        DefaultHttpClient client=new DefaultHttpClient();
        HttpResponse response=client.execute(get);
        result=EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("result===="+result);

//        获取cookies信息
        store=client.getCookieStore();//获取所有的cookies信息
        List<Cookie> cookieList=store.getCookies();

        for(Cookie cookie:cookieList){
            String name=cookie.getName();
            String value=cookie.getValue();
            System.out.println("cookie name="+name+";cookie value="+value);
        }
    }

    //dependsOnMethods = {"testGetCookies"}依赖于testGetCookies这个方法，即这个
    //这个执行完后才执行下面这个方法
    //也就是cookies信息,需要先获取，下面才能使用
    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostCookies() throws IOException {
        String uri=bundle.getString("test.post.cookies");
        //拼接最终的测试地址
        String testurl=this.url+uri;
        //声明一个client对象，用来进行方法执行
        DefaultHttpClient client=new DefaultHttpClient();

        //声明一个方法，这个方法就是post方法
        HttpPost post=new HttpPost(testurl);

        //添加参数,startupCookies.json里面post的json串
        JSONObject parm=new JSONObject();
        parm.put("name","wang");
        parm.put("age","18");

        //设置请求头信息，设置header信息
        post.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity=new StringEntity(parm.toString(),"utf-8");
        post.setEntity(entity);//参数和post方法帮一块
        //声明一个对象来进行响应结果的存储
        String result;
        //设置cookies信息
        client.setCookieStore(this.store);
        //执行post方法
        HttpResponse response=client.execute(post);
        //获取响应结果
        result=EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("result+++++"+result);
        //处理结果，就是判断返回结果是否符合预期
        //1.将返回的响应结果字符串转化为json对象
        JSONObject resultjson=new JSONObject(result);

        //2.获取到结果值
        String success= (String) resultjson.get("wang");
        String status= (String) resultjson.get("status");
        //3.具体的判断返回结果的值
        Assert.assertEquals("success",success);//前面参数为期望结果，后面参数为实际结果
        Assert.assertEquals("1",status);

    }
}
