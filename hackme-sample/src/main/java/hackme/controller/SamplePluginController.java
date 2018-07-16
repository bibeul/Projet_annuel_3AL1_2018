package hackme.controller;

import hackme.util.Switch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;

import java.io.IOException;

public class SamplePluginController {

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button playButton;

    @FXML
    private Button mapButton;

    @FXML
    private Button pluginButton;

    public void initialize() throws Exception {

        System.out.println("OK google raconte moi une histoire !!!!!!!!!");
//        this.playButton.setOnAction(event -> {
//            playView(event);
//        });

//        this.mapButton.setOnAction(event -> {
//            try {
//                mapManagement(event);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        this.pluginButton.setOnAction(event -> {
//            try {
//                pluginManagement(event);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
    }

    private Switch switchscene = new Switch();

    public void mapManagement(ActionEvent event) throws IOException {
        switchscene.mapManagement(event);
    }

    public void pluginManagement(ActionEvent event) throws IOException {
        switchscene.pluginManagement(event);
    }

    public void playView(ActionEvent event) throws IOException {
        switchscene.playView(event);
//        System.out.println("toto");
    }
//    public void home() throws IOException {
//        switchscene.home(this.menuBar);
//    }

    public void logout() throws IOException {
        switchscene.logout(this.menuBar);
    }

    public void close() throws IOException {
        switchscene.close(this.menuBar);
    }
}
