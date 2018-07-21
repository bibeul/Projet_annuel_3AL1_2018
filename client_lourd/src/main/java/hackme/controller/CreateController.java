package hackme.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hackme.util.ApiClass;
import hackme.util.plugin.PluginLoader;
import hackmelibrary.util.plguin.CreateViewPlugin;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class CreateController {

    @FXML
    private AnchorPane baseCreateAnchor;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private TextField email;

    @FXML
    private Label ErrorMessage;

    private CreateViewPlugin cvp;

    ApiClass api = new ApiClass();

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private Stage stage;

    public void initialize() throws Exception {
        Path pluginPath = FileSystems.getDefault().getPath( "src/main/resources/activePlugins/plugins.json" );
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonPlugin = mapper.readTree(pluginPath.toFile());
        for (JsonNode json : jsonPlugin){
            String path = json.get("path").toString().substring(1,json.get("path").toString().length() - 1);
            try {
                PluginLoader pluginLoader = new PluginLoader("");
                this.cvp = (CreateViewPlugin) pluginLoader.loadPlugin(path);
            } catch (Exception e){
                e.getMessage();
            }
            if (this.cvp != null) {
                break;
            }
        }

        if (cvp != null){
            try{
                cvp.changeColor(this.baseCreateAnchor.getChildren());
            } catch (Exception e){
                e.getMessage();
            }
            try{
                cvp.printScene(this.baseCreateAnchor);
            } catch (Exception e){
                e.getMessage();
            }
        }
    }

    public void hello(){

    }

    public void create(ActionEvent event) throws IOException {

        String username = this.username.getText();
        String password = this.password.getText();
        String email = this.email.getText();

        boolean valid = EmailValidator.getInstance().isValid(email);

        if(valid){
            api.register(email,username,password);

            FXMLDocumentController fdc = new FXMLDocumentController();
            fdc.switchingScene(event, "login");
        }
        else {
            ErrorMessage.setText("votre email est incorrect");
        }
    }

    public void cancel(ActionEvent event) throws IOException{
        FXMLDocumentController fdc = new FXMLDocumentController();
        fdc.switchingScene(event, "login");
    }

    public void close(ActionEvent event) {
        Platform.exit();
    }
}
