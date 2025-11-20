package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;

import java.awt.*;

// This class is for the credits screen
public class CreditsScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map background;
    protected KeyLocker keyLocker = new KeyLocker();
    protected SpriteFont creditsLabel;
    protected SpriteFont createdByLabelAlex;
    protected SpriteFont createdByLabelHatters;
    protected SpriteFont createdByLabelBrian;
    protected SpriteFont createdByLabelBrody;
    protected SpriteFont createdByLabelHannah;
    protected SpriteFont createdByLabelMatthew;
    protected SpriteFont createdByLabelMia;
    protected SpriteFont returnInstructionsLabel;

    public CreditsScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        // setup graphics on screen (background map, spritefont text)
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        creditsLabel = new SpriteFont("Credits", 15, 7, "Yoster", 30, Color.white);
        createdByLabelAlex = new SpriteFont("Original Code by Alex Thimineur", 130, 121, "Times New Roman", 20, Color.white);
        createdByLabelHatters = new SpriteFont("Cardan's Adventure by Matt Hatters:", 130, 161, "Times New Roman", 20, Color.white);
        createdByLabelBrian = new SpriteFont("     Brian Palchizaca", 130, 201, "Times New Roman", 20, Color.white);
        createdByLabelBrody = new SpriteFont("     Brody Chevrier", 130, 241, "Times New Roman", 20, Color.white);
        createdByLabelHannah = new SpriteFont("     Hannah Willetts", 130, 281, "Times New Roman", 20, Color.white);
        createdByLabelMatthew = new SpriteFont("     Matthew Jones", 130, 321, "Times New Roman", 20, Color.white);
        createdByLabelMia = new SpriteFont("     Mia Hernandez", 130, 361, "Times New Roman", 20, Color.white);
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
        creditsLabel.draw(graphicsHandler);
        createdByLabelAlex.draw(graphicsHandler);
        createdByLabelHatters.draw(graphicsHandler);
        createdByLabelBrian.draw(graphicsHandler);
        createdByLabelBrody.draw(graphicsHandler);
        createdByLabelHannah.draw(graphicsHandler);
        createdByLabelMatthew.draw(graphicsHandler);
        createdByLabelMia.draw(graphicsHandler);
        returnInstructionsLabel.draw(graphicsHandler);
    }
}
