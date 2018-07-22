package hackme.compilation;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Compiler {

    public Erreur _err ;

    public Sortie _s ;
    public boolean isTest1 ;

    public boolean isTest2 ;

    public boolean isTest3;

    private  boolean compile(String command) throws Exception {
            Process pro = Runtime.getRuntime().exec(command, null);
             _s = new Sortie(pro,command);   _s.start();
             _err = new Erreur(pro,command); _err.start();
             _s.join();
             _err.join();
            return pro.exitValue() == 0 && !_err.is_error();
    }
    private  boolean run(String command, int timeout) throws Exception {
        Process pro = Runtime.getRuntime().exec(command, null, new File("src/main/resources"));
        pro.waitFor(timeout, TimeUnit.SECONDS);
        _s = new Sortie(pro,command);   _s.start();
        _err = new Erreur(pro,command); _err.start();
        _s.join();
        _err.join();
        return pro.exitValue() == 0 && !_err.is_error();
    }


    Compiler(){
        isTest1 = true ;
        isTest2 = true ;
        isTest3 = true ;

    }


    public boolean runTest(String className){
        try {
            boolean success = compile("javac src/main/resources/packagecompile/Main.java src/main/java/hackme/compilation/Hint.java src/main/java/hackme/compilation/Joker.java src/main/resources/packagecompile/"+className+".java");
            if(success) {
                return run("java packagecompile/Main",3);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false ;
        }
        return false;
    }


}

