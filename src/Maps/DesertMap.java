package Maps;
import EnhancedMapTiles.PowerUp;
import EnhancedMapTiles.QuicksandTile;
import EnhancedMapTiles.QuicksandTopTile;
import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.FallingPlatform;
import EnhancedMapTiles.HorizontalMovingPlatform;
import EnhancedMapTiles.VerticalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import Tilesets.DesertTileset;
import Utils.Direction;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class DesertMap extends Map {


    public DesertMap() {
        super("desert_map.txt", new DesertTileset());
        this.playerStartPosition = getMapTile(2, 21).getLocation();
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        DinosaurEnemy dinosaurEnemy1 = new DinosaurEnemy(getMapTile(114, 19).getLocation().addY(2), getMapTile(129, 19).getLocation().addY(2), Direction.RIGHT);
        enemies.add(dinosaurEnemy1);

        DinosaurEnemy dinosaurEnemy2 = new DinosaurEnemy(getMapTile(162, 6).getLocation().addY(2), getMapTile(164, 6).getLocation().addY(2), Direction.LEFT);
        enemies.add(dinosaurEnemy2);

        DinosaurEnemy dinosaurEnemy3 = new DinosaurEnemy(getMapTile(162, 3).getLocation().addY(2), getMapTile(164, 3).getLocation().addY(2), Direction.RIGHT);
        enemies.add(dinosaurEnemy3);

        DinosaurEnemy dinosaurEnemy4 = new DinosaurEnemy(getMapTile(162, 19).getLocation().addY(2), getMapTile(173, 19).getLocation().addY(2), Direction.LEFT);
        enemies.add(dinosaurEnemy4);

         BugEnemy bugEnemy = new BugEnemy(getMapTile(33, 19).getLocation().subtractY(25), Direction.LEFT);
        enemies.add(bugEnemy);

        BugEnemy bugEnemy2 = new BugEnemy(getMapTile(69, 15).getLocation().subtractY(25), Direction.RIGHT);
        enemies.add(bugEnemy2);

        BugEnemy bugEnemy3 = new BugEnemy(getMapTile(84, 15).getLocation().subtractY(25), Direction.LEFT);
        enemies.add(bugEnemy3);

        BugEnemy bugEnemy4 = new BugEnemy(getMapTile(99, 19).getLocation().subtractY(25), Direction.RIGHT);
        enemies.add(bugEnemy4);

        BugEnemy bugEnemy5 = new BugEnemy(getMapTile(106, 18).getLocation().subtractY(25), Direction.RIGHT);
        enemies.add(bugEnemy5);

        BugEnemy bugEnemy6 = new BugEnemy(getMapTile(140, 10).getLocation().subtractY(25), Direction.RIGHT);
        enemies.add(bugEnemy6);

        BugEnemy bugEnemy7 = new BugEnemy(getMapTile(155, 10).getLocation().subtractY(25), Direction.LEFT);
        enemies.add(bugEnemy7);

        BugEnemy bugEnemy8 = new BugEnemy(getMapTile(182, 19).getLocation().subtractY(25), Direction.RIGHT);
        enemies.add(bugEnemy8);

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        PowerUp iceBall = new PowerUp(getMapTile(10,16).getLocation(),"Ice Ball","ICEPOWER.png");
        PowerUp doubleJump = new PowerUp(getMapTile(45,16).getLocation(),"Double Jump","doubleJump.png");

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

        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(197, 18).getLocation());
        enhancedMapTiles.add(endLevelBox);
        enhancedMapTiles.add(doubleJump);
        enhancedMapTiles.add(iceBall);

        return enhancedMapTiles;
        
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        return npcs;
    }
}
