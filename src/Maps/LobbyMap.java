package Maps;

import java.util.ArrayList;

import Enemies.ArticFox;
import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import EnhancedMapTiles.JungleEnter;
import EnhancedMapTiles.SnowEnter;
import Level.Enemy;
import Level.EnhancedMapTile;
import Level.Map;
import Level.MapTile;
import EnhancedMapTiles.BarrierBlock;
import Tilesets.CommonTileset;
import Utils.Direction;
import Utils.Point;
import Level.TileType;


public class LobbyMap extends Map {

    public LobbyMap(){
        super("lobby_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(13, 11).getLocation();;
    }

    // @Override
    // public ArrayList<Enemy> loadEnemies() {
    // ArrayList<Enemy> enemies = new ArrayList<>();

    //     ArticFox fox1 = new ArticFox(getMapTile(10, 12).getLocation().subtractY(25), Direction.RIGHT);
    //     enemies.add(fox1);

    //     return enemies;
    // }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles(){
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        
        
        for(int i =1; i <= this.getHeight() - 4; i++){
            MapTile newTile = getMapTile(this.getEndBoundX(), i);
            if(newTile != null){
                BarrierBlock verticalBarrier = new BarrierBlock(newTile.getLocation(),"CommonTileset.png");
                enhancedMapTiles.add(verticalBarrier);
            }
        }

        for(int m =0; m <= this.getHeight() - 4; m++){
            MapTile anotherTile = getMapTile(34, m);
            if(anotherTile != null){
                BarrierBlock verticalBarrier2 = new BarrierBlock(anotherTile.getLocation(),"CommonTileset.png");
                enhancedMapTiles.add(verticalBarrier2);
            }
        }

        for(int j =10; j <= this.getWidth() - 4; j++){
            MapTile thirdTile = getMapTile(j, this.getEndBoundY());
            if(thirdTile != null){
                BarrierBlock verticalBarrier2 = new BarrierBlock(thirdTile.getLocation(),"CommonTileset.png");
                enhancedMapTiles.add(verticalBarrier2);
            }
        }
    
        

        JungleEnter jungleEnter = new JungleEnter(getMapTile(4,11).getLocation());
        enhancedMapTiles.add(jungleEnter);

        SnowEnter snowEnter = new SnowEnter(getMapTile(19,11).getLocation());
        enhancedMapTiles.add(snowEnter);

        return enhancedMapTiles;
    }
    
    
}
