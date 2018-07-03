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
     * @param jsonMaps
     * @param filenames
     * @param fpane
     *
     * Set all map in flowPane
     * @TODO Set button action to download the map selected
     */
//    public void addRemoteMap(JsonNode jsonMaps, List<String> filenames, FlowPane fpane) {
//
//        for(JsonNode jsonNode : jsonMaps) {
//            String name = jsonNode.get("name").toString().substring(1, jsonNode.get("name").toString().length() - 1);
//            //            String note = jsonNode.get("note").toString();
//
//            Button button1 = new Button("download");
//            button1.setId(name);
//            downloadMapButton(button1, filenames);
//
//            Button buttonName = new Button(name);
//            mapNameButton(buttonName, filenames);
//
//            Label labelCreate = new Label("créer par : Toto");
//            Label labelNote = new Label("note : " + 2);
//
//            ImageView imageView = new ImageView();
//            Image image = new Image(this.getClass().getResourceAsStream("/image/game.png"));
//            imageView.setImage(image);
//            imageView.setFitHeight(50);
//            imageView.setPreserveRatio(true);
//
//            VBox vbox = new VBox();
//            vbox.getChildren().addAll(labelCreate, labelNote);
//
//            BorderPane borderPane = new BorderPane();
//            borderPane.setPrefSize(fpane.getPrefWidth() / 3.5, 100);
//            borderPane.setTop(buttonName);
//            borderPane.setLeft(vbox);
//            borderPane.setCenter(imageView);
//            borderPane.setBottom(button1);
//            borderPane.setPadding(new Insets(10, 0, 10, 100));
//
//            fpane.getChildren().add(borderPane);
//        }
//    }

    /**
     *
     * @param jsonMaps
     * @param filenames
     * @param fpane
     */
    public void addMap(JsonNode jsonMaps, List<String> filenames, FlowPane fpane) {
        Iterator<JsonNode> jsonNodeIterator = jsonMaps.iterator();
        Iterator<String> iterator = filenames.iterator();

        List<BorderPane> list = new ArrayList();

        while (jsonNodeIterator.hasNext() || iterator.hasNext()){

            String jsonName = null;
            String file = null;
            if(iterator.hasNext()){
                file = iterator.next();
            }
            if(jsonNodeIterator.hasNext()){
                JsonNode jsonNode = jsonNodeIterator.next();
                jsonName = jsonNode.get("name").toString().substring(1, jsonNode.get("name").toString().length() - 1);
            }

            if(filenames.contains(jsonName) && file != null){
                BorderPane borderPane = prepareBorderPaneForFlowpane(file, filenames, fpane);
                list.add(borderPane);
            }
            else {
                if(file != null){
                   BorderPane borderPane1 = prepareBorderPaneForFlowpane(file, filenames, fpane);
                    list.add(borderPane1);
                }
                if(jsonName != null){
                    BorderPane borderPane2 = prepareBorderPaneForFlowpane(jsonName, filenames, fpane);
                    list.add(borderPane2);
                }
            }
        }
        Collections.sort(list, new Comparator<BorderPane>() {
            @Override
            public int compare(BorderPane o1, BorderPane o2) {
                return ((Button) o1.getTop()).getText().compareTo(((Button) o2.getTop()).getText());
            }
        });
        for(BorderPane bpane : list){
            fpane.getChildren().add(bpane);
        }

    }

    /**
     *
     * @param filenames
     * @param fpane
     *
     */
//    public void addLocalMap(List<String> filenames, FlowPane fpane) {
//
//        for(String directory : filenames){
//            filenames.add(directory);
//            Button button1 = new Button("download");
//
//            button1.setId(directory);
//            downloadMapButton(button1, filenames);
//
//            Button buttonName = new Button(directory);
//            mapNameButton(buttonName, filenames);
//
//            Label labelCreate = new Label("créer par : Toto");
//            Label labelNote = new Label("note : " + 2);
//
//            ImageView imageView = new ImageView();
//            Image image = new Image(this.getClass().getResourceAsStream("/image/game.png"));
//            imageView.setImage(image);
//            imageView.setFitHeight(50);
//            imageView.setPreserveRatio(true);
//
//            VBox vbox = new VBox();
//            vbox.getChildren().addAll(labelCreate, labelNote);
//
//            BorderPane borderPane = new BorderPane();
//            borderPane.setPrefSize(fpane.getPrefWidth() / 3.5, 100);
//            borderPane.setTop(buttonName);
//            borderPane.setLeft(vbox);
//            borderPane.setCenter(imageView);
//            borderPane.setBottom(button1);
//            borderPane.setPadding(new Insets(10, 0, 10, 100));
//
//            fpane.getChildren().add(borderPane);
//        }
//    }

    public BorderPane prepareBorderPaneForFlowpane(String name, List<String> filenames, FlowPane fpane){
        Button button1 = new Button("download");
        button1.setId(name);
        downloadMapButton(button1, filenames);

        Button buttonName = new Button(name);
        buttonName.setId(name);
        mapNameButton(buttonName, filenames);

        Label labelCreate = new Label("créer par : Toto");
        Label labelNote = new Label("note : " + 2);

        ImageView imageView = new ImageView();
        Image image = new Image(this.getClass().getResourceAsStream("/image/game.png"));
        imageView.setImage(image);
        imageView.setFitHeight(50);
        imageView.setPreserveRatio(true);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(labelCreate, labelNote);

        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(fpane.getPrefWidth() / 3.5, 100);
        borderPane.setTop(buttonName);
        borderPane.setLeft(vbox);
        borderPane.setCenter(imageView);
        borderPane.setBottom(button1);
        borderPane.setPadding(new Insets(10, 0, 10, 100));
        return borderPane;
    }


    /**
     *
     * @param button
     * @param filenames
     *
     * Set all map in flowPane
     * TODO Set button action to download the map selected
     */
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