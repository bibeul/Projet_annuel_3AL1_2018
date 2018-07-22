package hackme.compilation;

import hackme.util.JSONUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Enigme {

    private String _function_name ;

    private String _enonce;

    private String _blocTest1;

    private String _blocTest2;

    private String _blocTest3;

    private String _nameTest1;

    private String _nameTest2;

    private String _nameTest3;

    private String _className;


     Enigme(String function_name, String enonce){
        _function_name = function_name;
        _enonce = enonce;
        _blocTest1 ="";
        _blocTest2 ="";
        _blocTest3 ="";
        _nameTest1 ="";
        _nameTest2 ="";
        _nameTest3 ="";
        _className ="";
    }
    Enigme(){
        _function_name = "";
        _enonce = "";
        _blocTest1 ="";
        _blocTest2 ="";
        _blocTest3 ="";
        _nameTest1 ="";
        _nameTest2 ="";
        _nameTest3 ="";
        _className ="";
    }

    public String get_function_name() {
        return _function_name;
    }

    public void set_function_name(String _function_name) {
        this._function_name = _function_name;
    }

    public String get_enonce() {
        return _enonce;
    }

    public void set_enonce(String _enonce) {
        this._enonce = _enonce;
    }

    public String get_blocTest1() {
        return _blocTest1;
    }

    public void set_blocTest1(String _blocTest1) {
        this._blocTest1 = _blocTest1;
    }

    public String get_blocTest2() {
        return _blocTest2;
    }

    public void set_blocTest2(String _blocTest2) {
        this._blocTest2 = _blocTest2;
    }

    public String get_blocTest3() {
        return _blocTest3;
    }

    public void set_blocTest3(String _blocTest3) {
        this._blocTest3 = _blocTest3;
    }


    public String get_nameTest1() {
        return _nameTest1;
    }

    public void set_nameTest1(String nameTest1) {
         if(nameTest1 !="") {
            this._nameTest1 = "System.out.println(\"TEST 1 : \" + "+nameTest1 + "());";
        }
    }

    public String get_nameTest2() {
        return _nameTest2;
    }

    public void set_nameTest2(String nameTest2) {
        if(nameTest2 !="") {
            this._nameTest2 = "System.out.println(\"TEST 2 : \" + "+nameTest2 + "());";
        }
    }

    public String get_nameTest3() {
        return _nameTest3;
    }

    public void set_nameTest3(String nameTest3) {
        if(nameTest3 !="") {
            this._nameTest3 = "System.out.println(\"TEST 3 : \" + "+nameTest3 + "());";
        }
    }
    public static Enigme buildEnigmeFromJson(String path) throws IOException {
           JSONUtil jsonUtil = new JSONUtil();
           String filepath = Epreuve.readFile(System.getProperty("selectedMap")+"/Enigme/"+path+".json", StandardCharsets.UTF_8);
           return (Enigme)jsonUtil.convertStringJSONToObject(filepath,Enigme.class);
    }
    public String get_className() {
        return _className;
    }

    public void set_className(String _className) {
        this._className = _className;
    }
}
