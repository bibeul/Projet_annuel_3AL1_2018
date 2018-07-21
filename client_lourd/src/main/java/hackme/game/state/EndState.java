package hackme.game.state;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class EndState extends BasicGameState implements KeyListener {
    public static final int ID = 3;
    public Image background;
    public boolean end;
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.background = new Image("src/main/resources/image/UI/congrats.jpg");
        gameContainer.getInput().addKeyListener(this);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0, 0, gameContainer.getWidth(), gameContainer.getHeight());
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(end){

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
