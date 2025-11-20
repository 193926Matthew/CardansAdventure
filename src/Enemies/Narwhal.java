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
import Maps.SnowBossMap;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;
import java.util.HashMap;
import java.awt.image.BufferedImage;


// This class is for the black bug enemy
// enemy behaves like a Mario goomba -- walks forward until it hits a solid map tile, and then turns around
// if it ends up in the air from walking off a cliff, it will fall down until it hits the ground again, and then will continue walking
public class Narwhal extends Enemy {
      private float gravity = 10f;
    private float movementSpeed = 1f;
    private float iceSpeed = 1f;
    private Direction startFacingDirection;
    private Direction facingDirection;
    private AirGroundState airGroundState;
    private boolean hurt = false;
    private int health = 250;
    private int jumpStartTime = 0;
    private int jumpTime = 0;
    private double jumpRandom = Math.random() * (300) + 1;
    private boolean move = true; // true = left
    private boolean jump = false;
    private boolean stuck = false;
    private int moveInt = 0;
    private int jumpInt = 0;
    private int stuckInt = 0;
    private int stuckTimer = 0;
    private IceCrash iceCrash;
    private SnowBossMap arena;

    public Narwhal(Point location, Direction facingDirection, SnowBossMap arena) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Narwhal.png"), 200, 200), "WALK_LEFT");
        this.arena = arena;
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
    public int getHealth(){
        return this.health;
    }
    @Override
    public void initialize() {
        super.initialize();
        facingDirection = startFacingDirection;
        currentAnimationName = "WALK_RIGHT";
        airGroundState = AirGroundState.GROUND;
        
    }

    @Override
    public void update(Player player) {

        if (health <= 0) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
            if (arena != null) {
                arena.spawnFriend();
            }            
            super.update();
            return;
        }

        // System.out.println(health);

        float moveAmountX = 0;
        float moveAmountY = 0;

        // add gravity (if in air, this will cause bug to fall)
        moveAmountY += gravity;

        if (jumpStartTime <= jumpRandom) {
            jumpStartTime++;
        } else {
            jumpRandom = Math.random() * (300) + 1;
            jumpStartTime = 0;
        }

        // if on ground, walk forward based on facing direction
        if (airGroundState == AirGroundState.GROUND) {
            if (facingDirection == Direction.RIGHT) {
                moveAmountX += movementSpeed * 5;
            } else {
                moveAmountX -= movementSpeed * 5;
            }
        }

        if (player.getX() >= getX()) {

            if(this.getIceBallHitStatus() == true){
                 if (move) {
                    currentAnimationName = "FROZEN_WALK_RIGHT";
                 }else{
                    currentAnimationName = "FROZEN_WALK_LEFT";
                 }
               
            }
    
            if (isHurt()) {
                if (move && getY() > 950) {
                    currentAnimationName = "HURT_WALK_RIGHT";
                } else if (!move && getY() > 950) {
                    currentAnimationName = "HURT_WALK_LEFT";
                }
                if (jump) {
                    currentAnimationName = "HURT_JUMP";
                }
                // System.out.println("right");
                hurt = false;
            }
            
        }
            // System.out.println("right");

        if (player.getX() <= getX()) {
            if(this.getIceBallHitStatus() == true){
                 if (move) {
                    currentAnimationName = "FROZEN_WALK_RIGHT";
                 }else{
                    currentAnimationName = "FROZEN_WALK_LEFT";
                 }
                
            }
            if (isHurt()) {
                if (move && getY() > 950) {
                    currentAnimationName = "HURT_WALK_RIGHT";
                } else if (!move && getY() > 950) {
                    currentAnimationName = "HURT_WALK_LEFT";
                }
                if (jump) {
                    currentAnimationName = "HURT_JUMP";
                }
                hurt = false;
            }
            // System.out.println("left");
        }

        // System.out.println(stuckInt);
        if (stuckInt == 3) {
            currentAnimationName = "STUCK";
            moveAmountY = 0;
            stuckTimer++;

            player.addInvincible();

            if (stuckTimer == 200) {
                stuckInt = 0;
                stuckTimer = 0;
                player.removeInvincible();
            }
        }

        if (jump) {
            moveUp(moveAmountY);
            moveAmountX = 0;
        }

        // System.out.println(getY());

        if (!jump && getY() < 950) {
            moveDown(moveAmountY);
            moveAmountX = 0;
        }

