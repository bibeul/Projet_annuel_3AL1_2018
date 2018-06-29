package hackme.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.*;
import java.util.*;
import org.apache.commons.io.filefilter.DirectoryFileFilter;

public class MapManagement {

    public void addListMapButton(String map, VBox vbox) throws IOException {


        System.out.println(map);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonMaps = mapper.readTree(map);
        String maps = jsonMaps.get("maps").toString();

        JsonNode jsonNodes = mapper.readTree(maps);
        for (JsonNode jsonNode : jsonNodes){
            String name = jsonNode.get("name").toString().substring(1, jsonNode.get("name").toString().length() - 1);
            String id = jsonNode.get("id").toString();
            Button button1 = new Button(name);
            button1.setId(id);
            button1.setMaxWidth(Double.MAX_VALUE);
            button1.setOnAction(event -> {
                System.out.println(jsonNode);
            });
            vbox.getChildren().add(button1);
        }
    }

    /**
     *
     * @param map
     * @param fpane
     * @throws Exception
     *
     * Set all map in flowPane
     * @TODO Set button action to download the map selected
     */
    public void addMapThreeByThree(String map, FlowPane fpane) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonMaps = mapper.readTree(map);
        String maps = jsonMaps.get("maps").toString();
        JsonNode jsonNodes = mapper.readTree(maps);

        File dir = new File("src/main/resources/maps/");
        List<String> filenames = new ArrayList<>();

        File[] listDir = dir.listFiles((FileFilter) DirectoryFileFilter.DIRECTORY);
        for(File directory : listDir){
            filenames.add(directory.getName());
        }

//        for(File file : dir.listFiles()){
//            filenames.add(file.getName().substring(0, file.getName().lastIndexOf('.')));
//        }

        for(JsonNode jsonNode : jsonNodes){
            String name = jsonNode.get("name").toString().substring(1, jsonNode.get("name").toString().length() - 1);
            String id = jsonNode.get("id").toString();
            String note = jsonNode.get("note").toString();

            Button button1 = new Button("download");
            button1.setId(name);
            downloadMapButton(button1, filenames);

            Button buttonName = new Button(name);
            mapNameButton(buttonName, filenames);

            Label labelCreate = new Label("créer par : Toto");
            Label labelNote = new Label("note : " + note);

            ImageView imageView = new ImageView();
            Image image = new Image(this.getClass().getResourceAsStream("/image/game.png"));
            imageView.setImage(image);
            imageView.setFitHeight(50);
            imageView.setPreserveRatio(true);

            VBox vbox = new VBox();
            vbox.getChildren().addAll(labelCreate, labelNote);

            BorderPane borderPane = new BorderPane();
            borderPane.setPrefSize(fpane.getPrefWidth()/3.5,100);
            borderPane.setTop(buttonName);
            borderPane.setLeft(vbox);
            borderPane.setCenter(imageView);
            borderPane.setBottom(button1);
            borderPane.setPadding(new Insets(10,0,10,100));

            fpane.getChildren().add(borderPane);
        }
    }

    public void downloadMapButton(Button button, List<String> filenames){
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMaxHeight(Double.MAX_VALUE);
        button.setPrefSize(100.,20.);
        if (filenames.contains(button.getId())) {
            button.setDisable(true);
        } else {
            button.setOnAction(event -> {
                System.out.println(button.getId());
            });
        }
    }

    public void mapNameButton(Button button, List<String> filenames){
        button.setTextFill(Color.web("#FFFFFF"));
        button.setFont(new Font("Arial", 16));
        button.setAlignment(Pos.CENTER);
        button.setDisable(true);
        button.setMaxWidth(Double.MAX_VALUE);
        if(filenames.contains(button.getText())){
            button.setStyle("-fx-background-color: #00ac00");
        }
        else {
            button.setStyle("-fx-background-color: #3f3f3f");
        }
    }


}