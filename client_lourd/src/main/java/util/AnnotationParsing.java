package Interface.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationParsing {

    public static void main(String[] args) {
        try {
            for (Method method : AnnotationParsing.class.getClassLoader()
                    .loadClass(("Interface.util.AnnotationExample")).getMethods()) {
                // checks if MethodInfo annotation is present for the method
                if (method.isAnnotationPresent(Interface.util.MethodInfo.class)) {
                    try {
                        // iterates all the annotations available in the method
                        for (Annotation anno : method.getDeclaredAnnotations()) {
                            System.out.println("Annotation in Method '" + method + "' : " + anno);
                        }
                        Interface.util.MethodInfo methodAnno = method.getAnnotation(Interface.util.MethodInfo.class);
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