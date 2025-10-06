package Level;

import GameObject.Frame;
import GameObject.SpriteSheet;

import java.util.HashMap;

// This class is a base class for all enemies in the game -- all enemies should extend from it
public class Enemy extends MapEntity {

    private boolean isDead = false;

    public boolean isDead() {
        return isDead;
    }

    public void kill() {
        this.isDead = true;
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

    public void update(Player player, Player hitbox) {
        super.update();
        if (intersects(player)) {
            touchedPlayer(player);
        }
        if (intersects(hitbox)) {
            touchedHitbox(hitbox);
        }
    }

    // A subclass can override this method to specify what it does when it touches the player
    public void touchedPlayer(Player player) {
        player.hurtPlayer(this);
    }
    public void touchedHitbox(Player hitbox) {
        hitbox.hurtHitbox(this);
    }
}
