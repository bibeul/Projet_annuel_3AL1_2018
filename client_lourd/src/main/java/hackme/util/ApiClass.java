package hackme.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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


    public JsonNode getAllPlugin(){
        try{
            String response = getResponse("GET", "plugin/displayAll");
            if (response == null) {
                con.disconnect();
                return null;
            }
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response);

            return jsonNode;

        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public void downloadPlugin(String plugin){
        try {
            URL myurl = new URL(url + "plugin/download/" + plugin);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            InputStream in = con.getInputStream();
            String jarFile = "src/main/resources/modules/" + plugin +".jar";
            FileOutputStream out = new FileOutputStream(jarFile);
            copy(in, out, 1024);
            out.close();
        }catch (Exception e) {
            e.getMessage();
        }

    }


    public JsonNode getAllMap(){
        try{
            String response = getResponse("GET", "map/displayAll");
            if (response == null) {
                con.disconnect();
                return null;
            }
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response);

            return jsonNode;

        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public void downloadMap(String map){
        try {
            URL myurl = new URL(url + "map/download/" + map);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            InputStream in = con.getInputStream();
            String zippedFile = "src/main/resources/tmp/" + map +".zip";
            FileOutputStream out = new FileOutputStream(zippedFile);
            copy(in, out, 1024);

            out.close();
            unzip(zippedFile);

        }catch (Exception e) {
            e.getMessage();
        }

    }

    public void copy(InputStream input, OutputStream output, int bufferSize) throws IOException {
        byte[] buf = new byte[bufferSize];
        int n = input.read(buf);
        while (n >= 0) {
            output.write(buf, 0, n);
            n = input.read(buf);
        }
        output.flush();
    }

    public void unzip(String fileZip) throws IOException {
        String directory = fileZip.substring(fileZip.lastIndexOf("/"),fileZip.lastIndexOf(".")) + "/";
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        File testDir = new File("src/main/resources/maps/" + directory);
        testDir.mkdir();
        while(zipEntry != null){
            String fileName = zipEntry.getName();
            File newFile = new File("src/main/resources/maps/" + directory + fileName);
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
        Files.deleteIfExists(Paths.get(fileZip));
    }

    public void signIn(String username, String password) {
        JSONObject postData = new JSONObject();
        postData.put("username", username);
        postData.put("password", password);

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

            OutputStream os = getOutputStream("PUT","user/register");
            if (os == null) {
                con.disconnect();
                return;
            }
            os.write(postData.toString().getBytes("UTF-8"));
            os.close();


            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(con.getInputStream());


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
