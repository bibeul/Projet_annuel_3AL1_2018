package hackme.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hackme.util.Switch;
import hackme.util.plugin.PluginLoader;
import hackmelibrary.util.plguin.SampleViewPlugin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class SampleController {

    @FXML
    private AnchorPane basedAnchor;

    @FXML
    private TitledPane sampleTitledPane;

    @FXML
    private Pane basePane;

    @FXML
    private AnchorPane sampleAnchor;

    @FXML
    private MenuButton sampleMenuButton;

    private Switch switchscene = new Switch();

    private SampleViewPlugin sp;

    public void initialize() throws Exception {


        Path pluginPath = FileSystems.getDefault().getPath( "src/main/resources/activePlugins/plugins.json" );
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonPlugin = mapper.readTree(pluginPath.toFile());
        for (JsonNode json : jsonPlugin){
            String path = json.get("path").toString().substring(1,json.get("path").toString().length() - 1);
            try {
                PluginLoader pluginLoader = new PluginLoader("");
                this.sp = (SampleViewPlugin) pluginLoader.loadPlugin(path);
            } catch (Exception e){
                e.getMessage();
            }
            if (this.sp != null) {
                break;
            }
        }


        if (sp != null){
            try{
                sp.changeColor(this.basePane.getChildren());
            } catch (Exception e){
                e.getMessage();
            }
            try{
                sp.printScene(this.basedAnchor);
            } catch (Exception e){
                e.getMessage();
            }
        }
        sampleTitledPane.setCollapsible(false);
    }

    public void mapManagement(ActionEvent event) throws IOException {
        switchscene.mapManagement(event);
    }

    public void pluginManagement(ActionEvent event) throws IOException {
        switchscene.pluginManagement(event);
    }

    public void pluginGestion() throws IOException {
        switchscene.pluginGestion(sampleMenuButton);
    }

    public void playView(ActionEvent event) throws IOException {
        switchscene.playView(event);
    }
    public void home() throws IOException {
        switchscene.home(sampleMenuButton);
    }

    public void logout() throws IOException {
        switchscene.logout(sampleMenuButton);
    }

    public void close() throws IOException {
        switchscene.close(sampleMenuButton);
    }
}
