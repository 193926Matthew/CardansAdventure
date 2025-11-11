package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Utils.Point;

import java.util.HashMap;

// This class is for the end level gold box tile
// when the player touches it, it will tell the player that the level has been completed
public class EndLevelBox extends EnhancedMapTile {
    public EndLevelBox(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("PandaCage.png"), 64, 64), TileType.PASSABLE);
    }

    @Override
    public void update(Player player) {
        if (player.complete == true) {
            currentAnimationName = "OPEN";
        }
        super.update(player);
        if (intersects(player)) {
            player.completeLevel();
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 20)
                        .withScale(0.8f)
                        .withBounds(1, 1, 64, 64)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 20)
                        .withScale(0.8f)
                        .withBounds(1, 1, 64, 64)
                        .build(),
            });
            put("OPEN", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 2), 20)
                        .withScale(0.8f)
                        .withBounds(1, 1, 64, 64)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 20)
                        .withScale(0.8f)
                        .withBounds(1, 1, 64, 64)
                        .build(),
            });
        }};
    }
}
