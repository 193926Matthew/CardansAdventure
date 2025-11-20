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
public class JungleTileset extends Tileset {

    public JungleTileset() {
        super(ImageLoader.load("JungleTileset.png"), 48, 48, 1);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        // top left block
        Frame topLeftFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder topLeftTile = new MapTileBuilder(topLeftFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(topLeftTile);

        //top
        Frame topFrame = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder topTile = new MapTileBuilder(topFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(topTile);

        // top right sand
        Frame topRightFrame = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder topRightTile = new MapTileBuilder(topRightFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(topRightTile);

        // sun
        Frame[] sunFrames = new Frame[]{
                new FrameBuilder(getSubImage(3, 2), 50)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(3, 3), 50)
                        .withScale(tileScale)
                        .build()
        };

        MapTileBuilder sunTile = new MapTileBuilder(sunFrames);

        mapTiles.add(sunTile);

        //rocky block
        Frame rockyBlockFrame = new FrameBuilder(getSubImage(0, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder rockyBlockTile = new MapTileBuilder(rockyBlockFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(rockyBlockTile);

        // depth block
        Frame depthBlockFrame = new FrameBuilder(getSubImage(0, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder depthBlockTile = new MapTileBuilder(depthBlockFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(depthBlockTile);

        //jungle leaves block
        Frame jungleLeavesFrame = new FrameBuilder(getSubImage(0, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder jungleLeavesTile = new MapTileBuilder(jungleLeavesFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(jungleLeavesTile);

        // tiki
        Frame[] tikiFrames = new Frame[] {
                new FrameBuilder(getSubImage(1, 1), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(1, 2), 65)
                        .withScale(tileScale)
                        .build(),
        };

        MapTileBuilder tikiTile = new MapTileBuilder(tikiFrames);

        mapTiles.add(tikiTile);

        // flowers
        Frame[] flowerFrames = new Frame[] {
                new FrameBuilder(getSubImage(2, 0), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(2, 1), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(2, 2), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(2, 1), 65)
                        .withScale(tileScale)
                        .build(),
        };

        MapTileBuilder flowerTile = new MapTileBuilder(flowerFrames);

        mapTiles.add(flowerTile);


        // tree trunk with full hole
        Frame treeTrunkWithFullHoleFrame = new FrameBuilder(getSubImage(1, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder treeTrunkWithFullHoleTile = new MapTileBuilder(treeTrunkWithFullHoleFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(treeTrunkWithFullHoleTile);

        // left end branch
        Frame leftEndBranchFrame = new FrameBuilder(getSubImage(1, 3))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder leftEndBranchTile = new MapTileBuilder(leftEndBranchFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(leftEndBranchTile);

        // right end branch
        Frame rightEndBranchFrame = new FrameBuilder(getSubImage(1, 3))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();

        MapTileBuilder rightEndBranchTile = new MapTileBuilder(rightEndBranchFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(rightEndBranchTile);

         // tree trunk
        Frame treeTrunkFrame = new FrameBuilder(getSubImage(1, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder treeTrunkTile = new MapTileBuilder(treeTrunkFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(treeTrunkTile);

         // middle branch
        Frame middleBranchFrame = new FrameBuilder(getSubImage(2, 3))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder middleBranchTile = new MapTileBuilder(middleBranchFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(middleBranchTile);

        // tree trunk hole top
        Frame treeTrunkHoleTopFrame = new FrameBuilder(getSubImage(2, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder treeTrunkHoleTopTile = new MapTileBuilder(treeTrunkHoleTopFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(treeTrunkHoleTopTile);

        // tree trunk hole bottom
        Frame treeTrunkHoleBottomFrame = new FrameBuilder(getSubImage(2, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder treeTrunkHoleBottomTile = new MapTileBuilder(treeTrunkHoleBottomFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(treeTrunkHoleBottomTile);

        // left 45 degree slope
        Frame leftSlopeFrame = new FrameBuilder(getSubImage(3, 4))
                .withScale(tileScale)
                .build();


        MapTileBuilder leftSlopeTile = new MapTileBuilder(leftSlopeFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createLeft45SlopeLayout(48, (int) 1));

        mapTiles.add(leftSlopeTile);

        // right 45 degree slope
        Frame rightSlopeFrame = new FrameBuilder(getSubImage(3, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder rightSlopeTile = new MapTileBuilder(rightSlopeFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createRight45SlopeLayout(48, (int) 1));

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
        Frame spike = new FrameBuilder(getSubImage(1, 0))
                .withScale(tileScale)
                .withBounds(14, 26, 4, 6)
                .build();

        MapTileBuilder spikeTile = new MapTileBuilder(spike)
                .withTileType(TileType.SPIKE);

        mapTiles.add(spikeTile);

        // sky
        Frame skyFrame = new FrameBuilder(getSubImage(4, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder skyTile = new MapTileBuilder(skyFrame);

        mapTiles.add(skyTile);

        // vines
        Frame vinesButtomFrame = new FrameBuilder(getSubImage(3, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder vinesButtomTile = new MapTileBuilder(vinesButtomFrame)
                .withTileType(TileType.VINES);

        mapTiles.add(vinesButtomTile);

        // vines buttom
        Frame vinesFrame = new FrameBuilder(getSubImage(3, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder vines = new MapTileBuilder(vinesFrame)
                .withTileType(TileType.VINES);

        mapTiles.add(vines);

         //spikedown
        Frame spikeDown = new FrameBuilder(getSubImage(4, 4))
                .withScale(tileScale)
                .withBounds(14, 26, 4, 6)
                .build();

        MapTileBuilder spikeTileDown = new MapTileBuilder(spikeDown)
                .withTileType(TileType.SPIKE);

        mapTiles.add(spikeTileDown);

          //spike right
        Frame spikeRight = new FrameBuilder(getSubImage(4, 3))
                .withScale(tileScale)
                .withBounds(14, 26, 4, 6)
                .build();

        MapTileBuilder spikeTileRight = new MapTileBuilder(spikeRight)
                .withTileType(TileType.SPIKE);

        mapTiles.add(spikeTileRight);

          //spike left
        Frame spikeLeft = new FrameBuilder(getSubImage(4, 5))
                .withScale(tileScale)
                .withBounds(14, 26, 4, 6)
                .build();

        MapTileBuilder spikeTileLeft = new MapTileBuilder(spikeLeft)
                .withTileType(TileType.SPIKE);

        mapTiles.add(spikeTileLeft);

        return mapTiles;
        
    }
}