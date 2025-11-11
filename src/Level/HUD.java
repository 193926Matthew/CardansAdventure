package Level;

import Engine.GraphicsHandler;

public class HUD {
    private final HealthBarWheels healthBar;
    private int lastHp;
    private HealthProvider provider; 

    public HUD(HealthProvider provider) {
        // x, y, width, height, can be changed if position on screen looks weird
        this.healthBar = new HealthBarWheels(provider, 16, 16, 260, 24);
        this.lastHp = provider.getCurrentHealth();
    }

    //call this for when the player dies and a new player is made
    public void setProvider(HealthProvider provider){
        this.provider = provider; 
        this.healthBar.setProvider(provider);
        this.lastHp = provider.getCurrentHealth(); 
    }
    public void update(HealthProvider provider) {
        int current = provider.getCurrentHealth();
        if (current < lastHp) {
            // tell bar player took damage so it can flash
            healthBar.notifyDamageIfNeeded(lastHp);
        }
        lastHp = current;

        healthBar.update();
    }

    public void draw(GraphicsHandler g) {
        healthBar.draw(g);
    }
}
