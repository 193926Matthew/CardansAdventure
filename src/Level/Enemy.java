package Level;

import GameObject.Frame;
import GameObject.SpriteSheet;

import java.util.HashMap;

// This class is a base class for all enemies in the game -- all enemies should extend from it
public class Enemy extends MapEntity {

    private boolean isDead = false;
    private boolean hitWithIceBall = false;
    private boolean hitByPoison = false;
    private float movementSpeed;
    private int damage = 0;
    private int startingHealth;
    private int health;
    private int poisonDelay = 100;

    public boolean isDead() {
        return isDead;
    }

    public int damageValue() {
        return damage;
    }



    public void kill(int damage) {
        this.isDead = true;
        this.damage = damage;
    }

    public void live() {
        this.isDead = false;
    }

    public Enemy(float x, float y, SpriteSheet spriteSheet, String startingAnimation) {
        super(x, y, spriteSheet, startingAnimation);
    }

    public Enemy(float x, float y, HashMap<String, Frame[]> animations, String startingAnimation) {
        super(x, y, animations, startingAnimation);
    }

    public Enemy(float x, float y, Frame[] frames) {
        super(x, y, frames);
    }

    public Enemy(float x, float y, Frame frame) {
        super(x, y, frame);
    }

    public Enemy(float x, float y) {
        super(x, y);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    public void update(Player player) {
        super.update();
        if (intersects(player)) {
            touchedPlayer(player);
        }
        if(this.getPoisonStatus() == true){
            if(poisonDelay == 0){
                // System.out.println("poison ailment damage taken!" + " 5 damage taken");
                this.kill(5);
                poisonDelay =100; 
            }

            poisonDelay--;
        }
    }

    // A subclass can override this method to specify what it does when it touches
    // the player
    public void touchedPlayer(Player player) {
        player.hurtPlayer(this);
    }

    //will be used in Player class and called when Iceball hits enemy
    //to reduce the enemy's speed 
    
    public float getMovementSpeed(){
        return this.movementSpeed;
    }

    public void setMovementSpeed(float movementSpeed){
        this.movementSpeed = movementSpeed;
    }

    public void setIceBallHitStatus(Boolean hitStatus){
        this.hitWithIceBall = hitStatus;
    }

    public boolean getIceBallHitStatus(){
        return this.hitWithIceBall;
    }

    public void setPoisonStatus(Boolean hitStatus){
        this.hitByPoison = hitStatus;
    }

    public boolean getPoisonStatus(){
        return this.hitByPoison;
    }

    public void setHealth(int healthValue){
        this.startingHealth = healthValue;
    }

    public int getHealth(){
        return this.startingHealth;
    }


}
