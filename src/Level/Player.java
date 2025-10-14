package Level;

import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import EnhancedMapTiles.QuicksandTile;
import EnhancedMapTiles.QuicksandTopTile;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Utils.AirGroundState;
import Utils.Direction;
import EnhancedMapTiles.PowerUp;
import Level.IceBall;
import Utils.Point;

import java.util.ArrayList;
import Utils.Point;
import java.util.List;

public abstract class Player extends GameObject {
    // values that affect player movement
    // these should be set in a subclass
    protected float walkSpeed = 0;
    protected float gravity = 0;
    protected float jumpHeight = 0;
    protected float jumpDegrade = 0;
    protected float terminalVelocityY = 0;
    protected float momentumYIncrease = 0;

    // values for player stats
    public int health = 100;

    // values used to handle player movement
    protected float jumpForce = 0;
    protected float momentumY = 0;
    protected float moveAmountX, moveAmountY;
    protected float lastAmountMovedX, lastAmountMovedY;

    // values used to keep track of player's current state
    protected PlayerState playerState;
    protected PlayerState previousPlayerState;
    protected Direction facingDirection;
    protected AirGroundState airGroundState;
    protected AirGroundState previousAirGroundState;
    protected LevelState levelState;

    // classes that listen to player events can be added to this list
    protected ArrayList<PlayerListener> listeners = new ArrayList<>();

    // define keys
    protected KeyLocker keyLocker = new KeyLocker();
    protected Key JUMP_KEY = Key.UP;
    protected Key MOVE_LEFT_KEY = Key.LEFT;
    protected Key MOVE_RIGHT_KEY = Key.RIGHT;
    protected Key CROUCH_KEY = Key.DOWN;
    protected Key TAIL_ATTACK_DASH_KEY = Key.T;
    protected Key TAIL_ATTACK_SPIN_KEY = Key.Q;
    protected Key DOUBLE_JUMP_KEY = Key.D;
    protected Key ICE_BALL_KEY = Key.I;



    // Attack variables
    private boolean isAttacking = false;
    private boolean isReturning = false;
    private int attackStartX;
    private int attackDistance = 40; // pixels forward
    private int attackSpeed = 4;     // dash speed

    //PowerUp variables
    private boolean hasDoubleJump = false;
    private boolean usedDoubleJump = false;
    private boolean hasIceBall = false;
    private boolean hasJumped = false;
    private int doubleJumpKeyCount = 0;

    // flags
    protected boolean isInvincible = false; // if true, player cannot be hurt by enemies (good for testing)
    protected int invincibleTimer;
    protected int duration = 60;
    protected boolean isOnPlatform = false; //checks to see if the player is standing on a moving platform (used for vertical moving platforms)
    public boolean isInTile = false; //checks to see if the player is in a quicksand tile

    public Player(SpriteSheet spriteSheet, float x, float y, String startingAnimationName) {
        super(spriteSheet, x, y, startingAnimationName);
        facingDirection = Direction.RIGHT;
        airGroundState = AirGroundState.AIR;
        previousAirGroundState = airGroundState;
        playerState = PlayerState.STANDING;
        previousPlayerState = playerState;
        levelState = LevelState.RUNNING;

        
    }

    private void spawnHitbox(HitboxState state) {
        // determine spawn position relative to player
        float hitboxX = getX();
        float hitboxY = getY();

        // create hitbox entity
        HitboxR hitbox = new HitboxR(hitboxX, hitboxY);
        hitbox.hitboxState = state;
        hitbox.facingDirection = this.facingDirection;

        // System.out.println(hitbox);
        map.addHitbox(hitbox);
    }

    public void update() {
        moveAmountX = 0;
        moveAmountY = 0;

        // if player is currently playing through level (has not won or lost)
        if (levelState == LevelState.RUNNING) {

            applyGravity();

            // update player's state and current actions, which includes things like
            // determining how much it should move each frame and if its walking or jumping
            do {
                previousPlayerState = playerState;
                handlePlayerState();
            } while (previousPlayerState != playerState);

            previousAirGroundState = airGroundState;

            // move player with respect to map collisions based on how much player needs to
            // move this frame
            lastAmountMovedX = super.moveXHandleCollision(moveAmountX);
            lastAmountMovedY = super.moveYHandleCollision(moveAmountY);

            handlePlayerAnimation();

            updateLockedKeys();

            // update player's animation
            super.update();

            if (isInvincible) {
                invincibleTimer--;
                if (invincibleTimer <= 0) {
                    isInvincible = false;
                }
            }
        }

        // if player has beaten level
        else if (levelState == LevelState.LEVEL_COMPLETED) {
            updateLevelCompleted();
        }

        // if player has lost level
        else if (levelState == LevelState.PLAYER_DEAD) {
            updatePlayerDead();
        }
    }

