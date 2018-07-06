package hackme.model;

public class Player extends Person {

    int stealth;

    Treasure treasure;

    public Player(int stealth, Treasure treasure) {
        this.stealth = stealth;
        this.treasure = treasure;
    }

    public Player(int posX, int posY, int stealth, Treasure treasure) {
        super(posX, posY);
        this.stealth = stealth;
        this.treasure = treasure;
    }

    public int getStealth() {
        return stealth;
    }

    public void setStealth(int stealth) {
        this.stealth = stealth;
    }

    public Treasure getTreasure() {
        return treasure;
    }

    public void setTreasure(Treasure treasure) {
        this.treasure = treasure;
    }
}
