package com.vip.jfeatures.jdk15.hiddenclass;

import org.objectweb.asm.ClassWriter;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;

import static java.lang.invoke.MethodHandles.Lookup.ClassOption.NESTMATE;

public class HiddenClassDemo {
    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        ClassWriter cw =
                GenerateClass.getClassWriter(HiddenClassDemo.class);
        byte[] bytes = cw.toByteArray();

        Class<?> c = lookup.defineHiddenClass(bytes, true, NESTMATE).lookupClass();
        Constructor<?> constructor = c.getConstructor(null);
        Object object = constructor.newInstance(null);
        Test test = (Test) object;
        /* This way of creating instance is deprecated.
        Test test =  (Test)c.newInstance();
        */
        test.test(new String[]{"sample"});

        System.out.println("End of main method in class " + HiddenClassDemo.class.getName());
    }
}
