package com.course.testng;

import org.testng.annotations.Test;

public class DependTest {
    @Test
    public void test1(){
        System.out.println("test1 run");
        throw new RuntimeException();
    }
    @Test(dependsOnMethods = {"test1"})//其中test1为要依赖的方法名称test1，如果test1运行失败则test2不执行
    public void test2(){
        System.out.println("test2 run");
    }
}
