package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;
import Maps.JungleMap;

import java.awt.*;

// This class is for the controls screen
public class ControlsScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map background;
    protected KeyLocker keyLocker = new KeyLocker();
    protected SpriteFont controlsLabel;
    protected SpriteFont movement;
    protected SpriteFont movementW;
    protected SpriteFont movementA;
    protected SpriteFont movementS;
    protected SpriteFont movementD;



    protected SpriteFont attack;
    protected SpriteFont tailSpinAttack;
    protected SpriteFont tailAttack;
    protected SpriteFont icePowerUp;
    protected SpriteFont doubleJumpPowerUp;
    protected SpriteFont returnInstructionsLabel;

    public ControlsScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        // setup graphics on screen (background map, spritefont text)
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        controlsLabel = new SpriteFont("Controls for Game", 125,0,"Times New Roman", 75, Color.blue);
        movement = new SpriteFont("Game Movement", 0,100,"Times New Roman", 30, Color.blue);
        movementW = new SpriteFont("jump: w key", 10,150,"Times New Roman", 20, Color.blue);
        movementA = new SpriteFont("walk-left: a key", 10,200,"Times New Roman", 20, Color.blue);
        movementS = new SpriteFont("crouch: s key", 10,250,"Times New Roman", 20, Color.blue);
        movementD = new SpriteFont("walk-right: d key", 10,300,"Times New Roman", 20, Color.blue);

        attack = new SpriteFont("Attacks", 480,100,"Times New Roman", 30, Color.blue);
        tailAttack = new SpriteFont("Tail Attack: right arrow key", 360,150,"Times New Roman", 20, Color.blue);
        tailSpinAttack = new SpriteFont("Tail spin attack: left arrow key", 360,200,"Times New Roman", 20, Color.blue);
        icePowerUp = new SpriteFont("IceBall attack power-up(when acquired): i-key", 360, 250, "Times New Roman", 20, Color.blue);
        doubleJumpPowerUp = new SpriteFont("Double-Jump power-up(when-acquired): press w key twice", 10,1350,"Times New Roman", 20, Color.blue);

        returnInstructionsLabel = new SpriteFont("Press Space to return to the menu", 20, 532, "Times New Roman", 30, Color.white);
        keyLocker.lockKey(Key.SPACE);
    }

    public void update() {
        background.update(null);

        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }

        // if space is pressed, go back to main menu
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            screenCoordinator.setGameState(GameState.MENU);
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        movement.draw(graphicsHandler);
        movementW.draw(graphicsHandler);
        movementA.draw(graphicsHandler);
        movementS.draw(graphicsHandler);
        movementD.draw(graphicsHandler);

        attack.draw(graphicsHandler);
        tailAttack.draw(graphicsHandler);
        tailSpinAttack.draw(graphicsHandler);
        icePowerUp.draw(graphicsHandler);
        doubleJumpPowerUp.draw(graphicsHandler);

        controlsLabel.draw(graphicsHandler);
        returnInstructionsLabel.draw(graphicsHandler);
    }
}
