package Maps;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Sprite;
import GameObject.SpriteSheet;
import Level.Hitbox;
import Level.Map;
import Tilesets.CommonTileset;
import Utils.Colors;
import Utils.Point;

// Represents the map that is used as a background for the main menu and credits menu screen
public class TitleScreenMap extends Map {

    private Sprite cat;
    private Sprite skunk;
    private Sprite iceBall1, iceBall2, iceBall3;
    private Sprite dinosaurEnemy;
    private Sprite fireBall;

    public TitleScreenMap() {
        super("title_screen_map.txt", new CommonTileset());

        SpriteSheet cardanSpriteSheet = new SpriteSheet(ImageLoader.load("Cardan.png"), 70, 70);

        Point catLocation = getMapTile(7, 6).getLocation().subtractX(35).subtractY(8);
        cat = new Sprite(cardanSpriteSheet.getSprite(2, 1));
        cat.setScale(1);
        cat.setLocation(catLocation.x, catLocation.y);

        Point skunkLocation = getMapTile(10, 8).getLocation().subtractX(70).subtractY(-8);
        skunk = new Sprite(ImageLoader.loadSubImage("Skunk.png", Colors.MAGENTA, 0, 0, 24, 15));
        skunk.setScale(3);
        skunk.setLocation(skunkLocation.x, skunkLocation.y);

        Point iceBall1Location = getMapTile(10, 8).getLocation().subtractX(70).subtractY(10);
        iceBall1 = new Sprite(ImageLoader.loadSubImage("IceBall.png", Colors.MAGENTA, 0, 0, 15, 15));
        iceBall1.setScale(1);
        iceBall1.setLocation(iceBall1Location.x, iceBall1Location.y);

        Point iceBall2Location = getMapTile(9, 7).getLocation().subtractX(70).subtractY(10);
        iceBall2 = new Sprite(ImageLoader.loadSubImage("IceBall.png", Colors.MAGENTA, 0, 0, 15, 15));
        iceBall2.setScale(1);
        iceBall2.setLocation(iceBall2Location.x, iceBall2Location.y);

        Point iceBall3Location = getMapTile(10, 8).getLocation().subtractX(95).subtractY(35);
        iceBall3 = new Sprite(ImageLoader.loadSubImage("IceBall.png", Colors.MAGENTA, 0, 0, 15, 15));
        iceBall3.setScale(1);
        iceBall3.setLocation(iceBall3Location.x, iceBall3Location.y);

        
        Point dinosaurEnemyLocation = getMapTile(13, 7).getLocation().subtractX(0).subtractY(0);
        dinosaurEnemy = new Sprite(ImageLoader.loadSubImage("DinosaurEnemy.png", Colors.MAGENTA, 0, 18, 14, 17));
        dinosaurEnemy.setScale(3);
        dinosaurEnemy.setLocation(dinosaurEnemyLocation.x, dinosaurEnemyLocation.y);

        //ADD FIRE BALL MAKE SURE TO DRAW IT 
        Point fireBallLocation = getMapTile(12, 7).getLocation().subtractX(0).subtractY(0);
        fireBall = new Sprite(ImageLoader.loadSubImage("Fireball.png", Colors.MAGENTA, 0, 0, 7, 7));
        fireBall.setScale(3);
        fireBall.setLocation(fireBallLocation.x, fireBallLocation.y);

    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        cat.draw(graphicsHandler);
        skunk.draw(graphicsHandler);
        iceBall1.draw(graphicsHandler);
        iceBall2.draw(graphicsHandler);
        iceBall3.draw(graphicsHandler);
        dinosaurEnemy.draw(graphicsHandler);
        fireBall.draw(graphicsHandler);
    }


}
