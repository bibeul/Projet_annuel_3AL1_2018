package hackme.controller;

import com.fasterxml.jackson.databind.JsonNode;
import hackme.util.ApiClass;
import hackme.util.Switch;
import hackme.util.MapManagement;
import hackme.util.plugin.PluginLoader;
import hackmelibrary.util.plguin.MapViewPlugin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class MapManagementController {

    @FXML
    AnchorPane baseMapAnchor;

    @FXML
    private ScrollPane mapManagementScrollPane;

    @FXML
    private FlowPane mapManagementFPane;

    @FXML
    private TitledPane mapManagementTiltedPane;

    @FXML
    private MenuButton menuButtonMapManagement;

    private MapViewPlugin mvp = null;

    private MapManagement mapManagement = new MapManagement();

    private Switch switchscene = new Switch();

    private JsonNode map;

    private ApiClass apiClass = new ApiClass();


    public void initialize() throws IOException {
        Path direct = FileSystems.getDefault().getPath( "src/main/resources/modules/" );
        DirectoryStream<Path> stream = Files.newDirectoryStream(direct, "*.jar");
        for (Path path : stream) {
            try {
                PluginLoader pluginLoader = new PluginLoader("");
                this.mvp = (MapViewPlugin) pluginLoader.loadPlugin(path.toString());
            } catch (Exception e) {
                e.getMessage();
            }
            if (this.mvp != null) {
                break;
            }
        }
        if (mvp != null){
            try{
                mvp.changeColor(this.baseMapAnchor.getChildren());
            } catch (Exception e){
                e.getMessage();
            }
            try{
                mvp.printScene(this.baseMapAnchor);
            } catch (Exception e){
                e.getMessage();
            }
        }
        mapManagementTiltedPane.setCollapsible(false);
        mapManagementFPane.setMaxWidth(Double.MAX_VALUE);
        mapManagementFPane.setMaxHeight(Double.MAX_VALUE);
        map = apiClass.getAllMap();
        controlListMap();

    }


    public void controlListMap() throws IOException {
        List<String> filenames = new ArrayList<>();
        List<Path> paths = new ArrayList();

        Files.list(Paths.get("src/main/resources/maps/"))
                .forEach(path -> {
                    paths.add(path);
                });

        for (Path path : paths){
            if(Files.isDirectory(path)){
                filenames.add(path.getFileName().toString());
                System.out.println(path.getFileName().toString());
            }
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
    public void home() throws IOException {
        switchscene.home(menuButtonMapManagement);
    }

    public void logout() throws IOException {
        switchscene.logout(menuButtonMapManagement);
    }

    public void close() throws IOException {
        switchscene.close(menuButtonMapManagement);
    }
}
