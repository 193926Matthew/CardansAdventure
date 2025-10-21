package Maps;
import EnhancedMapTiles.PowerUp;
import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import EnhancedMapTiles.VerticalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import NPCs.Walrus;
import Tilesets.CommonTileset;
import Utils.Direction;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class TestMap extends Map {

    public TestMap() {
        super("test_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(2, 11).getLocation();
        
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        BugEnemy bugEnemy = new BugEnemy(getMapTile(16, 10).getLocation().subtractY(25), Direction.LEFT);
        enemies.add(bugEnemy);

        BugEnemy bugEnemy2 = new BugEnemy(getMapTile(72, 10).getLocation().subtractY(25), Direction.LEFT);
        enemies.add(bugEnemy2);

        BugEnemy bugEnemy3 = new BugEnemy(getMapTile(41, 10).getLocation().subtractY(25), Direction.LEFT);
        enemies.add(bugEnemy3);

        BugEnemy bugEnemy4 = new BugEnemy(getMapTile(42, 10).getLocation().subtractY(25), Direction.RIGHT);
        enemies.add(bugEnemy4);

          BugEnemy bugEnemy5 = new BugEnemy(getMapTile(59, 10).getLocation().subtractY(25), Direction.RIGHT);
        enemies.add(bugEnemy5);

        DinosaurEnemy dinosaurEnemy = new DinosaurEnemy(getMapTile(19, 1).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT);
        enemies.add(dinosaurEnemy);

        DinosaurEnemy dinosaurEnemy2 = new DinosaurEnemy(getMapTile(46, 1).getLocation().addY(2), getMapTile(49, 1).getLocation().addY(2), Direction.LEFT);
        enemies.add(dinosaurEnemy2);

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        HorizontalMovingPlatform hmp = new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getMapTile(24, 6).getLocation(),
                getMapTile(27, 6).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        );
        enhancedMapTiles.add(hmp);
        
        PowerUp doubleJump = new PowerUp(getMapTile(15,9).getLocation(),"Double Jump","doubleJump.png");

        PowerUp iceBall = new PowerUp(getMapTile(10,9).getLocation(),"Ice Ball","ICEPOWER.png");

        //PowerUp tester = new PowerUp(getMapTile(15,9).getLocation(),"tester","Cat.png");

        HorizontalMovingPlatform hmp2 = new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getMapTile(38, 2).getLocation(),
                getMapTile(41, 2).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        );

        
        enhancedMapTiles.add(hmp2);

        HorizontalMovingPlatform hmp3 = new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getMapTile(43, 2).getLocation(),
                getMapTile(46, 2).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        );
        enhancedMapTiles.add(hmp3);
    
        VerticalMovingPlatform vmp1 = new VerticalMovingPlatform(
                ImageLoader.load("OrangePlatform.png"),
                getMapTile(34, 6).getLocation(),
                getMapTile(34, 10).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.DOWN
        );
        enhancedMapTiles.add(vmp1);

        VerticalMovingPlatform vmp2 = new VerticalMovingPlatform(
                ImageLoader.load("OrangePlatform.png"),
                getMapTile(36, 1).getLocation(),
                getMapTile(36, 6).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.DOWN
        );
        enhancedMapTiles.add(vmp2);
      
        
        
        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(78, 10).getLocation());
        enhancedMapTiles.add(endLevelBox);
        enhancedMapTiles.add(doubleJump);
        enhancedMapTiles.add(iceBall);

        return enhancedMapTiles;
        
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Walrus walrus = new Walrus(getMapTile(30, 10).getLocation().subtractY(13));
        npcs.add(walrus);

        return npcs;
    }

    @Override
    public ArrayList<BarrierBlock> loadBarrierBlocks(){
        ArrayList<BarrierBlock> barrierBlocks = new ArrayList<>();
        /* 
        for(int i = 0; i < this.getWidth(); i++){
        
        MapTile tile = getMapTile(i, this.getEndBoundY());
        MapTile thisTile = getTileByPosition(i,this.getEndBoundY() - 1);
        MapTile tileBelow = getMapTile(i, this.getEndBoundY() + 4);
        int barrierNum = this.getHeight() - 1;
        if( tile != null && tileBelow.getTileType() == TileType.PASSABLE){
            BarrierBlock barrierY = new BarrierBlock(tile.getLocation(), "Cat.png");
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
            BarrierBlock barrierX = new BarrierBlock(newTile.getLocation(), "Cat.png");
           // this.addBarrierBlock(barrierY);
            barrierBlocks.add(barrierX);
            enhancedMapTiles.add(barrierX);
            //System.out.println("! Tile at ( " + i + ", " + this.getHeight() + " is null");
        }

        /* 
        for(int k = getWidth(); k >= 0; j--){
            int startingY = this.getEndBoundY();
            MapTile finalTile = getMapTile(k, startingY);
            MapTile finalTileBelow = getMapTile(k + 4, startingY);
             if(finalTile != null && finalTileBelow.getTileType() == TileType.PASSABLE){
                BarrierBlock barrierXY = new BarrierBlock(newTile.getLocation(), "Cat.png");
            // this.addBarrierBlock(barrierY);
                barrierBlocks.add(barrierXY);
                enhancedMapTiles.add(barrierXY);
                //System.out.println("! Tile at ( " + i + ", " + this.getHeight() + " is null");
            }

        */
    final int BARRIER_DEPTH = 4;
    final String BARRIER_IMAGE = "Cat.png";

    int startX = getEndBoundX();
    int endX = getEndBoundX() + getWidth() - 1;
    int startY = getEndBoundY() - getHeight() + 1;
    int endY = getEndBoundY();

    // Horizontal barrier along bottom edge
    for (int x = startX; x <= endX; x++) {
        MapTile tile = getMapTile(x, endY);
        MapTile tileBelow = getMapTile(x, endY + BARRIER_DEPTH);

        if (tile != null && tileBelow != null && tileBelow.getTileType() == TileType.PASSABLE) {
            BarrierBlock barrier = new BarrierBlock(tile.getLocation(), BARRIER_IMAGE);
            barrierBlocks.add(barrier);
            enhancedMapTiles.add(barrier);
        }
    }

    




    
       
    

    return barrierBlocks;
    }
        

        
    

}
