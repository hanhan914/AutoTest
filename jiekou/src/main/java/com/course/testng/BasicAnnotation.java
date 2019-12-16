package com.course.testng;

import org.testng.annotations.*;

public class BasicAnnotation {
    @Test
    public  void testCase1(){
        System.out.println("测试用例1");
    }
    @Test
    public void testCase2(){
        System.out.println("测试用例2");
    }
    @BeforeMethod
    public void beforetrest(){
        System.out.println("方法之前运行");
    }
    @AfterMethod
    public void aftertest(){
        System.out.println("方法之后运行");
    }
    @BeforeClass
    public void beforClass(){
        System.out.println("beforClass 方法运行在类之前");
    }
    @AfterClass
    public void AfterClass(){
        System.out.println("AfterClass 方法运行在类之后");
    }
    @BeforeSuite
    public void beforSuite(){
        System.out.println("BeforeSuite测试套件");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("AfterSuite测试套件");
    }
}
