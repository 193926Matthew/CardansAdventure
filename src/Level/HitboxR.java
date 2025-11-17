package Level;

import GameObject.Frame;
import GameObject.SpriteSheet;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;

import java.awt.Color;
import java.util.HashMap;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.Keyboard;

// This class is a base class for all enemies in the game -- all enemies should extend from it
public class HitboxR extends MapEntity {

    protected HitboxState hitboxState;
    protected HitboxState previousHitboxState;

        protected Direction facingDirection;

        protected int existTimer = 48;


    protected Key TAIL_ATTACK_DASH_KEY = Key.RIGHT;
    protected Key TAIL_ATTACK_SPIN_KEY = Key.LEFT;

    public HitboxR(float x, float y, SpriteSheet spriteSheet, String startingAnimation) {
        super(x, y, spriteSheet, startingAnimation);
    }

    public HitboxR(float x, float y, HashMap<String, Frame[]> animations, String startingAnimation) {
        super(x, y, animations, startingAnimation);
    }

    public HitboxR(float x, float y, Frame[] frames) {
        super(x, y, frames);
    }

    public HitboxR(float x, float y, Frame frame) {
        super(x, y, frame);
    }

    public HitboxR(float x, float y) {
        super(x, y, new Hitbox(new Point(x, y)).loadAnimations(new SpriteSheet(ImageLoader.load("ATTACK.png"), 210, 70)), "ATTACK_RIGHT_SPIN");
        hitboxState = HitboxState.NOTHING;
        previousHitboxState = hitboxState;

    }

    @Override
    public void initialize() {
        super.initialize();
    }

    public void update(Player player) {
        handleHitboxState();
        handleHitboxAnimation();

        super.update();

        if (existTimer <= 0) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
            return;
        }

        existTimer--;

        // Check if hitbox hits an enemy
        for (MapEntity entity : map.getEnemies()) {
            if (entity instanceof Enemy && this.intersects(entity)) {
                if (hitboxState == HitboxState.ATTACKING_DASH) {
                    ((Enemy) entity).kill(2);
                }
                if (hitboxState == HitboxState.ATTACKING_SPIN) {
                    ((Enemy) entity).kill(1);
                }
            }
        }
    }

    protected void handleHitboxState() {
        switch (hitboxState) {
            case NOTHING:
                hitboxNoting();
                break;
            case ATTACKING_DASH:
                hitboxAttackingDash();
                break;
            case ATTACKING_SPIN:
                hitboxAttackingSpin();
                break;
        }
    }

    private void hitboxNoting() {
        if (Keyboard.isKeyDown(TAIL_ATTACK_SPIN_KEY)) {
            hitboxState = HitboxState.ATTACKING_SPIN;
        }
        if (Keyboard.isKeyDown(TAIL_ATTACK_DASH_KEY)) {
            hitboxState = HitboxState.ATTACKING_DASH;
        }
    }

    private void hitboxAttackingSpin() {
        if (Keyboard.isKeyUp(TAIL_ATTACK_SPIN_KEY)) {
            hitboxState = HitboxState.NOTHING;
        }
    }

    private void hitboxAttackingDash() {
        if (Keyboard.isKeyUp(TAIL_ATTACK_DASH_KEY)) {
            hitboxState = HitboxState.NOTHING;
        }
    }

    // anything extra the player should do based on interactions can be handled here
    protected void handleHitboxAnimation() {
        if (hitboxState == HitboxState.NOTHING) {
            // sets animation to a STAND animation based on which way player is facing
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "NOTHING_RIGHT" : "NOTHING_LEFT";
        }
        else if (hitboxState == HitboxState.ATTACKING_SPIN) {
            // sets animation to a ATTACK SPIN animation based on which way player is facing
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "ATTACK_RIGHT_SPIN" : "ATTACK_LEFT_SPIN";

        }
        else if (hitboxState == HitboxState.ATTACKING_DASH) {
            // sets animation to a ATTACK SPIN animation based on which way player is facing
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "ATTACK_RIGHT_DASH" : "ATTACK_LEFT_DASH";

        }

    }

    // // A subclass can override this method to specify what it does when it touches the player
    // public void touchedPlayer(Player player) {
    //     player.hurtPlayer(this);
    // }
}
