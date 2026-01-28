package org.example;

import org.apache.commons.collections.functors.InvokerTransformer;

public class InvokerTransformerTrigger {
    public static void main(String[] args){
        Class[] cls = new Class[] {String.class};
        Object[] obj = new Object[] {"calc.exe"};
        InvokerTransformer invokerTransformer = new InvokerTransformer("exec", cls, obj);
        invokerTransformer.transform(Runtime.getRuntime());
    }
}
