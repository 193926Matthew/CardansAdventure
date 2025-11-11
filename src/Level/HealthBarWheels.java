package Level;

import java.awt.Color;
import Engine.GraphicsHandler;
import EnhancedMapTiles.HealthPowerUp;

/* redesigning to use less clutter compared to original attempt
and adding other features like a moving bar and damage reaction
*/

public class HealthBarWheels{

    private HealthProvider provider; 

    //position and size control on the screen
    private final int x; 
    private final int y;
    private final int width; 
    private final int height; 

    //animation handling/state
    private float shownRatio = 1.0f; // what is being drawn
    private long lastUpdateNanos = System.nanoTime(); 
    private boolean damageFlash = false; 
    private long damageFlashUntil = 0L;
    
    //adjustable settings
    private float lerpPerSecond = 6.0f; // catchup rate of bar
    private int backgroundAlpha = 120;
    private int borderThickness = 2; 

    public HealthBarWheels(HealthProvider provider, int x, int y, int width, int height){
        this.provider = provider;
        this.x=x; 
        this.y = y; 
        this.width= width; 
        this.height = height; 

        //starts at actual health
        this.shownRatio = getSafeRatio(); 
    }

    //calling every frame before it gets drawn with update
    public void update(){
        long now = System.nanoTime();
        float dt = (now-lastUpdateNanos) / 1_000_000_000f;
        lastUpdateNanos = now; 
        
        float target = getSafeRatio(); 
        float diff = target - shownRatio; 

        //rough exponential rate
        shownRatio += diff * Math.min(1f, lerpPerSecond * dt); 

        //stop small wiggling
        if(Math.abs(target-shownRatio)< 0.001f){
            shownRatio = target; 
        }

        // damage flash timer

        if (damageFlash & now > damageFlashUntil){
            damageFlash = false; 
        }
    }

    //this gets called when hp drops(dmg is done) to make the bar flash
    public void notifyDamageIfNeeded(int oldHp) {
        int current = provider.getCurrentHealth();
        if (current < oldHp) {
            damageFlash = true;
            damageFlashUntil = System.nanoTime() + 200_000_000L; // 0.2 seconds
        }
    }


    private static float clamp(float v) {
        return Math.max(0f, Math.min(1f, v));
    }

    private static Color lerpColor(Color a, Color b, float t) {
        t = clamp(t);
        int r = (int) (a.getRed() + (b.getRed() - a.getRed()) * t);
        int g = (int) (a.getGreen() + (b.getGreen() - a.getGreen()) * t);
        int bl = (int) (a.getBlue() + (b.getBlue() - a.getBlue()) * t);
        int al = (int) (a.getAlpha() + (b.getAlpha() - a.getAlpha()) * t);
        return new Color(r, g, bl, al);
    }
    /*
     * health bar colors
     * 1.0 = green
     * 0.6 - 0.3 = green -> yellow
     * 0.3 and below = yellow -> red
     */

     private Color getHealthColor(float ratio) {
        ratio = Math.max(0f, Math.min(1f, ratio));

        Color green = new Color(0, 200, 0);
        Color yellow = new Color(255, 200, 0);
        Color red = new Color(200, 0, 0);

        if (ratio >= 0.6f) {
            // mostly green, maybe nudge toward yellow
            float t = (1f - ratio) / 0.4f; // 0.6 -> 0, 1.0 -> negative, but clamped
            t = clamp(t);
            return lerpColor(green, yellow, t);
        } else if (ratio >= 0.3f) {
            // yellow-ish
            float t = (0.6f - ratio) / 0.3f; // 0.6 -> 0, 0.3 -> 1
            t = clamp(t);
            return lerpColor(yellow, red, t * 0.2f); // only a little reddening here
        } else {
            // low -> go to red quickly
            float t = ratio / 0.3f; // 0.3 -> 1, 0 -> 0
            t = 1f - clamp(t);
            return lerpColor(yellow, red, t);
        }
    }

    //drawning the actual health bar
    public void draw(GraphicsHandler g) {
        // background
        Color bg = new Color(0, 0, 0, backgroundAlpha);
        g.drawFilledRectangle(x, y, width, height, bg);

        // compute fill width
        int barWidth = (int) (width * shownRatio);
        if (barWidth < 0) barWidth = 0;
        if (barWidth > width) barWidth = width;

        // pick color based on actual health, not shown health
        float realRatio = getSafeRatio();
        Color fillColor = getHealthColor(realRatio);

        // draw fill
        g.drawFilledRectangle(x, y, barWidth, height, fillColor);

        // border
        g.drawRectangle(x, y, width, height, Color.BLACK, borderThickness);

        // flash overlay
        if (damageFlash) {
            g.drawFilledRectangle(x, y, width, height, new Color(255, 255, 255, 70));
        }
    }


    private float getSafeRatio(){
        int max = provider.getMaxHealth(); 
        if(max<=0) return 0f;
        return Math.max(0f, Math.min(1f, provider.getCurrentHealth()/ (float) max)); 
    }

    public void setProvider(HealthProvider provider){
        this.provider = provider; 
        //should fix bug of tracking old player object after death by changing ratio back
        this.shownRatio = getSafeRatio();
    }


}
