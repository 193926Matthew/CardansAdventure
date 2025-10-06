package Maps;
import EnhancedMapTiles.PowerUp;
import EnhancedMapTiles.QuicksandTile;
import EnhancedMapTiles.QuicksandTopTile;
import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.FallingPlatform;
import EnhancedMapTiles.HorizontalMovingPlatform;
import EnhancedMapTiles.VerticalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import NPCs.Walrus;
import Tilesets.DesertTileset;
import Utils.Direction;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class DesertMap extends Map {

    public DesertMap() {
        super("desert_map.txt", new DesertTileset());
        this.playerStartPosition = getMapTile(2, 21).getLocation();
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        FallingPlatform fp = new FallingPlatform(
                ImageLoader.load("FallingPlatform.png"),
                getMapTile(4, 16).getLocation(),
                getMapTile(4, 0).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.DOWN
        );
        enhancedMapTiles.add(fp);

        FallingPlatform fp2 = new FallingPlatform(
                ImageLoader.load("FallingPlatform.png"),
                getMapTile(6, 14).getLocation(),
                getMapTile(6, 0).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.DOWN
        );

        enhancedMapTiles.add(fp2);


        QuicksandTile qs = new QuicksandTile(getMapTile(12, 21).getLocation());
        QuicksandTile qs2 = new QuicksandTile(getMapTile(12, 22).getLocation());
        QuicksandTile qs3 = new QuicksandTile(getMapTile(12, 23).getLocation());
        QuicksandTile qs4 = new QuicksandTile(getMapTile(12, 24).getLocation());
        QuicksandTile qs5 = new QuicksandTile(getMapTile(12, 25).getLocation());
        QuicksandTile qs6 = new QuicksandTile(getMapTile(12, 26).getLocation());
        QuicksandTile qs7 = new QuicksandTile(getMapTile(12, 27).getLocation());
        QuicksandTile qs8 = new QuicksandTile(getMapTile(12, 28).getLocation());
        QuicksandTile qs9 = new QuicksandTile(getMapTile(12, 29).getLocation());

        QuicksandTile qs10 = new QuicksandTile(getMapTile(13, 21).getLocation());
        QuicksandTile qs11 = new QuicksandTile(getMapTile(13, 22).getLocation());
        QuicksandTile qs12 = new QuicksandTile(getMapTile(13, 23).getLocation());
        QuicksandTile qs13 = new QuicksandTile(getMapTile(13, 24).getLocation());
        QuicksandTile qs14 = new QuicksandTile(getMapTile(13, 25).getLocation());
        QuicksandTile qs15 = new QuicksandTile(getMapTile(13, 26).getLocation());
        QuicksandTile qs16 = new QuicksandTile(getMapTile(13, 27).getLocation());
        QuicksandTile qs17 = new QuicksandTile(getMapTile(13, 28).getLocation());
        QuicksandTile qs18 = new QuicksandTile(getMapTile(13, 29).getLocation());

        QuicksandTopTile qsTop = new QuicksandTopTile(getMapTile(12, 20).getLocation());
        QuicksandTopTile qsTop2 = new QuicksandTopTile(getMapTile(13, 20).getLocation());


        enhancedMapTiles.add(qs);
        enhancedMapTiles.add(qs2);
        enhancedMapTiles.add(qs3);
        enhancedMapTiles.add(qs4);
        enhancedMapTiles.add(qs5);
        enhancedMapTiles.add(qs6);
        enhancedMapTiles.add(qs7);
        enhancedMapTiles.add(qs8);
        enhancedMapTiles.add(qs9);
        enhancedMapTiles.add(qs10);
        enhancedMapTiles.add(qs11);
        enhancedMapTiles.add(qs12);
        enhancedMapTiles.add(qs13);
        enhancedMapTiles.add(qs14);
        enhancedMapTiles.add(qs15);
        enhancedMapTiles.add(qs16);
        enhancedMapTiles.add(qs17);
        enhancedMapTiles.add(qs18);

        enhancedMapTiles.add(qsTop);
        enhancedMapTiles.add(qsTop2);

        return enhancedMapTiles;
        
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        return npcs;
    }
}
