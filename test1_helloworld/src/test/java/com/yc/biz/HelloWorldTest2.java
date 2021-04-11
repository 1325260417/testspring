package com.yc.biz;


import com.yc.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@DependsOn("helloWorld")  //只要加了这个注解,则这个类可以被spring容器托管
public class HelloWorldTest2 {
    @Autowired
    private HelloWorld hw; //默认情况下所有的bean都是  eager(渴望加载)

    @Autowired
    private HelloWorld hw2;

    @Test
    public void testHello() {
       System.out.println("aaa");

       System.out.println(hw.hashCode()+ "\t" + hw2.hashCode());
        //spring 容器时单例模式

    }
}
