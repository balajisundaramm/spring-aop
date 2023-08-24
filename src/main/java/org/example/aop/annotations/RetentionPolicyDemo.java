package org.example.aop.annotations;

import java.lang.annotation.*;

/**
 * @author = mbalaji on 24-08-2023
 * @project = spring-aop
 */
public class RetentionPolicyDemo {
    public static void main(String[] args) {
        Annotation a[]
                = new ClassA().getClass().getAnnotations();
        Annotation b[]
                = new ClassB().getClass().getAnnotations();
        Annotation c[]
                = new ClassC().getClass().getAnnotations();

        // Printing the number of retained annotations of
        // each class at runtime
        System.out.println(
                "Number of annotations attached to "
                        + "class A at Runtime: " + a.length);

        System.out.println(
                "Number of annotations attached to "
                        + "class B at Runtime: " + b.length);

        System.out.println(
                "Number of annotations attached to "
                        + "class C at Runtime: " + c.length);

        // Since the class C is annotated with an annotation
        //  which has retention policy as runtime so it
        // can be accessed during runtime while annotations
        // of other two classes are discarded before runtime
        // so they can't be accessed
        System.out.println(
                "Annotation attached to class C: " + c[0]);
    }
}

@Retention(RetentionPolicy.SOURCE)
@interface SourceRetention {
    String value() default "SOURCE";
}
@Retention(RetentionPolicy.CLASS)
@interface ClassRetention {
    String value() default "CLASS";
}

@Retention(RetentionPolicy.RUNTIME)
@interface RuntimeRetention {
    String value() default "RUNTIME";
}

@SourceRetention(value = "CLASS_A")
class  ClassA {

}

@ClassRetention(value = "CLASS_B")
class  ClassB {

}

@RuntimeRetention
class ClassC {

}