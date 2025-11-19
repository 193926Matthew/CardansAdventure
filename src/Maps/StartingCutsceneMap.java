package Maps;

import Tilesets.CommonTileset;
import Utils.Direction;
import Utils.Point;

import java.lang.reflect.Array;
import java.util.ArrayList;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import EnhancedMapTiles.NewEndLevel;
import Level.*;

public class StartingCutsceneMap extends Map {
    public StartingCutsceneMap() {
        super("startingCutscene.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(1, 18).getLocation();
    }

    // x 53 y 12

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        return enhancedMapTiles;
    }
    
}
