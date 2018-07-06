package hackme.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLDocumentController {


    public void  switchingScene(ActionEvent event, String name) throws IOException {
        Parent main = FXMLLoader.load(getClass().getClassLoader().getResource("view/"+name+".fxml"));
        Scene main_p =  new Scene(main);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(main_p);
        window.show();
    }
}
