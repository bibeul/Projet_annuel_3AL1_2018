package hackme.controller;

import hackme.util.Switch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SampleController {

    @FXML
    private TitledPane sampleTitledPane;

    @FXML
    private MenuButton sampleMenuButton;

    private Switch switchscene = new Switch();

    public void initialize() throws Exception {
        sampleTitledPane.setCollapsible(false);
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
        switchscene.home(event,sampleMenuButton);
    }

    public void logout(ActionEvent event) throws IOException {
        switchscene.logout(event,sampleMenuButton);
    }

    public void close(ActionEvent event) throws IOException {
        switchscene.close(event,sampleMenuButton);
    }
}
