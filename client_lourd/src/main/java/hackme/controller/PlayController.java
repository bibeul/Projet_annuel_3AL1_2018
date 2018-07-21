package hackme.controller;

import hackme.util.Switch;
import hackme.util.plugin.PluginLoader;
import hackme.util.plugin.PluginManagement;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hackmelibrary.util.plguin.IPlugin;
import hackmelibrary.util.plguin.PlayViewPlugin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import hackme.game.state.StateGame;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import hackme.util.MapManagement;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.newdawn.slick.SlickException;

public class PlayController {

    @FXML
    private AnchorPane basedAnchorPane;

    @FXML
    private VBox playMapVbox;

    @FXML
    private TitledPane playTiltedPane;

    @FXML
    private Button playButton;

    @FXML
    private MenuButton playMenuButton;

    private File selectedMap;

    private MapManagement mapManagement = new MapManagement();

    private PluginManagement pluginManagement = new PluginManagement();

    private Switch switchscene = new Switch();

    private IPlugin ip;

    private PlayViewPlugin pp;

    /**
     * Initialize play.fxml
     */
    public void initialize() throws IOException {
        Path pluginPath = FileSystems.getDefault().getPath( "src/main/resources/activePlugins/plugins.json" );
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonPlugin = mapper.readTree(pluginPath.toFile());
        for (JsonNode json : jsonPlugin){
            String path = json.get("path").toString().substring(1,json.get("path").toString().length() - 1);
            try {
                PluginLoader pluginLoader = new PluginLoader("");
                this.ip = (IPlugin) pluginLoader.loadPlugin(path);
            } catch (Exception e){
                e.getMessage();
            }
            if (this.ip != null) {
                break;
            }
            try {
                PluginLoader pluginLoader = new PluginLoader("");
                this.pp = (PlayViewPlugin) pluginLoader.loadPlugin(path);
            } catch (Exception e){
                e.getMessage();
            }
            if (this.pp != null) {
                break;
            }
        }
        if (ip != null){
            ip.printHello();
        }

        if (pp != null){
            try{
                pp.changeColor(this.basedAnchorPane.getChildren());
            } catch (Exception e){
                e.getMessage();
            }
            try{
                pp.printScene(this.basedAnchorPane);
            } catch (Exception e){
                e.getMessage();
            }
        }

        playTiltedPane.setCollapsible(false);
        printDowloadedMap(this.playMapVbox);

        playButton.setOnAction(event -> {
            String[] arg = {String.valueOf(getSelectedMap())};
            try {
                System.setProperty("selectedMap", getSelectedMap().toString());
                StateGame.main(arg);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        });
    }

    public void mapManagement(ActionEvent event) throws IOException {
        switchscene.mapManagement(event);
    }

    public void pluginManagement(ActionEvent event) throws IOException {
        switchscene.pluginManagement(event);
    }

    public void pluginGestion() throws IOException {
        switchscene.pluginGestion(playMenuButton);
    }

    public void playView(ActionEvent event) throws IOException {
        switchscene.playView(event);
    }
    public void home() throws IOException {
        switchscene.home(playMenuButton);
    }

    public void logout() throws IOException {
        switchscene.logout(playMenuButton);
    }

    public void close() throws IOException {
        switchscene.close(playMenuButton);
    }


    public void printDowloadedMap(VBox vbox){
        File dir = new File("src/main/resources/maps/");
        Map<String, File> map = new HashMap<>();
        File[] listDir = dir.listFiles((FileFilter) DirectoryFileFilter.DIRECTORY);

        for(File file : listDir){
            map.put(file.getName(), file);
        }

        for(Map.Entry<String, File> entry : map.entrySet()){
            Button button1 = new Button(entry.getKey());
            button1.setId(entry.getKey());
            button1.setOnAction( event -> {
                this.selectedMap = entry.getValue();
            });
            button1.setMaxWidth(Double.MAX_VALUE);
            vbox.getChildren().add(button1);
        }
    }

    public File getSelectedMap() {
        return selectedMap;
    }

    public MapManagement getMapManagement() {
        return mapManagement;
    }


}
