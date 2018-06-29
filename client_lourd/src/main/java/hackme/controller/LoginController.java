package hackme.controller;

import hackme.util.ApiClass;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Label loginLabel;

    ApiClass apiClass = new ApiClass();

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private Stage stage;

    public void hello(){

    }

    public void connection(ActionEvent event) throws IOException {
        String email = this.email.getText();
        String password = this.password.getText();

        apiClass.signIn(email,password);
        if(apiClass.getAuth() == "true"){
//            this.loginLabel.setText("");
            FXMLDocumentController fdc = new FXMLDocumentController();
            fdc.switchingScene(event, "sample");
        }
        else {
            this.loginLabel.setText("Bad email or password");
            this.loginLabel.setFont(new Font("Arial", 14));
            this.loginLabel.setStyle("-fx-text-fill: #db0000");
        }
    }

    public void create(ActionEvent event) throws IOException {
        FXMLDocumentController fdc = new FXMLDocumentController();
        fdc.switchingScene(event, "create");
    }

    public void close(ActionEvent event) throws IOException {
        Platform.exit();
    }
}
