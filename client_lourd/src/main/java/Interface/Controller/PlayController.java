package Interface.Controller;

import Interface.util.plugin.PluginLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Interface.util.MapManagement;

public class PlayController {

    @FXML
    private VBox playMapVbox;

    @FXML
    private VBox playPluginVbox;

    @FXML
    private TitledPane playTiltedPane;

    @FXML
    private Button playButton;

    private File selectedMap;


    private MapManagement mapManagement = new MapManagement();

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


    public void printDowloadedMap(VBox vbox){
        File dir = new File("src/main/resources/maps/");
        Map<String, File> map = new HashMap<>();
        String filenames;

        for(File file : dir.listFiles()){
            filenames = file.getName().substring(0, file.getName().lastIndexOf('.'));
            map.put(filenames, file);
        }

        for(Map.Entry<String, File> entry : map.entrySet()){
            System.out.println(entry.getKey());
            Button button1 = new Button(entry.getKey());
            button1.setId(entry.getKey());
            button1.setOnAction( event -> {
                this.selectedMap = entry.getValue();
//                System.out.println(entry.getValue());
            });
            button1.setMaxWidth(Double.MAX_VALUE);
            vbox.getChildren().add(button1);
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
