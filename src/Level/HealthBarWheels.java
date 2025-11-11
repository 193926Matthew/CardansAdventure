package Level;
import java.awt.*;

import Engine.GraphicsHandler;

public class HealthBarWheels {
    private final HealthProvider provider;

    // Animation state
    private float shownRatio = 1.0f;     // what’s currently drawn (0..1)
    private long lastUpdateNanos = System.nanoTime();
    private boolean damageFlash = false;
    private long damageFlashUntil = 0L;

    // Tweakables
    private float lerpPerSecond = 6.0f;  // how quickly the bar catches up to real health
    private int cornerRadius = 8;
    private int borderThickness = 2;

    public HealthBarWheels(HealthProvider provider) {
        this.provider = provider;
    }

    /** Call from your game loop each frame before render. */
    public void update() {
        long now = System.nanoTime();
        float dt = (now - lastUpdateNanos) / 1_000_000_000f;
        lastUpdateNanos = now;

        float target = getSafeRatio();
        // Smoothly approach target
        float diff = target - shownRatio;
        float step = Math.copySign(Math.min(Math.abs(diff), lerpPerSecond * dt), diff);
        shownRatio += step;
        if (Math.abs(target - shownRatio) < 0.001f) shownRatio = target;

        // Stop flash when time is up
        if (damageFlash && now > damageFlashUntil) damageFlash = false;
    }

    /** Call when damage happens to trigger a quick flash. */
    public void onDamaged() {
        damageFlash = true;
        damageFlashUntil = System.nanoTime() + 120_000_000L; // ~120ms flash
    }

    /** Draw the bar at x,y with given size (pixels). */
    public void render(GraphicsHandler g, int x, int y, int width, int height) {
        Object oldAA = g.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background
        g.setColor(new Color(30, 30, 30, 200));
        g.fillRoundRect(x, y, width, height, cornerRadius, cornerRadius);

        // Border
        g.setStroke(new BasicStroke(borderThickness));
        g.setColor(new Color(0, 0, 0, 220));
        g.drawRoundRect(x, y, width, height, cornerRadius, cornerRadius);

        // Fill (left -> right) using shownRatio
        int innerX = x + borderThickness;
        int innerY = y + borderThickness;
        int innerW = width - borderThickness * 2;
        int innerH = height - borderThickness * 2;

        int filledW = Math.max(0, Math.round(innerW * clamp01(shownRatio)));

        // Choose fill color: green → yellow → red. Flash on recent damage.
        float r = clamp01(shownRatio);
        Color base =
            (r > 0.5f) ? lerp(new Color(255, 220, 0), new Color(60, 200, 60), (r - 0.5f) * 2f) // 0.5..1
                       : lerp(new Color(220, 60, 60), new Color(255, 220, 0), r * 2f);            // 0..0.5

        Color fill = damageFlash ? new Color(255, 80, 80) : base;

        g.setColor(fill);
        g.fillRoundRect(innerX, innerY, filledW, innerH, cornerRadius, cornerRadius);

        // Greyed remainder to show capacity
        if (filledW < innerW) {
            g.setColor(new Color(120, 120, 120, 60));
            g.fillRoundRect(innerX + filledW, innerY, innerW - filledW, innerH, cornerRadius, cornerRadius);
        }

        // Optional text (e.g., "73 / 100")
        String label = provider.getCurrentHealth() + " / " + provider.getMaxHealth();
        g.setFont(g.getFont().deriveFont(Font.BOLD, Math.max(10f, innerH * 0.6f)));
        FontMetrics fm = g.getFontMetrics();
        int tx = x + (width - fm.stringWidth(label)) / 2;
        int ty = y + (height + fm.getAscent() - fm.getDescent()) / 2;
        g.setColor(new Color(0, 0, 0, 170));
        g.drawString(label, tx + 1, ty + 1);
        g.setColor(Color.WHITE);
        g.drawString(label, tx, ty);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, oldAA);
    }

    /** Call this from your damage code to keep the flash in sync. */
    public void notifyDamageIfNeeded(int oldHp) {
        if (provider.getCurrentHealth() < oldHp) onDamaged();
    }

    private float getSafeRatio() {
        int max = Math.max(1, provider.getMaxHealth());
        int cur = Math.max(0, Math.min(provider.getCurrentHealth(), max));
        return cur / (float) max;
    }

    private static float clamp01(float v) { return Math.max(0f, Math.min(1f, v)); }

    private static Color lerp(Color a, Color b, float t) {
        t = clamp(t);
        int r = (int)(a.getRed()   + (b.getRed()   - a.getRed())   * t);
        int g = (int)(a.getGreen() + (b.getGreen() - a.getGreen()) * t);
        int bl= (int)(a.getBlue()  + (b.getBlue()  - a.getBlue())  * t);
        int al= (int)(a.getAlpha() + (b.getAlpha() - a.getAlpha()) * t);
        return new Color(r, g, bl, al);
    }

    private static float clamp(float v) { return Math.max(0f, Math.min(1f, v)); }

    public void render(GraphicsHandler g, int margin, int margin2, int width, int height) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }
}
