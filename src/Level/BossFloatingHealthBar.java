package Level;

import java.awt.Color;
import java.awt.Font; 
import Engine.GraphicsHandler;

// Floating healthbar that shows above bosses
//Can be told where to draw from every frame with screenX and screenY

public class BossFloatingHealthBar {

     private final BossHealthProvider provider;

    // current animated ratio
    private float shownRatio = 1f;
    private long lastUpdateNanos = System.nanoTime();

    // config
    private int width = 80;
    private int height = 10;
    private int nameOffsetY = -14;   // name above bar
    private int barOffsetY = -2;     // bar above enemy (add to enemy Y)
    private float lerpPerSecond = 6f;
    private static final Font NAME_FONT = new Font("Arial", Font.BOLD, 11);

    public BossFloatingHealthBar(BossHealthProvider provider) {
        this.provider = provider;
        this.shownRatio = getSafeRatio();
    }

    public void update() {
        long now = System.nanoTime();
        float dt = (now - lastUpdateNanos) / 1_000_000_000f;
        lastUpdateNanos = now;

        float target = getSafeRatio();
        float diff = target - shownRatio;
        shownRatio += diff * Math.min(1f, lerpPerSecond * dt);
        if (Math.abs(target - shownRatio) < 0.001f) {
            shownRatio = target;
        }
    }

    // drawing the bar above the boss, 
    public void draw(GraphicsHandler g, int screenX, int screenY) {
        // name
        String name = provider.getBossName();
        g.drawString(name, screenX, screenY + nameOffsetY, NAME_FONT, Color.WHITE);

        // bar bg
        int x = screenX;
        int y = screenY + barOffsetY;
        g.drawFilledRectangle(x, y, width, height, new Color(0,0,0,120));

        int fillWidth = (int)(width * shownRatio);
        if (fillWidth < 0) fillWidth = 0;
        if (fillWidth > width) fillWidth = width;

        Color c = getHealthColor(getSafeRatio());
        g.drawFilledRectangle(x, y, fillWidth, height, c);

        g.drawRectangle(x, y, width, height, Color.BLACK, 1);
    }

    private float getSafeRatio() {
        int max = provider.getMaxHealth();
        if (max <= 0) return 0f;
        return Math.max(0f, Math.min(1f, provider.getCurrentHealth() / (float) max));
    }

    private Color getHealthColor(float ratio) {
        ratio = Math.max(0f, Math.min(1f, ratio));
        if (ratio > 0.6f) {
            return new Color(0, 200, 0);
        } else if (ratio > 0.3f) {
            return new Color(255, 200, 0);
        } else {
            return new Color(200, 0, 0);
        }
    }
    
}
