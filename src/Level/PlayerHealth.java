package Level;

public class PlayerHealth implements HealthProvider {
    private int maxHealth;
    private int health;

    public PlayerHealth(int maxHealth) {
        this.maxHealth = Math.max(1, maxHealth);
        this.health = this.maxHealth;
    }

    public void takeDamage(int amount) {
        if (amount <= 0) return;
        health = Math.max(0, health - amount);
    }

    public void heal(int amount) {
        if (amount <= 0) return;
        health = Math.min(maxHealth, health + amount);
    }

    public void setMaxHealth(int newMax) {
        maxHealth = Math.max(1, newMax);
        health = Math.min(health, maxHealth);
    }

    @Override public int getCurrentHealth() { return health; }
    @Override public int getMaxHealth()     { return maxHealth; }

    public boolean isDead() { return health <= 0; }
}
