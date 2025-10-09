package EnhancedMapTiles;

import java.util.HashMap;

import Builders.FrameBuilder;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.MapTile;
import Engine.*;

import Players.Cat;
import Level.Player;

import Level.TileType;
import Utils.Direction;
//import SpriteFont.SpriteFont;
import Utils.Point;


public class QuicksandTile extends EnhancedMapTile {

    public QuicksandTile(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("DesertTileset.png"), 16, 16), TileType.PASSABLE);
    } 

    @Override
    public void update(Player player) {
        super.update(player);
    }
    
        @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(3, 1), 65)
                        .withScale(3)
                        .withBounds(1, 1, 14, 14)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(4, 3), 65)
                        .withScale(3)
                        .withBounds(1, 1, 14, 14)
                        .build()
            });
        }};
    }
}

