package hackme.util.plugin;

import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.jar.Attributes;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

public class PluginLoader {

    String pluginPath;
    String jarFile;

    public PluginLoader() {
        this.pluginPath = "src/main/resources/modules/";
    }

    public PluginLoader(String pluginPath){
        this.pluginPath = pluginPath;
    }

    public Object loadPlugin(String jarFile) throws Exception {
        this.jarFile = jarFile;
        String path = this.pluginPath + this.jarFile;

        Path file = Paths.get(path);
        URL load = file.toUri().toURL();
        URL[] classUrl = new URL[]{load};

        InputStream in = load.openStream();
        JarInputStream jis = new JarInputStream(in);
        Manifest mf = jis.getManifest();
        Attributes att = mf.getMainAttributes();
        String name = mf.getMainAttributes().getValue("Main-Class");

        ClassLoader cl = new URLClassLoader(classUrl);
        Class loadedClass = cl.loadClass(name); // must be in package.class name format
        Object obj = loadedClass.getConstructor().newInstance();
        return obj;
    }
}
