package hackme.util;

import hackme.controller.FXMLDocumentController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

import java.io.IOException;

public class Switch {

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
    public void home(Node menuButton) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/sample.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) menuButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void logout(Node menuButton) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) menuButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void close(Node menuButton) throws IOException {
        Stage stage = (Stage) menuButton.getScene().getWindow();
        stage.close();
    }
}
