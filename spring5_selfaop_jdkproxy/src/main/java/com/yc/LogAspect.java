package com.yc;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogAspect implements InvocationHandler {
    private Object target; //目标类的对象

    public LogAspect(Object target) {
        this.target = target;
    }

    public Object createProxy() {
        //     新建一个代理实例
        return Proxy.newProxyInstance( this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this );
    }
    @Override  //回调方法   当jvm调用代理对象的被代理的方法时， 会由jvm自动调用这个invoke

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println( "代理类的对象:" + proxy.getClass());
        System.out.println( "方法:" + method); //目标类的方法
        System.out.println( "方法中的参数:" + args);


        if( method.getName().startsWith("add")) {//切入点表达式的解析  -> AspectJ的切入点表达式
            //前置增强
            log();
        }

        Object returnValue = method.invoke( this.target, args);  //直接执行了  StudentBizImpl中的 find()
        //后置增强
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
