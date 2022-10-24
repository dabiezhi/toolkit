package com.bloom.bloomspringbootdemo.javassist;

import javassist.ClassPool;
import javassist.LoaderClassPath;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author curry
 * Created by on 2022-10-18 下午6:13
 * @link {https://www.jianshu.com/p/abd1c885c341}
 */
public final class ClassGenerator {

    private static final Map<ClassLoader, ClassPool> POOL_MAP = new ConcurrentHashMap<>(); //ClassLoader - ClassPool

    public static ClassPool getClassPool(ClassLoader loader) {
        if (loader == null) {
            return ClassPool.getDefault();
        }

        ClassPool pool = POOL_MAP.get(loader);
        if (pool == null) {
            pool = new ClassPool(true);
            pool.appendClassPath(new LoaderClassPath(loader));
            POOL_MAP.put(loader, pool);
        }
        return pool;
    }
}