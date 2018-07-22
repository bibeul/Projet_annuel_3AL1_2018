package hackme.util;

import com.fasterxml.jackson.databind.JsonNode;
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

public class MapManagement {

    private Switch switchscene = new Switch();

    private ApiClass api = new ApiClass();

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
                file = iterator.next();
            }
            else {
                if(file != null){
                    BorderPane borderPane1 = prepareBorderPaneForFlowpane(file, filenames, fpane);
                    list.add(borderPane1);
                }
                if(jsonName != null){
                    BorderPane borderPane2 = prepareBorderPaneForFlowpane(jsonName, filenames, fpane);
                    list.add(borderPane2);
                    JsonNode jsonNode = jsonNodeIterator.next();
                    jsonName = jsonNode.get("name").toString().substring(1, jsonNode.get("name").toString().length() - 1);
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


    public BorderPane prepareBorderPaneForFlowpane(String name, List<String> filenames, FlowPane fpane){
        Button button1 = new Button("download");
        button1.setId(name);
        downloadMapButton(button1, filenames);

        Button buttonName = new Button(name);
        buttonName.setId(name);
        mapNameButton(buttonName, filenames);

        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(fpane.getPrefWidth() / 3.5, 100);
        borderPane.setTop(buttonName);
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
     */
    public void downloadMapButton(Button button, List<String> filenames){
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMaxHeight(Double.MAX_VALUE);
        button.setPrefSize(100.,20.);
        if (filenames.contains(button.getId())) {
            button.setDisable(true);
        } else {
            button.setOnAction(event -> {
                api.downloadMap(button.getId());
                try {
                    switchscene.mapManagement(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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