package hackme.game.state;

import hackme.game.HUD.HUDMapState;
import hackme.game.model.Map;
import hackme.game.model.Player;
import hackme.game.PlayerController;
import hackme.game.TriggerController;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;

public class MapState extends BasicGameState {
    public static final int ID = 1;
    private GameContainer container;

    private Image background;
    private Map map = new Map();
    private Player player = new Player(map);
    private CodeState codeState ;
    private HUDMapState hudMapState;



    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.container = container;
        this.map.init(container);
        this.player.init();
        this.background = new Image("src/main/resources/image/UI/blackbg.jpg");
        StateGame.getTriggerController().initMapState(map,player);
        PlayerController playerController = new PlayerController(this.player);
        container.getInput().addKeyListener(playerController);
        hudMapState = new HUDMapState();
        hudMapState.init(container,this);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
            container.setShowFPS(false);
            g.resetTransform();
            g.resetFont();
            g.resetLineWidth();
            g.drawImage(this.background,0,0);
            this.map.renderBackground();
            this.player.render(g);
            this.map.renderForeground();
            hudMapState.render(container,g);
        StateGame.getSuperHUD().render(container,g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        StateGame.time +=delta;
            try {
                StateGame.getTriggerController().update(container, game, delta);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.player.update(delta);
            hudMapState.update(container,game,delta);
        }



    public int getID() {
        return ID;
    }


    public CodeState getCodeState() {
        return codeState;
    }

    public void setCodeState(CodeState codeState) {
        this.codeState = codeState;
    }


    public Map getMap() {
        return map;
    }
    public void setMap(Map map) {
        this.map = map;
    }

}