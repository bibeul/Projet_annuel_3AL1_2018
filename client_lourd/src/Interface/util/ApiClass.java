package Interface.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ApiClass {

    private static HttpURLConnection con;

    private String url = "http://localhost:8080/";
    private String api_key;

    public OutputStream getOutputStream(String httpReq, String route) {
        try {
            URL myurl = new URL(url + route);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod(httpReq);
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            OutputStream os = con.getOutputStream();
            return os;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }
        return null;
    }

    public JSONObject getJsonFromInputStream() {
        try {
            InputStream inputStream = con.getInputStream();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(
                    new InputStreamReader(inputStream, "UTF-8"));
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return null;
        }
    }

    public void signIn(String email, String password) throws IOException {

        JSONObject postData = new JSONObject();
        postData.put("email", email);
        postData.put("password", password);
        System.out.print(email + "/-/" + password);

        try {

            OutputStream os = getOutputStream("POST", "user/signin");
            if (os == null) {
                con.disconnect();
                return;
            }
            os.write(postData.toString().getBytes("UTF-8"));
            os.close();

            JSONObject jsonObject = getJsonFromInputStream();
            api_key = jsonObject.get("token").toString();

        } finally {
            con.disconnect();
        }
    }

    public void register(String email, String user, String password) {
        JSONObject postData = new JSONObject();
        postData.put("email", email);
        postData.put("username", user);
        postData.put("password", password);

        try {

            OutputStream os = getOutputStream("PUT","user/");
            if (os == null) {
                con.disconnect();
                return;
            }
            os.write(postData.toString().getBytes("UTF-8"));
            os.close();
            JSONObject jsonObject = getJsonFromInputStream();


            System.out.print(jsonObject.toString());


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }
    }
}
