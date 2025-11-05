package Maps;
import EnhancedMapTiles.PowerUp;
import EnhancedMapTiles.QuicksandTile;
import EnhancedMapTiles.QuicksandTopTile;
import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.BarrierBlock;
import EnhancedMapTiles.BackToLobby;
import EnhancedMapTiles.CheckPoint;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.FallingPlatform;
import EnhancedMapTiles.HorizontalMovingPlatform;
import EnhancedMapTiles.NewEndLevel;
import EnhancedMapTiles.VerticalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import Tilesets.DesertTileset;
import Tilesets.SnowTileset;
import Utils.Direction;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class SnowMap extends Map {


    public SnowMap() {
        super("snow_map.txt", new SnowTileset());
         this.playerStartPosition = getMapTile(2, 2).getLocation();
    }

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

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        return npcs;
    }

    /* 
      @Override
    public ArrayList<BarrierBlock> loadBarrierBlocks(){
        ArrayList<BarrierBlock> barrierBlocks = new ArrayList<>();
        
        for(int i = 0; i < this.getWidth(); i++){
        
        MapTile tile = getMapTile(i, this.getEndBoundY());
        MapTile thisTile = getTileByPosition(i,this.getEndBoundY() - 1);
        MapTile tileBelow = getMapTile(i, this.getEndBoundY() + 4);
        int barrierNum = this.getHeight() - 1;
        if( tile != null && tileBelow.getTileType() == TileType.PASSABLE){
            BarrierBlock barrierY = new BarrierBlock(tile.getLocation(), "CommonTileset.png");
           // this.addBarrierBlock(barrierY);
            barrierBlocks.add(barrierY);
            enhancedMapTiles.add(barrierY);
            //System.out.println("! Tile at ( " + i + ", " + this.getHeight() + " is null");
        }
    }

    

        for(int j = 0; j < this.getHeight(); j++){
        
        MapTile newTile = getMapTile(this.getEndBoundX(), j);
        MapTile newTileBelow = getMapTile(this.getEndBoundX() + 4, j);
        if( newTile != null && newTileBelow.getTileType() == TileType.PASSABLE){
            BarrierBlock barrierX = new BarrierBlock(newTile.getLocation(), "CommonTileset.png");
           // this.addBarrierBlock(barrierY);
            barrierBlocks.add(barrierX);
            enhancedMapTiles.add(barrierX);
            //System.out.println("! Tile at ( " + i + ", " + this.getHeight() + " is null");
        }

        
    }
     return barrierBlocks;
    }
     */
}
    
