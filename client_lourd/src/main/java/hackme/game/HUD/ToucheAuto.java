package hackme.game.HUD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ToucheAuto extends Canvas implements KeyListener {


    ToucheAuto(){

        addKeyListener(this);
    }
    public static boolean run;
    public static void main(String[] args) {
        ToucheAuto toucheAuto =  new ToucheAuto();
        JFrame f = new JFrame();
        f.getContentPane().add(toucheAuto);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
        toucheAuto.requestFocus();

        try {
            toucheAuto.run();
        }catch (AWTException e) {
            Logger.getLogger(ToucheAuto.class.getName()).log(Level.SEVERE, null, e.getLocalizedMessage());
        }
        System.exit(0);
    }



    public void run() throws AWTException {
        ToucheAuto.run = true;
        Robot robot;
        robot = new Robot();
        while(ToucheAuto.run) {


            //Ecrit les touches à effectuer ici


            robot.keyPress(KeyEvent.VK_NUMPAD1);
            //robot.keyRelease(KeyEvent.VK_NUMPAD5);
            robot.delay( ThreadLocalRandom.current().nextInt(300, 400 + 1)); // Random une valeur entre 300 et 400
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            ToucheAuto.run =false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key pressed");
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key Released");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key Released");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key typed");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key typed");
        }

    }


}




