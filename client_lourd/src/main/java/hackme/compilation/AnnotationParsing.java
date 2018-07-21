package hackme.compilation;

import hackme.qj.util.lang.DynamicClassLoader;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


public class AnnotationParsing {

    public static boolean findJokerAnnot(String methodname , String classpath)  {

        Class<?> cls = new DynamicClassLoader("src/main/resources").load(classpath);

        for (Method method : cls.getMethods()) {
                if (method.isAnnotationPresent(Joker.class)) {

                    try {
                        // iterates all the annotations available in the method
                        for (Annotation anno : method.getDeclaredAnnotations()) {
                            return true;
                        }
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }
        return false;
    }


    public static int findHintAnnot(String methodname,String classpath) {
        Class<?> cls = new DynamicClassLoader("src/main/resources").load(classpath);

            for (Method method : cls.getMethods()) {
                // checks if MethodInfo annotation is present for the method
                if (method.isAnnotationPresent(Hint.class)) {
                    try {
                        Hint hint = method.getAnnotation(Hint.class);
                        if (hint.test()>0 && hint.test()<3) {
                            return hint.test();
                        }
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }


        return -1;
    }

}