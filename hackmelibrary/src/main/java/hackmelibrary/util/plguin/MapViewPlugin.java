package hackmelibrary.util.plguin;

import javafx.scene.Node;

import java.util.List;

public interface MapViewPlugin {

    public void changeColor(List<Node> node);

    public void addButon(Node node);

    public void printScene(Node node);
}