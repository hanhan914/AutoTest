package com.tester.extentDemo;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TesterMethodsDemo {
    @Test
    public void test1(){
//        断言判断两个参数是否相等
        Assert.assertEquals(1,2);
    }
    @Test
    public void test2(){
        Assert.assertEquals(1,1);
    }

    @Test
    public void test3(){
        Assert.assertEquals(1,1);
    }
    @Test
    public void logDemo(){
//        添加log日志
        Reporter.log("这是我们自己写的日志");
        throw new RuntimeException("这是我自己运行时异常");
    }
}
