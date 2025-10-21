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

public class BackToLobby extends EnhancedMapTile{

    private int triggerCode = 0;

    public BackToLobby(Point location){
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("PandaCage.png"), 64, 64), TileType.PASSABLE);
    }

    public void update(Player player) {
        super.update(player);
        if (intersects(player)) {
            System.out.println("lobby touched");
            triggerCode = 3;
            //gameState = GameState.MENU;
        }
    }

    public int getTriggerCode() {
        return triggerCode;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 40)
                        .withScale(0.8)
                        .withBounds(1, 1, 14, 14)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 40)
                        .withScale(0.8)
                        .withBounds(1, 1, 14, 14)
                        .build(),
            });
        }};
    }
}
