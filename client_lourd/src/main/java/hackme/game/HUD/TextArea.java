package hackme.game.HUD;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.gui.GUIContext;

public class TextArea extends  CustomTextField {
    private static final int INITIAL_KEY_REPEAT_INTERVAL = 400;
    private static final int KEY_REPEAT_INTERVAL = 50;
    private int width;
    private int height;
    protected int x;
    protected int y;
    private int maxCharacter;
    private String value;
    private Font font;
    private Color border;
    private Color text;
    private Color background;
    private int cursorPos;
    private boolean visibleCursor;
    private int lastKey;
    private char lastChar;
    private long repeatTimer;
    private String oldText;
    private int oldCursorPos;
    private boolean consume;
    private int temp_height;

    public TextArea(GUIContext container, Font font, int x, int y, int width, int height) {
            super(container,font,x,y,width,height);
        this.maxCharacter = 10000;
        this.value = "";
        this.text = Color.white;
        this.background = new Color(Color.black);
        this.visibleCursor = true;
        this.lastKey = -1;
        this.lastChar = 0;
        this.consume = true;
        this.font = font;
        this.setLocation(x, y);
        this.width = width;
        this.height = height;
        this.temp_height=height;

    }


/*@Override
    public void keyPressed(int key, char c) {
        if (this.hasFocus()) {

            if (this.lastKey != key) {
                this.lastKey = key;
                this.repeatTimer = System.currentTimeMillis() + 400L;
            } else {
                this.repeatTimer = System.currentTimeMillis() + 50L;
            }

            this.lastChar = c;
            if (key == Input.KEY_LEFT) {
                if (this.cursorPos > 0) {
                    --this.cursorPos;
                }

                if (this.consume) {
                    this.container.getInput().consumeEvent();
                }
            } else if (key == Input.KEY_RIGHT) {
                if (this.cursorPos < this.value.length()) {
                    ++this.cursorPos;
                }

                if (this.consume) {
                    this.container.getInput().consumeEvent();
                }
            } else if (key == Input.KEY_BACK) {
                if (this.cursorPos > 0 && this.value.length() > 0) {
                    if (this.cursorPos < this.value.length()) {
                        this.value = this.value.substring(0, this.cursorPos - 1) + this.value.substring(this.cursorPos);
                    } else {
                        this.value = this.value.substring(0, this.cursorPos - 1);
                    }

                    --this.cursorPos;
                }

                if (this.consume) {
                    this.container.getInput().consumeEvent();
                }
            }

        }

    }*/


}

