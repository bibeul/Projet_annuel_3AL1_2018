package hackme.compilation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Sortie extends Thread{
    public Process _pCommande;
    String _command;
    public List<String> _stdout;
    Sortie(Process pCommande,String command){
        _pCommande = pCommande;
        _command = command;
        _stdout = new ArrayList<String>();
    }

    public void run() {
        try {
                printLines(_command + " stdout:", _pCommande.getInputStream(),this);
        } catch (Exception ioe) {}
    }

    private static void printLines(String name, InputStream ins,Sortie obj) throws Exception {
        String line;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(name + " " + line);
            obj._stdout.add(name + " " + line ) ;
        }
    }
}