package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
public class GroupsOnMethod {
    //testng分组测试
    @Test(groups = "server")
    public void test1(){
        System.out.println("这是服务端1111111");
    }
    @Test(groups = "server")
    public void test2(){
        System.out.println("这是服务端222222");
    }
    @Test(groups = "client")
    public void test3(){
        System.out.println("这是客户端333333");
    }
    @Test(groups = "client")
    public void test4(){
        System.out.println("这是客户端4444444");
    }
    @BeforeGroups("server")//这里的server对应@Test(groups = "server")里的server
    public void beforeGroupsOnserver(){
        System.out.println("这是服务端组运行之前运行的方法");
    }
    @AfterGroups("server")
    public void AfterGroupsOnserver(){
        System.out.println("这是服务端组运行之后运行的方法!!!!!!!!!!");
    }

    @BeforeGroups("client")//这里的client对应@Test(groups = "client")client
    public void beforeGroupsOnclint(){
        System.out.println("这是客户端组运行之前运行的方法");
    }
    @AfterGroups("client")
    public void AfterGroupsOnclient(){
        System.out.println("这是客户端组运行之后运行的方法!!!!!!!!!!");
    }
}
