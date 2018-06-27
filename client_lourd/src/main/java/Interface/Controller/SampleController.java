package Interface.Controller;

import Interface.util.TestConstant;
import Interface.util.plugin.PluginLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SampleController {

    @FXML
    private TitledPane sampleTitledPane;

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
        FXMLDocumentController fdc = new FXMLDocumentController();
        fdc.switchingScene(event,"mapManagement");
    }

    public void pluginManagement(ActionEvent event) throws IOException {
        FXMLDocumentController fdc = new FXMLDocumentController();
        fdc.switchingScene(event,"pluginManagement");
    }

    public void playView(ActionEvent event) throws IOException {
        FXMLDocumentController fdc = new FXMLDocumentController();
        fdc.switchingScene(event,"play");
    }
}
