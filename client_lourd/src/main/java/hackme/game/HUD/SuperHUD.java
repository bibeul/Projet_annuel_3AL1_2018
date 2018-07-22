package hackme.game.HUD;

import hackme.game.state.StateGame;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class SuperHUD {
        private Image clockImage;
    public void init(GameContainer gameContainer) throws SlickException {
        SpriteSheet spriteSheet = new SpriteSheet("src/main/resources/image/UI/clock.png", 105, 32);
        this.clockImage = spriteSheet.getSubImage(104,34,112,111);

    }

    public void render(GameContainer gameContainer , Graphics g) throws SlickException {
        g.scale(0.5F,0.5F);
        g.drawImage(clockImage,0/0.5F,640/0.5F);
        g.resetTransform();
        g.setColor(Color.white);
        g.drawString(String.valueOf(StateGame.time/1000),60,670);
    }
}
