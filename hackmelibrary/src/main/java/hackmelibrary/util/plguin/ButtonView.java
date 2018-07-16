package hackmelibrary.util.plguin;

import javafx.event.ActionEvent;

import java.io.IOException;

public interface ButtonView {

    public void mapManagement(ActionEvent event);

    public void pluginManagement(ActionEvent event);

    public void playView(ActionEvent event);

    public void home();

    public void logout();

    public void close();

}