        if (jumpInt == 200) {
            jump = true;
                if (move) {
                    currentAnimationName = "JUMP_LEFT";
                } else {
                    currentAnimationName = "JUMP_RIGHT";
                }
            jumpTime++;

            if (jumpTime < 25) {
                if (jumpTime % 4 == 0) {
            int fireballX;
            if (jumpTime % 3 == 0) {
                fireballX = Math.round(getX() + 90);
                iceSpeed = -1.5f;
            } else {
                fireballX = Math.round(getX() + 260);
                iceSpeed = 1.5f;
            }

            // define where fireball will spawn on the map (y location) relative to dinosaur enemy's location
            int fireballY = Math.round(getY()) + 70;

            if (jumpTime % 3 == 0) {
                iceCrash = new IceCrash(new Point(fireballX, fireballY), iceSpeed, 400, "LEFT");
            } else {
                iceCrash = new IceCrash(new Point(fireballX, fireballY), iceSpeed, 400, "RIGHT");
            }

            map.addEnemy(iceCrash);
        }
        }

            if (jumpTime == 25) {
                jump = false;
                jumpInt = 0;
                jumpTime = 0;
                stuckInt++;
            }
        } else {
            jumpInt++;
        }

        if (moveAmountX != 0) {
            if (moveInt == 140) {
                moveInt = 0;
                if (move) {
                    move = false;
                    currentAnimationName = "WALK_LEFT";
                } else {
                    move = true;
                    currentAnimationName = "WALK_RIGHT";
                }
            } else {
                moveInt++;
            }
        }

        if (move) {
            moveLeft(moveAmountX);
        } else {
            moveRight(moveAmountX);
        }

        super.update(player);
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction,  MapEntity entityCollidedWith) {
        // if bug has collided into something while walking forward,
        // it turns around (changes facing direction)
            if (direction == Direction.RIGHT) {
                currentAnimationName = "WALK_RIGHT";
                movementSpeed = 1f;
            } else {
                currentAnimationName = "WALK_LEFT";
                movementSpeed = 1f;
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
                            .withScale(2)
                            .withBounds(2, 100, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 8)
                            .withScale(2)
                            .withBounds(2, 100, 200, 95)
                            .build(),
            });

            put("WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 8)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 100, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 8)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 100, 200, 95)
                            .build(),
            });

            put("JUMP_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 8)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(92, 2, 15, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 2), 8)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(92, 2, 15, 95)
                            .build(),
            });

            put("JUMP_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 8)
                            .withScale(2)
                            .withBounds(92, 2, 15, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 2), 8)
                            .withScale(2)
                            .withBounds(92, 2, 15, 95)
                            .build()
            });

            put("HURT_JUMP", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 3))
                            .withScale(2)
                            .withBounds(90, 2, 20, 95)
                            .build()
            });

            put("HURT_WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 3))
                            .withScale(2)
                            .withBounds(2, 100, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 3))
                            .withScale(2)
                            .withBounds(2, 100, 200, 95)
                            .build()
            });

            put("HURT_WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 3), 8)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 100, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 3), 8)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 100, 200, 95)
                            .build()
            });
            put("FROZEN_WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 8)
                            .withScale(2)
                            .withBounds(2, 100, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 8)
                            .withScale(2)
                            .withBounds(2, 100, 200, 95)
                            .build(),
            });

            put("STUCK", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(3, 1), 8)
                            .withScale(2)
                            .withBounds(68, 2, 65, 160)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 2), 8)
                            .withScale(2)
                            .withBounds(68, 2, 65, 160)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 1), 8)
                            .withScale(2)
                            .withBounds(68, 2, 65, 160)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 2), 8)
                            .withScale(2)
                            .withBounds(68, 2, 65, 160)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 1), 8)
                            .withScale(2)
                            .withBounds(68, 2, 65, 160)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 2), 8)
                            .withScale(2)
                            .withBounds(68, 2, 65, 160)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 1), 8)
                            .withScale(2)
                            .withBounds(68, 2, 65, 160)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 2), 8)
                            .withScale(2)
                            .withBounds(68, 2, 65, 160)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 1), 8)
                            .withScale(2)
                            .withBounds(68, 2, 65, 160)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 2), 8)
                            .withScale(2)
                            .withBounds(68, 2, 65, 160)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 1), 8)
                            .withScale(2)
                            .withBounds(68, 2, 65, 160)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 2), 8)
                            .withScale(2)
                            .withBounds(68, 2, 65, 160)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 1), 8)
                            .withScale(2)
                            .withBounds(68, 2, 65, 160)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 2), 8)
                            .withScale(2)
                            .withBounds(68, 2, 65, 160)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 1), 8)
                            .withScale(2)
                            .withBounds(68, 2, 65, 160)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 2))
                            .withScale(2)
                            .withBounds(68, 2, 65, 160)
                            .build()
            });

            put("FROZEN_WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 8)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 100, 200, 95)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 8)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 100, 200, 95)
                            .build(),
            });
        }};
    }
}