package Maps;

import Tilesets.CommonTileset;
import Utils.Direction;
import Utils.Point;
import Level.Enemy;

import java.lang.reflect.Array;
import java.util.ArrayList;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import EnhancedMapTiles.NewEndLevel;
import Level.EnhancedMapTile;
import Level.Map;

public class TutorialMap extends Map {
    public TutorialMap() {
        super("tutorial_map.txt", new CommonTileset());
        this.playerStartPosition = new Point(1, 11);
    }

    // x 53 y 12

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        BugEnemy bugEnemy1 = new BugEnemy(getMapTile(53, 12).getLocation().subtractY(25), Direction.RIGHT);
        enemies.add(bugEnemy1);

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        NewEndLevel newEndLevel = new NewEndLevel(getMapTile(74, 12).getLocation());
        enhancedMapTiles.add(newEndLevel);

        return enhancedMapTiles;
    }

}
