package hackme.util;

import packagecompile.ArrayUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationParsing {

    public static void main(String[] args) {
        try {

            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            System.out.println();
            for (Method method : classLoader.loadClass("packagecompile.Math").getMethods() ) {
                // checks if MethodInfo annotation is present for the method
                if (method.isAnnotationPresent(hackme.util.MethodInfo.class)) {
                    try {
                        // iterates all the annotations available in the method
                        for (Annotation anno : method.getDeclaredAnnotations()) {
                            System.out.println("Annotation in Method '" + method + "' : " + anno);
                        }
                        hackme.util.MethodInfo methodAnno = method.getAnnotation(hackme.util.MethodInfo.class);
                        if (methodAnno.revision() == 1) {
                            System.out.println("Method with revision no 1 = " + method);
                        }

                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}