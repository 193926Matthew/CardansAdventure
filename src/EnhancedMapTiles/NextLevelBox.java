package EnhancedMapTiles;

import java.util.HashMap;

import Builders.FrameBuilder;
import Game.GameState;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;

import Engine.*;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;

import java.awt.*;

import Level.Player;

import Level.TileType;

//import SpriteFont.SpriteFont;
import Utils.Point;


public class NextLevelBox extends EnhancedMapTile {

        public KeyLocker keyLocker = new KeyLocker();
        protected ScreenCoordinator screenCoordinator;
        





    public NextLevelBox(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("GoldBox.png"), 16, 16), TileType.PASSABLE);

    }

    @Override
    public void update(Player player) {
        super.update(player);
    }
    

        @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 40)
                        .withScale(3)
                        .withBounds(1, 1, 14, 14)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 40)
                        .withScale(3)
                        .withBounds(1, 1, 14, 14)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 40)
                        .withScale(3)
                        .withBounds(1, 1, 14, 14)
                        .build()
            });
        }};
    }
}

