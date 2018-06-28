package Interface.util.plugin;

import java.io.File;

public class PluginManagement {

    public boolean searchPlugin(String name){
        File dir = new File("src/main/resources/modules/");

        for(File file : dir.listFiles()){
            String fileName = file.getName().substring(0, file.getName().lastIndexOf('.'));
            if (fileName.equals(name)){
                return true;
            }
        }
        return false;
    }
}
