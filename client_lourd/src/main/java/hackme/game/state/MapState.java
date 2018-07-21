package hackme.game.state;

import hackme.compilation.Enigme;
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

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    private Map map = new Map();
    private Player player = new Player(map);
    private StateBasedGame game ;
    private CodeState codeState ;
    private HUDMapState hudMapState;
    private TriggerController triggerController;
    // Les objets sont crées, il nous faut encore les initialiser, et pour cela on va compléter la méthode «  init() ». Un tableau de sprite est représenté par la classe org.newdawn.slick.SpriteSheet, il suffit d'instancier cette classe en lui donnant en argument le nom du fichier et les dimensions des cellules soit 64x64 dans mon cas.
    private boolean on = true;



    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        //container.reinit();
        this.container = container;
        this.map.init();
        this.player.init();
        this.game = game;
        //this.triggerController = new TriggerController(this.map,this.player,this.game,this.codeState);
        StateGame.getTriggerController().initMapState(map,player);
        PlayerController playerController = new PlayerController(this.player);
        container.getInput().addKeyListener(playerController);
        hudMapState = new HUDMapState();
        hudMapState.init(container,this);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
            g.resetTransform();
            g.resetFont();
            g.resetLineWidth();
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

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
}