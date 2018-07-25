package hackme.game.state;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;

public class EndState extends BasicGameState implements KeyListener {
    public static final int ID = 3;
    public Image background;
    public boolean end;
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.background = new Image("src/main/resources/image/UI/congrats.jpg");
        end=false;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0, 0, gameContainer.getWidth(), gameContainer.getHeight());
        graphics.setColor(Color.black);
        graphics.drawString("--- PRESS SPACE ----" ,540,360);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(end){
            try {
                StateGame.getTriggerController().update(gameContainer,stateBasedGame,i);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getID() {
        return ID;
    }

    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_SPACE:
                this.end = true;
                break;
        }
    }

}
