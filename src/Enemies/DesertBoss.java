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
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;

import java.awt.Color;
import java.util.HashMap;
import Level.IceBall;
import Level.BossHealthProvider;
import Level.BossFloatingHealthBar;


// This class is for the green dinosaur enemy that shoots fireballs
// It walks back and forth between two set points (startLocation and endLocation)
// Every so often (based on shootTimer) it will shoot a Fireball enemy
public class DesertBoss extends Enemy implements BossHealthProvider {
    // start and end location defines the two points that it walks between
    // is only made to walk along the x axis and has no air ground state logic, so make sure both points have the same Y value
    protected Point startLocation;
    protected Point endLocation;

    private boolean frozenFireball = false;
    // private Fireball fireball; //shouldnt need this with new system
    private int wSnake = 48;
    private int hSnake = 48;
    private int xSnake = 48;
    private int ySnake = 48;
    protected float movementSpeed = 1f;
    private boolean hurt = false;
    private int hurtTimer = 0;


    private Direction startFacingDirection;
    protected Direction facingDirection;
    protected AirGroundState airGroundState;
    private int health = 30;
    private int setHealth = 29;

    // boss health bar
    private BossFloatingHealthBar bossBar;
    private final int maxHealth = 30; // match your current health
    private String bossName = "Desert Serpent"; // call it whatever

    //boss attack pattern index
    // for this basis we use fireball shooting, can change later
    // 0 = single ball, 1 = double ball, 2 = spread attack
    private int attackPattern = 0; 


    // timer is used to determine how long dinosaur freezes in place before shooting fireball
    protected int shootWaitTimer;

    // timer is used to determine when a fireball is to be shot out
    protected int shootTimer;

    // can be either WALK or SHOOT based on what the enemy is currently set to do
    protected DinosaurState dinosaurState;
    protected DinosaurState previousDinosaurState;

    public DesertBoss(Point startLocation, Point endLocation, Direction facingDirection) {
        super(startLocation.x, startLocation.y, new SpriteSheet(ImageLoader.load("Snake.png"), 48, 48), "WALK_RIGHT");
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startFacingDirection = facingDirection;
        this.initialize();
    }

    public boolean isHurt() {
        return hurt;
    }

    @Override
    public void initialize() {
        super.initialize();
        setMovementSpeed(movementSpeed);
        dinosaurState = DinosaurState.WALK;
        previousDinosaurState = dinosaurState;
        facingDirection = startFacingDirection;
        if (facingDirection == Direction.RIGHT) {
            currentAnimationName = "WALK_RIGHT";
        } else if (facingDirection == Direction.LEFT) {
            currentAnimationName = "WALK_LEFT";
        }
        airGroundState = AirGroundState.GROUND;

        //boss health bar
        bossBar = new BossFloatingHealthBar(this); 

        // every certain number of frames, the fireball will be shot out
        shootWaitTimer = 65;
    }


    public void draw(GraphicsHandler graphicsHandler) {
        if (isDead()) {
            if (health >= 0) {
                health = health - damageValue();
                // System.out.println(health);
                this.live();
            } else {
                this.mapEntityStatus = MapEntityStatus.REMOVED;
            }
        }
        
        super.draw(graphicsHandler);
        //drawBounds(graphicsHandler, new Color(255, 0, 0, 170));
        // drawBounds(graphicsHandler, new Color(255, 0, 0, 170));

        // temp background to denote boss:
        graphicsHandler.drawFilledRectangle(Math.round(getX())-2, Math.round(getY())-2,
        getWidth()+4, getHeight()+4, new Color(255, 255, 0, 60));


        //drawn the boss healthbar above the boss sprite
        if (bossBar != null){
            int screenX = Math.round(getX()); 
            int screenY = Math.round(getY()); 
            bossBar.draw(graphicsHandler, screenX, screenY);
        }
    }

    @Override
    public void update(Player player) {
        if(getIceBallHitStatus() == true){
            if(facingDirection == Direction.RIGHT){
                currentAnimationName = "FROZEN_WALK_RIGHT";
            }else{
                currentAnimationName = "FROZEN_WALK_RIGHT";
            }
        }
       
        if (health <= 0) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
            super.update();
            return;
        }
        
        float startBound = startLocation.x;
        float endBound = endLocation.x;

