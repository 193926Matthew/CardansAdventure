package Maps;

import java.util.ArrayList;

import Level.EnhancedMapTile;
import Level.Map;
import Tilesets.CommonTileset;
import Utils.Direction;
import Enemies.JungleBoss;
import EnhancedMapTiles.NewEndLevel;
import EnhancedMapTiles.SpawnBossTrigger;

public class JungleBossArena extends Map {

    private boolean bossAlreadySpawned = false;
    private boolean BossDead = false;


    public JungleBossArena() {
        super("JungleBossArena.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(2, 4).getLocation();
    }

    public boolean isBossAlreadySpawned() {
        return bossAlreadySpawned;
    }

    public boolean isBossDead(){
        return BossDead;
    }

    public void spawnBossNow() {
        JungleBoss boss = new JungleBoss(
            getMapTile(15, 5).getLocation().subtractY(25),
            Direction.LEFT
        );

        addEnemy(boss);
        bossAlreadySpawned = true;
        System.out.println("Jungle Boss Spawned");
    }   


    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        SpawnBossTrigger spawnBossTrigger = new SpawnBossTrigger(getMapTile(10, 4).getLocation(),this);
        enhancedMapTiles.add(spawnBossTrigger);

        NewEndLevel friend = new NewEndLevel(getMapTile(4,11).getLocation());
        if (BossDead){
            enhancedMapTiles.add(friend);
        }
        return enhancedMapTiles;
        
    }

    @Override
    public ArrayList<Level.Enemy> loadEnemies() {
        ArrayList<Level.Enemy> enemies = new ArrayList<>();


        return enemies;
    }
}
