package Interface.model;

import Interface.util.SpriteAnimation;
import com.sun.javafx.geom.Rectangle;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Character extends Pane{

    public enum Card{
        NORTH(1),
        EAST(2),
        WEST(3),
        SOUTH(4);
        private int way ;
       Card(int way){
            this.way = way;
        }
    }
    ImageView imageView;
    int count ;
    int columns;
    int offsetX;
    int offsetY;
    int width;
    int height;
    int score;

    Rectangle removeRect = null;
    SpriteAnimation animation ;


    private Vec2d x_y ;

    private int lookingAt;

    public Character(ImageView imageView){
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetY,offsetX,width,height));
        animation = new SpriteAnimation(imageView , Duration.millis(200),count,columns,offsetX,offsetY,width,height);
        getChildren().addAll(imageView);
    }

    public void moveX(int x){
        boolean right = x > 0;
        for(int i =0;i<Math.abs(x);i++){
            if(right) this.setTranslateX(this.getTranslateX()+1);
            else this.setTranslateX(this.getTranslateX()-1);
        }
    }

    public void moveY(int y){
        boolean right = y > 0;
        for(int i =0;i<Math.abs(y);i++){
            if(right) this.setTranslateY(this.getTranslateY()+1);
            else this.setTranslateY(this.getTranslateY()-1);
        }
    }

}
