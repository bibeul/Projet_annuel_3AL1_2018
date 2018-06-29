package hackme.controller;

import javafx.application.Platform;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class CreateController {

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private Stage stage;

    public void hello(){

    }

    public void create(ActionEvent event) throws IOException {

    }

    public void cancel(ActionEvent event) throws IOException{
        FXMLDocumentController fdc = new FXMLDocumentController();
        fdc.switchingScene(event, "login");
    }

    public void close(ActionEvent event) {
        Platform.exit();
    }
}
