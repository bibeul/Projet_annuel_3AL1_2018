package hackme.game.HUD;


import org.apache.commons.lang3.StringUtils;
import org.lwjgl.Sys;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;

import java.util.ArrayList;
import java.util.List;

public class CustomTextField extends TextField {
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
    private boolean area ;
    public boolean focus = false;


    public boolean isConsume() {
        return consume;
    }

    public void setConsume(boolean consume) {
        this.consume = consume;
    }

    public CustomTextField(GUIContext container, Font font, int x, int y, int width, int height) {
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
        this.x =x;
        this.y = y;
        this.setLocation(x, y);
        this.width = width;
        this.height = height;
        this.temp_height=height;
        this.area=false;
    }


    @Override
    public void render(GUIContext container, Graphics g){

        int x_temp = x;
        int y_temp = y;
        if (this.lastKey != -1) {
            if (this.input.isKeyDown(this.lastKey)) {
                if (this.repeatTimer < System.currentTimeMillis()) {
                    this.repeatTimer = System.currentTimeMillis() + 50L;
                    this.keyPressed(this.lastKey, this.lastChar);
                }
            } else {
                this.lastKey = -1;
            }
        }

        Rectangle oldClip = g.getClip();
        g.setWorldClip((float)this.x, (float)this.y, (float)this.width, (float)this.height);
        Color clr = g.getColor();
        if (this.background != null) {
            g.setColor(this.background);
            g.fillRect((float)this.x, (float)this.y, (float)this.width, (float)this.height);
        }

        g.setColor(this.text);
        Font temp = g.getFont();
        int lastpost = 0;
        if(this.value.substring(lastpost,this.cursorPos).lastIndexOf("\r")!=-1){
            lastpost = this.value.substring(lastpost,this.cursorPos).lastIndexOf("\r");
        }

        int cpos = this.font.getWidth(this.value.substring(lastpost, this.cursorPos));
        int tx = 0;
        int ty = 0;
        int t_y =0;

        int s_x = x;
        int s_y= y;
        if (cpos > this.width) {
            tx = this.width - cpos - this.font.getWidth("_");
        }
        t_y =StringUtils.countMatches(this.value.substring(0, this.cursorPos),'\r');
        if(t_y*25>temp_height-25){
            ty = (1+t_y)*25-temp_height ;
        }
        g.translate((float)(tx + 2), (float)(-ty-10));
        g.setFont(this.font);
        String[] parts;
        parts = this.value.split("\r");

        int i =0 ;
        int lastchar =0;
        //int t_x;
        for(String part : parts) {
            s_x =x_temp;
            g.drawString(part, (float) (s_x + 1), (float) (s_y + 8));
            s_y +=25;
            i++;
        }
        if(this.value.length() >0){

            lastchar = this.value.charAt(this.value.length()-1);
        }

        if (lastchar == 13){
                s_y+=25;
            }



            if (this.hasFocus() && this.visibleCursor) {
                g.drawString("_", (float) (this.x + 1 + cpos + 2), (float) (this.y+t_y*25 + 8));
            }
        x=x_temp;
        y=y_temp;

        g.translate((float)(-tx - 2), 0);
        if (this.border != null) {
            g.setColor(this.border.multiply(clr));
            g.drawRect((float)this.x, (float)y, (float)this.width, (float)this.height);
        }

        g.setColor(clr);
        g.setFont(temp);
        g.clearWorldClip();
        g.setClip(oldClip);
    }
    public boolean isArea() {
        return area;
    }

    public void setArea(boolean area) {
        this.area = area;
    }


    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setBackgroundColor(Color color) {
        this.background = color;
    }

    public void setBorderColor(Color color) {
        this.border = color;
    }

    public void setTextColor(Color color) {
        this.text = color;
    }
    public String getText() {
        return this.value;
    }

    @Override
    public void deactivate() {
        focus = false;
        setFocus(false);
    }

    public void setFocus(boolean focus) {
        this.lastKey = -1;
        super.setFocus(focus);
    }

    public void setText(String value) {
        this.value = value;
        if (this.cursorPos > value.length()) {
            this.cursorPos = value.length();
        }

    }

    public void setCursorPos(int pos) {
        this.cursorPos = pos;
        if (this.cursorPos > this.value.length()) {
            this.cursorPos = this.value.length();
        }

    }

