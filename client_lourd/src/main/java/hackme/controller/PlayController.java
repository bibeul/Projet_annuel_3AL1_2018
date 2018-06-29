package hackme.controller;

import hackme.util.Switch;
import hackme.util.plugin.PluginLoader;
import hackme.util.plugin.PluginManagement;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import hackme.util.MapManagement;
import javafx.scene.text.Font;
import org.apache.commons.io.filefilter.DirectoryFileFilter;

public class PlayController {

    @FXML
    private VBox playMapVbox;

    @FXML
    private VBox playPluginVbox;

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


    /**
     * Initialize play.fxml
     */
    public void initialize(){
        try{
            PluginLoader pluginLoader = new PluginLoader();
        }catch (Exception e){

        }
        playTiltedPane.setCollapsible(false);
        printDowloadedMap(this.playMapVbox);

        playButton.setOnAction(event -> {
            System.out.println(getSelectedMap());
        });
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
        switchscene.home(event,playMenuButton);
    }

    public void logout(ActionEvent event) throws IOException {
        switchscene.logout(event,playMenuButton);
    }

    public void close(ActionEvent event) throws IOException {
        switchscene.close(event,playMenuButton);
    }


    public void printDowloadedMap(VBox vbox){
        File dir = new File("src/main/resources/maps/");
        Map<String, File> map = new HashMap<>();
        File[] listDir = dir.listFiles((FileFilter) DirectoryFileFilter.DIRECTORY);
        File requiredPlugins;
//        String filenames;

        for(File file : listDir){
//            filenames = file.getName().substring(0, file.getName().lastIndexOf('.'));
            map.put(file.getName(), file);
        }

        for(Map.Entry<String, File> entry : map.entrySet()){
            Button button1 = new Button(entry.getKey());
            button1.setId(entry.getKey());
            button1.setOnAction( event -> {
                this.selectedMap = entry.getValue();
                for(File directory : listDir){
                    if(directory.getName().equals(this.selectedMap.getName())){
                        for (File file : directory.listFiles()){
                            if(file.getName().equals("plugin.json")){
                                try {
                                    printRequiredPlugin(file);
                                } catch (Exception e) {
                                    e.getMessage();
                                }
                                break;
                            }
                        }
                        break;
//                        requiredPlugins = File(file.get)
                    }
                }
            });
            button1.setMaxWidth(Double.MAX_VALUE);
            vbox.getChildren().add(button1);
        }
    }

    public void printRequiredPlugin(File file) throws Exception {
        int i = 0;
        this.playPluginVbox.getChildren().clear();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonPlugin = mapper.readValue(new File(String.valueOf(file)), JsonNode.class);
        String plugin = jsonPlugin.get("plugin").toString();
        JsonNode jsonName = mapper.readTree(plugin);
        for( JsonNode jsonNode : jsonName){
            String name = jsonNode.get("name").toString().substring(1, jsonNode.get("name").toString().length() - 1);

            Label label = new Label(name);
            label.setFont(new Font("Arial", 14));

            ImageView imageView = new ImageView();
            Image image;
            if(pluginManagement.searchPlugin(name)){
                image = new Image(this.getClass().getResourceAsStream("/image/valide.jpg"));
            }
            else {
                image = new Image(this.getClass().getResourceAsStream("/image/erreur.png"));
            }
            imageView.setImage(image);
            imageView.setFitHeight(50);
            imageView.setPreserveRatio(true);

            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(label);
            borderPane.setRight(imageView);

            if(i % 2 == 0){
                borderPane.setStyle("-fx-background-color: #d9cdff");
            }
            else {
                borderPane.setStyle("-fx-background-color: #e6e6e6");
            }
            this.playPluginVbox.getChildren().add(borderPane);
            i++;
        }

    }

    public VBox getPlayMapVbox() {
        return playMapVbox;
    }

    public void setPlayMapVbox(VBox playMapVbox) {
        this.playMapVbox = playMapVbox;
    }

    public VBox getPlayPluginVbox() {
        return playPluginVbox;
    }

    public TitledPane getPlayTiltedPane() {
        return playTiltedPane;
    }

    public Button getPlayButton() {
        return playButton;
    }

    public File getSelectedMap() {
        return selectedMap;
    }

    public MapManagement getMapManagement() {
        return mapManagement;
    }
}
