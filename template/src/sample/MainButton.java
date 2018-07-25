package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;

public class MainButton extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Button button = new Button();
        button.setText("hey");
        button.setOnAction(e->System.out.println("bendo"));
        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(layout, 500, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
