package hackme.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hackme.util.ApiClass;
import hackme.util.plugin.PluginLoader;
import hackmelibrary.util.plguin.LoginViewPlugin;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class LoginController {

    @FXML
    private AnchorPane baseAnchor;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label loginLabel;

    private LoginViewPlugin lvp;

    public void initialize() throws Exception {
        Path pluginPath = FileSystems.getDefault().getPath( "src/main/resources/activePlugins/plugins.json" );
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonPlugin = mapper.readTree(pluginPath.toFile());
        for (JsonNode json : jsonPlugin){
            String path = json.get("path").toString().substring(1,json.get("path").toString().length() - 1);
            try {
                PluginLoader pluginLoader = new PluginLoader("");
                this.lvp = (LoginViewPlugin) pluginLoader.loadPlugin(path);
            } catch (Exception e){
                e.getMessage();
            }
            if (this.lvp != null) {
                break;
            }
        }

        if (lvp != null){
            try{
                lvp.changeColor(this.baseAnchor.getChildren());
            } catch (Exception e){
                e.getMessage();
            }
            try{
                lvp.printScene(this.baseAnchor);
            } catch (Exception e){
                e.getMessage();
            }
        }
    }


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
        String username = this.username.getText();
        String password = this.password.getText();

        apiClass.signIn(username,password);
        if(apiClass.getAuth() == "true"){
//            this.loginLabel.setText("");
            System.setProperty("user", username);
            System.setProperty("token", apiClass.getApi_key());
            FXMLDocumentController fdc = new FXMLDocumentController();
            fdc.switchingScene(event, "sample");
        }
        else {
            this.loginLabel.setText("Bad username or password");
            this.loginLabel.setFont(new Font("Arial", 14));
            this.loginLabel.setStyle("-fx-text-fill: #db0000");
        }
    }

    public void create(ActionEvent event) throws IOException {
        FXMLDocumentController fdc = new FXMLDocumentController();
        fdc.switchingScene(event, "create");
    }

    public void close() throws IOException {
        Platform.exit();
    }
}
