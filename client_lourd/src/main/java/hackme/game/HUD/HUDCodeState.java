package hackme.game.HUD;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;

public class HUDCodeState {
    private ButtonHUD button_compile;
    private List<TextField> _textFieldList ;



    private WindowCompilation _windowCompilation;
    private ButtonQuit _buttonQuit;
    public void init(GameContainer gameContainer) throws SlickException {
        SpriteSheet spriteSheet = new SpriteSheet("src/main/resources/image/UI/preview_164.png", 105, 32);
        button_compile = new ButtonHUD("Test",120,660);
        Animation[] animations = new Animation[3];
        animations[0] = loadAnimation(spriteSheet, 1, 2, 2);
        animations[1] = loadAnimation(spriteSheet, 1, 3, 2);
        animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
        button_compile.setAnimations(animations);
        _buttonQuit = new ButtonQuit("Quit",870,660);
        _buttonQuit.setAnimations(animations);
        TrueTypeFont font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF,java.awt.Font.BOLD , 16), false);
        _textFieldList = new ArrayList<>();
        _windowCompilation = new WindowCompilation("test",130,180);
        _windowCompilation.setImage(spriteSheet.getSubImage(638,195,96,89));
        _windowCompilation.setOK(spriteSheet.getSubImage(58,280,18,18));
        _windowCompilation.setKO(spriteSheet.getSubImage(83,330,18,18));
        SpriteSheet windowscomp = new SpriteSheet("src/main/resources/image/UI/windowscompil.png",10,10);
        SpriteSheet windowscompKO= new SpriteSheet("src/main/resources/image/UI/windowscompKO.png",10,10);
        _windowCompilation.setWindowOK(windowscomp.getSubImage(103,129,1126,455));
        _windowCompilation.setWindowKO(windowscompKO.getSubImage(103,129,1126,455));
        CustomTextField customTextField = new CustomTextField(gameContainer,font,490,200,700,300);
        customTextField.setArea(true);
        _windowCompilation.setErrorField(customTextField);
        ButtonOk buttonOk = new ButtonOk("OK",290,400);
        buttonOk.setAnimations(animations);
        _windowCompilation.setButtonOk(buttonOk);
    }


    public void render(GameContainer gameContainer , Graphics g,boolean isCompiling) throws SlickException {
        g.resetTransform();
        button_compile.render(g);
        for(TextField textField : _textFieldList){
            g.resetTransform();
            textField.render(gameContainer,g);
        }
        if(isCompiling) {
            _windowCompilation.render(gameContainer,g);
        }
        _buttonQuit.render(g);

    }
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        button_compile.update( gameContainer,  stateBasedGame,i);
        _windowCompilation.update(gameContainer,stateBasedGame,i);
        _buttonQuit.update(gameContainer,stateBasedGame,i);

    }

    private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 200);
        }
        return animation;
    }
    public List<TextField> get_textFieldList() {
        return _textFieldList;
    }

    public void set_textFieldList(List<TextField> _textFieldList) {
        this._textFieldList = _textFieldList;
    }
    public  void add_textFieldList(TextField textField){
        this._textFieldList.add(textField);
    }


    public WindowCompilation get_windowCompilation() {
        return _windowCompilation;
    }
}
