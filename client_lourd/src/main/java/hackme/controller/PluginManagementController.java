package hackme.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hackme.util.ApiClass;
import hackme.util.Switch;
import hackme.util.plugin.PluginLoader;
import hackme.util.plugin.PluginManagement;
import hackmelibrary.util.plguin.PluginViewPlugin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;


public class PluginManagementController {

    @FXML
    private AnchorPane baseAnchorPane;

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

    private PluginViewPlugin pvp;

    private ApiClass api = new ApiClass();

    PluginManagement pluginManagement = new PluginManagement();


    public void initialize() throws Exception {

        Path pluginPath = FileSystems.getDefault().getPath( "src/main/resources/activePlugins/plugins.json" );
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonPlugin = mapper.readTree(pluginPath.toFile());
        for (JsonNode json : jsonPlugin){
            String path = json.get("path").toString().substring(1,json.get("path").toString().length() - 1);
            try {
                PluginLoader pluginLoader = new PluginLoader("");
                this.pvp = (PluginViewPlugin) pluginLoader.loadPlugin(path);
            } catch (Exception e){
                e.getMessage();
            }
            if (this.pvp != null) {
                break;
            }
        }

        if (pvp != null){
            try{
                pvp.printScene(this.baseAnchorPane);
            } catch (Exception e){
                e.getMessage();
            }
            try{
                pvp.changeColor(this.baseAnchorPane.getChildren());
            } catch (Exception e){
                e.getMessage();
            }
        }

        try{
            PluginLoader pluginLoader = new PluginLoader();
        }catch (Exception e){

        }
        inPluginManagementTiltedPane.setCollapsible(false);
        outPluginManagementTiltedPane.setCollapsible(false);

        JsonNode jsonNode = api.getAllPlugin();
        printPlugin(jsonNode, inPluginManagementVbox, outPluginManagementVbox);
        removePlugin.setOnAction(event -> {
            if(selectedPlugin == null ||selectedPlugin.equals("")){
                this.ErrorMessage.setText("Impossible de trouver le plugin");
            }
            else {
                Path path = Paths.get(getSelectedPlugin());
                try{
                    if(Files.deleteIfExists(path)){
                        try {
                            switchscene.pluginManagement(event);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        this.ErrorMessage.setText("Vous ne possédez pas ce plugin");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        downloadPlugin.setOnAction(event -> {
            if(selectedPlugin == null ||selectedPlugin.equals("")){
                this.ErrorMessage.setText("Impossible de trouver le plugin");
            }
            else {
                String name = null;
                try {
                    if (pluginManagement.searchPlugin(name)) {
                        this.ErrorMessage.setText("vous avez déja ce plugin");
                    } else {
                        try{
                            Path file = Paths.get(getSelectedPlugin());
                            api.downloadPlugin(file.getFileName().toString());
                            name = file.getFileName().toString().substring(0,file.getFileName().toString().lastIndexOf("."));
                        }catch (Exception e){
                            e.getMessage();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                switchscene.pluginManagement(event);
            } catch (IOException e) {
                e.printStackTrace();
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

    public void pluginGestion() throws IOException {
        switchscene.pluginGestion(pluginMenuButton);
    }

    public void home() throws IOException {
        switchscene.home(pluginMenuButton);
    }

    public void logout() throws IOException {
        switchscene.logout(pluginMenuButton);
    }

    public void close() throws IOException {
        switchscene.close(pluginMenuButton);
    }

    public String getSelectedPlugin() {
        return selectedPlugin;
    }

    public void printPlugin(JsonNode jsonNodes, VBox inVbox, VBox outVbox) throws Exception {

        List<String> filenames = new ArrayList<>();

        Path direct = FileSystems.getDefault().getPath( "src/main/resources/modules/" );
        DirectoryStream<Path> stream = Files.newDirectoryStream(direct, "*.jar");

        for (Path path : stream) {
            String pathName = path.getFileName().toString().substring(0,path.getFileName().toString().lastIndexOf("."));
            filenames.add(pathName);
            Button inButton = new Button(pathName);
            inButton.setId(pathName);
            inButton.setMaxSize(Double.MAX_VALUE, 30);
            inButton.setMinHeight(30);
            inButton.setPadding(new Insets(3));
            inButton.setOnAction(event -> {
                this.selectedPlugin = path.toString();
            });
            inVbox.getChildren().add(inButton);
        }
        stream.close();

        for(JsonNode jsonNode : jsonNodes) {
            String name = jsonNode.get("name").toString().substring(1, jsonNode.get("name").toString().length() - 1);
            String id = jsonNode.get("id").toString();
            if(!filenames.contains(name)){
                Button button = new Button(name);
                button.setId(id);
                button.setMaxSize(Double.MAX_VALUE,30);
                button.setMinHeight(30);
                button.setPadding(new Insets(3));
                button.setOnAction(event -> {
                    this.selectedPlugin = button.getText();
                });
                outVbox.getChildren().add(button);
            }
        }
    }
}
