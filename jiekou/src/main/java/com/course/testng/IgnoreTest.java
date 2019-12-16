package com.course.testng;

import org.testng.annotations.Test;

public class IgnoreTest {
    /*
    * 忽略测试
    * */
    @Test
    public void ignoreTest1(){
        System.out.println("ignoreTest1 执行");
    }
    @Test(enabled = false)//enabled = fals为不让改方法执行
    public void ignoreTest2(){
        System.out.println("ignoreTest2 执行");
    }
}
