package hackme.game.state;

import hackme.compilation.Enigme;
import hackme.compilation.Epreuve;
import hackme.game.HUD.SuperHUD;
import hackme.game.TriggerController;
import hackme.util.ApiClass;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;

public class StateGame extends StateBasedGame  {
    private static TriggerController triggerController;
    public static int time = 0;
    private static SuperHUD superHUD;
    private static boolean endwell;
    public StateGame() {
        super("Game");
        triggerController = new TriggerController(this);
        superHUD =new SuperHUD();

        this.addState(new MapState());
        this.addState(new CodeState());
        this.addState(new EndState());

    }

    public static TriggerController getTriggerController() {
        return triggerController;
    }

    public static void setTriggerController(TriggerController triggerController) {
        StateGame.triggerController = triggerController;
    }

    public static SuperHUD getSuperHUD() {
        return superHUD;
    }

    public static void setSuperHUD(SuperHUD superHUD) {
        StateGame.superHUD = superHUD;
    }

    /**
     * Ici il suffit d'ajouter nos deux boucles de jeux.
     * La première ajoutèe sera celle qui sera utilisée au début
     */
    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        superHUD.init(container);
        this.getState(CodeState.ID).init(container,this);
        CodeState codeState =(CodeState) this.getState(CodeState.ID);

        this.enterState(MapState.ID);

    }

    public static void main(String[] args) throws SlickException {
        int maxFPS = 60;
        AppGameContainer app = new AppGameContainer(new StateGame(), 1280, 720, false);
        app.setTargetFrameRate(maxFPS);
        app.start();
        app.destroy();
        ApiClass apiClass = new ApiClass();
        if(endwell) {
            apiClass.putScore(System.getProperty("user"), System.getProperty("selectedMap"), time / 60);
        }
        }

    public void initGame() throws SlickException {
        int maxFPS = 60;
        AppGameContainer app = new AppGameContainer(new StateGame(), 1280, 720, false);
        app.setAlwaysRender(true);
        app.setTargetFrameRate(maxFPS);
        app.start();
        app.destroy();

    }


}