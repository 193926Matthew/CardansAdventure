package Enemies;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;
import Level.Enemy;
import Level.MapEntity;
import Level.MapEntityStatus;
import Level.Player;
import Maps.JungleBossArena;

public class JungleBoss extends Enemy{
    private float gravity = 3f;
    private float movementSpeed = 4f;
    private float forzenSpeed = movementSpeed / 4;
    private Direction startFacingDirection;
    private Direction facingDirection;
    private AirGroundState airGroundState;
    private boolean hurt = false;
    private int health = 50; // orginal 25
    private int HurtTimer = 240;
    
    // jumping
    private boolean isJumping = false;
    private float jumpVelocity = 0;
    private int jumpCooldown = 0; // counts frames until next jump
    private int jumpCoolDownMax = 40;

    // firebll
    private Fireball fireball;
    private int ShootCoolDown = 0;

    // enemy size
    private static final float BOSS_SCALE = 2f;
    private static final int BOUND_X = 20; //2
    private static final int BOUND_Y = 35; //4
    private static final int BOUND_WIDTH = 60; // 90
    private static final int BOUND_HEIGHT = 30; //56

    public JungleBossArena arena;

    


    public JungleBoss(Point location, Direction facingDirection, JungleBossArena arena){
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("ElephantBoss.png"), 100, 64), "WALK_LEFT");
        this.startFacingDirection = facingDirection;
        this.arena = arena;
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
        //drawBounds(graphicsHandler, new Color(255, 0, 0, 170));
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
        //System.out.println(movementSpeed);
        System.out.println("Boss HP: " + health);


        // bosss dies
        if (health <= 0) {
            this.mapEntityStatus = MapEntityStatus.REMOVED; 

            // TELL THE MAP THAT THE BOSS DIED
            if (arena != null) {
                arena.spawnFriend();
            }
            
            super.update();
            return;
        }

        float moveAmountX = 0;
        float moveAmountY = 0;

        // add gravity (if in air, this will cause bug to fall)
        // Gravity always pulls down
        if (airGroundState == AirGroundState.AIR) {
            moveAmountY += gravity;
        }

        // Handle jumping
        if (airGroundState == AirGroundState.GROUND && !isJumping) {
        // increment cooldown until ready
            if (jumpCooldown < jumpCoolDownMax) { // 120 = 2 seconds at 60fps
                jumpCooldown++;
            } else {
            // trigger jump
            isJumping = true;
            airGroundState = AirGroundState.AIR;
            jumpVelocity = -40f; // how strong the jump is
            jumpCooldown = 0;
            }
        }

        if (isJumping) {
            moveAmountY += jumpVelocity;
            jumpVelocity += gravity; // apply gravity to jump velocity
            ShootCoolDown += 1;
            if (ShootCoolDown >= 1.5) { // shoot fireball every second while jumping
                ShootStuff();
                ShootCoolDown = 0;
            }

            // stop jumping when falling back to ground
            if (jumpVelocity > 0) {
                isJumping = false;
            }
        }


        // always move horizontally, even while jumping
        if (facingDirection == Direction.RIGHT) {
            moveAmountX += (airGroundState == AirGroundState.AIR ? movementSpeed * 0.8f : movementSpeed);
        } else {
            moveAmountX -= (airGroundState == AirGroundState.AIR ? movementSpeed * 0.8f : movementSpeed);
        }



        if (player.getX() >= getX()) {
            /* 
            if(this.getIceBallHitStatus() == true){
                 if (facingDirection == Direction.RIGHT) {
                    currentAnimationName = "FROZEN_WALK_RIGHT";
                    movementSpeed = forzenSpeed;
                 }else{
                    currentAnimationName = "FROZEN_WALK_LEFT";
                    movementSpeed = forzenSpeed;
                 }
            }
            */
    
            if (isHurt()) {

                //System.out.println(HurtTimer);
                if (facingDirection == Direction.RIGHT) {
                    currentAnimationName = "HURT_WALK_RIGHT";
                } else {
                    currentAnimationName = "HURT_WALK_LEFT";
                }
                movementSpeed = 10;
                jumpCoolDownMax = 5;

                HurtTimer--;
                if (HurtTimer <= 0){
                    System.out.println("im better now");
                    HurtTimer = 240;
                    jumpCoolDownMax = 40;
                    hurt = false;
                }
            }
            // System.out.println("right");
        }

        if (player.getX() <= getX()) {
            /* 
            if(this.getIceBallHitStatus() == true){
                 if (facingDirection == Direction.RIGHT) {
                    currentAnimationName = "FROZEN_WALK_RIGHT";
                    movementSpeed = forzenSpeed;
                 }else{
                    currentAnimationName = "FROZEN_WALK_LEFT";
                    movementSpeed = forzenSpeed;
                 }
            }
            */
            if (isHurt()) {

                //System.out.println(HurtTimer);
                if (facingDirection == Direction.RIGHT) {
                    currentAnimationName = "HURT_WALK_RIGHT";
                } else {
                    currentAnimationName = "HURT_WALK_LEFT";
                }
                movementSpeed = 10;
                jumpCoolDownMax = 5;

                HurtTimer--;
                if (HurtTimer <= 0){
                    System.out.println("im better now");
                    HurtTimer = 240;
                    jumpCoolDownMax = 40;
                    hurt = false;
                }

                /*
                if (facingDirection == Direction.RIGHT) {
                    currentAnimationName = "HURT_WALK_RIGHT";
                } else {
                    currentAnimationName = "HURT_WALK_LEFT";
                }
                moveAmountX = 15;
                if (movementSpeed < 2) {
                    movementSpeed += movementSpeed + 0.1;
                }
                 */
                // System.out.println("left");
                
            }
            // System.out.println("left");
        }

        // move bug

        
        
        moveYHandleCollision(moveAmountY);
        moveXHandleCollision(moveAmountX);

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
                movementSpeed = 4f;
            } else {
                facingDirection = Direction.RIGHT;
                currentAnimationName = "WALK_RIGHT";
                movementSpeed = 4f;
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

    public void ShootStuff(){
        System.out.println("shooting");
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
        int fireballY = Math.round(getY()) + 100;

        // create Fireball enemy
        if (facingDirection == Direction.LEFT) {
            fireball = new Fireball(new Point(fireballX, fireballY), movementSpeed, 400, "LEFT");
        } else {
            fireball = new Fireball(new Point(fireballX, fireballY), movementSpeed, 400, "RIGHT");
        }

        //sets the fireball to be frozen if the enemy it comes from is frozen
        //fireball.setIceBallHitStatus(frozenFireball);

        // add fireball enemy to the map for it to spawn in the level
        map.addEnemy(fireball);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                            .withScale(BOSS_SCALE)
                            .withBounds(BOUND_X, BOUND_Y, BOUND_WIDTH, BOUND_HEIGHT)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 8)
                            .withScale(BOSS_SCALE)
                            .withBounds(BOUND_X, BOUND_Y, BOUND_WIDTH, BOUND_HEIGHT)
                            .build()
       
            });

            put("WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                            .withScale(BOSS_SCALE)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(BOUND_X, BOUND_Y, BOUND_WIDTH, BOUND_HEIGHT)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 8)
                            .withScale(BOSS_SCALE)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(BOUND_X, BOUND_Y, BOUND_WIDTH, BOUND_HEIGHT)
                            .build()
   
            });

            put("HURT_WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 8)
                            .withScale(BOSS_SCALE)
                            .withBounds(BOUND_X, BOUND_Y, BOUND_WIDTH, BOUND_HEIGHT)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 8)
                            .withScale(BOSS_SCALE)
                            .withBounds(BOUND_X, BOUND_Y, BOUND_WIDTH, BOUND_HEIGHT)
                            .build(),
                    /* 
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 8)
                            .withScale(BOSS_SCALE)
                            .withBounds(BOUND_X, BOUND_Y, BOUND_WIDTH, BOUND_HEIGHT)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 8)
                            .withScale(BOSS_SCALE)
                            .withBounds(BOUND_X, BOUND_Y, BOUND_WIDTH, BOUND_HEIGHT)
                            .build()
                    */
            });

            put("HURT_WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 8)
                            .withScale(BOSS_SCALE)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(BOUND_X, BOUND_Y, BOUND_WIDTH, BOUND_HEIGHT)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 8)
                            .withScale(BOSS_SCALE)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(BOUND_X, BOUND_Y, BOUND_WIDTH, BOUND_HEIGHT)
                            .build(),

                    /* 
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 8)
                            .withScale(BOSS_SCALE)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(BOUND_X, BOUND_Y, BOUND_WIDTH, BOUND_HEIGHT)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 8)
                            .withScale(BOSS_SCALE)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(BOUND_X, BOUND_Y, BOUND_WIDTH, BOUND_HEIGHT)
                            .build()
                    */
                    
            });
        }};
    }
}
