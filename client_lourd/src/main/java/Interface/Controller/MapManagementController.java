package Interface.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MapManagementController {

    @FXML
    private ScrollPane mapManagementScrollPane;

    public void initialize(){


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
    public void home(ActionEvent event) throws IOException {
        FXMLDocumentController fdc = new FXMLDocumentController();
        fdc.switchingScene(event,"sample");
    }
}
