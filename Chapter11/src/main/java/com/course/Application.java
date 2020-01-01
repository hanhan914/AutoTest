package com.course;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static org.springframework.boot.SpringApplication.*;

//Application一般为入口类
@SpringBootApplication   //托管
@ComponentScan("com.course")     //扫描哪个包下面的类进行托管操作
public class Application {
    public static void main(String[] args) {
        run(Application.class,args);//固定写法
    }
}
