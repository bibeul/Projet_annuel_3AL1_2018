package hackme.compilation;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Compiler {

    public Erreur _err ;

    public Sortie _s ;
    public boolean isTest1 ;

    public boolean isTest2 ;

    public boolean isTest3;

    private  boolean runProcess(String command,int timeout) throws Exception {
            Process pro = Runtime.getRuntime().exec(command, null);
            pro.waitFor(timeout, TimeUnit.SECONDS);
            //printLines(command + " stdout:", pro.getInputStream());
            //printLines(command + " stderr:", pro.getErrorStream());
             _s = new Sortie(pro,command);   _s.start();
             _err = new Erreur(pro,command); _err.start();
             _s.join();
             _err.join();
            System.out.println(command + " exitValue() " + pro.exitValue());
        return pro.exitValue() == 0 && !_err.is_error();
    }
    private  boolean compile(String command,int timeout) throws Exception {
        Process pro = Runtime.getRuntime().exec(command, null, new File("src/main/resources"));
        pro.waitFor(timeout, TimeUnit.SECONDS);
        //printLines(command + " stdout:", pro.getInputStream());
        //printLines(command + " stderr:", pro.getErrorStream());
        _s = new Sortie(pro,command);   _s.start();
        _err = new Erreur(pro,command); _err.start();
        _s.join();
        _err.join();
        System.out.println(command + " exitValue() " + pro.exitValue());
        return pro.exitValue() == 0 && !_err.is_error();
    }





     Compiler(){
        isTest1 = true ;
        isTest2 = true ;
        isTest3 = true ;

    }

    /*public static void main(String[] args) {
        try {
            runProcess("javac compilation/packagecompile/Main.java compilation/packagecompile/TestClass.java ");
            runProcess("java compilation/packagecompile/Main");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }*/

    public boolean runTest(String className){
        try {
            boolean success = runProcess("javac src/main/resources/packagecompile/Main.java src/main/java/hackme/compilation/Hint.java src/main/java/hackme/compilation/Joker.java src/main/resources/packagecompile/"+className+".java",10); //
            if(success) {
                return compile("java packagecompile/Main",3);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false ;
        }
        return false;
    }


}

