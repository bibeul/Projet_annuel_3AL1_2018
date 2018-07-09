package hackme.util.plugin;

import javafx.application.Application;

import java.io.*;

import java.net.URL;
import java.net.URLClassLoader;

public class PluginLoader {

    String pluginPath;
    String className;
    String jarFile;

    public PluginLoader() {
        this.pluginPath = "src/main/resources/modules/";

//        ClassLoader classLoader = Application.class.getClassLoader();
//        File dir = new File("src/main/resources/modules/hackme-plugin.jar");
//        URL loadPath = dir.toURI().toURL();
//        URL[] classUrl = new URL[]{loadPath};
//
//        ClassLoader cl = new URLClassLoader(classUrl);
//        Class loadedClass = cl.loadClass("com.plugin.HelloSample"); // must be in package.class name format
//        Object obj = (Object) loadedClass.getConstructor().newInstance();
//        IPlugin iPlugin = ((IPlugin) obj);
//        iPlugin.printHello();
    }

//    public PluginLoader(String pluginPath, String className) throws Exception {
//        this.pluginPath = pluginPath;
//        this.className = className;
//
//        ClassLoader classLoader = Application.class.getClassLoader();
//        File dir = new File(this.pluginPath);
//        URL loadPath = dir.toURI().toURL();
//        URL[] classUrl = new URL[]{loadPath};
//
//        ClassLoader cl = new URLClassLoader(classUrl);
//        Class loadedClass = cl.loadClass(this.className); // must be in package.class name format
//        Object obj = (Object) loadedClass.getConstructor().newInstance();
//        this.iPlugin = ((IPlugin) obj);
//        this.iPlugin.printHello();
//
//    }

    public PluginLoader(String pluginPath){
        this.pluginPath = pluginPath;
    }

    public Object loadPlugin(String jarFile, String className) throws Exception {
        this.jarFile = jarFile;
        this.className = className;
        String path = this.pluginPath + this.jarFile;

//        ClassLoader classLoader = Application.class.getClassLoader();
        File dir = new File(path);
        URL loadPath = dir.toURI().toURL();
        URL[] classUrl = new URL[]{loadPath};

        ClassLoader cl = new URLClassLoader(classUrl);
        Class loadedClass = cl.loadClass(this.className); // must be in package.class name format
        Object obj = loadedClass.getConstructor().newInstance();
        return obj;
    }
}
