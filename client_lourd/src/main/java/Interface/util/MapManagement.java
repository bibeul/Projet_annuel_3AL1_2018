package Interface.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.net.URL;

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

    public void addMapThreeByThree(String map, FlowPane fpane) throws IOException {
        int i = 3;

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonMaps = mapper.readTree(map);
        String maps = jsonMaps.get("maps").toString();
        JsonNode jsonNodes = mapper.readTree(maps);

        for(JsonNode jsonNode : jsonNodes){
            String name = jsonNode.get("name").toString().substring(1, jsonNode.get("name").toString().length() - 1);
            String id = jsonNode.get("id").toString();
            String note = jsonNode.get("note").toString();


            Button button1 = new Button("download");
            button1.setId(id);
            button1.setOnAction(event -> {
                System.out.println(jsonNode);
            });
            button1.setMaxWidth(Double.MAX_VALUE);
            button1.setMaxHeight(Double.MAX_VALUE);
            button1.setPrefSize(100.,20.);


            Button buttonName = new Button(name);
            buttonName.setStyle("-fx-background-color: #3f3f3f");
            buttonName.setTextFill(Color.web("#FFFFFF"));
            buttonName.setFont(new Font("Arial", 16));
            buttonName.setAlignment(Pos.CENTER);
            buttonName.setDisable(true);
            buttonName.setMaxWidth(Double.MAX_VALUE);

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
}
