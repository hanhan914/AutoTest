package com.course.testng.paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {
    //参数化
    @Test(dataProvider = "data")
    public void testDataProvider(String name,int age){
        System.out.println("name="+name+",age="+age);
    }
    @DataProvider(name = "data")//data和@Test(dataProvider = "data")中的data一致
    public Object[][] providerData(){
        Object[][] o=new Object[][]{
                {"zhangshan",10},
                {"lisi",20},
                {"wangwu",30}
        };
        return o;
    }
    //按方法参数化
    @Test(dataProvider = "methodData")
    public void test1(String name,int age){
        System.out.println("test1111方法 name="+name+";age="+age);
    }
    //按方法参数化
    @Test(dataProvider = "methodData")
    public void test2(String name,int age){
        System.out.println("test2222方法 name="+name+";age="+age);
    }
    //按方法参数化
    @DataProvider(name = "methodData")
    public Object[][] methodDataTest(Method method){//Method method就是将对应的方法名引进来
        Object[][]  result=null;
        if(method.getName().equals("test1")){
            result=new Object[][]{
                    {"zhangsan",20},
                    {"lisi",25}
            };
        }else if(method.getName().equals("test2")){
            result=new Object[][]{
                    {"wangwu",50},
                    {"zhaoliu",60}
            };
        }
        return result;
    }
}
