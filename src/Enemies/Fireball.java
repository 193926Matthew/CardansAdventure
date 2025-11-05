package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntity;
import Level.MapEntityStatus;
import Level.Player;
import Utils.Direction;
import Utils.Point;

import java.util.HashMap;

// This class is for the fireball enemy that the DinosaurEnemy class shoots out
// it will travel in a straight line (x axis) for a set time before disappearing
// it will disappear early if it collides with a solid map tile
public class Fireball extends Enemy {
    private float movementSpeed;
    private int existenceFrames;
    private String facing;

    public Fireball(Point location, float movementSpeed, int existenceFrames, String facing) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("SnakeSpit.png"), 24, 24), "RIGHT");
        this.movementSpeed = movementSpeed;
        this.facing = facing;

        if (facing == "LEFT") {
            currentAnimationName = "LEFT";
        } else {
            currentAnimationName = "RIGHT";
        }

        // how long the fireball will exist for before disappearing
        this.existenceFrames = existenceFrames;

        initialize();
    }

    @Override
    public void update(Player player) {
        // if timer is up, set map entity status to REMOVED
        // the camera class will see this next frame and remove it permanently from the map
        if (existenceFrames == 0) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        } else {
            // move fireball forward at different speeds depending on if it gets hit with the iceball
            if(this.getIceBallHitStatus() == true){
                moveXHandleCollision(movementSpeed/2);
            }else{
                moveXHandleCollision(movementSpeed);
            }
            super.update(player);
        }
        existenceFrames--;
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if fireball collides with anything solid on the x axis, it is removed
        if (hasCollided) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    @Override
    public void touchedPlayer(Player player) {
        // if fireball touches player, it disappears
        super.touchedPlayer(player);
        this.mapEntityStatus = MapEntityStatus.REMOVED;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 7)
                            .withScale(1)
                            .withBounds(1, 1, 5, 5)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 7)
                            .withScale(1)
                            .withBounds(1, 1, 5, 5)
                            .build()
            });
            put("LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 7)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(1, 1, 5, 5)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 7)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(1, 1, 5, 5)
                            .build()
            });
        }};
    }

    
}
