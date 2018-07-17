package hackme.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONUtil {
    private ObjectMapper _objectMapper;

    public JSONUtil(){
        _objectMapper = new ObjectMapper();
    }

    static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public String convertObjectToStringJSON(Object object){
        ObjectWriter objectWriter = _objectMapper.writer();
        String json ;
        try {
            json = objectWriter.writeValueAsString(object);
        }catch(JsonProcessingException e){
            json = "";
        }
        return json;
    }

    public Object convertStringJSONToObject(String jsonString , Class<?> objectClass)throws IOException{
        return _objectMapper.readValue(jsonString,objectClass);
    }
}
