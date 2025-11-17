package Maps;

import java.util.ArrayList;

import Enemies.ArcticFox;
import Enemies.DinosaurEnemy;
import EnhancedMapTiles.BackToLobby;
import EnhancedMapTiles.BarrierBlock;
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
        this.playerStartPosition = new Point(2, 8);

    }

        @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles(){
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        DinosaurEnemy dino1 = new DinosaurEnemy(getMapTile(27, 10).getLocation().addY(2), getMapTile(29, 10).getLocation().addY(2), Direction.LEFT);
        enemies.add(dino1);

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
