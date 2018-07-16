package hackme.game;

import hackme.compilation.Enigme;
import hackme.compilation.Epreuve;
import hackme.game.state.CodeState;
import hackme.game.state.MapState;
import hackme.game.state.StateGame;
import hackme.game.model.Map;
import hackme.game.model.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;
import java.util.ArrayList;

public class TriggerController {
    private Map map;
    private Player player;
    private StateBasedGame game;
    public TriggerController(StateBasedGame game) {
        this.game = game;
    }
    private int time ;


    public void update(GameContainer gameContainer,StateBasedGame basedGame,int delta) throws SlickException, IOException {
        if (basedGame.getCurrentStateID()==MapState.ID && player.isAction()) {
            for (int objectID = 0; objectID < map.getObjectCount(); objectID++) {
                if (isInTrigger(objectID)) {
                    if ("Enigme".equals(map.getObjectType(objectID))) {

                        launchEnigme(gameContainer, basedGame, map.getObjectProperty(objectID, "ID", ""));
                    }
                }
            }
        }
        if(basedGame.getCurrentStateID()==CodeState.ID){
            CodeState codeState = (CodeState)basedGame.getCurrentState();
            if(codeState.isCompiling()) {
                Epreuve epreuve = codeState.getEpreuve();
                epreuve.set_answer(codeState.get_answer());
                epreuve.createClassesToExecute();
                epreuve.writeMainClass();
                ArrayList arrayList = epreuve.tryIt();
                if( arrayList.size()==2){
                    //epreuve.set_isSucceed(false);
                    epreuve.is_errorStack();
                }else{

                }
                epreuve.set_test(arrayList);
            }
            codeState.setCompiling(false);

        }

    }

    public void enigmeResolved(){
        System.out.print(StateGame.time/1000);
    }

    public void initMapState(Map map ,Player player){
        this.map = map;
        this.player = player;

    }
    //public void

    public void initCodeState(){

    }
    private boolean isInTrigger(int id) {
        return player.getX() > map.getObjectX(id)
                && player.getX() < map.getObjectX(id) + map.getObjectWidth(id)
                && player.getY()-10 > map.getObjectY(id)
                && player.getY()-10 < map.getObjectY(id) + map.getObjectHeight(id);
    }

    private void launchEnigme(GameContainer gameContainer , StateBasedGame basedGame, String objectID) throws SlickException, IOException {
        //Input input = gameContainer.getInput();
        //input.clearKeyPressedRecord();
        Enigme enigme = Enigme.buildEnigmeFromJson(objectID);
        Epreuve epreuve = new Epreuve(enigme);
        CodeState cs =(CodeState)this.game.getState(CodeState.ID);
        MapState mapState = (MapState)this.game.getState(MapState.ID);
        mapState.setOn(false);
        cs.initUI(gameContainer,epreuve);

        this.game.enterState(CodeState.ID);

    }

}