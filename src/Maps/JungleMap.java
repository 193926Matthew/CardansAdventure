package Maps;

import java.util.ArrayList;

import Enemies.ArcticFox;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.BackToLobby;
import EnhancedMapTiles.BarrierBlock;
import EnhancedMapTiles.CheckPoint;
import EnhancedMapTiles.HorizontalMovingPlatform;
import EnhancedMapTiles.NewEndLevel;
import EnhancedMapTiles.VerticalMovingPlatform;
import GameObject.Rectangle;
import Level.Enemy;
import Level.EnhancedMapTile;
import Level.Map;
import Level.MapTile;
import Level.TileType;
import Tilesets.JungleTileset;
import Utils.Direction;
import Utils.Point;

public class JungleMap extends Map{

    public JungleMap() {
        super("Testjunglemap.txt", new JungleTileset());
        this.playerStartPosition = new Point(1, 20);

    }

        @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles(){
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        HorizontalMovingPlatform hmp = new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getMapTile(83, 8).getLocation(),
                getMapTile(92, 8).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        );
        enhancedMapTiles.add(hmp);

        VerticalMovingPlatform vmp1 = new VerticalMovingPlatform(
                ImageLoader.load("OrangePlatform.png"),
                getMapTile(95, 4).getLocation(),
                getMapTile(95, 8).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.DOWN
        );
        enhancedMapTiles.add(vmp1);

        VerticalMovingPlatform vmp2 = new VerticalMovingPlatform(
                ImageLoader.load("OrangePlatform.png"),
                getMapTile(142, 11).getLocation(),
                getMapTile(142, 16).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.UP
        );
        enhancedMapTiles.add(vmp2);

         VerticalMovingPlatform vmp3 = new VerticalMovingPlatform(
                ImageLoader.load("OrangePlatform.png"),
                getMapTile(145, 5).getLocation(),
                getMapTile(145, 11).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.UP
        );
        enhancedMapTiles.add(vmp3);

        NewEndLevel newEndLevel = new NewEndLevel(getMapTile(197, 21).getLocation());
        enhancedMapTiles.add(newEndLevel);

        CheckPoint checkpoint1 = new CheckPoint(getMapTile(107,10).getLocation());
        enhancedMapTiles.add(checkpoint1);

        CheckPoint checkpoint2 = new CheckPoint(getMapTile(166,21).getLocation());
        enhancedMapTiles.add(checkpoint2);

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        DinosaurEnemy dino1 = new DinosaurEnemy(getMapTile(27, 10).getLocation().addY(2), getMapTile(29, 10).getLocation().addY(2), Direction.LEFT);
        enemies.add(dino1);

        DinosaurEnemy dino2 = new DinosaurEnemy(getMapTile(167, 2).getLocation().addY(2), getMapTile(169, 2).getLocation().addY(2), Direction.LEFT);
        enemies.add(dino2);

        DinosaurEnemy dino3 = new DinosaurEnemy(getMapTile(167, 5).getLocation().addY(2), getMapTile(169, 5).getLocation().addY(2), Direction.LEFT);
        enemies.add(dino3);

        return enemies;
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
