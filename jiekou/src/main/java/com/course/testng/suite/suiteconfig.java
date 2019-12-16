package com.course.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class suiteconfig {
    /*
    * 写一些测试的共有的东西
    */
    @BeforeSuite
    public void befortSuite(){
        System.out.println("befortSuite运行啦");
    }
    @AfterSuite
    public void aftertSuite(){
        System.out.println("aftertSuite运行拉");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("beforeTest运行拉");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("afterTest运行拉");
    }

}
