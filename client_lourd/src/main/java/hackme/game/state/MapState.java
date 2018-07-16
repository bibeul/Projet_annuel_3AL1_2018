package hackme.game.state;

import hackme.game.PlayerController;
import hackme.game.TriggerController;
import hackme.game.model.Map;
import hackme.game.model.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;

public class MapState extends BasicGameState {
    public static final int ID = 1;
    private GameContainer container;
    private Map map = new Map();
    private Player player = new Player(map);
    private StateBasedGame game ;
    private CodeState codeState ;
    private TriggerController triggerController;
    // Les objets sont crées, il nous faut encore les initialiser, et pour cela on va compléter la méthode «  init() ». Un tableau de sprite est représenté par la classe org.newdawn.slick.SpriteSheet, il suffit d'instancier cette classe en lui donnant en argument le nom du fichier et les dimensions des cellules soit 64x64 dans mon cas.
    private boolean on = true;


    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.container = container;
        this.map.init();
        this.player.init();
        this.game = game;
        //this.triggerController = new TriggerController(this.map,this.player,this.game,this.codeState);
        StateGame.getTriggerController().initMapState(map,player);
        PlayerController playerController = new PlayerController(this.player);
        container.getInput().addKeyListener(playerController);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        if(on) {
            this.map.renderBackground();
            this.player.render(g);
            this.map.renderForeground();
        }
        StateGame.getSuperHUD().render(container,g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        StateGame.time +=delta;
        if(on) {
            try {
                StateGame.getTriggerController().update(container, game, delta);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.player.update(delta);
        }

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