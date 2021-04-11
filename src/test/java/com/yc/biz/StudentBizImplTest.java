package com.yc.biz;

import com.yc.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class StudentBizImplTest{
    //容器
    ApplicationContext ac;
    private StudentBizImpl studentBiz;

    @Before
    public void setup() {
        ac = new AnnotationConfigApplicationContext(AppConfig.class);
        studentBiz = (StudentBizImpl) ac.getBean("studentBizImpl");
    }

    @Test
    public void testAdd() {
        studentBiz.add("李四");
    }

    @Test
    public void testUpdate() {
        studentBiz.update("李四");
    }
}