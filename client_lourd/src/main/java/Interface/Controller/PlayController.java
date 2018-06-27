package Interface.Controller;

import Interface.util.TestConstant;
import Interface.util.plugin.PluginLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import Interface.util.MapManagement;

public class PlayController {

    @FXML
    private VBox playMapVbox;

    @FXML
    private VBox playPluginVbox;

    private MapManagement mapManagement = new MapManagement();

    public void initialize() throws Exception {
        try{
            PluginLoader pluginLoader = new PluginLoader();
        }catch (Exception e){

        }
        mapManagement.addListMapButton(TestConstant.jsonString, this.playMapVbox);
    }

    public VBox getPlayMapVbox() {
        return playMapVbox;
    }

    public void setPlayMapVbox(VBox playMapVbox) {
        this.playMapVbox = playMapVbox;
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
