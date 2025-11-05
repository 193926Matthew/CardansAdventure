package Maps;

import java.util.ArrayList;

import Enemies.ArcticFox;
import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import EnhancedMapTiles.JungleEnter;
import EnhancedMapTiles.SnowEnter;
import Level.Enemy;
import Level.EnhancedMapTile;
import Level.Map;
import Level.MapTile;
import EnhancedMapTiles.BarrierBlock;
import EnhancedMapTiles.DesertEnter;
import Tilesets.CommonTileset;
import Utils.Direction;
import Utils.Point;
import Level.TileType;


public class LobbyMap extends Map {

    public LobbyMap(){
        super("lobby_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(13, 11).getLocation();;
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
    ArrayList<Enemy> enemies = new ArrayList<>();

        ArcticFox fox1 = new ArcticFox(getMapTile(10, 12).getLocation().subtractY(25), Direction.RIGHT);
        enemies.add(fox1);

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles(){
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        
        BarrierBlock testBarrier = new BarrierBlock(getMapTile(0,11 ).getLocation(), "CommonTileset.png");
        enhancedMapTiles.add(testBarrier);
       
        
        DesertEnter desertEnter = new DesertEnter(getMapTile(10,11).getLocation());
        enhancedMapTiles.add(desertEnter);


        JungleEnter jungleEnter = new JungleEnter(getMapTile(4,11).getLocation());
        enhancedMapTiles.add(jungleEnter);

        SnowEnter snowEnter = new SnowEnter(getMapTile(19,11).getLocation());
        enhancedMapTiles.add(snowEnter);

        return enhancedMapTiles;
    }
    
    
}