    protected void applyGravity() {
        // if player is in quicksand, reduce walk speed and jump height and limit downward momentum
        if (isInQuicksand()) {
            isInTile = true;
            setWalkSpeed(2.15f); 
            setJumpHeight(14.5f/2);
            if (momentumY > 0.001f) {
                setYMomentum(0.001f);
            }
        //if the player is not in quicksand, reset walk speed and jump height
        } else {
            isInTile = false;
            setJumpHeight(14.5f);
            setWalkSpeed(4.3f);
        }

        moveAmountY += gravity + momentumY;

    }

    // based on player's current state, call appropriate player state handling
    // method
    protected void handlePlayerState() {
        switch (playerState) {

            case STANDING:
                playerStanding();
                break;
            case WALKING:
                playerWalking();
                break;
            case CROUCHING:
                playerCrouching();
                break;
            case JUMPING:
                playerJumping();
                break;
            case ATTACKING_DASH:
                playerAttackingDash();
                break;
            case ATTACKING_SPIN:
                playerAttackingSpin();
                break;
            case DOUBLE_JUMP:
                playerDoubleJump();
                break;
            case ICE_BALL:
                playerIceBallAttack();
                break;
        }
    }

    // player STANDING state logic
    protected void playerStanding() {
        isAttacking = false;
        // if walk left or walk right key is pressed, player enters WALKING state
        if (Keyboard.isKeyDown(MOVE_LEFT_KEY) || Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
            playerState = PlayerState.WALKING;
        }

        // if jump key is pressed, player enters JUMPING state
        else if (Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY)) {
            keyLocker.lockKey(JUMP_KEY);
            playerState = PlayerState.JUMPING;
        }

        // if crouch key is pressed, player enters CROUCHING state
        else if (Keyboard.isKeyDown(CROUCH_KEY)) {
            playerState = PlayerState.CROUCHING;
        }

        // should check if the attack key is being pressed as well
        else if (Keyboard.isKeyDown(TAIL_ATTACK_DASH_KEY) && !isAttacking && !isReturning) {
        playerState = PlayerState.ATTACKING_DASH;
        spawnHitbox(HitboxState.ATTACKING_DASH);
            }

