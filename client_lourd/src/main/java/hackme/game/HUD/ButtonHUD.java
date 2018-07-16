package hackme.game.HUD;

import hackme.game.state.CodeState;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class ButtonHUD {
    private boolean clicked = false;
    private Animation[] animations = new Animation[3];
    int x ;
    int y ;
    int last_anim;
    private String name;


    public void render(Graphics g) throws SlickException {
        g.drawAnimation(animations[clicked ? 1:0],x,y);
        g.setColor(clicked? Color.white:Color.black);
        g.drawString(name,x+25,y+7);
    }
    public ButtonHUD(String name,int _x , int _y){
        this.name = name ;
        this.x = _x;
        this.y= _y ;
    }

    public Animation[] getAnimations() {
        return animations;
    }

    public void setAnimations(Animation[] animations) {
        this.animations = animations;
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame,int i) throws SlickException {
        CodeState codeState = (CodeState)stateBasedGame.getCurrentState();
        if(clicked && i - last_anim > 300){
            this.clicked = false;
        }
        this.animations[0].update(i);

        int posX = Mouse.getX();
        int posY = (int)(720*(1-(float)Mouse.getY()/720));

        if((posX> this.x && posX <this.animations[0].getWidth()+this.x)&&(posY>this.y && this.animations[0].getHeight()+this.y>posY) && !codeState.isCompiling() ) {
            if (Mouse.isButtonDown(0)&& !codeState.isResultCompile()) {
                clicked = true ;
                this.last_anim = i ;
                codeState.setCompiling(true);
                codeState.setResultCompile(true);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}