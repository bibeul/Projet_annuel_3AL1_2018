package hackme.game.state;

import hackme.game.HUD.SuperHUD;
import hackme.game.TriggerController;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class StateGame extends StateBasedGame {
    private static TriggerController triggerController;
    public static int time = 0;
    private static SuperHUD superHUD;
    public StateGame() {
        super("Lesson 15 :: StateGame");
        triggerController = new TriggerController(this);
        superHUD =new SuperHUD();

        this.addState(new MapState());
        this.addState(new CodeState());

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
        //codeState.initUI(container);

       /*Enigme enigme = null;
        try {
            enigme = Enigme.buildEnigmeFromJson("7486");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Epreuve epreuve = new Epreuve(enigme);
        CodeState cs =(CodeState)this.getState(CodeState.ID);
        MapState mapState = (MapState)this.getState(MapState.ID);
        mapState.setOn(false);
        cs.initUI(container,epreuve);
        this.enterState(CodeState.ID);*/

    }

    public static void main(String[] args) throws SlickException {
        int maxFPS = 60;
        AppGameContainer app = new AppGameContainer(new StateGame(), 1280, 720, false);
        app.setTargetFrameRate(maxFPS);
        app.start();
    }


}