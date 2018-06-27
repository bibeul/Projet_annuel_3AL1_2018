package Interface.Controller;

import Interface.util.TestConstant;
import Interface.util.MapManagement;
import Interface.util.plugin.PluginLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MapManagementController {

    @FXML
    private ScrollPane mapManagementScrollPane;

    @FXML
    private FlowPane mapManagementFPane;

    private MapManagement mapManagement = new MapManagement();


    public void initialize() throws Exception {
        try{
            PluginLoader pluginLoader = new PluginLoader();
        }catch (Exception e){

        }
        this.mapManagementFPane.setMaxWidth(Double.MAX_VALUE);
        this.mapManagementFPane.setMaxHeight(Double.MAX_VALUE);
//        mapManagementVBox.setFillWidth(true);
        mapManagement.addMapThreeByThree(TestConstant.jsonString, this.mapManagementFPane);


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
