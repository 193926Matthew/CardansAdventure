package Maps;

import java.util.ArrayList;

import Level.EnhancedMapTile;
import Level.Map;
import Tilesets.CommonTileset;
import Tilesets.JungleTileset;
import Utils.Direction;

import Enemies.JungleBoss;
import EnhancedMapTiles.HealthPowerUp;
import EnhancedMapTiles.NewEndLevel;
import EnhancedMapTiles.PowerUp;
import EnhancedMapTiles.SpawnBossTrigger;

public class JungleBossArena extends Map {

    private boolean bossAlreadySpawned = false;
    private boolean BossDead = false;


    public JungleBossArena() {
        super("JungleBossArena.txt", new JungleTileset());
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
            getMapTile(28, 11).getLocation().subtractY(25),
            Direction.LEFT,
            this
        );

        addEnemy(boss);
        bossAlreadySpawned = true;
        // System.out.println("Jungle Boss Spawned");
    }
    
    public void spawnFriend(){
        // System.out.println("frined");
        NewEndLevel friend = new NewEndLevel(getMapTile(22,11).getLocation());
        addEnhancedMapTile(friend);
        friend.initialize();
        return;

    }


    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        SpawnBossTrigger spawnBossTrigger = new SpawnBossTrigger(getMapTile(10, 4).getLocation(),this);
        enhancedMapTiles.add(spawnBossTrigger);

        NewEndLevel friend = new NewEndLevel(getMapTile(4,11).getLocation());
        if (BossDead){
            enhancedMapTiles.add(friend);
        }

        
        PowerUp fire = new PowerUp(getMapTile(6,4).getLocation(),"Fire Ball","fireFlower.png");
        enhancedMapTiles.add(fire);

        HealthPowerUp health1 = new HealthPowerUp(getMapTile(16,4).getLocation());
        HealthPowerUp health2 = new HealthPowerUp(getMapTile(28,4).getLocation());
        enhancedMapTiles.add(health1);
        enhancedMapTiles.add(health2);
        
        return enhancedMapTiles;
        
    }

    @Override
    public ArrayList<Level.Enemy> loadEnemies() {
        ArrayList<Level.Enemy> enemies = new ArrayList<>();


        return enemies;
    }
}
