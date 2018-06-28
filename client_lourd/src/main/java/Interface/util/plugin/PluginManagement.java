package Interface.util.plugin;

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


    public void printPlugin(String plugins, VBox inVbox, VBox outVbox) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonPlugins = mapper.readTree(plugins);
        String plugin = jsonPlugins.get("plugins").toString();
        JsonNode jsonNodes = mapper.readTree(plugin);

        File dir = new File("src/main/resources/modules/");
        List<String> filenames = new ArrayList<>();

        for(File file : dir.listFiles()){
            filenames.add(file.getName().substring(0, file.getName().lastIndexOf('.')));
            Button inButton = new Button(file.getName().substring(0, file.getName().lastIndexOf('.')));
            inButton.setId(file.getName().substring(0, file.getName().lastIndexOf('.')));
            inButton.setMaxSize(Double.MAX_VALUE, 30);
            inButton.setMinHeight(30);
            inButton.setPadding(new Insets(3));
            inButton.setOnAction(event -> {
                System.out.println(inButton.getText());
            });
            inVbox.getChildren().add(inButton);
        }

        for(JsonNode jsonNode : jsonNodes) {
            String name = jsonNode.get("name").toString().substring(1, jsonNode.get("name").toString().length() - 1);
            String id = jsonNode.get("id").toString();
            System.out.println(name);
            if(!filenames.contains(name)){
                Button button = new Button(name);
                button.setId(id);
                button.setMaxSize(Double.MAX_VALUE,30);
                button.setMinHeight(30);
                button.setPadding(new Insets(3));
                button.setOnAction(event -> {
                    System.out.println(button.getText());
                });
                outVbox.getChildren().add(button);
            }
        }
    }

}
