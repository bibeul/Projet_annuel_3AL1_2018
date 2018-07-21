package hackme.controller;

import hackmelibrary.util.plguin.SampleViewPlugin;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

public class SamplePlugin implements SampleViewPlugin {




    @Override
    public void changeColor(List<Node> nodes){
        for (Node n : nodes){
//            System.out.println(n.getClass());
            if (n.getClass() == Button.class){
                n.setStyle("-fx-background-color:" + "ff0000");
            }
//            System.out.println(n.clipProperty());
        }

//        node.setStyle("-fx-background-color:" + "ff0000");
    }

    @Override
    public void addButon(Node nodes) {

    }

    @Override
    public void printScene(Node node) {
        Parent parent = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/samplePlugin.fxml"));
            fxmlLoader.setController(new SamplePluginController());
            parent = fxmlLoader.load();
            // Chargement du FXML.
//            fxmlLoader.setRoot((AnchorPane) node);

//            fxmlLoader.load();
//            ((AnchorPane) node).getChildren().setAll((Node) fxmlLoader.load());
//            AnchorPane parent = (AnchorPane)fxmlLoader.load();
//            SamplePluginController cntl = (SamplePluginController)fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(parent);
        ((AnchorPane) node).getChildren().setAll(parent);
//        stage.show();
    }
}
