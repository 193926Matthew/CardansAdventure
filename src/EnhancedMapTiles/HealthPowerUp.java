package EnhancedMapTiles;

import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.MapEntityStatus;
import Level.Player;
import Level.TileType;
import Utils.Point;
import Builders.FrameBuilder;
import Engine.GraphicsHandler;

import java.util.HashMap;

public class HealthPowerUp extends EnhancedMapTile {

    private int healAmount = 100;
    private boolean collected = false;

    public HealthPowerUp(Point location) {
        // Load HealthItem.png sprite sheet with tile size 15x15 (adjust if needed)
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("HealthItem.png"), 25, 25), TileType.PASSABLE);
    }

    @Override
    public void update(Player player) {
        int currentHealth = player.getHealth();
        super.update(player);
        if (!collected && intersects(player)) {
            collected = true;
            player.setHealth(currentHealth + healAmount);
            this.mapEntityStatus = MapEntityStatus.REMOVED;
            // System.out.println("Collected Health PowerUp: healed " + healAmount);
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {
            {
                put("DEFAULT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 0))
                                .withScale(1)
                                .withBounds(1, 1, 14, 14)
                                .build()
                });
            }
        };
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        if (!collected) {
            super.draw(graphicsHandler);
        }
    }
}
