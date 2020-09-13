package com.vip.jfeatures.jdk15;

import java.io.FileOutputStream;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class GenerateClass {

    public static void main(String[] args) throws Exception {
        ClassWriter cw = getClassWriter(HiddenClassDemo.class);

        //save bytecode into disk
        FileOutputStream out = new FileOutputStream("/tmp/sample/HelloGen.class");
        out.write(cw.toByteArray());
        out.close();
    }

    private static String getHiddenClassName(Class<?> lookupClass) {
        return lookupClass.getName().replace('.', '/') + "$$HiddenClass";
    }

    public static ClassWriter getClassWriter(Class<HiddenClassDemo> ownerClassName) {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, getHiddenClassName(ownerClassName),
                null, "java/lang/Object", null);

        //default constructor
        {
            MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0); //load the first local variable: this
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }

        //main method
        {
            MethodVisitor mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"); //put System.out to operand stack
            mv.visitLdcInsn("Hello"); //load const "Hello" from const_pool, and put onto the operand stack
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        cw.visitEnd();
        return cw;
    }
}

/*

    the java
    source that
    would compile
    to the same

class bytecode

package sample;

public class Hello {


    public static void main(String[] args) {
        System.out.println("Hello");
    }

}


*/
