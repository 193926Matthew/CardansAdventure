package Maps;

import java.util.ArrayList;

import EnhancedMapTiles.JungleEnter;
import EnhancedMapTiles.SnowEnter;
import Level.EnhancedMapTile;
import Level.Map;

import Tilesets.CommonTileset;
import Utils.Point;

public class LobbyMap extends Map {

    public LobbyMap(){
        super("lobby_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(13, 11).getLocation();;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles(){
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        JungleEnter jungleEnter = new JungleEnter(getMapTile(4,11).getLocation());
        enhancedMapTiles.add(jungleEnter);

        SnowEnter snowEnter = new SnowEnter(getMapTile(19,11).getLocation());
        enhancedMapTiles.add(snowEnter);

        return enhancedMapTiles;
    }
    
    
}
