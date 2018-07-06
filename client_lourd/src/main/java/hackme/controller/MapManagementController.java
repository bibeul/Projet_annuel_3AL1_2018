package hackme.controller;

import com.fasterxml.jackson.databind.JsonNode;
import hackme.util.ApiClass;
import hackme.util.Switch;
import hackme.util.MapManagement;
import hackme.util.plugin.PluginLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.FlowPane;
import org.apache.commons.io.filefilter.DirectoryFileFilter;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapManagementController {

    @FXML
    private ScrollPane mapManagementScrollPane;

    @FXML
    private FlowPane mapManagementFPane;

    @FXML
    private TitledPane mapManagementTiltedPane;

    @FXML
    private MenuButton menuButtonMapManagement;

    private MapManagement mapManagement = new MapManagement();

    private Switch switchscene = new Switch();

    private JsonNode map;

    private ApiClass apiClass = new ApiClass();


    public void initialize() {
        try{
            PluginLoader pluginLoader = new PluginLoader("src/main/resources/modules/hackme-plugin.jar", "com.plugin.HelloSample");
        }catch (Exception e){
            e.getMessage();
        }
        mapManagementTiltedPane.setCollapsible(false);
        mapManagementFPane.setMaxWidth(Double.MAX_VALUE);
        mapManagementFPane.setMaxHeight(Double.MAX_VALUE);
        map = apiClass.getAllMap();
        controlListMap(map,this.mapManagementFPane);

    }


    public void controlListMap(JsonNode jsonNode, FlowPane fpane){

        File dir = new File("src/main/resources/maps/");
        List<String> filenames = new ArrayList<>();

        File[] listDir = dir.listFiles((FileFilter) DirectoryFileFilter.DIRECTORY);
        for(File directory : listDir){
            filenames.add(directory.getName());
        }

        mapManagement.addMap(map,filenames,this.mapManagementFPane);
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
        switchscene.home(event,menuButtonMapManagement);
    }

    public void logout(ActionEvent event) throws IOException {
        switchscene.logout(event,menuButtonMapManagement);
    }

    public void close(ActionEvent event) throws IOException {
        switchscene.close(event,menuButtonMapManagement);
    }
}
