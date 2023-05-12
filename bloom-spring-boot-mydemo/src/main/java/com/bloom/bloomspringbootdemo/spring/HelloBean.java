package com.bloom.bloomspringbootdemo.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author curry
 * Created by on 2023-05-12 3:43 PM
 */
public class HelloBean {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void sayHello() {
        System.out.println("Hello, " + name + "!");
    }

    public static void main(String[] args) {
        // 加载 Spring 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 获取 Bean 对象
        HelloBean helloBean = (HelloBean) context.getBean("helloBean");

        // 调用 Bean 方法
        helloBean.sayHello();
    }
}
