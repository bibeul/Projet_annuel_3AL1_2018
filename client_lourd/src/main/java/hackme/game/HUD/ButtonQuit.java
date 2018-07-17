package hackme.game.HUD;

import hackme.game.state.CodeState;
import hackme.game.state.MapState;
import hackme.game.state.StateGame;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ButtonQuit  extends  ButtonHUD{

    private boolean clicked = false;
    private Animation[] animations = new Animation[3];
    private int x ;
    private int y ;
    private int last_anim;
    private String name;
    ButtonQuit(String name, int _x, int _y){
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
        if(this.clicked && i - last_anim > 300){
            this.clicked = false;
        }
        this.animations[0].update(i);

        int posX = Mouse.getX();
        int posY = (int)(720*(1-(float)Mouse.getY()/720));

        if((posX> this.x && posX <this.animations[0].getWidth()+this.x)&&(posY>this.y && this.animations[0].getHeight()+this.y>posY) ) {
            if (Mouse.isButtonDown(0)) {
                clicked = true ;
                this.last_anim = i ;
                if(stateBasedGame.getCurrentStateID()== MapState.ID) {
                    gameContainer.setForceExit(false);
                    gameContainer.exit();
                }else if(stateBasedGame.getCurrentStateID()== CodeState.ID){
                    stateBasedGame.enterState(MapState.ID);
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



