package Maps;

import java.util.ArrayList;

import EnhancedMapTiles.BackToLobby;
import EnhancedMapTiles.JungleEnter;
import EnhancedMapTiles.SnowEnter;
import Level.EnhancedMapTile;
import Level.Map;
import Tilesets.CommonTileset;
import Utils.Point;

public class JungleMap extends Map{

    public JungleMap() {
        super("Testjunglemap.txt", new CommonTileset());
        this.playerStartPosition = new Point(2, 8);

    }

        @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles(){
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        BackToLobby backToLobby = new BackToLobby(getMapTile(4,11).getLocation());
        enhancedMapTiles.add(backToLobby);

        return enhancedMapTiles;
    }
}
