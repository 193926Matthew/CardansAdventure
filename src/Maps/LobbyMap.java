package Maps;

import java.util.ArrayList;

import EnhancedMapTiles.NextLevelBox;

import Level.EnhancedMapTile;
import Level.Map;

import Tilesets.CommonTileset;
import Utils.Point;

public class LobbyMap extends Map{



    public LobbyMap(){
        super("lobby_map.txt", new CommonTileset());
        this.playerStartPosition = new Point(-5, 1);
    }

    // @Override
    // public ArrayList<EnhancedMapTile> loadEnhancedMapTiles(){
    //     ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        
    //     NextLevelBox nextLevelBox = new NextLevelBox(getMapTile(2, 10).getLocation());
    //     enhancedMapTiles.add(nextLevelBox);

        

    //     return enhancedMapTiles;
    // }
    
    
}
