package hackme.util.plugin;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
