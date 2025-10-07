package Level;

import Engine.ImageLoader;
import GameObject.SpriteSheet;

public class PlayerHealth extends Player{
    private static float healthX;
    private static float healthY;
    private static int healthW;
    private static int healthH;
    public PlayerHealth (float x, float y){
       super(new SpriteSheet(ImageLoader.load(".png"), 0, 0), x, y-100, "STAND_RIGHT");
       
    }
}
