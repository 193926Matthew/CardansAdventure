package EnhancedMapTiles;
import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Utils.Point;

public class NewEndLevel extends EnhancedMapTile {

    public NewEndLevel(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("PandaCage.png"), 64, 64), TileType.PASSABLE);
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (intersects(player)) {
            System.out.println("Level Completed!");
            currentAnimationName = "BROKEOUT";
            currentAnimationName = "FREE";
            player.completeLevel();
        }
    }
    
    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 40)
                        .withScale(1)
                        .withBounds(1, 1, 64, 64)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 40)
                        .withScale(1)
                        .withBounds(1, 1, 64, 64)
                        .build(),

            });
            
            put("BROKEOUT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 2), 40)
                        .withScale(1)
                        .withBounds(1, 1, 64, 64)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 40)
                        .withScale(1)
                        .withBounds(1, 1, 64, 64)
                        .build(),
            });

            put("FREE", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0,6), 40)
                        .withScale(1)
                        .withBounds(1, 1, 64, 64)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0,7), 40)
                        .withScale(1)
                        .withBounds(1, 1, 64, 64)
                        .build(),
            }); 
            
        }};
    }
}
