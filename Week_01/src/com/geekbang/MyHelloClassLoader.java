package com.geekbang;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Objects;

/**
 * 自定义类加载器 加载指定文件
 */
public class MyHelloClassLoader extends ClassLoader {

    /**
     * 加载类的全限定名
     */
    private String className;

    /**
     * 文件路径
     */
    private String classFileName;

    public MyHelloClassLoader(String className, String classFileName) {
        this.className = className;
        this.classFileName = classFileName;
    }

    @Override
    protected Class<?> findClass(String name) {
        try {
            String classPath = this.getClass().getResource("/").getPath();
            File file = new File(URLDecoder.decode(classPath, "utf-8").concat(classFileName));
            byte[] bytes = decode(getClassBytes(file));
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Class<?> findClass() {
        return findClass(className);
    }

    /**
     * 读取文件到byte[]
     *
     * @param file
     * @return
     * @throws IOException
     */
    private byte[] getClassBytes(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length = 0;
        byte[] bytes = new byte[fileInputStream.available()];
        while ((length = fileInputStream.read(bytes, 0, bytes.length)) != -1) {
            byteArrayOutputStream.write(bytes, 0, length);
        }
        fileInputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 解码读取到的字节（x=255-x）
     *
     * @param bytes
     * @return
     */
    private byte[] decode(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
        return bytes;
    }

    public static void main(String[] args) {
        String className = "Hello";
        String classMethod = "hello";
        String classFileName = "com".concat(File.separator).concat("geekbang").concat(File.separator).concat("Hello.xlass");
        // 自定义加载类
        Class<?> clz = new MyHelloClassLoader(className, classFileName).findClass();
        Objects.requireNonNull(clz, "自定义类加载器MyHelloClassLoader加载类失败," + className + "类未找到.");
        try {
            // 获取方法
            Method helloMethod = clz.getMethod(classMethod);
            // 调用指定实例的方法
            helloMethod.invoke(clz.newInstance());
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}