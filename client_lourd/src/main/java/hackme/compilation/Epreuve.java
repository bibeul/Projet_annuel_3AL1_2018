package hackme.compilation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Epreuve {
    private boolean _isSucceed;
    private Enigme _enigme;
    private String _answer;
    private ArrayList _test;
    private boolean _errorStack;
    private String _enigmeID;
    public Enigme get_enigme() {
        return _enigme;
    }

    public void set_enigme(Enigme enigme) {
        this._enigme = enigme;
    }


    private final static String PATH = "src/main/resources/packagecompile/";
   public Epreuve(Enigme enigme,String ID){
        _isSucceed = false ;
        _enigme  = enigme;
        _errorStack = false ;
        _enigmeID  = ID;
    }


    public ArrayList tryIt() {
        Compiler compiler = new Compiler();
        ArrayList arrayList = new ArrayList();
        //createClassesToExecute();
        if (compiler.runTest(_enigme.get_className())){
            System.out.print("good");
            boolean[] booleans = checkinList(compiler._s._stdout,true,_enigme);
            this._isSucceed = checkIfSucceed(booleans);
            return new ArrayList<>(Arrays.asList(booleans));
        }else{
            System.out.print("Erreur de compilation ou dexecution");
            ArrayList erreurlist = new ArrayList();
            erreurlist.add(checkinList(compiler._s._stdout,false,_enigme));
            erreurlist.add(compiler._err._stdout);
            //arrayList.add(erreurlist);
            return erreurlist;
        }
    }

    public void createClassesToExecute(){
       File userClass = new File(PATH+_enigme.get_className()+".java");
       File mainClass = new File(PATH+"Main"+".java");
       try{
           BufferedWriter writer = new BufferedWriter(new FileWriter(userClass));
           writer.write("package packagecompile;"+_answer);
           writer.close();
       }catch(IOException e ){

       }
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(mainClass));
            writer.write(writeMainClass());
            writer.close();
        }catch(IOException e ){

        }
    }

    public String writeMainClass() throws IOException {
       String class_name = _enigme.get_className();
       String result = readFile("src/main/resources/packagecompile/TemplateClassMain",StandardCharsets.UTF_8);
       result =result.replace("$TESTCLASS" ,class_name);
       result = result.replace("$TEST1",_enigme.get_nameTest1());
       result = result.replace("$TEST2",_enigme.get_nameTest2());
       result = result.replace("$TEST3",_enigme.get_nameTest3());
       result = result.replace("$BLOC1",_enigme.get_blocTest1());
       result = result.replace("$BLOC2",_enigme.get_blocTest2());
       result = result.replace("$BLOC3",_enigme.get_blocTest3());


        return result ;
    }

    public static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }


    public void set_answer(String _answer) {
        this._answer = _answer;
    }

    public boolean[] checkinList(List<String> stringList,Boolean bool,Enigme enigme){
        boolean[] boolarray = new boolean[4];
        boolarray[0] = bool;
       for(String s : stringList) {
           String err =s.substring(s.length()-13,s.length());
          if(err.matches("TEST 1 : true")|| enigme.get_blocTest1()==""){
                boolarray[1] = true;
           } if(err.matches("TEST 2 : true")|| enigme.get_blocTest2()==""){
                boolarray[2] = true;
          } if(err.matches("TEST 3 : true")|| enigme.get_blocTest3()==""){
                boolarray[3] = true;
          }
       }
       return boolarray;

    }

    public ArrayList get_test() {
        return _test;
    }

    public void set_test(ArrayList _test) {
        this._test = _test;
    }

    public boolean is_isSucceed() {
        return _isSucceed;
    }

    public void set_isSucceed(boolean _isSucceed) {
        this._isSucceed = _isSucceed;
    }

    public boolean is_errorStack() {
        return _errorStack;
    }

    public void set_errorStack(boolean _errorStack) {
        this._errorStack = _errorStack;
    }


    public boolean checkIfSucceed(boolean[] booleans){
        for (boolean aBoolean : booleans) {

            if (!aBoolean) {
                return false;
            }
        }
        return true ;
    }

    public String get_enigmeID() {
        return _enigmeID;
    }

    public void set_enigmeID(String _enigmeID) {
        this._enigmeID = _enigmeID;
    }
}
