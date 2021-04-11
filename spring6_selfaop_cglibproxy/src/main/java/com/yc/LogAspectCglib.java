package com.yc;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogAspectCglib implements MethodInterceptor {
    private Object target;

    public LogAspectCglib(Object target) {
        this.target = target;
    }

    public Object createProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass( this.target.getClass() );
        enhancer.setCallback(  this  );
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println( "代理类的对象:" + o.getClass());
        System.out.println( "方法:" + method); //目标类的方法
        System.out.println( "方法中的参数:" + args);
        System.out.println("要代理的方法:" + methodProxy);
        if( method.getName().startsWith("update")) {//转换成切入点表达式的解析  -> AspectJ的切入点表达式
            //前置增强
            log();
        }
        Object returnValue = method.invoke( this.target, args);
        return returnValue;
    }


    private void log() {
        System.out.println("=============before advice===============");
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(d);
        System.out.println("执行时间为:"+str);
        System.out.println("=============before advice end============");
    }
}
