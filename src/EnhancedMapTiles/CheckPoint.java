package EnhancedMapTiles;

import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.TileType;
import Utils.Point;
import Level.Player;
import Builders.FrameBuilder;
import java.util.HashMap;
import Game.GameState;
import Game.ScreenCoordinator;
import Game.Game;

public class CheckPoint extends EnhancedMapTile{
    private boolean activated;

    public CheckPoint(Point location){
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("GoldBox.png"), 16, 16), TileType.PASSABLE);
    }

    public void update(Player player) {
        super.update(player);
        //player.setRespawnPoint(new Point(2, 21)); // Default respawn point at start
        if (intersects(player)) {
            // checkpoint stuff here
            //System.out.println("Checkpoint reached!");
            activated = true;
            player.setRespawnPoint(new Point(this.getX(), this.getY())); // Set respawn point above the checkpoint
            //System.out.println("Respawn point set to: (" + this.getX() + ", " + this.getY() + ")");

        }
    }

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
