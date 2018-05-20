package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application{

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //Group root = new Group();

        Scene scene = new Scene(root,Color.LIGHTBLUE);
        //Button send =  (Button) getClass().getResource("dd");

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

