package hackme.controller;

import hackme.util.Switch;
import hackme.util.TestConstant;
import hackme.util.plugin.PluginLoader;
import hackme.util.plugin.PluginManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

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

    private Switch switchscene = new Switch();

    PluginManagement pluginManagement = new PluginManagement();


    public void initialize() throws Exception {
        try{
            PluginLoader pluginLoader = new PluginLoader();
        }catch (Exception e){

        }
        inPluginManagementTiltedPane.setCollapsible(false);
        outPluginManagementTiltedPane.setCollapsible(false);

        pluginManagement.printPlugin(TestConstant.jsonPlugin, inPluginManagementVbox, outPluginManagementVbox);
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
}
