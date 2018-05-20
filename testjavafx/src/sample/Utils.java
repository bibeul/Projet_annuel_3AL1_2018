package sample;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {

    /*private static HttpURLConnection con;

    private String url = "http://localhost:8080/user/signin";
    private JSONObject postData = new JSONObject();


    public JSONObject signIn(String email, String password) throws IOException {

        postData.put("email", email);
        postData.put("password", password);

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

            return jsonObject;

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            JSONObject emptyJson = new JSONObject();
            con.disconnect();
            return emptyJson;
        }
    }*/
}