        // if shoot timer is up and dinosaur is not currently shooting, set its state to SHOOT
        if (shootWaitTimer == 0 && dinosaurState != DinosaurState.SHOOT_WAIT) {
            dinosaurState = DinosaurState.SHOOT_WAIT;
        }
        else {
            shootWaitTimer--;
        }

        // if dinosaur is walking, determine which direction to walk in based on facing direction
        if (dinosaurState == DinosaurState.WALK) {
            if (facingDirection == Direction.RIGHT && getIceBallHitStatus() == false) {
                if (health < setHealth) {
                    currentAnimationName = "HURT_WALK_RIGHT";
                    moveXHandleCollision(movementSpeed);
                    hurtTimer++;
                    if (hurtTimer > 20) {
                        setHealth = health;
                        hurtTimer = 0;
                    }
                } else {
                    //System.out.println(movementSpeed);
                    currentAnimationName = "WALK_RIGHT";
                    moveXHandleCollision(movementSpeed);
                }
            } else if(facingDirection == Direction.LEFT && getIceBallHitStatus() == false) {
                if (health < setHealth) {
                    currentAnimationName = "HURT_WALK_LEFT";
                    moveXHandleCollision(-movementSpeed);
                    hurtTimer++;
                    if (hurtTimer > 20) {
                        setHealth = health;
                        hurtTimer = 0;
                    }
                } else {
                    //System.out.println(movementSpeed);
                    currentAnimationName = "WALK_LEFT";
                    moveXHandleCollision(-movementSpeed);
                }
            }else if(facingDirection == Direction.RIGHT && getIceBallHitStatus() == true){
                currentAnimationName = "FROZEN_WALK_RIGHT";
                //movementSpeed -= 0.8;
                moveXHandleCollision(movementSpeed);
            }else{
                //movementSpeed -= 0.8;
                currentAnimationName = "FROZEN_WALK_LEFT";
                moveXHandleCollision(-movementSpeed);
            }

            // if dinosaur reaches the start or end location, it turns around
            // dinosaur may end up going a bit past the start or end location depending on movement speed
            // this calculates the difference and pushes the enemy back a bit so it ends up right on the start or end location
            if (getX1() + getWidth() >= endBound) {
                float difference = endBound - (getX2());
                moveXHandleCollision(-difference);
                facingDirection = Direction.LEFT;
            } else if (getX1() <= startBound) {
                float difference = startBound - getX1();
                moveXHandleCollision(difference);
                facingDirection = Direction.RIGHT;
            }
        }

        // if dinosaur is waiting to shoot, it first turns read for a set number of frames
        // after this waiting period is over, the fireball is actually shot out
        if (dinosaurState == DinosaurState.SHOOT_WAIT) {
            if (previousDinosaurState == DinosaurState.WALK) {
                shootTimer = 65;
                currentAnimationName = facingDirection == Direction.RIGHT ? "SHOOT_RIGHT" : "SHOOT_LEFT";
            } else if (shootTimer == 0) {
                if(this.getIceBallHitStatus()){
                    frozenFireball = true;
                    dinosaurState = DinosaurState.SHOOT;
                }else{
                    frozenFireball = false;
                    dinosaurState = DinosaurState.SHOOT;
                }
                
            }
            else {
                shootTimer--;
            }
        }

        // this is for actually having the dinosaur spit out the fireball
        if (dinosaurState == DinosaurState.SHOOT) {

            // use the pattern helpers (they already spawn and add to map)
            switch (attackPattern) {
                case 0:
                    shootStraight(player);
                    break;
                case 1:
                    shootBurst(player, 2);
                    break;
                case 2:
                    shootSpread(player);
                    break;
            }

            // next time, use the next pattern
            attackPattern = (attackPattern + 1) % 3;

            // go back to walking and reset cooldown
            dinosaurState = DinosaurState.WALK;
            shootWaitTimer = 130;
        }

        super.update(player);

        if(bossBar != null){
            bossBar.update(); 
        }

