package Enemies;

import java.awt.Color;
import Engine.GraphicsHandler;
import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntity;
import Level.MapEntityStatus;
import Level.Player;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;
import java.util.HashMap;
import java.awt.image.BufferedImage;


// This class is for the black bug enemy
// enemy behaves like a Mario goomba -- walks forward until it hits a solid map tile, and then turns around
// if it ends up in the air from walking off a cliff, it will fall down until it hits the ground again, and then will continue walking
public class ArticFox extends Enemy {
      private float gravity = 10f;
    private float movementSpeed = 1f;
    private Direction startFacingDirection;
    private Direction facingDirection;
    private AirGroundState airGroundState;
    private boolean hurt = false;
    private int health = 50;
    private int jumpStartTime = 0;
    private int jumpTime = 0;
    private double jumpRandom = Math.random() * (300) + 1;

    public ArticFox(Point location, Direction facingDirection) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("ArticFox.png"), 200, 100), "WALK_LEFT");
        this.startFacingDirection = facingDirection;
        this.initialize();
    }

    public boolean isHurt() {
        return hurt;
    }

    public void draw(GraphicsHandler graphicsHandler) {
        if (isDead()) {
            if (health >= 0) {
                health = health - damageValue();
                hurt = true;
                // System.out.println(health);
                this.live();
            } else {
                this.mapEntityStatus = MapEntityStatus.REMOVED;
            }
        }
        super.draw(graphicsHandler);
        // drawBounds(graphicsHandler, new Color(255, 0, 0, 170));
    }

    @Override
    public void initialize() {
        super.initialize();
        facingDirection = startFacingDirection;
        if (facingDirection == Direction.RIGHT) {
            currentAnimationName = "WALK_RIGHT";
        } else if (facingDirection == Direction.LEFT) {
            currentAnimationName = "WALK_LEFT";
        }
        airGroundState = AirGroundState.GROUND;
        
    }

    @Override
    public void update(Player player) {

        if (health <= 0) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
            super.update();
            return;
        }

        float moveAmountX = 0;
        float moveAmountY = 0;

        // add gravity (if in air, this will cause bug to fall)
        moveAmountY += gravity;

        if (jumpStartTime <= jumpRandom) {
            jumpStartTime++;
        } else {
            jumpRandom = Math.random() * (300) + 1;
            jumpStartTime = 0;
            gravity = -10f;
        }

        // if on ground, walk forward based on facing direction
        if (airGroundState == AirGroundState.GROUND) {
            if (facingDirection == Direction.RIGHT) {
                moveAmountX += movementSpeed;
            } else {
                moveAmountX -= movementSpeed;
            }
        }

        if (player.getX() >= getX()) {

            if(this.getIceBallHitStatus() == true){
                 if (facingDirection == Direction.RIGHT) {
                    currentAnimationName = "FROZEN_WALK_RIGHT";
                 }else{
                    currentAnimationName = "FROZEN_WALK_LEFT";
                 }

            }
    
            if (isHurt()) {
                if (facingDirection == Direction.RIGHT) {
                    currentAnimationName = "HURT_WALK_RIGHT";
                } else {
                    currentAnimationName = "HURT_WALK_LEFT";
                }
                gravity = -10f;
                // System.out.println("right");
                hurt = false;
            } else if (gravity == -10f && currentAnimationName != "HURT_WALK_RIGHT" || gravity == -10f && currentAnimationName != "HURT_WALK_LEFT") {
                if (facingDirection == Direction.RIGHT) {
                    currentAnimationName = "JUMP_RIGHT";
                } else {
                    currentAnimationName = "JUMP_LEFT";
                }
            } else if (gravity == 10f) {
                if (facingDirection == Direction.RIGHT) {
                    currentAnimationName = "WALK_RIGHT";
                } else if (facingDirection == Direction.LEFT) {
                    currentAnimationName = "WALK_LEFT";
            }
        }
            // System.out.println("right");
        }

        if (player.getX() <= getX()) {
            if(this.getIceBallHitStatus() == true){
                 if (facingDirection == Direction.RIGHT) {
                    currentAnimationName = "FROZEN_WALK_RIGHT";
                 }else{
                    currentAnimationName = "FROZEN_WALK_LEFT";
                 }

            }
            if (isHurt()) {
                if (facingDirection == Direction.RIGHT) {
                    currentAnimationName = "HURT_WALK_RIGHT";
                } else {
                    currentAnimationName = "HURT_WALK_LEFT";
                }
                gravity = -10f;
                // System.out.println("left");
                hurt = false;
            } else if (gravity == -10f && currentAnimationName != "HURT_WALK_RIGHT" || gravity == -10f && currentAnimationName != "HURT_WALK_LEFT") {
                if (facingDirection == Direction.RIGHT) {
                    currentAnimationName = "JUMP_RIGHT";
                } else {
                    currentAnimationName = "JUMP_LEFT";
                }
            } else if (gravity == 10f) {
                if (facingDirection == Direction.RIGHT) {
                    currentAnimationName = "WALK_RIGHT";
                } else if (facingDirection == Direction.LEFT) {
                    currentAnimationName = "WALK_LEFT";
                }
            }
            // System.out.println("left");
        }

        // move bug

        
        
        moveYHandleCollision(moveAmountY);
        moveXHandleCollision(moveAmountX);

        if (gravity != 10f) {
            jumpTime++;
        }

        if (jumpTime == 20) {
            jumpTime = 0;
            gravity = 10f;
        }


        super.update(player);
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction,  MapEntity entityCollidedWith) {
        // if bug has collided into something while walking forward,
        // it turns around (changes facing direction)
        if (hasCollided) {
            if (direction == Direction.RIGHT) {
                facingDirection = Direction.LEFT;
                currentAnimationName = "WALK_LEFT";
                movementSpeed = 1f;
            } else {
                facingDirection = Direction.RIGHT;
                currentAnimationName = "WALK_RIGHT";
                movementSpeed = 1f;
            }
        }
    }

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if bug is colliding with the ground, change its air ground state to GROUND
        // if it is not colliding with the ground, it means that it's currently in the air, so its air ground state is changed to AIR
        if (direction == Direction.DOWN) {
            if (hasCollided) {
                airGroundState = AirGroundState.GROUND;
            } else {
                airGroundState = AirGroundState.AIR;
            }
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                            .withScale(0.5)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 8)
                            .withScale(0.5)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                            .withScale(0.5)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 2), 8)
                            .withScale(0.5)
                            .withBounds(2, 4, 200, 95)
                            .build()
            });

            put("WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 2), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 200, 95)
                            .build()
            });

            put("JUMP_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 2), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 200, 95)
                            .build()
            });

            put("JUMP_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 8)
                            .withScale(0.5)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 8)
                            .withScale(0.5)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 8)
                            .withScale(0.5)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 2), 8)
                            .withScale(0.5)
                            .withBounds(2, 4, 200, 95)
                            .build()
            });

            put("HURT_WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 8)
                            .withScale(0.5)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 8)
                            .withScale(0.5)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 8)
                            .withScale(0.5)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 8)
                            .withScale(0.5)
                            .withBounds(2, 4, 200, 95)
                            .build()
            });

            put("HURT_WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 200, 95)
                            .build()
            });
            put("FROZEN_WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 8)
                            .withScale(0.5)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 8)
                            .withScale(0.5)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 8)
                            .withScale(0.5)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 2), 8)
                            .withScale(0.5)
                            .withBounds(2, 4, 200, 95)
                            .build()
            });

            put("FROZEN_WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 2), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 200, 95)
                            .build()
            });
        }};
    }
}