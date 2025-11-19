package Maps;
import EnhancedMapTiles.PowerUp;
import EnhancedMapTiles.QuicksandTile;
import EnhancedMapTiles.QuicksandTopTile;
import Enemies.ArcticFox;
import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.BarrierBlock;
import EnhancedMapTiles.BackToLobby;
import EnhancedMapTiles.CheckPoint;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.FallingPlatform;
import EnhancedMapTiles.HealthPowerUp;
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
         this.playerStartPosition = getMapTile(2, 20).getLocation();
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

         ArcticFox fox1 = new ArcticFox(getMapTile(24, 20).getLocation().subtractY(25), Direction.LEFT);
        enemies.add(fox1);


        ArcticFox fox2 = new ArcticFox(getMapTile(85, 18).getLocation().subtractY(25), Direction.LEFT);
        enemies.add(fox2);

        ArcticFox fox3 = new ArcticFox(getMapTile(137, 14).getLocation().subtractY(25), Direction.LEFT);
        enemies.add(fox3);

        ArcticFox fox4 = new ArcticFox(getMapTile(178, 14).getLocation().subtractY(25), Direction.LEFT);
        enemies.add(fox4);

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        

        PowerUp iceBall = new PowerUp(getMapTile(8,18).getLocation(),"Ice Ball","updatedIce.png");
        enhancedMapTiles.add(iceBall);

        CheckPoint checkpoint1 = new CheckPoint(getMapTile(100,18).getLocation());
        enhancedMapTiles.add(checkpoint1);

        HealthPowerUp healthPack1 = new HealthPowerUp(getMapTile(97,18).getLocation());
        enhancedMapTiles.add(healthPack1);
        CheckPoint checkpoint2 = new CheckPoint(getMapTile(133,14).getLocation());
        enhancedMapTiles.add(checkpoint2);
        


        /* 
        BackToLobby backToLobby = new BackToLobby(getMapTile(196,20).getLocation());
        enhancedMapTiles.add(backToLobby);
        */

        //This is what we use to end the level, ignore BackToLobby thing. that was a test.
        NewEndLevel newEndLevel = new NewEndLevel(getMapTile(178, 14).getLocation());
        enhancedMapTiles.add(newEndLevel);

         int endLevelX = 169;
        int targetY = 17;
        //sets barrierBlocks along y axis 
         for(int i = 0; i < this.getWidth(); i++){
        
            MapTile tile = getMapTile(i, this.getEndBoundY());
            MapTile thisTile = getTileByPosition(i,this.getEndBoundY() - 1);
            MapTile tileBelow = getMapTile(i, this.getEndBoundY() + 4);
            int barrierNum = this.getHeight() - 1;
            if( tile != null && tileBelow.getTileType() == TileType.PASSABLE){
                BarrierBlock barrierHorizontal = new BarrierBlock(tile.getLocation(), "CommonTileset.png");
            // this.addBarrierBlock(barrierY);
                enhancedMapTiles.add(barrierHorizontal);
                //System.out.println("! Tile at ( " + i + ", " + this.getHeight() + " is null");
        }
    }

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
    
