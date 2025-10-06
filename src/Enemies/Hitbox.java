package Enemies;

import java.awt.Color;
import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.LevelState;
import Level.MapEntity;
import Level.Player;
import Level.PlayerListener;
import Screens.LobbyScreen;
import Screens.PlayLevelScreen;

import java.util.HashMap;

// This is the class for the Cat player character
// basically just sets some values for physics and then defines animations

// Need to rename to Cardan

public class Hitbox extends Player {

    private static float xHitbox = 10;
    private static float yHitbox = 12;
    private static int wHitbox = 48;
    private static int hHitbox = 42;
    private Player player;

    public Hitbox(float x, float y, Player player) {
        super(new SpriteSheet(ImageLoader.load("Attack.png"), 210, 70), x - 70, y-100, "STAND_RIGHT");
        gravity = 1f;
        terminalVelocityY = 6f;
        jumpHeight = 14.5f;
        jumpDegrade = .5f;
        walkSpeed = 4.3f;
        momentumYIncrease = 0.5f;
        this.player = player;

        System.out.println("h");
    }

    public void update() {

        this.x = player.getX() - 70;
        this.y = player.getY();  

        super.update();
    }

    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        drawBounds(graphicsHandler, new Color(255, 0, 0, 170));
    }


@Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{

            put("ATTACK_RIGHT_SPIN", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 7)
                            .withScale(1)
                            .withBounds(xHitbox, yHitbox, wHitbox + 20, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 7)
                            .withScale(1)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 7)
                            .withScale(1)
                            .withBounds(xHitbox + 120, yHitbox, wHitbox + 20, hHitbox)
                            .build()
            });

                        put("ATTACK_LEFT_SPIN", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 7)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox + 120, yHitbox, wHitbox + 20, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 7)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 7)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox, yHitbox, wHitbox + 20, hHitbox)
                            .build()
            });

            put("STAND_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0))
                            .withScale(1)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build()
            });

            put("STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0))
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build()
            });

            put("WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build()
            });

            put("WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build()
            });

            put("JUMP_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build()
            });

            put("JUMP_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build()
            });

            put("FALL_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build()
            });

            put("FALL_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build()
            });

            put("CROUCH_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0))
                            .withScale(1)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build()
            });

            put("CROUCH_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0))
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build()
            });

            put("DEATH_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 8)
                            .withScale(1)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 8)
                            .withScale(1)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), -1)
                            .withScale(1)
                            .build()
            });

            put("DEATH_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 8)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 8)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), -1)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });

            put("SWIM_STAND_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0))
                            .withScale(1)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build()
            });

            put("SWIM_STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0))
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox + 80, yHitbox, wHitbox / 2, hHitbox)
                            .build()
            });

            put("TAIL_ATTACK_DASH_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 12) 
                        .withScale(1)
                            .withBounds(xHitbox, yHitbox, wHitbox + 20, hHitbox)
                        .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 12) 
                        .withScale(1)
                            .withBounds(xHitbox + 120, yHitbox, wHitbox + 20, hHitbox)
                        .build()
            });

           put("TAIL_ATTACK_DASH_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 12)
                        .withScale(1)
                        .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox + 120, yHitbox, wHitbox + 20, hHitbox)
                        .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 12)
                        .withScale(1)
                        .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xHitbox, yHitbox, wHitbox + 20, hHitbox)
                        .build()
            });

        }};
}

}