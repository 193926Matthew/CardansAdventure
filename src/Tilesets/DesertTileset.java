package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.TileType;
import Level.Tileset;
import Utils.SlopeTileLayoutUtils;

import java.util.ArrayList;

// This class represents a "common" tileset of standard tiles defined in the CommonTileset.png file
public class DesertTileset extends Tileset {

    public DesertTileset() {
        super(ImageLoader.load("DesertTileset2.png"), 48, 48, 1);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        // regular sand block
        Frame sandFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder sandTile = new MapTileBuilder(sandFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(sandTile);

        // sky
        Frame skyFrame = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder skyTile = new MapTileBuilder(skyFrame);

        mapTiles.add(skyTile);

        // left sand
        Frame leftSandFrame = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder leftSandTile = new MapTileBuilder(leftSandFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(leftSandTile);

        // sun
        Frame[] sunFrames = new Frame[]{
                new FrameBuilder(getSubImage(2, 0), 50)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(2, 1), 50)
                        .withScale(tileScale)
                        .build()
        };

        MapTileBuilder sunTile = new MapTileBuilder(sunFrames);

        mapTiles.add(sunTile);

        // fully cracked sandstone
        Frame fullyCrackedSandstoneFrame = new FrameBuilder(getSubImage(2, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder fullyCrackedSandstoneTile = new MapTileBuilder(fullyCrackedSandstoneFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(fullyCrackedSandstoneTile);

        // sandstone branch 
        Frame sandstoneBranchFrame = new FrameBuilder(getSubImage(1, 5))
                .withScale(tileScale)
                .withBounds(0, 6, 48, 4)
                .build();

        MapTileBuilder sandstoneBranchTile = new MapTileBuilder(sandstoneBranchFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(sandstoneBranchTile);

        // sandstone block
        Frame sandstoneBlockFrame = new FrameBuilder(getSubImage(1, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder sandstoneBlockTile = new MapTileBuilder(sandstoneBlockFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(sandstoneBlockTile);

        // right sand 
        Frame rightSandFrame = new FrameBuilder(getSubImage(1, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder rightSandTile = new MapTileBuilder(rightSandFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(rightSandTile);

         // top sand 
        Frame topSandFrame = new FrameBuilder(getSubImage(0, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder topSandTile = new MapTileBuilder(topSandFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(topSandTile);

        // yellow flower
        Frame[] yellowFlowerFrames = new Frame[] {
                new FrameBuilder(getSubImage(1, 2), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(1, 3), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(1, 2), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(1, 4), 65)
                        .withScale(tileScale)
                        .build()
        };

        MapTileBuilder yellowFlowerTile = new MapTileBuilder(yellowFlowerFrames);

        mapTiles.add(yellowFlowerTile);

        // cactus
        Frame[] cactusFrames = new Frame[] {
                new FrameBuilder(getSubImage(0, 3), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(0, 4), 65)
                        .withScale(tileScale)
                        .build()
        };

        MapTileBuilder cactusTile = new MapTileBuilder(cactusFrames);

        mapTiles.add(cactusTile);

        // cracked sandstone branch
        Frame crackedSandstoneBranchFrame = new FrameBuilder(getSubImage(2, 3))
                .withScale(tileScale)
                .withBounds(0, 6, 48, 4)
                .build();

        MapTileBuilder crackedSandstoneBranchTile = new MapTileBuilder(crackedSandstoneBranchFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(crackedSandstoneBranchTile);

        // slightly cracked sandstone block 1
        Frame slightlyCrackedSandstoneBlock1Frame = new FrameBuilder(getSubImage(2, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder slightlyCrackedSandstoneBlock1Tile = new MapTileBuilder(slightlyCrackedSandstoneBlock1Frame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(slightlyCrackedSandstoneBlock1Tile);

        // slightly cracked sandstone block 2
        Frame slightlyCrackedSandstoneBlock2Frame = new FrameBuilder(getSubImage(2, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder slightlyCrackedSandstoneBlock2Tile = new MapTileBuilder(slightlyCrackedSandstoneBlock2Frame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(slightlyCrackedSandstoneBlock2Tile);

        // quick sand top
        Frame[] quickSandTopFrames = new Frame[] {
                new FrameBuilder(getSubImage(3, 0), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(4, 2), 65)
                        .withScale(tileScale)
                        .build(),
        };

        MapTileBuilder quickSandTopTile = new MapTileBuilder(quickSandTopFrames);


        mapTiles.add(quickSandTopTile);

        // qucik sand 
        Frame[] quickSandFrames = new Frame[] {
                new FrameBuilder(getSubImage(3, 1), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(4, 3), 65)
                        .withScale(tileScale)
                        .build(),
        };

        MapTileBuilder quickSandTile = new MapTileBuilder(quickSandFrames);

        quickSandTile.withTileType(TileType.WATER);

        mapTiles.add(quickSandTile);
        

        // sand rock 1
        Frame sandRock1Frame = new FrameBuilder(getSubImage(3, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder sandRock1Tile = new MapTileBuilder(sandRock1Frame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(sandRock1Tile);

        // sand rock 2
        Frame sandRock2Frame = new FrameBuilder(getSubImage(3, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder sandRock2Tile = new MapTileBuilder(sandRock2Frame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(sandRock2Tile);

        // left 45 degree slope
        Frame leftSlopeFrame = new FrameBuilder(getSubImage(3, 3))
                .withScale(tileScale)
                .build();


        MapTileBuilder leftSlopeTile = new MapTileBuilder(leftSlopeFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createLeft45SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(leftSlopeTile);

        // right 45 degree slope
        Frame rightSlopeFrame = new FrameBuilder(getSubImage(3, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder rightSlopeTile = new MapTileBuilder(rightSlopeFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createRight45SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(rightSlopeTile);

        // left 30 degree slope bottom
        Frame leftStairsBottomFrame = new FrameBuilder(getSubImage(4, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder leftStairsBottomTile = new MapTileBuilder(leftStairsBottomFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createBottomLeft30SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(leftStairsBottomTile);

        // left 30 degree slope top
        Frame leftStairsTopFrame = new FrameBuilder(getSubImage(4, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder leftStairsTopTile = new MapTileBuilder(leftStairsTopFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createTopLeft30SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(leftStairsTopTile);

        //spike
        Frame spike = new FrameBuilder(getSubImage(4, 4))
                .withScale(tileScale)
                .withBounds(14, 26, 4, 6)
                .build();
                

        MapTileBuilder spikeTile = new MapTileBuilder(spike)
                .withTileType(TileType.SPIKE);

        mapTiles.add(spikeTile);

        return mapTiles;
    }
}
