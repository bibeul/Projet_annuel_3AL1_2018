package hackme.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hackme.util.Switch;
import hackme.util.plugin.PluginLoader;
import hackmelibrary.util.plguin.LoadPluginView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class LoadPluginController {

    @FXML
    private AnchorPane baseAnchor;

    @FXML
    private VBox enablePlugins;

    @FXML
    private VBox disablePlugins;

    @FXML
    private MenuButton pluginMenuButton;

    @FXML
    private Button disableButton;

    @FXML
    private Button enableButton;

    private Switch switchscene = new Switch();

    private LoadPluginView lpv;

    private String selectedPlugin;

    public String getSelectedPlugin() {
        return selectedPlugin;
    }

    public void initialize() throws Exception {

        Path pluginPath = FileSystems.getDefault().getPath( "src/main/resources/activePlugins/plugins.json" );
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonPlugin = mapper.readTree(pluginPath.toFile());
        for (JsonNode json : jsonPlugin){
            String path = json.get("path").toString().substring(1,json.get("path").toString().length() - 1);
            try {
                PluginLoader pluginLoader = new PluginLoader("");
                this.lpv = (LoadPluginView) pluginLoader.loadPlugin(path);
            } catch (Exception e){
                e.getMessage();
            }
            if (this.lpv != null) {
                break;
            }
        }

        dispatchPlugins(this.enablePlugins, this.disablePlugins);

        this.disableButton.setOnAction(event -> {
            JSONArray jsonArray = new JSONArray();
            Path path = Paths.get(getSelectedPlugin());
            for (JsonNode json : jsonPlugin){
                Path jsonPath = Paths.get(json.get("path").toString().substring(1,json.get("path").toString().length() - 1));
                if (!jsonPath.equals(path)){
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", json.get("name").toString().substring(1,json.get("name").toString().length() - 1));
                    jsonObject.put("path", json.get("path").toString().substring(1,json.get("path").toString().length() - 1));
                    jsonArray.put(jsonObject);
                    try {
                        Files.deleteIfExists(pluginPath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            write(pluginPath, jsonArray);
            try {
                pluginGestion();
            } catch (IOException e) {
                e.getMessage();
            }
        });
        this.enableButton.setOnAction(event -> {
            JSONArray jsonArray = new JSONArray();
            Path path = Paths.get(getSelectedPlugin());
            boolean exist = false;
            for (JsonNode json : jsonPlugin) {
                JSONObject jsonObject = new JSONObject();
                String jarName = json.get("name").toString().substring(1, json.get("name").toString().length() - 1);
                Path jsonPath = Paths.get(json.get("path").toString().substring(1, json.get("path").toString().length() - 1));
                String jarPath = jsonPath.toString().replace("\\", "/");
                jsonObject.put("name", jarName);
                jsonObject.put("path", jarPath);
                jsonArray.put(jsonObject);
                if (jsonPath.equals(path)){
                    exist = true;
                }
            }
            System.out.println("jsonarray : " + jsonArray);
            if (!exist){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", path.getFileName().toString().substring(0,path.getFileName().toString().lastIndexOf(".")));
                jsonObject.put("path", path.toString().replace("\\", "/"));
                jsonArray.put(jsonObject);
            }
            System.out.println("jsonarray : " + jsonArray);
            try {
                Files.deleteIfExists(pluginPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            write(pluginPath, jsonArray);
            try {
                switchscene.pluginGestion(pluginMenuButton);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void write(Path path, JSONArray jsonArray){
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream fos = new FileOutputStream(path.toString());
            fos.write(jsonArray.toString().getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            pluginGestion();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void dispatchPlugins(VBox inVbox, VBox outVbox) throws Exception {

        List<String> filenames = new ArrayList<>();

        Path pluginPath = FileSystems.getDefault().getPath( "src/main/resources/activePlugins/plugins.json" );
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonPlugin = mapper.readTree(pluginPath.toFile());
        ArrayList<String> loadedPlugins = new ArrayList();

        for(JsonNode jsonNode : jsonPlugin){
            String path = jsonNode.get("path").toString().substring(1,jsonNode.get("path").toString().length() - 1);
            Path jsonPath = Paths.get(path);
            if (!path.equals("") && path != null){
                loadedPlugins.add(jsonPath.toString());
            }

        }

        Path direct = FileSystems.getDefault().getPath("src/main/resources/modules/");
        DirectoryStream<Path> stream = Files.newDirectoryStream(direct, "*.jar");

        for (Path path : stream) {
            String pathName = path.getFileName().toString().substring(0, path.getFileName().toString().lastIndexOf("."));
            filenames.add(pathName);
            Button button = new Button(pathName);
            button.setId(pathName);
            button.setMaxSize(Double.MAX_VALUE, 30);
            button.setMinHeight(30);
            button.setPadding(new Insets(3));
            button.setOnAction(event -> {
                this.selectedPlugin = path.toString();
            });
            if (loadedPlugins.contains(path.toString())){
                inVbox.getChildren().add(button);
            }
            else {
                outVbox.getChildren().add(button);
            }

        }
        stream.close();
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

    public void pluginGestion() throws IOException {
        switchscene.pluginGestion(pluginMenuButton);
    }

    public void home() throws IOException {
        switchscene.home(pluginMenuButton);
    }

    public void logout() throws IOException {
        switchscene.logout(pluginMenuButton);
    }

    public void close() throws IOException {
        switchscene.close(pluginMenuButton);
    }
}
