package com.it.自定义类加载器;

/**
 *
 * @author shuaihong-coding
 * @date 2026-03-26 21:06
 */
public class MyCLassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return null;
    }
}
