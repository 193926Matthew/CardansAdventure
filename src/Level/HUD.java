package Level;
import java.awt.Container;

public class HUD {
    private final HealthBarWheels healthBar;

    public HUD(Container parent, HealthProvider provider) {
        // position & size: tweak as you like
        this.healthBar = new HealthBarWheels(parent, provider, 16, 16, 260, 24);
    }

    /** Call each frame/tick. */
    public void update() {
        healthBar.update();
    }

    /** Forward damage flashes (call with previous HP). */
    public void notifyDamageIfNeeded(int oldHp) {
        healthBar.notifyDamageIfNeeded(oldHp);
    }
}