        previousDinosaurState = dinosaurState;
    }

    private void spawnFireball(Direction dir, float yOffset) {
    int baseX;
    float xSpeed;
    if (dir == Direction.RIGHT) {
        baseX = Math.round(getX()) + getWidth();
        xSpeed = 1.5f;
    } else {
        baseX = Math.round(getX() - 21);
        xSpeed = -1.5f;
    }
    int baseY = Math.round(getY()) + 4 + Math.round(yOffset * 10); // small vertical shift

    Fireball fb;
    if (dir == Direction.LEFT) {
        fb = new Fireball(new Point(baseX, baseY), xSpeed, 400, "LEFT");
    } else {
        fb = new Fireball(new Point(baseX, baseY), xSpeed, 400, "RIGHT");
    }

    fb.setIceBallHitStatus(frozenFireball);
    map.addEnemy(fb);
}


    private void shootStraight(Player player) {
        spawnFireball(facingDirection, 0);
    }

    private void shootBurst(Player player, int count) {
            for (int i = 0; i < count; i++) {
                spawnFireball(facingDirection, 0);
            }
        }

    private void shootSpread(Player player) {
            // center
            spawnFireball(facingDirection, 0);
            // up-ish
            spawnFireball(facingDirection, -0.5f);
            // down-ish
            spawnFireball(facingDirection, 0.5f);
        }


    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if dinosaur enemy collides with something on the x axis, it turns around and walks the other way
        if (hasCollided) {
            if (direction == Direction.RIGHT && getIceBallHitStatus() == false) {
                facingDirection = Direction.LEFT;
                currentAnimationName = "WALK_LEFT";
            } else if(direction == Direction.LEFT && getIceBallHitStatus() == false){
                facingDirection = Direction.RIGHT;
                currentAnimationName = "WALK_RIGHT";
            }else if(direction == Direction.RIGHT && getIceBallHitStatus() == true){
                facingDirection = Direction.LEFT;
                currentAnimationName = "FROZEN_WALK_RIGHT";
            }else{
                facingDirection = Direction.RIGHT;
                currentAnimationName = "FROZEN_WALK_LEFT";
            }
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("WALK_LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 14)
                            .withScale(1)
                            .withBounds(4, 2, 45, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                            .withScale(1)
                            .withBounds(4, 2, 45, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 14)
                            .withScale(1)
                            .withBounds(4, 2, 45, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 2), 14)
                            .withScale(1)
                            .withBounds(4, 2, 45, 40)
                            .build()
            });

            put("WALK_RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 45, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 45, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 45, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 2), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 45, 40)
                            .build()
            });   
                        
            put("HURT_WALK_LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 5), 14)
                            .withScale(1)
                            .withBounds(4, 2, 45, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 4), 14)
                            .withScale(1)
                            .withBounds(4, 2, 45, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 5), 14)
                            .withScale(1)
                            .withBounds(4, 2, 45, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 6), 14)
                            .withScale(1)
                            .withBounds(4, 2, 45, 40)
                            .build()
            });

            put("HURT_WALK_RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 5), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 45, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 4), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 45, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 5), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 45, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 6), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 45, 40)
                            .build()
            });
            

             put("FROZEN_WALK_LEFT", new Frame[]{
                     new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                            .withScale(1)
                            .withBounds(4, 2, 45, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                            .withScale(1)
                            .withBounds(4, 2, 45, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                            .withScale(1)
                            .withBounds(4, 2, 45, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                            .withScale(1)
                            .withBounds(4, 2, 45, 40)
                            .build()
            });

            put("FROZEN_WALK_RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 45, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 45, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 45, 40)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 45, 40)
                            .build()
            });

            put("SHOOT_LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 2))
                            .withScale(1)
                            .withBounds(4, 2, 45, 40)
                            .build(),
            });

             put("SHOOT_RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 2))
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 45, 40)
                            .build(),
            });
/* 
            put("SHOOT_LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(1, 0))
                            .withScale(3) 
                            .withBounds(4, 2, 5, 13)
                    new FrameBuilder(spriteSheet.getSprite(0, 3))
                            .withScale(1)
                            .withBounds(4, 2, 45, 40)
                            .build()
            });


*/

        }};
    }
    
    @Override
    public float getMovementSpeed(){
        return this.movementSpeed;
    }

    @Override
    public void setMovementSpeed(float x){
        movementSpeed = x;
    }

    @Override
    public int getCurrentHealth() {
        return this.health;
    }

    @Override
    public int getMaxHealth() {
        return this.maxHealth;
    }

    @Override
    public String getBossName() {
        return this.bossName;
    }


    public enum DinosaurState {
        WALK, SHOOT_WAIT, SHOOT
    }
}
