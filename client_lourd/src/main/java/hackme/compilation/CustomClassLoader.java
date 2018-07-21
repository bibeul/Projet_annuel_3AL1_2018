package hackme.compilation;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;


public class CustomClassLoader extends ClassLoader {



    public CustomClassLoader(ClassLoader parent) {
        super(parent);
    }

    public Class<?> reloadedClass(Class<?> myClass){
        System.out.printf("my class is Class@%x%n", myClass.hashCode());
        System.out.println("reloading");
        URL[] urls={ myClass.getProtectionDomain().getCodeSource().getLocation() };
        ClassLoader delegateParent = myClass.getClassLoader().getParent();
        try(URLClassLoader cl=new URLClassLoader(urls, delegateParent)) {
           return cl.loadClass(myClass.getName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}