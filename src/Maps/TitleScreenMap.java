package Maps;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Sprite;
import Level.Hitbox;
import Level.Map;
import Tilesets.CommonTileset;
import Utils.Colors;
import Utils.Point;

// Represents the map that is used as a background for the main menu and credits menu screen
public class TitleScreenMap extends Map {

    private Sprite cat;

    public TitleScreenMap() {
        super("title_screen_map.txt", new CommonTileset());
        Point catLocation = getMapTile(6, 8).getLocation().subtractX(70).subtractY(10);
        cat = new Sprite(ImageLoader.loadSubImage("Cardan.png", Colors.MAGENTA, 0, 0, 70, 70));
        cat.setScale(1);
        cat.setLocation(catLocation.x, catLocation.y);
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        cat.draw(graphicsHandler);
    }


}
