package Players;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Player;

import java.awt.Color;
import java.util.HashMap;

// This is the class for the Cat player character
// basically just sets some values for physics and then defines animations

// Need to rename to Cardan

public class Cat extends Player {
        private static float xCardan = 10;
        private static float yCardan = 8;
        private static int wCardan = 48;
        private static int hCardan = 48;
        private static int attackDelay = 12;
        private static int iceDelay = 3;
        private static int idleDelay = 1;
        // private BufferedImage attackOverlay;

    public Cat(float x, float y) {
        super(new SpriteSheet(ImageLoader.load("Cardan.png"), 70, 70), x, y-100, "STAND_RIGHT");
        gravity = 1f;
        terminalVelocityY = 6f;
        jumpHeight = 14.5f;
        jumpDegrade = .5f;
        walkSpeed = 4.3f;
        momentumYIncrease = .5f;

    }

    public void update() {
        
        super.update();
    }

        @Override
        public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        // drawBounds(graphicsHandler, new Color(255, 0, 0, 170));
        }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{

            put("ATTACK_RIGHT_SPIN", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(7, 2), attackDelay)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(7, 1), attackDelay)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(7, 0), attackDelay)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build()
            });

                        put("ATTACK_LEFT_SPIN", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(7, 0), attackDelay)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(7, 1), attackDelay)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(7, 2), attackDelay)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build()
            });

                put("ICE_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(4, 3), iceDelay)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(4, 2), iceDelay)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(4, 1), iceDelay)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build()
            });

                                    put("ICE_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(4, 3), iceDelay)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(4, 2), iceDelay)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(4, 1), iceDelay)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build()
            });

            put("STAND_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), idleDelay + 30)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), idleDelay)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), idleDelay)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), idleDelay + 7)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), idleDelay)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), idleDelay)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build()
            });

            put("STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), idleDelay + 30)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                                                new FrameBuilder(spriteSheet.getSprite(0, 1), idleDelay)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                                                new FrameBuilder(spriteSheet.getSprite(0, 2), idleDelay)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), idleDelay + 7)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 2), idleDelay)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 1), idleDelay)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build()
            });

            put("WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 3), 14)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build()
            });

            put("WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 3), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build()
            });

            put("JUMP_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 14)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 2), 14)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 3), 14)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build()
            });

            put("JUMP_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 2), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 3), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build()
            });

            put("FALL_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(3, 0), 14)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 1), 14)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 2), 14)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 3), 14)
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build()
            });

            put("FALL_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(3, 0), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 1), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 2), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 3), 14)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build()
            });

            put("CROUCH_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(4, 0))
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build()
            });

            put("CROUCH_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(4, 0))
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build()
            });

            put("DEATH_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(5, 0), 8)
                            .withScale(1)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(5, 1), 8)
                            .withScale(1)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(5, 2), -1)
                            .withScale(1)
                            .build()
            });

            put("DEATH_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(5, 0), 8)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(5, 1), 8)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(5, 2), -1)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });

            put("SWIM_STAND_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(6, 0))
                            .withScale(1)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build()
            });

            put("SWIM_STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(6, 0))
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(xCardan, yCardan, wCardan, hCardan)
                            .build()
            });

            put("TAIL_ATTACK_DASH_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(7, 0), attackDelay)
                        .withScale(1)
                        .withBounds(xCardan, yCardan, wCardan, hCardan)
                        .build(),
                    new FrameBuilder(spriteSheet.getSprite(7, 2), attackDelay)
                        .withScale(1)
                        .withBounds(xCardan, yCardan, wCardan, hCardan)
                        .build()
            });

           put("TAIL_ATTACK_DASH_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(7, 0), attackDelay)
                        .withScale(1)
                        .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                        .withBounds(xCardan, yCardan, wCardan, hCardan)
                        .build(),
                    new FrameBuilder(spriteSheet.getSprite(7, 2), attackDelay)
                        .withScale(1)
                        .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                        .withBounds(xCardan, yCardan, wCardan, hCardan)
                        .build()
            });

        }};
        
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public float getGravity() {
        return gravity;
    }  
}
