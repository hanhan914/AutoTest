package com.course.testng.multThread;

import org.testng.annotations.Test;

public class MultiThreadOnAnnotion {

    @Test(invocationCount = 8,threadPoolSize = 3)
    //invocationCount = 8用8个线程跑,threadPoolSize = 3线程池设置为3
    public void test(){
        System.out.println(1);//打印出10个1
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());//打印当前线程id
    }
}
