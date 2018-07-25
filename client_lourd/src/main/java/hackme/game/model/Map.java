package hackme.game.model;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.io.IOException;
import java.util.ArrayList;

public class Map {


    private TiledMap tiledMap;
    public boolean[] boolenigme;
    public ArrayList<Epreuve> epreuves = new ArrayList<Epreuve>();
    public void init(GameContainer container) throws SlickException {
        this.tiledMap = new TiledMap(System.getProperty("selectedMap")+"/map.tmx" );//  src/main/resources/maps/map0/map.tmx System.getProperty("selectedMap")+"/map.tmx"
        if(this.tiledMap.getWidth()*this.tiledMap.getTileWidth()>720 || this.tiledMap.getTileHeight()*this.tiledMap.getHeight()>720){
            container.setForceExit(false);
            container.exit();
        }

        epreuves = initArray();
    }

    public ArrayList<Epreuve> getEpreuves() {
        return epreuves;
    }

    private ArrayList<Epreuve> initArray() throws SlickException {
        ArrayList<Epreuve> epreuvesResult =new ArrayList<Epreuve>();
        int count =0 ;
        for (int objectID = 0; objectID < getObjectCount(); objectID++) {
                if ("Enigme".equals(getObjectType(objectID))) {
                    //launchEnigme(gameContainer, basedGame, map.getObjectProperty(objectID, "ID", ""));
                    Enigme enigme = null;
                    String enigmeID = getObjectProperty(objectID,"ID","");
                    try {
                        enigme = Enigme.buildEnigmeFromJson(enigmeID );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    epreuvesResult.add( new Epreuve(enigme,enigmeID));
                   count++;
                }
            }
        return epreuvesResult;
    }


    public Epreuve getEpreuveByID(String epreuveID){
        for(Epreuve epreuve : epreuves){
            if(epreuve.get_enigmeID() == epreuveID){
                return epreuve ;
            }
        }
        return null;
    }

    public void renderBackground() {
        this.tiledMap.render(0, 0,0);
        this.tiledMap.render(0, 0,1);
    }

    public void renderForeground() {
        //this.tiledMap.getLayerIndex("Wall");
        this.tiledMap.render(0, 0, 2);
    }

    public boolean isCollision(float x, float y) {
        if(x <0 || y <0 || x>tiledMap.getWidth()*tiledMap.getTileWidth() || y >tiledMap.getHeight()*tiledMap.getTileHeight()) {
            return true ;
        }
        int tileW = this.tiledMap.getTileWidth();
        int tileH = this.tiledMap.getTileHeight();
        int logicLayer = this.tiledMap.getLayerIndex("Wall");
        Image tile = this.tiledMap.getTileImage((int) x / tileW, (int) y / tileH, logicLayer);
        boolean collision = tile != null;
        if (collision) {
            Color color = tile.getColor((int) x % tileW, (int) y % tileH);
            collision = color.getAlpha() > 0;
        }
        return collision;
    }


    public int getObjectCount() {
        return this.tiledMap.getObjectCount(0);
    }
    public String getObjectType(int objectID) {
        return this.tiledMap.getObjectType(0, objectID);
    }
    public float getObjectX(int objectID) {
        return this.tiledMap.getObjectX(0, objectID);
    }
    public float getObjectY(int objectID) {
        return this.tiledMap.getObjectY(0, objectID);
    }
    public float getObjectWidth(int objectID) {
        return this.tiledMap.getObjectWidth(0, objectID);
    }
    public float getObjectHeight(int objectID) {
        return this.tiledMap.getObjectHeight(0, objectID);
    }
    public String getObjectProperty(int objectID, String propertyName, String def) {
        return this.tiledMap.getObjectProperty(0, objectID, propertyName, def);
    }


}