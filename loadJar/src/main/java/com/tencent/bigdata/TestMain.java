package com.tencent.bigdata;

/**
 * @author bryan
 * @date 2016/5/2.
 */


        import java.io.File;
        import java.io.IOException;
        import java.lang.reflect.Field;
        import java.lang.reflect.Method;
        import java.net.URL;
        import java.net.URLClassLoader;
        import java.util.Enumeration;
        import java.util.jar.JarEntry;
        import java.util.jar.JarFile;

public class TestMain {
    public static void getJarName(String jarFile) throws Exception {

        try{
            //通过将给定路径名字符串转换为抽象路径名来创建一个新File实例
            File f = new File(jarFile);
            URL url1 = f.toURI().toURL();
            URLClassLoader myClassLoader = new URLClassLoader(new URL[]{url1},Thread.currentThread().getContextClassLoader());

            //通过jarFile和JarEntry得到所有的类
            JarFile jar = new JarFile(jarFile);
            //返回zip文件条目的枚举
            Enumeration<JarEntry> enumFiles = jar.entries();
            JarEntry entry;

            //测试此枚举是否包含更多的元素
            while(enumFiles.hasMoreElements()){
                entry = (JarEntry)enumFiles.nextElement();
                if(entry.getName().indexOf("META-INF")<0){
                    String classFullName = entry.getName();
                    if(classFullName.indexOf(".class")<0){
                        classFullName = classFullName.substring(0,classFullName.length()-1);
                    } else{
                        //去掉后缀.class
                        String className = classFullName.substring(0,classFullName.length()-6).replace("/", ".");
                        Class<?> myclass = myClassLoader.loadClass(className);
                        //打印类名
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~打印类名:~~~~~~~~~~~~~~~~~~~~~~~~~" + className);


                        //通过getMethod得到类中包含的方法
                        Method m[] = myclass.getMethods();

                        for(int i=0; i<m.length; i++){
                            String sm = m[i].getName();
                            //打印除默认方法外的方法
                            if(DEFAULT_METHOD.indexOf(sm)<0){
                                System.out.println("~~~~打印方法名：~~~~~" + m[i].toString().substring((m[i].toString().indexOf(className))));
                            }
                        }
                    }
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }



    /**
     * 这些默认方法不打印
     */
    private static String DEFAULT_METHOD = "waitequalsnotifynotifyAlltoStringhashCodegetClass";


    public static void main(String[] args) throws Exception {
        //jar包所在路径
        /*getJarName("F:\\user.jar");
        getJarName("F:\\role1.jar");
        getJarName("F:\\role2.jar");
        getJarName("F:\\roleTest3.jar");*/
        getJarName("E:\\projects\\local\\TmpPro\\BeRefJar\\target\\BerefletJar-0.6.1-SNAPSHOT.jar");
    }
}
