package EnhancedMapTiles;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Utils.Point;
import java.util.HashMap;
import Builders.FrameBuilder;
import Engine.GraphicsHandler;




public class PowerUp extends EnhancedMapTile{

//keeps track of name of powerup that will be used
    private String powerName;
//tracks whether user has made contact or not
    private Boolean madeContact = false;

//tracks how many times user has made contact, so when touched once will disappear
    private int counter = 0;


    public PowerUp(Point location, String powerName, String imageName){
        super(location.x, location.y, new SpriteSheet(ImageLoader.load(imageName), 15, 15), TileType.PASSABLE);
        this.powerName = powerName;
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (intersects(player)) {
            madeContact = true;
            counter +=1;
        }
        if(madeContact != false && counter <= 1){
            System.out.println("Collected PowerUp: " + powerName);

        }
    }


    //Raster issues previously due to animations, will work now
    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                        .withScale(1)
                        .withBounds(1, 1, 14, 14)
                        .build(),
                /* 
                new FrameBuilder(spriteSheet.getSprite(0, 1), 40)
                        .withScale(1)
                        .withBounds(1, 1, 14, 14)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 40)
                        .withScale(1)
                        .withBounds(1, 1, 14, 14)
                        .build()
                */
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler){
        if(!madeContact){
            super.draw(graphicsHandler);
        }
    }
}
