package Enemies;

import java.awt.Color;
import Engine.GraphicsHandler;
import Builders.FrameBuilder;
import Enemies.DinosaurEnemy.DinosaurState;
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
public class PoisonFrog extends Enemy {
      private float gravity = 10f;
    private float movementSpeed = 1f;
    private Direction startFacingDirection;
    private Direction facingDirection;
    private AirGroundState airGroundState;
    private Fireball fireball;
    private boolean hurt = false;
    private int health = 200;
    private int jumpStartTime = 0;
    private int jumpTime = 0;
    private double jumpRandom = Math.random() * (500) + 1;
    protected int shootWaitTimer;
    protected int shootTimer;
    protected PoisonFrogState poisonFrogState;
    protected PoisonFrogState previousPoisonFrogState;


    public PoisonFrog(Point location, Direction facingDirection) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("PoisonFrog.png"), 100, 100), "WALK_LEFT");
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
        poisonFrogState = PoisonFrogState.WALK;
        previousPoisonFrogState = poisonFrogState;
        facingDirection = startFacingDirection;
        if (facingDirection == Direction.RIGHT) {
            currentAnimationName = "WALK_RIGHT";
        } else if (facingDirection == Direction.LEFT) {
            currentAnimationName = "WALK_LEFT";
        }
        airGroundState = AirGroundState.GROUND;
        
        shootWaitTimer = 65;
    }

    @Override
    public void update(Player player) {

        if (health <= 0) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
            super.update();
            return;
        }

        if (shootWaitTimer == 0 && poisonFrogState != PoisonFrogState.SHOOT_WAIT && gravity > 0) {
            poisonFrogState = PoisonFrogState.SHOOT_WAIT;
        }
        else {
            shootWaitTimer--;
        }


        if (poisonFrogState == PoisonFrogState.SHOOT_WAIT) {
            if (previousPoisonFrogState == PoisonFrogState.WALK) {
                shootTimer = 65;
                currentAnimationName = facingDirection == Direction.RIGHT ? "SHOOT_RIGHT" : "SHOOT_LEFT";
            } else if (shootTimer == 0) {
                if(this.getIceBallHitStatus()){
                    // frozenFireball = true;
                    poisonFrogState = PoisonFrogState.SHOOT;
                }else{
                    // frozenFireball = false;
                    poisonFrogState = PoisonFrogState.SHOOT;
                }
                
            }
            else {
                shootTimer--;
            }
        }

        if (poisonFrogState == PoisonFrogState.SHOOT) {
            // define where fireball will spawn on map (x location) relative to dinosaur enemy's location
            // and define its movement speed
            int fireballX;
            float movementSpeed;
            if (facingDirection == Direction.RIGHT) {
                fireballX = Math.round(getX()) + getWidth();
                movementSpeed = 1.5f;
            } else {
                fireballX = Math.round(getX() - 21);
                movementSpeed = -1.5f;
            }

            // define where fireball will spawn on the map (y location) relative to dinosaur enemy's location
            int fireballY = Math.round(getY()) + 14;

            // create Fireball enemy
            if (facingDirection == Direction.LEFT) {
                fireball = new Fireball(new Point(fireballX, fireballY), movementSpeed, 400, "LEFT");
            } else {
                fireball = new Fireball(new Point(fireballX, fireballY), movementSpeed, 400, "RIGHT");
            }

            //sets the fireball to be frozen if the enemy it comes from is frozen
            // fireball.setIceBallHitStatus(frozenFireball);

            // add fireball enemy to the map for it to spawn in the level
            map.addEnemy(fireball);

            // change dinosaur back to its WALK state after shooting, reset shootTimer to wait a certain number of frames before shooting again
            poisonFrogState = PoisonFrogState.WALK;

            // reset shoot wait timer so the process can happen again (dino walks around, then waits, then shoots)
            shootWaitTimer = 130;
        }

        if (poisonFrogState == PoisonFrogState.WALK) {
            if (gravity < 0) {
                currentAnimationName = facingDirection == Direction.RIGHT ? "JUMP_RIGHT" : "JUMP_LEFT";
            } else if (airGroundState == AirGroundState.GROUND) {
                currentAnimationName = facingDirection == Direction.RIGHT ? "WALK_RIGHT" : "WALK_LEFT";
            } else {
                currentAnimationName = facingDirection == Direction.RIGHT ? "FALL_RIGHT" : "FALL_LEFT";
            }
        }

        super.update(player);

        previousPoisonFrogState = poisonFrogState;

        float moveAmountX = 0;
        float moveAmountY = 0;

        // add gravity (if in air, this will cause bug to fall)
        moveAmountY += gravity;

        if (jumpStartTime <= jumpRandom) {
            jumpStartTime++;
        } else {
            jumpRandom = Math.random() * (500) + 1;
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

        if (airGroundState == AirGroundState.AIR) {
            if (facingDirection == Direction.RIGHT) {
                moveAmountX += movementSpeed + 5;
            } else {
                moveAmountX -= movementSpeed + 5;
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
                // System.out.println("right");
                hurt = false;
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
                // System.out.println("left");
                hurt = false;
            }
            // System.out.println("left");
        }

        // move bug

        
        
        moveYHandleCollision(moveAmountY);
        moveXHandleCollision(moveAmountX);

        if (gravity != 10f) {
            jumpTime++;
        }

        if (jumpTime == 15) {
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
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 3), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 3), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 20, 88, 80)
                            .build()
            });

            put("WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 8)
                            .withScale(0.5)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 3), 8)
                            .withScale(0.5)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 8)
                            .withScale(0.5)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 3), 8)
                            .withScale(0.5)
                            .withBounds(2, 20, 88, 80)
                            .build()
            });

            put("JUMP_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 1))
                            .withScale(0.5)
                            .withBounds(2, 20, 88, 80)
                            .build()
            });

            put("JUMP_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 1))
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 20, 88, 80)
                            .build(),
            });

            put("FALL_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 3))
                            .withScale(0.5)
                            .withBounds(2, 20, 88, 80)
                            .build()
            });

            put("FALL_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 3))
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 20, 88, 80)
                            .build(),
            });

            put("HURT_WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(4, 1), 8)
                            .withScale(0.5)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(4, 3), 8)
                            .withScale(0.5)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(4, 2), 8)
                            .withScale(0.5)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(4, 3), 8)
                            .withScale(0.5)
                            .withBounds(2, 20, 88, 80)
                            .build()
            });

            put("HURT_WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(4, 1), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(4, 3), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(4, 2), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(4, 3), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 20, 88, 80)
                            .build()
            });
            put("FROZEN_WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 8)
                            .withScale(0.5)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 8)
                            .withScale(0.5)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 8)
                            .withScale(0.5)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 2), 8)
                            .withScale(0.5)
                            .withBounds(2, 20, 88, 80)
                            .build()
            });

            put("FROZEN_WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 2), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 20, 88, 80)
                            .build()
            });

            put("SHOOT_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(3, 1), 50)
                            .withScale(0.5)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 3), 58)
                            .withScale(0.5)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 2), 8)
                            .withScale(0.5)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 3), 8)
                            .withScale(0.5)
                            .withBounds(2, 20, 88, 80)
                            .build()
            });

            put("SHOOT_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(3, 1), 50)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 3), 58)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 2), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 20, 88, 80)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 3), 8)
                            .withScale(0.5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 20, 88, 80)
                            .build()
            });
        }};
    }

    public enum PoisonFrogState {
        SHOOT_WAIT, SHOOT, WALK
    }
}