    public void setCursorVisible(boolean visibleCursor) {
        this.visibleCursor = visibleCursor;
    }

    public void setMaxLength(int length) {
        this.maxCharacter = length;
        if (this.value.length() > this.maxCharacter) {
            this.value = this.value.substring(0, this.maxCharacter);
        }

    }

    protected void doPaste(String text) {
        this.recordOldPosition();

        for(int i = 0; i < text.length(); ++i) {
            this.keyPressed(-1, text.charAt(i));
        }

    }

    protected void recordOldPosition() {
        this.oldText = this.getText();
        this.oldCursorPos = this.cursorPos;
    }

    protected void doUndo(int oldCursorPos, String oldText) {
        if (oldText != null) {
            this.setText(oldText);
            this.setCursorPos(oldCursorPos);
        }

    }

    public void keyPressed(int key, char c) {
        if (super.hasFocus()) {
            if (key != -1 && !this.area) {
                label129:
                {
                    if (key == 47 && (this.input.isKeyDown(29) || this.input.isKeyDown(157))) {
                        String text = Sys.getClipboard();
                        if (text != null) {
                            this.doPaste(text);
                        }

                        return;
                    }

                    if (key != 44 || !this.input.isKeyDown(29) && !this.input.isKeyDown(157) && !this.input.isKeyDown(34)) {
                        if (!this.input.isKeyDown(157)) {
                            if (!this.input.isKeyDown(56)) {
                                break label129;
                            }

                            return;
                        }

                        return;
                    }

                    if (this.oldText != null) {
                        this.doUndo(this.oldCursorPos, this.oldText);
                    }

                    return;
                }
            }

            if (this.lastKey != key) {
                this.lastKey = key;
                this.repeatTimer = System.currentTimeMillis() + 200L;
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
            } else if (key == Input.KEY_BACK && !this.area) {
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
            }else if(key == Input.KEY_UP){
                this.cursorPos = checkWhereIsLastReturn();
                if (this.consume) {
                    this.container.getInput().consumeEvent();
                }
        }else if(key == Input.KEY_DOWN){
                this.cursorPos += checkWhereIsNextReturn();
                if (this.consume) {
                    this.container.getInput().consumeEvent();
                }
            } else if (key == Input.KEY_DELETE) {
                if (this.value.length() > this.cursorPos) {
                    this.value = this.value.substring(0, this.cursorPos) + this.value.substring(this.cursorPos + 1);
                }

                if (this.consume) {
                    this.container.getInput().consumeEvent();
                }
            } else if ( (c < 135 && c > 31 || c == 13 ) && this.value.length() < this.maxCharacter && !area) {
                if (this.cursorPos < this.value.length()) {
                    this.value = this.value.substring(0, this.cursorPos) + c + this.value.substring(this.cursorPos);
                } else {
                    this.value = this.value.substring(0, this.cursorPos) + c;
                }

                ++this.cursorPos;
                if (this.consume) {
                    this.container.getInput().consumeEvent();
                }
            } else if (key == 28) {
                this.notifyListeners();
                if (this.consume) {
                    this.container.getInput().consumeEvent();
                }
            }

        }

    }

    public int checkWhereIsLastReturn(){
        int result = this.value.substring(0,this.cursorPos).lastIndexOf("\r");
        if(result ==-1){
            return 0;
        }
            return result;

    }

    public int checkWhereIsNextReturn(){
        int result = this.value.substring(this.cursorPos).indexOf("\r");
        if(result ==0){
            result = this.value.substring(this.cursorPos+1).indexOf("\r");
        } if(result ==-1){
            return this.value.substring(this.cursorPos).length();
        }
            return result;
    }

    public void nullify() {
        width =0;
        height = 0 ;
    }
    private static String splitToNChar(String text, int size) {
        List<String> parts = new ArrayList<>();
        String result ="";
        int length = text.length();
        for (int i = 0; i < length; i += size) {
            String string = text.substring(i, Math.min(length, i + size));
            if(size+i<=length){
               string =  string.concat("\r");
            }
            result = result.concat(string);
        }
        return result;
    }

    public void formatText() {
        if(area){
            value = splitToNChar(this.value,this.width/this.font.getWidth("a"));
        }
    }
}

