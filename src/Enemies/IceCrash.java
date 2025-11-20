package Enemies;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
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

import java.awt.Color;
import java.util.HashMap;

// This class is for the fireball enemy that the DinosaurEnemy class shoots out
// it will travel in a straight line (x axis) for a set time before disappearing
// it will disappear early if it collides with a solid map tile
public class IceCrash extends Enemy {
    private float iceSpeed;
    private int existenceFrames;
    private String facing;
    private int upTime = 0;

    public IceCrash(Point location, float iceSpeed, int existenceFrames, String facing) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("IceChunk.png"), 64, 64), "RIGHT");
        this.iceSpeed = iceSpeed;
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

    // public void draw(GraphicsHandler graphicsHandler) {
    //     super.draw(graphicsHandler);
    //     // drawBounds(graphicsHandler, new Color(255, 0, 0, 170));
    // }

    @Override
    public void update(Player player) {
        // if timer is up, set map entity status to REMOVED
        // the camera class will see this next frame and remove it permanently from the map
        if (existenceFrames == 0) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        } else {
            moveXHandleCollision(iceSpeed * 2);

            if (upTime != 30) {
                moveYHandleCollision(-5);
                upTime++;
            } else {
                moveYHandleCollision(5);
            }

            super.update(player);
        }
        existenceFrames--;
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if fireball collides with anything solid on the x axis, it is removed
        if (getY() >= 980) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if fireball collides with anything solid on the x axis, it is removed
        if (getY() >= 980) {
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
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 4)
                            .withScale(0.5)
                            .withBounds(10, 10, 40, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 4)
                            .withScale(0.5)
                            .withBounds(10, 10, 40, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 3), 4)
                            .withScale(0.5)
                            .withBounds(10, 10, 40, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 4)
                            .withScale(0.5)
                            .withBounds(10, 10, 40, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 4)
                            .withScale(0.5)
                            .withBounds(10, 10, 40, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 3), 4)
                            .withScale(0.5)
                            .withBounds(10, 10, 40, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 1), 4)
                            .withScale(0.5)
                            .withBounds(10, 10, 40, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 2), 4)
                            .withScale(0.5)
                            .withBounds(10, 10, 40, 40)
                            .build()
            });
            put("LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 4)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(10, 10, 40, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 4)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(10, 10, 40, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 3), 4)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(10, 10, 40, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 4)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(10, 10, 40, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 4)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(10, 10, 40, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 3), 4)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(10, 10, 40, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 1), 4)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(10, 10, 40, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 2), 4)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(10, 10, 40, 40)
                            .build()
            });
        }};
    }

    
}
