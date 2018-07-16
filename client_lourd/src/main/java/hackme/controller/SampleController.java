package hackme.controller;

import hackme.util.Switch;
import hackme.util.plugin.PluginLoader;
import hackmelibrary.util.plguin.ScenePlugin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

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

    private ScenePlugin sp;

    public void initialize() throws Exception {

        try{
            PluginLoader pluginLoader = new PluginLoader("src/main/resources/modules/");
            this.sp = (ScenePlugin) pluginLoader.loadPlugin("hackme-sample-plugin.jar");
//            ip.printHello();
//            PluginLoader pluginLoader = new PluginLoader();
        }catch (Exception e){
            e.getMessage();
        }

        if (sp != null){
//            sp.changeColor(this.basePane.getChildren());
            sp.printScene(this.basedAnchor);
        }
        sampleTitledPane.setCollapsible(false);
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