        else if (Keyboard.isKeyDown(TAIL_ATTACK_SPIN_KEY)) {
        playerState = PlayerState.ATTACKING_SPIN;
        spawnHitbox(HitboxState.ATTACKING_SPIN);
            }
        else if(Keyboard.isKeyDown(ICE_BALL_KEY)){
            playerState = PlayerState.ICE_BALL;
        }



    }

    // player WALKING state logic
    protected void playerWalking() {
        // if walk left key is pressed, move player to the left
        if (Keyboard.isKeyDown(MOVE_LEFT_KEY)) {
            moveAmountX -= walkSpeed;
            facingDirection = Direction.LEFT;
        }

        // if walk right key is pressed, move player to the right
        else if (Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
            moveAmountX += walkSpeed;
            facingDirection = Direction.RIGHT;
        } else if (Keyboard.isKeyUp(MOVE_LEFT_KEY) && Keyboard.isKeyUp(MOVE_RIGHT_KEY)) {
            playerState = PlayerState.STANDING;
        }

        // if jump key is pressed, player enters JUMPING state
        if (Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY)) {
            keyLocker.lockKey(JUMP_KEY);
            playerState = PlayerState.JUMPING;
        }

        // if crouch key is pressed,
        else if (Keyboard.isKeyDown(CROUCH_KEY)) {
            playerState = PlayerState.CROUCHING;
        }

        // should check if the attack key is being pressed as well
        else if (Keyboard.isKeyDown(TAIL_ATTACK_DASH_KEY) && !isAttacking && !isReturning) {
        playerState = PlayerState.ATTACKING_DASH;
        spawnHitbox(HitboxState.ATTACKING_DASH);
        }

        else if (Keyboard.isKeyDown(TAIL_ATTACK_SPIN_KEY)) {
        playerState = PlayerState.ATTACKING_SPIN;
        spawnHitbox(HitboxState.ATTACKING_SPIN);
        }

        else if(Keyboard.isKeyDown(ICE_BALL_KEY)){
            playerState = PlayerState.ICE_BALL;
        }
    }

    // player CROUCHING state logic
    protected void playerCrouching() {
        // if crouch key is released, player enters STANDING state
        if (Keyboard.isKeyUp(CROUCH_KEY)) {
            playerState = PlayerState.STANDING;
        }

        // if jump key is pressed, player enters JUMPING state
        if (Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY)) {
            keyLocker.lockKey(JUMP_KEY);
            playerState = PlayerState.JUMPING;
        }
        if (Keyboard.isKeyDown(TAIL_ATTACK_DASH_KEY)) {
            playerState = PlayerState.ATTACKING_DASH;
        }
        if (Keyboard.isKeyDown(TAIL_ATTACK_SPIN_KEY)) {
            playerState = PlayerState.ATTACKING_SPIN;
            spawnHitbox(HitboxState.ATTACKING_SPIN);
        }

        //if player is crouched, and hits the ice key, then if they have the powerup it will activate
        if(Keyboard.isKeyDown(ICE_BALL_KEY)){
            playerState = PlayerState.ICE_BALL;
        }
    }

    protected void playerAttackingSpin() {

        // System.out.println("Attack");

        // if jump key is pressed, player enters JUMPING state
        if (Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY)) {
            keyLocker.lockKey(JUMP_KEY);
            playerState = PlayerState.JUMPING;
        }

        if (Keyboard.isKeyUp(TAIL_ATTACK_SPIN_KEY)) {
            playerState = PlayerState.STANDING;
        }

    } 

    protected void playerDoubleJump(){
        //if jump key is pressed and the key is not locked
        if(Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY)){
            //locks the jump key
            keyLocker.lockKey(JUMP_KEY);
            //enters jumping state
            playerState = PlayerState.JUMPING;
            hasJumped = true;
        }
        //if Cardan is in the air, and the Double Jump key is not locked
        if(previousAirGroundState == AirGroundState.AIR && airGroundState == AirGroundState.AIR && !keyLocker.isKeyLocked(DOUBLE_JUMP_KEY)){
            //If Double jump key is pressed
              if(Keyboard.isKeyDown(DOUBLE_JUMP_KEY) && !keyLocker.isKeyLocked(DOUBLE_JUMP_KEY) && hasDoubleJump() == true){ 
                    keyLocker.lockKey(DOUBLE_JUMP_KEY);
                    usedDoubleJump = false;
                    doubleJumpKeyCount +=1;
                if(doubleJumpKeyCount == 2 && Keyboard.isKeyDown(DOUBLE_JUMP_KEY) && usedDoubleJump == false){
                    usedDoubleJump = true;
                //keyLocker.unlockKey(DOUBLE_JUMP_KEY);
                currentAnimationName = facingDirection == Direction.RIGHT ? "JUMP_RIGHT" : "JUMP_LEFT";

                airGroundState = AirGroundState.AIR;
                jumpForce = jumpHeight * 2;
                jumpForce -= 10;
                if (jumpForce > 0) {
                moveAmountY -= jumpForce;
                jumpForce -= jumpDegrade;
                if (jumpForce < 0) {
                    jumpForce = 0;
                }
                doubleJumpKeyCount = 0;
                usedDoubleJump = false;
                }
            }
              }
        }

        if(Keyboard.isKeyUp(DOUBLE_JUMP_KEY)){
            keyLocker.unlockKey(DOUBLE_JUMP_KEY);
        }
    }

    protected void playerIceBallAttack(){
        if(hasIceBall && Keyboard.isKeyDown(ICE_BALL_KEY) && !keyLocker.isKeyLocked(ICE_BALL_KEY)){
            keyLocker.lockKey(ICE_BALL_KEY);
            fireIceBall();
        }
        if(Keyboard.isKeyUp(ICE_BALL_KEY)){
            keyLocker.unlockKey(ICE_BALL_KEY);
            playerState = PlayerState.WALKING;
        }
    }

    protected void fireIceBall(){
        //float speed = facingDirection == Direction.RIGHT ? 3.0f: -3.0f;
        float speed = 0;

        //Point spawn = new Point(getX() + offsetX,getY());
        //IceBall iceBall = new IceBall(spawn,speed,60);
        //this.map.addEnhancedMapTile(iceBall);
        if(this.facingDirection == Direction.RIGHT){
            for(int i = 0; i < 3; i++){
                Point spawn = new Point(Math.round(getX() + getWidth()), getY() + (i * 10));
                speed = 1.5f;
                IceBall iceBall = new IceBall(spawn,speed + (i*1.2f),240,map.getEnemies());
                map.addEnhancedMapTile(iceBall);
            }

        }else{
            for(int i = 0; i < 3; i++){
                Point spawn = new Point(Math.round(getX() - 21), getY() + (i * 10));
                speed = -1.5f;
                IceBall iceBall = new IceBall(spawn,speed + (i*0.5f),240,map.getEnemies());
                map.addEnhancedMapTile(iceBall);
            }

        }
    }
        
    
    
    // player JUMPING state logic
    protected void playerJumping() {


        // if last frame player was on ground and this frame player is still on ground, the jump needs to be setup
        if (previousAirGroundState == AirGroundState.GROUND && airGroundState == AirGroundState.GROUND ) {

            // sets animation to a JUMP animation based on which way player is facing
            currentAnimationName = facingDirection == Direction.RIGHT ? "JUMP_RIGHT" : "JUMP_LEFT";

            // player is set to be in air and then player is sent into the air
            airGroundState = AirGroundState.AIR;
            jumpForce = jumpHeight;
            if (jumpForce > 0) {
                moveAmountY -= jumpForce;
                jumpForce -= jumpDegrade;
                if (jumpForce < 0) {
                    jumpForce = 0;
                }
            }
        }

        // if player is in air (currently in a jump) and has more jumpForce, continue
        // sending player upwards
        else if (airGroundState == AirGroundState.AIR) {
            if(Keyboard.isKeyDown(DOUBLE_JUMP_KEY)){
                playerState = PlayerState.DOUBLE_JUMP;
            }

            if (jumpForce > 0) {
                moveAmountY -= jumpForce;
                jumpForce -= jumpDegrade;
                if (jumpForce < 0) {
                    jumpForce = 0;
                }
            }

            // allows you to move left and right while in the air
            if (Keyboard.isKeyDown(MOVE_LEFT_KEY)) {
                moveAmountX -= walkSpeed;
            } else if (Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
                moveAmountX += walkSpeed;
            }

            // if player is falling, increases momentum as player falls so it falls faster
            // over time
            if (moveAmountY > 0) {
                increaseMomentum();
            }


            
        }

        // if player last frame was in air and this frame is now on ground, player
        // enters STANDING state
        else if (previousAirGroundState == AirGroundState.AIR && airGroundState == AirGroundState.GROUND) {
            playerState = PlayerState.STANDING;
        }

        if(Keyboard.isKeyDown(ICE_BALL_KEY)){
            playerState = PlayerState.ICE_BALL;
        }
    }

    // player ATTACKING state logic
    protected void playerAttackingDash() {
    // if attack just started, set up
    if (!isAttacking && !isReturning) {
        isAttacking = true;
        attackStartX = (int) getX();

            // set animation
            currentAnimationName = facingDirection == Direction.RIGHT ? "TAIL_ATTACK_DASH_RIGHT"
                    : "TAIL_ATTACK_DASH_LEFT";
        }

        if (isAttacking) {
            // dash forward
            if (facingDirection == Direction.RIGHT) {
                moveAmountX += attackSpeed;
                if (getX() >= attackStartX + attackDistance) {
                    isAttacking = false;
                    isReturning = true;

                    // flip around before returning
                    facingDirection = Direction.LEFT;
                    currentAnimationName = "TAIL_ATTACK_DASH_LEFT";
                }
            } else {
                moveAmountX -= attackSpeed;
                if (getX() <= attackStartX - attackDistance) {
                    isAttacking = false;
                    isReturning = true;

                    // flip around before returning
                    facingDirection = Direction.RIGHT;
                    currentAnimationName = "TAIL_ATTACK_DASH_RIGHT";
                }
            }
        } else if (isReturning) {
            // dash back toward start position
            if (facingDirection == Direction.LEFT) {
                moveAmountX -= attackSpeed;
                if (getX() <= attackStartX) {
                    isReturning = false;
                    playerState = PlayerState.STANDING;
                    facingDirection = Direction.RIGHT; // restore original facing
                }
            } else {
                moveAmountX += attackSpeed;
                if (getX() >= attackStartX) {
                    isReturning = false;
                    playerState = PlayerState.STANDING;
                    facingDirection = Direction.LEFT; // restore original facing
                }
            }
        }
    }


    public void setHasDoubleJump(boolean value){
        this.hasDoubleJump = value;
    }

    public boolean hasDoubleJump(){
        return hasDoubleJump;
    }

     public void setHasIceBall(boolean value){
        this.hasIceBall = value;
    }

    public boolean hasIceBall(){
        return hasIceBall;
    }
    // while player is in air, this is called, and will increase momentumY by a set amount until player reaches terminal velocity
    protected void increaseMomentum() {
        momentumY += momentumYIncrease;
        if (momentumY > terminalVelocityY) {
            momentumY = terminalVelocityY;
        }
    }

    protected void updateLockedKeys() {
        if (Keyboard.isKeyUp(JUMP_KEY)) {
            keyLocker.unlockKey(JUMP_KEY);
        }
    }

    // anything extra the player should do based on interactions can be handled here
    protected void handlePlayerAnimation() {
        if (playerState == PlayerState.STANDING) {
            // sets animation to a STAND animation based on which way player is facing
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "STAND_RIGHT" : "STAND_LEFT";

            // handles putting goggles on when standing in water
            // checks if the center of the player is currently touching a water tile
            int centerX = Math.round(getBounds().getX1()) + Math.round(getBounds().getWidth() / 2f);
            int centerY = Math.round(getBounds().getY1()) + Math.round(getBounds().getHeight() / 2f);
            MapTile currentMapTile = map.getTileByPosition(centerX, centerY);
            if (currentMapTile != null && currentMapTile.getTileType() == TileType.WATER) {
                this.currentAnimationName = facingDirection == Direction.RIGHT ? "SWIM_STAND_RIGHT" : "SWIM_STAND_LEFT";
            }
        } else if (playerState == PlayerState.ATTACKING_SPIN) {
            // sets animation to a ATTACK SPIN animation based on which way player is facing
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "ATTACK_RIGHT_SPIN" : "ATTACK_LEFT_SPIN";
        } else if (playerState == PlayerState.WALKING) {
            // sets animation to a WALK animation based on which way player is facing
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "WALK_RIGHT" : "WALK_LEFT";
        } else if (playerState == PlayerState.CROUCHING) {
            // sets animation to a CROUCH animation based on which way player is facing
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "CROUCH_RIGHT" : "CROUCH_LEFT";
        } else if (playerState == PlayerState.JUMPING) {
            // if player is moving upwards, set player's animation to jump. if player moving
            // downwards, set player's animation to fall
            if (lastAmountMovedY <= 0) {
                this.currentAnimationName = facingDirection == Direction.RIGHT ? "JUMP_RIGHT" : "JUMP_LEFT";
            } else {
                this.currentAnimationName = facingDirection == Direction.RIGHT ? "FALL_RIGHT" : "FALL_LEFT";
            }
        }
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {

        if (direction == Direction.RIGHT || direction == Direction.RIGHT) {
            if (hasCollided && isAttacking) {
                isAttacking = false;
                // Position = x - 10;
            }
        }
    }

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if player collides with a map tile below it, it is now on the ground
        // if player does not collide with a map tile below, it is in air
        if (direction == Direction.DOWN || isOnPlatform) {
            if (hasCollided) {
                momentumY = 0;
                airGroundState = AirGroundState.GROUND;
                //System.out.println("On Platform"); uncomment to see if player is on platform
            }else if(isInTile){
                airGroundState = AirGroundState.GROUND;
            }
             else{
                playerState = PlayerState.JUMPING;
                airGroundState = AirGroundState.AIR;
                // System.out.println("Not On Platform"); uncomment to see if player is not on
                // platform
                isOnPlatform = false; // reset the isOnPlatform only if the player is falling
            }
        }

        // if player collides with map tile upwards, it means it was jumping and then
        // hit into a ceiling -- immediately stop upwards jump velocity
        else if (direction == Direction.UP) {
            if (hasCollided) {
                jumpForce = 0;
            }
        }
    }

    // other entities can call this method to hurt the player
    public void hurtPlayer(MapEntity mapEntity) {
        if (!isInvincible) {
            // if map entity is an enemy, kill player on touch
            if (mapEntity instanceof Enemy) {
                health = health - 25;

                isInvincible = true;
                invincibleTimer = duration;
            }
            if (health <= 0) {
                levelState = LevelState.PLAYER_DEAD;
            }
        }
    }

    public void hurtHitbox(MapEntity mapEntity) {
        if (!isInvincible) {
            // if map entity is an enemy, kill player on touch
            if (mapEntity instanceof Enemy) {
                    mapEntity.kill();
            }
        }
    }

    // other entities can call this to tell the player they beat a level
    public void completeLevel() {
        levelState = LevelState.LEVEL_COMPLETED;
    }

    // if player has beaten level, this will be the update cycle
    public void updateLevelCompleted() {
        // if player is not on ground, player should fall until it touches the ground
        if (airGroundState != AirGroundState.GROUND && map.getCamera().containsDraw(this) && !isOnPlatform) {
            currentAnimationName = "FALL_RIGHT";
            if (gravity != 0f) {
                applyGravity();
            }
            increaseMomentum();
            super.update();
            moveYHandleCollision(moveAmountY);
        }
        // move player to the right until it walks off screen
        else if (map.getCamera().containsDraw(this)) {
            currentAnimationName = "WALK_RIGHT";
            super.update();
            moveXHandleCollision(walkSpeed);
        } else {
            // tell all player listeners that the player has finished the level
            for (PlayerListener listener : listeners) {
                listener.onLevelCompleted();
            }
        }
    }

    // if player has died, this will be the update cycle
    public void updatePlayerDead() {
        // change player animation to DEATH
        if (!currentAnimationName.startsWith("DEATH")) {
            if (facingDirection == Direction.RIGHT) {
                currentAnimationName = "DEATH_RIGHT";
            } else {
                currentAnimationName = "DEATH_LEFT";
            }
            super.update();
        }
        // if death animation not on last frame yet, continue to play out death
        // animation
        else if (currentFrameIndex != getCurrentAnimation().length - 1) {
            super.update();
        }
        // if death animation on last frame (it is set up not to loop back to start),
        // player should continually fall until it goes off screen
        else if (currentFrameIndex == getCurrentAnimation().length - 1) {
            if (map.getCamera().containsDraw(this)) {
                moveY(3);
            } else {
                // tell all player listeners that the player has died in the level
                for (PlayerListener listener : listeners) {
                    listener.onDeath();
                }
            }
        }
    }

    // checks if player is in quicksand tile
    protected boolean isInQuicksand() {
    for (EnhancedMapTile tile : map.getEnhancedMapTiles()) {
        if ((tile instanceof QuicksandTile || tile instanceof QuicksandTopTile) && getBounds().intersects(tile.getBounds())) {
            return true;
        }
    }
    return false;
    
}

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public AirGroundState getAirGroundState() {
        return airGroundState;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public void setIsOnPlatform(boolean isOnPlatfrom) {
        this.isOnPlatform = isOnPlatfrom;
    }

    public void setAirGroundState(AirGroundState airGroundState) {
        this.airGroundState = airGroundState;
    }

    public void setFacingDirection(Direction facingDirection) {
        this.facingDirection = facingDirection;
    }

    public void setLevelState(LevelState levelState) {
        this.levelState = levelState;
    }

    public void addListener(PlayerListener listener) {
        listeners.add(listener);
    }

    public float getYMomentum(){
        return momentumY;
    }

    public void setYMomentum(float momentumY){
        this.momentumY = momentumY;
    }

    public void setWalkSpeed(float walkSpeed) {
        this.walkSpeed = walkSpeed;
    }
    public float getWalkSpeed(){
        return walkSpeed;
    }

    public void setIsInTile(boolean isInTile){
        this.isInTile = isInTile;
    }

    public void setJumpHeight(float jumpHeight){
        this.jumpHeight = jumpHeight;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    // Uncomment this to have game draw player's bounds to make it easier to visualize
    /* 
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        drawBounds(graphicsHandler, new Color(255, 0, 0, 100));
    }
    */
}
