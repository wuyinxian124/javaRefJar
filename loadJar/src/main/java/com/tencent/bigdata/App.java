package com.tencent.bigdata;

/**
 * @author bryan
 * @date 2016/5/2.
 */

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;

public class App {
    public static void  main(String[] args){

        String jarpath = "E:\\projects\\local\\TmpPro\\BeRefJar\\target\\BerefletJar-0.6.1-SNAPSHOT.jar";
        String className = "edu.scut.runzhou.DecodeEncode";
        String methodName = "action";
        String paraType = "String";
        String ps = "crystal";
        String paraTypeB = "byte[]";
        byte[]  pb = "huanhuan".getBytes();
        try {
            new App().exe(jarpath,className,methodName);
            System.out.println("-------------------------------------");
            new App().exeS(jarpath, className, methodName, paraType, ps);
            System.out.println("-------------------------------------");
            new App().exeB(jarpath, className, methodName, paraTypeB, pb);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    public void exe(String jarpath,String className,String methodName)
            throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
                /*动态加载指定jar包调用其中某个类的方法*/
        File file=new File(jarpath);//jar包的路径
        URL url=file.toURI().toURL();
        ClassLoader loader=new URLClassLoader(new URL[]{url});//创建类加载器
        Class<?> cls=loader.loadClass(className);//加载指定类，注意一定要带上类的包名
        Object t = cls.newInstance();
        Method method = cls.getMethod(methodName);//方法名和对应的各个参数的类型
        System.out.println(method.getName());
        method.setAccessible(true);
        Object o = method.invoke(t);
//        Object o = method.invoke("something", ps);//调用得到的上边的方法method(静态方法，第一个参数可以为null)
        System.out.println(String.valueOf(o));//输出"000chen000","chen"字符串两边各加3个"0"字符串
    }

    public void exeS(String jarpath,String className,String methodName,String paType,String ps)
            throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
                /*动态加载指定jar包调用其中某个类的方法*/
        File file=new File(jarpath);//jar包的路径
        URL url=file.toURI().toURL();
        ClassLoader loader=new URLClassLoader(new URL[]{url});//创建类加载器
        Class<?> cls=loader.loadClass(className);//加载指定类，注意一定要带上类的包名
        Object t = cls.newInstance();
        Method method = cls.getMethod(methodName,getClassType(paType));//方法名和对应的各个参数的类型
        System.out.println(method.getName());
        method.setAccessible(true);
        Object o = method.invoke(t, ps);
//        Object o = method.invoke("something", ps);//调用得到的上边的方法method(静态方法，第一个参数可以为null)
        System.out.println(String.valueOf(o));//输出"000chen000","chen"字符串两边各加3个"0"字符串
    }


    public void exeB(String jarpath,String className,String methodName,String paTypeB,byte[] pb)
            throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

        /*动态加载指定jar包调用其中某个类的方法*/
        File file=new File(jarpath);//jar包的路径
        URL url=file.toURI().toURL();
        ClassLoader loader=new URLClassLoader(new URL[]{url});//创建类加载器
        Class<?> cls=loader.loadClass(className);//加载指定类，注意一定要带上类的包名
        Object t = cls.newInstance();
        Method method = cls.getMethod(methodName,getClassType(paTypeB));//方法名和对应的各个参数的类型
        Object o = method.invoke(t, pb);//调用得到的上边的方法method(静态方法，第一个参数可以为null)
        System.out.println(new String((byte[])o));
    }

    private Class getClassType(String classType){
        if("String".equals(classType)){
            System.out.println("STRING");
            return String.class;
        }

        if("byte[]".equals(classType)){
            System.out.println("BYTE ARRAY");
            return byte[].class;
        }
        return null;
    }
}
