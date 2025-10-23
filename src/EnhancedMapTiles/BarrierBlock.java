package EnhancedMapTiles;

import java.util.HashMap;

import Builders.FrameBuilder;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.MapTile;
import Engine.*;

import Players.Cat;
import Level.Player;

import Level.TileType;
import Utils.Direction;
//import SpriteFont.SpriteFont;
import Utils.Point;


public class BarrierBlock extends EnhancedMapTile{

    public BarrierBlock(Point location, String imageName) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load(imageName), 16, 16), TileType.NOT_PASSABLE);
        this.isAnimated();
    } 

    @Override
    public void update(Player player) {
        if(player.touching(this)){
            //System.out.println("Cannot pass!");    
        }
        
        super.update();

    }



    
    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 0)
                        .withScale(3)
                        .withBounds(1, 1, 14, 14)
                        .build(),
                /* 
                new FrameBuilder(spriteSheet.getSprite(0, 1), 65)
                        .withScale(3)
                        .withBounds(1, 1, 14, 14)
                        .build()
                */
            });
        }};
    }
        







}