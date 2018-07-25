package hackme.compilation;

import hackme.qj.util.lang.DynamicClassLoader;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


public class AnnotationParsing {

    public static boolean findJokerAnnot(String methodname , String classpath)  {

        Class<?> cls = new DynamicClassLoader("src/main/resources").load(classpath);

        for (Method method : cls.getMethods()) {
                if (method.isAnnotationPresent(Joker.class) && methodname.equals(method.getName())) {
                            return true;
                }
            }
        return false;
    }


    public static int findHintAnnot(String methodname,String classpath) {
        Class<?> cls = new DynamicClassLoader("src/main/resources").load(classpath);
            for (Method method : cls.getMethods()) {
                if (method.isAnnotationPresent(Hint.class) && methodname.equals(method.getName())) {
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