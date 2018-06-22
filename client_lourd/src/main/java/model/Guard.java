package model;

public class Guard extends Person{
    int vision;

    public Guard(int vision) {
        this.vision = vision;
    }

    public Guard(int posX, int posY, int vision) {
        super(posX, posY);
        this.vision = vision;
    }

    public int getVision() {
        return vision;
    }

    public void setVision(int vision) {
        this.vision = vision;
    }
}
