package hackme.game.HUD;

import hackme.game.state.CodeState;
import hackme.game.state.StateGame;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ButtonOk extends ButtonHUD {
    private boolean clicked = false;
    private Animation[] animations = new Animation[3];
    int x ;
    int y ;
    int last_anim;
    private String name;
    public ButtonOk(String name,int _x , int _y){
        super(name, _x, _y);
        this.name = name ;
        this.x = _x;
        this.y= _y;
    }
    @Override
    public void render(Graphics g) throws SlickException {

        g.drawAnimation(animations[clicked ? 1:0],x,y);
        g.drawString(name,x+25,y+7);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        CodeState codeState = (CodeState)stateBasedGame.getCurrentState();
        if(this.clicked && i - last_anim > 300){
            this.clicked = false;
        }
        this.animations[0].update(i);

        int posX = Mouse.getX();
        int posY = (int)(720*(1-(float)Mouse.getY()/720));

        if((posX> this.x && posX <this.animations[0].getWidth()+this.x)&&(posY>this.y && this.animations[0].getHeight()+this.y>posY) && !codeState.isCompiling() ) {
            if (Mouse.isButtonDown(0)) {
                clicked = true ;
                this.last_anim = i ;
                codeState.setCompiling(false);
                codeState.setResultCompile(false);
                if(codeState.getEpreuve().is_isSucceed()){
                    StateGame.getTriggerController().enigmeResolved(gameContainer,stateBasedGame);
                    codeState.setCompiling(false);
                }


            }
        }
    }

    public Animation[] getAnimations() {
        return animations;
    }

    public void setAnimations(Animation[] animations) {
        this.animations = animations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
