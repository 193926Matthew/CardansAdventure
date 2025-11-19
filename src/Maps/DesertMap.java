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
import Utils.Direction;
import EnhancedMapTiles.HealthPowerUp;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class DesertMap extends Map {


    public DesertMap() {
        super("desert_map.txt", new DesertTileset());
        this.playerStartPosition = getMapTile(160, 21).getLocation();
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        DinosaurEnemy dinosaurEnemy1 = new DinosaurEnemy(getMapTile(15, 18).getLocation().addY(2), getMapTile(15, 18).getLocation().addY(2), Direction.RIGHT);
        enemies.add(dinosaurEnemy1);

        DinosaurEnemy dinosaurEnemy3 = new DinosaurEnemy(getMapTile(63, 15).getLocation().addY(2), getMapTile(63, 15).getLocation().addY(2), Direction.RIGHT);
        enemies.add(dinosaurEnemy3);

        DinosaurEnemy dinosaurEnemy4 = new DinosaurEnemy(getMapTile(97, 19).getLocation().addY(2), getMapTile(125, 19).getLocation().addY(2), Direction.LEFT);
        enemies.add(dinosaurEnemy4);
        
        DinosaurEnemy dinosaurEnemy5 = new DinosaurEnemy(getMapTile(161, 22).getLocation().addY(2), getMapTile(161, 22).getLocation().addY(2), Direction.LEFT);
        enemies.add(dinosaurEnemy5);

        BugEnemy bugEnemy2 = new BugEnemy(getMapTile(66, 15).getLocation().subtractY(25), Direction.RIGHT);
        enemies.add(bugEnemy2);

        BugEnemy bugEnemy3 = new BugEnemy(getMapTile(84, 15).getLocation().subtractY(25), Direction.LEFT);
        enemies.add(bugEnemy3);

        BugEnemy bugEnemy4 = new BugEnemy(getMapTile(94, 19).getLocation().subtractY(25), Direction.RIGHT);
        enemies.add(bugEnemy4);

        BugEnemy bugEnemy5 = new BugEnemy(getMapTile(161, 22).getLocation().subtractY(25), Direction.LEFT);
        enemies.add(bugEnemy5);

        BugEnemy bugEnemy6 = new BugEnemy(getMapTile(111, 17).getLocation().subtractY(25), Direction.RIGHT);
        enemies.add(bugEnemy6);

        BugEnemy bugEnemy10 = new BugEnemy(getMapTile(122, 19).getLocation().subtractY(25), Direction.LEFT);
        enemies.add(bugEnemy10);

        BugEnemy bugEnemy7 = new BugEnemy(getMapTile(78, 15).getLocation().subtractY(25), Direction.LEFT);
        enemies.add(bugEnemy7);

        BugEnemy bugEnemy8 = new BugEnemy(getMapTile(103, 18).getLocation().subtractY(25), Direction.RIGHT);
        enemies.add(bugEnemy8);

        BugEnemy bugEnemy9 = new BugEnemy(getMapTile(54, 26).getLocation().subtractY(25), Direction.RIGHT);
        enemies.add(bugEnemy9);

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        PowerUp fireBall = new PowerUp(getMapTile(11,19).getLocation(),"Fire Ball","fireFlower.png");
        PowerUp doubleJump = new PowerUp(getMapTile(15,16).getLocation(),"Double Jump","updatedDoubleJump.png");

       

        HealthPowerUp extraHealth = new HealthPowerUp(getMapTile(126,17).getLocation());
        enhancedMapTiles.add(doubleJump);
        enhancedMapTiles.add(extraHealth);
        enhancedMapTiles.add(fireBall);

     
      


        CheckPoint checkpoint1 = new CheckPoint(getMapTile(57,19).getLocation());
        enhancedMapTiles.add(checkpoint1);
        CheckPoint checkpoint2 = new CheckPoint(getMapTile(135,10).getLocation());
        enhancedMapTiles.add(checkpoint2);
        CheckPoint checkpoint3 = new CheckPoint(getMapTile(31,19).getLocation());
        enhancedMapTiles.add(checkpoint3);
        CheckPoint checkpoint4 = new CheckPoint(getMapTile(121,15).getLocation());
        enhancedMapTiles.add(checkpoint4);

        FallingPlatform fp = new FallingPlatform(
                ImageLoader.load("FallingPlatform.png"),
                getMapTile(35, 17).getLocation(),
                getMapTile(35, 0).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.DOWN
        );
        enhancedMapTiles.add(fp);

        FallingPlatform fp2 = new FallingPlatform(
                ImageLoader.load("FallingPlatform.png"),
                getMapTile(37, 15).getLocation(),
                getMapTile(37, 0).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.DOWN
        );

        enhancedMapTiles.add(fp2);

        FallingPlatform fp3 = new FallingPlatform(
                ImageLoader.load("FallingPlatform.png"),
                getMapTile(52, 24).getLocation(),
                getMapTile(52, 0).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.DOWN
        );

        enhancedMapTiles.add(fp3);


        FallingPlatform fp4 = new FallingPlatform(
                ImageLoader.load("FallingPlatform.png"),
                getMapTile(54, 21).getLocation(),
                getMapTile(54, 0).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.DOWN
        );

        enhancedMapTiles.add(fp4);

        FallingPlatform fp8 = new FallingPlatform(
                ImageLoader.load("FallingPlatform.png"),
                getMapTile(141, 7).getLocation(),
                getMapTile(141, 0).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.DOWN
        );

        enhancedMapTiles.add(fp8);

        FallingPlatform fp7 = new FallingPlatform(
                ImageLoader.load("FallingPlatform.png"),
                getMapTile(145, 4).getLocation(),
                getMapTile(145, 0).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.DOWN
        );

        enhancedMapTiles.add(fp7);


        QuicksandTile qs = new QuicksandTile(getMapTile(12, 21).getLocation());
        QuicksandTile qs2 = new QuicksandTile(getMapTile(12, 22).getLocation());
        QuicksandTile qs3 = new QuicksandTile(getMapTile(12, 23).getLocation());
        QuicksandTile qs4 = new QuicksandTile(getMapTile(12, 24).getLocation());
        QuicksandTile qs5 = new QuicksandTile(getMapTile(12, 25).getLocation());
        QuicksandTile qs6 = new QuicksandTile(getMapTile(12, 26).getLocation());
        QuicksandTile qs7 = new QuicksandTile(getMapTile(12, 27).getLocation());
        QuicksandTile qs8 = new QuicksandTile(getMapTile(12, 28).getLocation());
        QuicksandTile qs9 = new QuicksandTile(getMapTile(12, 29).getLocation());

        QuicksandTile qs10 = new QuicksandTile(getMapTile(13, 21).getLocation());
        QuicksandTile qs11 = new QuicksandTile(getMapTile(13, 22).getLocation());
        QuicksandTile qs12 = new QuicksandTile(getMapTile(13, 23).getLocation());
        QuicksandTile qs13 = new QuicksandTile(getMapTile(13, 24).getLocation());
        QuicksandTile qs14 = new QuicksandTile(getMapTile(13, 25).getLocation());
        QuicksandTile qs15 = new QuicksandTile(getMapTile(13, 26).getLocation());
        QuicksandTile qs16 = new QuicksandTile(getMapTile(13, 27).getLocation());
        QuicksandTile qs17 = new QuicksandTile(getMapTile(13, 28).getLocation());
        QuicksandTile qs18 = new QuicksandTile(getMapTile(13, 29).getLocation());

        QuicksandTopTile qsTop = new QuicksandTopTile(getMapTile(12, 20).getLocation());
        QuicksandTopTile qsTop2 = new QuicksandTopTile(getMapTile(13, 20).getLocation());


        enhancedMapTiles.add(qs);
        enhancedMapTiles.add(qs2);
        enhancedMapTiles.add(qs3);
        enhancedMapTiles.add(qs4);
        enhancedMapTiles.add(qs5);
        enhancedMapTiles.add(qs6);
        enhancedMapTiles.add(qs7);
        enhancedMapTiles.add(qs8);
        enhancedMapTiles.add(qs9);
        enhancedMapTiles.add(qs10);
        enhancedMapTiles.add(qs11);
        enhancedMapTiles.add(qs12);
        enhancedMapTiles.add(qs13);
        enhancedMapTiles.add(qs14);
        enhancedMapTiles.add(qs15);
        enhancedMapTiles.add(qs16);
        enhancedMapTiles.add(qs17);
        enhancedMapTiles.add(qs18);

        enhancedMapTiles.add(qsTop);
        enhancedMapTiles.add(qsTop2);

        QuicksandTile qs19 = new QuicksandTile(getMapTile(43, 21).getLocation());
        QuicksandTile qs20 = new QuicksandTile(getMapTile(43, 22).getLocation());
        QuicksandTile qs21 = new QuicksandTile(getMapTile(43, 23).getLocation());
        QuicksandTile qs22 = new QuicksandTile(getMapTile(43, 24).getLocation());
        QuicksandTile qs23 = new QuicksandTile(getMapTile(43, 25).getLocation());
        QuicksandTile qs24 = new QuicksandTile(getMapTile(43, 26).getLocation());

        
        QuicksandTile qs25 = new QuicksandTile(getMapTile(44, 21).getLocation());
        QuicksandTile qs26 = new QuicksandTile(getMapTile(44, 22).getLocation());
        QuicksandTile qs27 = new QuicksandTile(getMapTile(44, 23).getLocation());
        QuicksandTile qs28 = new QuicksandTile(getMapTile(44, 24).getLocation());
        QuicksandTile qs29 = new QuicksandTile(getMapTile(44, 25).getLocation());
        QuicksandTile qs30 = new QuicksandTile(getMapTile(44, 26).getLocation());
        

        QuicksandTopTile qsTop3 = new QuicksandTopTile(getMapTile(43, 20).getLocation());
        QuicksandTopTile qsTop4 = new QuicksandTopTile(getMapTile(44, 20).getLocation());

        enhancedMapTiles.add(qs19);
        enhancedMapTiles.add(qs20);
        enhancedMapTiles.add(qs21);
        enhancedMapTiles.add(qs22);
        enhancedMapTiles.add(qs23);
        enhancedMapTiles.add(qs24);

        enhancedMapTiles.add(qs25);
        enhancedMapTiles.add(qs26);
        enhancedMapTiles.add(qs27);
        enhancedMapTiles.add(qs28);
        enhancedMapTiles.add(qs29);
        enhancedMapTiles.add(qs30);

        enhancedMapTiles.add(qsTop3);
        enhancedMapTiles.add(qsTop4);

        VerticalMovingPlatform vmp1 = new VerticalMovingPlatform(
                ImageLoader.load("OrangePlatform.png"),
                getMapTile(72, 8).getLocation(),
                getMapTile(72, 12).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.DOWN
        );
        enhancedMapTiles.add(vmp1);

        HorizontalMovingPlatform hmp = new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getMapTile(75, 8).getLocation(),
                getMapTile(80, 8).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        );
        enhancedMapTiles.add(hmp);

        FallingPlatform fp5 = new FallingPlatform(
                ImageLoader.load("FallingPlatform.png"),
                getMapTile(84, 10).getLocation(),
                getMapTile(84, 0).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.DOWN
        );

        enhancedMapTiles.add(fp5);

        FallingPlatform fp6 = new FallingPlatform(
                ImageLoader.load("FallingPlatform.png"),
                getMapTile(109,13).getLocation(),
                getMapTile(109, 0).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.DOWN
        );

        enhancedMapTiles.add(fp6);

        QuicksandTile qs31 = new QuicksandTile(getMapTile(133, 12).getLocation());
        QuicksandTile qs32 = new QuicksandTile(getMapTile(133, 13).getLocation());
        QuicksandTile qs33 = new QuicksandTile(getMapTile(133, 14).getLocation());
        QuicksandTile qs34 = new QuicksandTile(getMapTile(133, 15).getLocation());
        QuicksandTile qs35 = new QuicksandTile(getMapTile(133, 16).getLocation());
        QuicksandTile qs36 = new QuicksandTile(getMapTile(133, 17).getLocation());

        QuicksandTile qs37 = new QuicksandTile(getMapTile(134, 12).getLocation());
        QuicksandTile qs38 = new QuicksandTile(getMapTile(134, 13).getLocation());
        QuicksandTile qs39 = new QuicksandTile(getMapTile(134, 14).getLocation());
        QuicksandTile qs40 = new QuicksandTile(getMapTile(134, 15).getLocation());
        QuicksandTile qs41 = new QuicksandTile(getMapTile(134, 16).getLocation());
        QuicksandTile qs42 = new QuicksandTile(getMapTile(134, 17).getLocation());
        
        QuicksandTopTile qsTop5 = new QuicksandTopTile(getMapTile(133, 11).getLocation());
        QuicksandTopTile qsTop6 = new QuicksandTopTile(getMapTile(134, 11).getLocation());
       
        

        QuicksandTopTile qsTop7 = new QuicksandTopTile(getMapTile(149, 11).getLocation());
        QuicksandTopTile qsTop8 = new QuicksandTopTile(getMapTile(150, 11).getLocation());
        QuicksandTopTile qsTop9 = new QuicksandTopTile(getMapTile(151, 11).getLocation());
        

        QuicksandTile qs43 = new QuicksandTile(getMapTile(149, 12).getLocation());
        QuicksandTile qs44 = new QuicksandTile(getMapTile(149, 13).getLocation());
        QuicksandTile qs45 = new QuicksandTile(getMapTile(149, 14).getLocation());
        QuicksandTile qs46 = new QuicksandTile(getMapTile(149, 14).getLocation());
        QuicksandTile qs47 = new QuicksandTile(getMapTile(149, 15).getLocation());
        QuicksandTile qs48 = new QuicksandTile(getMapTile(149, 16).getLocation());
        QuicksandTile qs49 = new QuicksandTile(getMapTile(149, 17).getLocation());
        QuicksandTile qs50 = new QuicksandTile(getMapTile(149, 18).getLocation());
        QuicksandTile qs51 = new QuicksandTile(getMapTile(149, 19).getLocation());
        QuicksandTile qs52 = new QuicksandTile(getMapTile(149, 20).getLocation());
        QuicksandTile qs53 = new QuicksandTile(getMapTile(149, 21).getLocation());
        
        enhancedMapTiles.add(qs43);
        enhancedMapTiles.add(qs44);
        enhancedMapTiles.add(qs45);
        enhancedMapTiles.add(qs46);
        enhancedMapTiles.add(qs47);
        enhancedMapTiles.add(qs48);
        enhancedMapTiles.add(qs49);
        enhancedMapTiles.add(qs50);
        enhancedMapTiles.add(qs51);
        enhancedMapTiles.add(qs52);
        enhancedMapTiles.add(qs53);
        enhancedMapTiles.add(qsTop7);
        enhancedMapTiles.add(qsTop8);
        enhancedMapTiles.add(qsTop9);

        



        QuicksandTile qs54 = new QuicksandTile(getMapTile(150, 12).getLocation());
        QuicksandTile qs55 = new QuicksandTile(getMapTile(150, 13).getLocation());
        QuicksandTile qs56 = new QuicksandTile(getMapTile(150, 14).getLocation());
        QuicksandTile qs57 = new QuicksandTile(getMapTile(150, 14).getLocation());
        QuicksandTile qs58 = new QuicksandTile(getMapTile(150, 15).getLocation());
        QuicksandTile qs59 = new QuicksandTile(getMapTile(150, 16).getLocation());
        QuicksandTile qs60 = new QuicksandTile(getMapTile(150, 17).getLocation());
        QuicksandTile qs61 = new QuicksandTile(getMapTile(150, 18).getLocation());
        QuicksandTile qs62 = new QuicksandTile(getMapTile(150, 19).getLocation());
        QuicksandTile qs63 = new QuicksandTile(getMapTile(150, 20).getLocation());
        QuicksandTile qs64 = new QuicksandTile(getMapTile(150, 21).getLocation());
        
        enhancedMapTiles.add(qs54);
        enhancedMapTiles.add(qs55);
        enhancedMapTiles.add(qs56);
        enhancedMapTiles.add(qs57);
        enhancedMapTiles.add(qs58);
        enhancedMapTiles.add(qs59);
        enhancedMapTiles.add(qs60);
        enhancedMapTiles.add(qs61);
        enhancedMapTiles.add(qs62);
        enhancedMapTiles.add(qs63);
        enhancedMapTiles.add(qs64);

        QuicksandTile qs65 = new QuicksandTile(getMapTile(151, 12).getLocation());
        QuicksandTile qs66 = new QuicksandTile(getMapTile(151, 13).getLocation());
        QuicksandTile qs67 = new QuicksandTile(getMapTile(151, 14).getLocation());
        QuicksandTile qs68 = new QuicksandTile(getMapTile(151, 14).getLocation());
        QuicksandTile qs69 = new QuicksandTile(getMapTile(151, 15).getLocation());
        QuicksandTile qs70 = new QuicksandTile(getMapTile(151, 16).getLocation());
        QuicksandTile qs71 = new QuicksandTile(getMapTile(151, 17).getLocation());
        QuicksandTile qs72 = new QuicksandTile(getMapTile(151, 18).getLocation());
        QuicksandTile qs73 = new QuicksandTile(getMapTile(151, 19).getLocation());
        QuicksandTile qs74 = new QuicksandTile(getMapTile(151, 20).getLocation());
        QuicksandTile qs75 = new QuicksandTile(getMapTile(151, 21).getLocation());
        
        enhancedMapTiles.add(qs65);
        enhancedMapTiles.add(qs66);
        enhancedMapTiles.add(qs67);
        enhancedMapTiles.add(qs68);
        enhancedMapTiles.add(qs69);
        enhancedMapTiles.add(qs70);
        enhancedMapTiles.add(qs71);
        enhancedMapTiles.add(qs72);
        enhancedMapTiles.add(qs73);
        enhancedMapTiles.add(qs74);
        enhancedMapTiles.add(qs75);
        
        enhancedMapTiles.add(qs31);
        enhancedMapTiles.add(qs32);
        enhancedMapTiles.add(qs33);
        enhancedMapTiles.add(qs34);
        enhancedMapTiles.add(qs35);
        enhancedMapTiles.add(qs36);

        enhancedMapTiles.add(qs37);
        enhancedMapTiles.add(qs38);
        enhancedMapTiles.add(qs39);
        enhancedMapTiles.add(qs40);
        enhancedMapTiles.add(qs41);
        enhancedMapTiles.add(qs42);

        enhancedMapTiles.add(qsTop5);
        enhancedMapTiles.add(qsTop6);

        //EndLevelBox endLevelBox = new EndLevelBox(getMapTile(197, 19).getLocation());
        

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
/* 
         for(int j = 0; j < this.getHeight(); j++){
        
        MapTile newTile = getMapTile(this.getEndBoundX(), j);
        MapTile newTileBelow = getMapTile(this.getEndBoundX() + 4, j);
        if( newTile != null && newTileBelow.getTileType() == TileType.PASSABLE){
            BarrierBlock barrierX = new BarrierBlock(newTile.getLocation(), "CommonTileset.png");
           // this.addBarrierBlock(barrierY);
            enhancedMapTiles.add(barrierX);
            //System.out.println("! Tile at ( " + i + ", " + this.getHeight() + " is null");
        }
    }
*/

       

        //The new end level box OG POINT: 198 19
        NewEndLevel newEndLevel = new NewEndLevel(getMapTile(169, 22).getLocation());
        enhancedMapTiles.add(newEndLevel);

        return enhancedMapTiles;
        
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        return npcs;
    }

    
}
    