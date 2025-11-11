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
    private Sprite snakeEnemy;
    private Sprite snakeSpit;
    private Sprite pandaCage;

    public TitleScreenMap() {
        super("title_screen_map.txt", new CommonTileset());

        SpriteSheet cardanSpriteSheet = new SpriteSheet(ImageLoader.load("Cardan.png"), 70, 70);

        Point catLocation = getMapTile(7, 6).getLocation().subtractX(35).subtractY(8);
        cat = new Sprite(cardanSpriteSheet.getSprite(2, 1));
        cat.setScale(1);
        cat.setLocation(catLocation.x, catLocation.y);

        Point skunkLocation = getMapTile(10, 7).getLocation().subtractX(70).subtractY(-40);
        skunk = new Sprite(ImageLoader.loadSubImage("Skunk.png", Colors.MAGENTA, 0, 0, 99, 64));
        skunk.setScale(1);
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

        
        Point snakeEnemyLocation = getMapTile(13, 8).getLocation().subtractX(0).subtractY(45);
        snakeEnemy = new Sprite(ImageLoader.loadSubImage("Snake.png", Colors.MAGENTA, 147, 0, 48, 47));
        snakeEnemy.setScale(1);
        snakeEnemy.setLocation(snakeEnemyLocation.x, snakeEnemyLocation.y);

        Point snakeSpitLocation = getMapTile(12, 7).getLocation().subtractX(0).subtractY(0);
        snakeSpit = new Sprite(ImageLoader.loadSubImage("SnakeSpit.png", Colors.MAGENTA, 0, 0, 23, 23));
        snakeSpit.setScale(1);
        snakeSpit.setLocation(snakeSpitLocation.x, snakeSpitLocation.y);

        Point pandaCageLocation = getMapTile(14,6).getLocation().subtractX(0).subtractY(30);
        pandaCage = new Sprite(ImageLoader.loadSubImage("PandaCage.png", Colors.MAGENTA, 0, 0, 63,63));
        pandaCage.setScale(0.8f);
        pandaCage.setLocation(pandaCageLocation.x, pandaCageLocation.y);

    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        cat.draw(graphicsHandler);
        skunk.draw(graphicsHandler);
        iceBall1.draw(graphicsHandler);
        iceBall2.draw(graphicsHandler);
        iceBall3.draw(graphicsHandler);
        snakeEnemy.draw(graphicsHandler);
        snakeSpit.draw(graphicsHandler);
        pandaCage.draw(graphicsHandler);
    }


}
