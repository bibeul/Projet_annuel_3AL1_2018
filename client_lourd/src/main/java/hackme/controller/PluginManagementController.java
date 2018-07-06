package hackme.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hackme.util.Switch;
import hackme.util.TestConstant;
import hackme.util.plugin.PluginLoader;
import hackme.util.plugin.PluginManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PluginManagementController {

    @FXML
    private VBox inPluginManagementVbox;

    @FXML
    private VBox outPluginManagementVbox;

    @FXML
    private TitledPane inPluginManagementTiltedPane;

    @FXML
    private TitledPane outPluginManagementTiltedPane;

    @FXML
    private MenuButton pluginMenuButton;

    @FXML
    private Button downloadPlugin;

    @FXML
    private Button removePlugin;

    @FXML
    private Label ErrorMessage;

    private String selectedPlugin;

    private Switch switchscene = new Switch();

    PluginManagement pluginManagement = new PluginManagement();


    public void initialize() throws Exception {
        try{
            PluginLoader pluginLoader = new PluginLoader();
        }catch (Exception e){

        }
        inPluginManagementTiltedPane.setCollapsible(false);
        outPluginManagementTiltedPane.setCollapsible(false);

        printPlugin(TestConstant.jsonPlugin, inPluginManagementVbox, outPluginManagementVbox);
        removePlugin.setOnAction(event -> {
            if(selectedPlugin == null ||selectedPlugin.equals("")){
                this.ErrorMessage.setText("Impossible de trouver le plugin");
            }
            else {
                if (pluginManagement.searchPlugin(getSelectedPlugin())) {
                    this.ErrorMessage.setText("");
                    System.out.println(getSelectedPlugin());
                } else {
                    this.ErrorMessage.setText("Vous ne possédez pas ce plugin");
                }
            }
        });
        downloadPlugin.setOnAction(event -> {
            if(selectedPlugin == null ||selectedPlugin.equals("")){
                this.ErrorMessage.setText("Impossible de trouver le plugin");
            }
            else {
                if (pluginManagement.searchPlugin(getSelectedPlugin())) {
                    this.ErrorMessage.setText("vous avez déja ce plugin");
                } else {
                    this.ErrorMessage.setText("");
                    System.out.println(getSelectedPlugin());
                }
            }
        });
    }


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private Stage stage;

    public void hello(){

    }

    public void mapManagement(ActionEvent event) throws IOException {
        switchscene.mapManagement(event);
    }

    public void pluginManagement(ActionEvent event) throws IOException {
        switchscene.pluginManagement(event);
    }

    public void playView(ActionEvent event) throws IOException {
        switchscene.playView(event);
    }
    public void home(ActionEvent event) throws IOException {
        switchscene.home(event,pluginMenuButton);
    }

    public void logout(ActionEvent event) throws IOException {
        switchscene.logout(event,pluginMenuButton);
    }

    public void close(ActionEvent event) throws IOException {
        switchscene.close(event,pluginMenuButton);
    }

    public String getSelectedPlugin() {
        return selectedPlugin;
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
                this.selectedPlugin = inButton.getText();
//                System.out.println(inButton.getText());
            });
            inVbox.getChildren().add(inButton);
        }

        for(JsonNode jsonNode : jsonNodes) {
            String name = jsonNode.get("name").toString().substring(1, jsonNode.get("name").toString().length() - 1);
            String id = jsonNode.get("id").toString();
//            System.out.println(name);
            if(!filenames.contains(name)){
                Button button = new Button(name);
                button.setId(id);
                button.setMaxSize(Double.MAX_VALUE,30);
                button.setMinHeight(30);
                button.setPadding(new Insets(3));
                button.setOnAction(event -> {
                    this.selectedPlugin = button.getText();
//                    System.out.println(button.getText());
                });
                outVbox.getChildren().add(button);
            }
        }
    }
}
