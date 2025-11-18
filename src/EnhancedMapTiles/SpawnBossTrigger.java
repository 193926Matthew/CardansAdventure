package EnhancedMapTiles;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Maps.JungleBossArena;
import Utils.Point;

public class SpawnBossTrigger extends EnhancedMapTile{
    private JungleBossArena arena;

    public SpawnBossTrigger(Point location, JungleBossArena arena){
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("CommonTileset2.png"), 48, 48), TileType.PASSABLE);
        this.arena = arena;
    }


    public void update(Player player) {
    super.update(player);

    if (intersects(player) && !arena.isBossAlreadySpawned()) {
        arena.spawnBossNow();
    }
        
    }

    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 1), 40)
                        .withScale(1)
                        .withBounds(1, 1, 14, 14)
                        .build()
            });
        }};
    }

}
