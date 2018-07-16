package hackme.game.HUD;

import hackme.compilation.Enigme;
import hackme.game.state.CodeState;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class WindowCompilation {
    private Animation[] animations = new Animation[3];
    private String name ;
    private Image image;
    private Image OK;
    private Image KO;
    private Enigme enigme;
    private ArrayList test;
    private Image windowOK;
    private Image windowKO;
    private CustomTextField errorField;
    private ButtonOk buttonOk;
    private boolean succeed;
    private int x ;
    private int y ;
    public void render(GameContainer gameContainer , Graphics g) throws SlickException {
        //g.drawImage(KO,300,100);
        //g.drawImage(OK,350,100);
        //Font font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF,java.awt.Font.BOLD , 26), false);
        //g.scale(25F , 10F);

        g.drawImage(succeed ? windowOK:windowKO,x,y);
        g.resetTransform();
        g.scale(3F , 3F);
        //g.translate(1280/2, 720/2);
        g.drawImage(image,200/3F,200/3F);
        g.resetTransform();
        Image verif;
        g.setColor(Color.black);
        if(test!=null) {
            boolean[] booleans = (boolean[])test.get(0);
                g.drawString("COMPILED", 290, 220);

                if (booleans[0] ) {
                    verif = this.OK;
                } else {
                    verif = this.KO;
                }
                g.drawImage(verif, 370, 220);


            if (!enigme.get_nameTest1().equals("")) {
                g.drawString("TEST1", 240, 255);
                if (booleans[1] ) {
                    verif = this.OK;
                } else {
                    verif = this.KO;
                }
                g.drawImage(verif, 290, 255);
            }

            if (!enigme.get_nameTest2().equals("")) {
                g.drawString("TEST2", 240, 315);
                if (booleans[2]) {
                    verif = this.OK;
                } else {
                    verif = this.KO;
                }
                g.drawImage(verif, 290, 315);
            }

            if (!enigme.get_nameTest3().equals("")) {
                g.drawString("TEST3", 240, 375);
                if (booleans[3]) {
                    verif = this.OK;
                } else {
                    verif = this.KO;
                }
                g.drawImage(verif, 290, 375);
            }
            if(test.size()==2){
                StringBuilder sb = new StringBuilder();
                for (String s : (ArrayList<String>)test.get(1))
                {
                    sb.append(s.substring(70,s.length()));
                    sb.append("\r");
                }
                errorField.setBackgroundColor(Color.gray);
                errorField.setText(sb.toString());
                errorField.render(gameContainer,g);
            }
            buttonOk.render(g);
        }


    }
    public WindowCompilation(String name,int x, int y){
        this.name = name ;
        this.x = x;
        this.y = y ;
    }

    public Animation[] getAnimations() {
        return animations;
    }

    public void setAnimations(Animation[] animations) {
        this.animations = animations;
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        CodeState codeState = (CodeState)stateBasedGame.getCurrentState();
        this.test = codeState.getEpreuve().get_test();
        this.enigme = codeState.getEpreuve().get_enigme();
        this.succeed = codeState.getEpreuve().is_isSucceed();
        this.buttonOk.update(gameContainer,stateBasedGame,i);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getOK() {
        return OK;
    }

    public void setOK(Image OK) {
        this.OK = OK;
    }

    public Image getKO() {
        return KO;
    }

    public void setKO(Image KO) {
        this.KO = KO;
    }

    public Image getWindowOK() {
        return windowOK;
    }

    public void setWindowOK(Image windowOK) {
        this.windowOK = windowOK;
    }

    public Image getWindowKO() {
        return windowKO;
    }

    public void setWindowKO(Image windowKO) {
        this.windowKO = windowKO;
    }

    public CustomTextField getErrorField() {
        return errorField;
    }

    public void setErrorField(CustomTextField errorField) {
        this.errorField = errorField;
    }

    public ButtonOk getButtonOk() {
        return buttonOk;
    }

    public void setButtonOk(ButtonOk buttonOk) {
        this.buttonOk = buttonOk;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }
}
