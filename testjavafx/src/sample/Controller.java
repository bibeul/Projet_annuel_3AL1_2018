package sample;

import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.scene.control.TextField;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class Controller {

    private static HttpURLConnection con;

    private String url = "http://localhost:8080/user/signin";
    private JSONObject postData = new JSONObject();
    @FXML private TextField mailEdit;
    @FXML private TextField passEdit;
    @FXML private Text json;


    @FXML
    public void signIn() throws IOException {


        postData.put("email", mailEdit.getText());
        postData.put("password", passEdit.getText());
        System.out.print(mailEdit+"/-/"+passEdit);

        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            OutputStream os = con.getOutputStream();
            os.write(postData.toString().getBytes("UTF-8"));
            os.close();

            InputStream inputStream = con.getInputStream();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(
                    new InputStreamReader(inputStream, "UTF-8"));

            json.setText(jsonObject.toString());


        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }
    }
}
