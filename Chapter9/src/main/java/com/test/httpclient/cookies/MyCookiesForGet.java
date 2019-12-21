package com.test.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {

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
    public void testGetWithCookies() throws IOException {
        String uri=bundle.getString("test.get.cookies");
        String testUrl=this.url+uri;
        HttpGet get=new HttpGet(testUrl);
        DefaultHttpClient client=new DefaultHttpClient();

        //设置cookies信息
        client.setCookieStore(this.store);

        HttpResponse response=client.execute(get);

        //获取响应的状态码
        int statusCode=response.getStatusLine().getStatusCode();
        System.out.println("statusCode="+statusCode);

        if(statusCode==200){
            //如果状态码为200，则返回结果值
            String result=EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println("result===="+result);
        }

    }
}
