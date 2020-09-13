package com.vip.jfeatures.jdk15;

public class InstanceOfDemo {
    public static void main(String[] args) {
        Integer integer = Integer.valueOf("1");

        if (integer instanceof Integer) {
            Integer integer1 = (Integer) integer;
            System.out.println(integer.toString());
        }

        /*
            The instanceof operator "matches" the target number to the type test pattern
            as follows:
             1. if number is an instance of String, then it is cast to String
             and assigned to the binding variable s.
             2. The binding variable is in scope in the true block of the if
             statement, and not in the false block of
             the if statement.
         */
        if (integer instanceof Number number) {
            System.out.println(number.intValue());
        } else {
            System.out.println("Binding variable number not accessible here");
        }

        /*
        This is example of short circuit && operator.
        Here s.length() will be called only when onj is instance of String,
        that's why it makes sense you have s accessible on right hand side of
        short circuit operator as well.
        And when both conditions are true, which also means s is String
        definitely and hence s is accessible in if block as well.
        This way scoping rules doesn't any chance of error making it bug free.
         */
        Object obj = "Name";
        if (obj instanceof String s && s.length() > 3)
        {
            System.out.println(s.charAt(1));
        }
        else
        {
            System.out.println("s not accessible here");
        }

        /* Compilation error.
        This is example of || operator.
        When obj is not String, it doesn't get assigned into binding variable.

        The binding variable s is not in scope on the right hand side of the ||
        operator, nor is it in scope in the true block. s at these points refers
        to a field in the enclosing class, if any available, otherwise it
        shows compilation error.s


        if (obj instanceof String s || s.length() > 3)
        {
            System.out.println(s.charAt(1));
        }*/

    }
}
