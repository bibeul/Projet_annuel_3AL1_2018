package hackme.controller;

import hackme.util.ApiClass;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class CreateController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private TextField email;

    ApiClass api = new ApiClass();

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

        String username = this.username.getText();
        String password = this.password.getText();
        String email = this.email.getText();

        api.register(email,username,password);

        FXMLDocumentController fdc = new FXMLDocumentController();
        fdc.switchingScene(event, "login");
    }

    public void cancel(ActionEvent event) throws IOException{
        FXMLDocumentController fdc = new FXMLDocumentController();
        fdc.switchingScene(event, "login");
    }

    public void close(ActionEvent event) {
        Platform.exit();
    }
}
