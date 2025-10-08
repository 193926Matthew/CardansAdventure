package Level;

import java.awt.Color;
import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Screens.LobbyScreen;
import Screens.PlayLevelScreen;
import Utils.Point;


import java.util.HashMap;

// This is the class for the Cat player character
// basically just sets some values for physics and then defines animations

// Need to rename to Cardan

public class Hitbox extends HitboxR {

    private static float xHitbox = 10;
    private static float yHitbox = 12;
    private static int wHitbox = 48;
    private static int hHitbox = 42;
    private static int attackDelay = 12;

    public Hitbox(Point location) {
        // super(new SpriteSheet(ImageLoader.load("Attack.png"), 210, 70), x - 70, y-100, "STAND_RIGHT");
        super(location.x - 70, location.y, new SpriteSheet(ImageLoader.load("Attack.png"), 210, 70), "ATTACK_RIGHT_SPIN");

        hitboxState = HitboxState.NOTHING;
        previousHitboxState = hitboxState;

        initialize();
    }

    public void update(Player player) { 
        handleHitboxState();
        handleHitboxAnimation();

        // System.out.println(currentAnimationName);

        super.update(player);
    }

    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        // drawBounds(graphicsHandler, new Color(255, 0, 0, 170));
    }


@Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{

            put("NOTHING_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), attackDelay)
                            .withScale(1)
                            .withBounds(xHitbox, yHitbox, wHitbox + 20, hHitbox)
                            .build(),
            });

            put("NOTHING_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), attackDelay)
                            .withScale(1)
                            .withBounds(xHitbox, yHitbox, wHitbox + 20, hHitbox)
                            .build(),
            });

            put("ATTACK_RIGHT_SPIN", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0), attackDelay)
                            .withScale(1)
                            .withBounds(xHitbox, yHitbox, wHitbox + 20, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), attackDelay)
                            .withScale(1)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 0), attackDelay)
                            .withScale(1)
                            .withBounds(xHitbox + 120, yHitbox, wHitbox + 20, hHitbox)
                            .build()
            });

                        put("ATTACK_LEFT_SPIN", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0), attackDelay)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox + 120, yHitbox, wHitbox + 20, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), attackDelay)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 0), attackDelay)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox, yHitbox, wHitbox + 20, hHitbox)
                            .build()
            });

            put("ATTACK_RIGHT_DASH", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0), attackDelay - 4) 
                        .withScale(1)
                            .withBounds(xHitbox, yHitbox, wHitbox + 20, hHitbox)
                        .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 0), attackDelay - 4) 
                        .withScale(1)
                            .withBounds(xHitbox + 120, yHitbox, wHitbox + 60, hHitbox)
                        .build()
            });

           put("ATTACK_LEFT_DASH", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0), attackDelay - 4)
                        .withScale(1)
                        .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox + 120, yHitbox, wHitbox + 20, hHitbox)
                        .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 0), attackDelay - 4)
                        .withScale(1)
                        .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox, yHitbox, wHitbox + 60, hHitbox)
                        .build()
            });

        }};
}

public boolean isRemoved() {
    if (existTimer == 0 || currentAnimationName != "ATTACK_LEFT_DASH" && currentAnimationName != "ATTACK_RIGHT_DASH" && currentAnimationName != "ATTACK_LEFT_SPIN" && currentAnimationName != "ATTACK_RIGHT_SPIN") {
        return true;
    } else {
        return false;
    }
}

}