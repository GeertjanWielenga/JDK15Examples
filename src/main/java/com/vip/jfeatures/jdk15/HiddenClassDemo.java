package com.vip.jfeatures.jdk15;

import org.objectweb.asm.ClassWriter;

import java.lang.invoke.MethodHandles;

import static java.lang.invoke.MethodHandles.Lookup.ClassOption.NESTMATE;

public class HiddenClassDemo {
    public static void main(String[] args) throws Throwable {

        Thread testLambda = new Thread(() -> System.out.println("Test Lambda"));

        MethodHandles.Lookup lookup = MethodHandles.lookup();

        ClassWriter cw =
                GenerateClass.getClassWriter(HiddenClassDemo.class);
        byte[] bytes = cw.toByteArray();

        Class<?> c = lookup.defineHiddenClass(bytes, true, NESTMATE).lookupClass();

        //todo create a method in hidden class and call here

        testLambda.start();
    }


}
