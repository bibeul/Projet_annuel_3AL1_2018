package hackme.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class ApiClass {

    private static HttpURLConnection con;

    private String url = "http://localhost:8080/";
    private String api_key = null;
    private String auth = null;

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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }
        return null;
    }

    public String getResponse(String httpReq, String route){
        try {
            URL myurl = new URL(url + route);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod(httpReq);
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

//            OutputStream os = con.getOutputStream();
//            os.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            br.close();
            return sb.toString();

        }catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public JsonNode getJsonFromInputStream(String httpReq, String route) {
        try {
            URL myurl = new URL(url + route);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod(httpReq);
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(con.getInputStream());
            return jsonNode;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JsonNode getAllMap(){
        try{
            String response = getResponse("GET", "map/displayAll");
            System.out.println(response);
            if (response == null) {
                con.disconnect();
                return null;
            }
//            JsonNode jsonObject = getJsonFromInputStream("GET", "map/displayAll");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response);
            System.out.println(jsonNode);

            return jsonNode;

        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public void signIn(String email, String password) {

        JSONObject postData = new JSONObject();
        postData.put("email", email);
        postData.put("password", password);
        System.out.println(email + "/-/" + password);

        try {

            OutputStream os = getOutputStream("POST", "user/signin");
            if (os == null) {
                con.disconnect();
                return;
            }
            try{
                os.write(postData.toString().getBytes("UTF-8"));
                os.close();

                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(con.getInputStream());
                System.out.println(jsonNode);

                api_key = jsonNode.get("token").toString();

                auth = jsonNode.get("auth").toString();

            } catch (Exception e){
                api_key = null;
                e.getMessage();
            }

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

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(con.getInputStream());

//            JSONObject jsonObject = ;
            os.close();

            System.out.print(jsonNode.toString());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }
    }

    public String getApi_key() {
        return api_key;
    }

    public String getAuth() {
        return auth;
    }
}
