package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;

import java.awt.*;
import java.io.File;

// This is the class for the main menu screen
public class MenuScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected int currentMenuItemHovered = 0; // current menu item being "hovered" over
    protected int menuItemSelected = -1;
    protected SpriteFont playGame;
    protected SpriteFont credits;
    protected SpriteFont controls;
    protected SpriteFont cardansAdventuSpriteFont;
    protected Map background;
    protected int keyPressTimer;
    protected int pointerLocationX, pointerLocationY;
    private float rainbowColorHue = 0f;
    private int sinTimer = 0;
    private int titleBaseX = 50;
    private int titleBaseY = 125;
    private int bounceAmmount = 10;
    private double bounceSpeed = 0.1;
    private int bouncePhaseDelay = 10;
    public KeyLocker keyLocker = new KeyLocker();
    Font videoGameFont;

    public MenuScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        playGame = new SpriteFont("PLAY GAME", 100, 200, "ARCADECLASSIC.ttf", 30, new Color(49, 207, 240));
        playGame.setOutlineColor(Color.black);
        playGame.setOutlineThickness(3);
        credits = new SpriteFont("CREDITS", 100, 300, "ARCADECLASSIC.ttf", 30, new Color(49, 207, 240));
        credits.setOutlineColor(Color.black);
        credits.setOutlineThickness(3);
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        cardansAdventuSpriteFont = new SpriteFont("CARDANS ADVENTURE", 80, 75, "ARCADECLASSIC.ttf", 75, Color.getHSBColor(rainbowColorHue, 0.8f, 0.8f));
        cardansAdventuSpriteFont.setOutlineColor(Color.black);
        cardansAdventuSpriteFont.setOutlineThickness(3);
        controls = new SpriteFont("CONTROLS", 100, 400, "ARCADECLASSIC.ttf", 30,  new Color(49, 207, 240));
        controls.setOutlineColor(Color.black);
        controls.setOutlineThickness(3);
        keyPressTimer = 0;
        menuItemSelected = -1;
        keyLocker.lockKey(Key.SPACE);
    }

    public void update() {
        // update background map (to play tile animations)
        background.update(null);

        // if down or up is pressed, change menu item "hovered" over (blue square in front of text will move along with currentMenuItemHovered changing)
        if (Keyboard.isKeyDown(Key.DOWN) &&  keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered++;
        } else if (Keyboard.isKeyDown(Key.UP) &&  keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered--;
        } else {
            if (keyPressTimer > 0) {
                keyPressTimer--;
            }
        }

        // if down is pressed on last menu item or up is pressed on first menu item, "loop" the selection back around to the beginning/end
        if (currentMenuItemHovered > 2) {
            currentMenuItemHovered = 0;
        } else if (currentMenuItemHovered < 1) {
            currentMenuItemHovered = 0;
        }

        // sets location for blue square in front of text (pointerLocation) and also sets color of spritefont text based on which menu item is being hovered
        if (currentMenuItemHovered == 0) {
            playGame.setColor(new Color(255, 184, 0));
            credits.setColor(new Color(34, 225, 0));
            controls.setColor(new Color(34, 225, 0));
            pointerLocationX = 70;
            pointerLocationY = 205;
        } else if (currentMenuItemHovered == 1) {
            playGame.setColor(new Color(34, 225, 0));
            credits.setColor(new Color(255, 184, 0));
            controls.setColor(new Color(34, 225, 0));
            pointerLocationX = 70;
            pointerLocationY = 305;
        }else if(currentMenuItemHovered == 2){
            playGame.setColor(new Color(34, 225, 0));
            controls.setColor(new Color(255, 184, 0));
            credits.setColor(new Color(34, 225, 0));
            pointerLocationX = 70;
            pointerLocationY = 405;
        }

        // if space is pressed on menu item, change to appropriate screen based on which menu item was chosen
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            menuItemSelected = currentMenuItemHovered;
            if (menuItemSelected == 0) {
                screenCoordinator.setGameState(GameState.OPENING);
            } else if (menuItemSelected == 1) {
                screenCoordinator.setGameState(GameState.CREDITS);
            }else if(menuItemSelected == 2){
                screenCoordinator.setGameState(GameState.CONTROLS);
            }
            
        }

        rainbowColorHue += 0.001f;
        if (rainbowColorHue > 1.0f) {
            rainbowColorHue = 0.0f;
        }  

        sinTimer++;
    }

    public void draw(GraphicsHandler graphicsHandler) {
        if (this.background != null) {
            background.draw(graphicsHandler);
        }
        playGame.draw(graphicsHandler);
        credits.draw(graphicsHandler);
        controls.draw(graphicsHandler);
        //cardansAdventuSpriteFont.draw(graphicsHandler);

        String titleText = cardansAdventuSpriteFont.getText();
        Font titleFont = cardansAdventuSpriteFont.getFont();
        Color titleColor =  Color.getHSBColor(rainbowColorHue, 1.0f, 1.0f);

        graphicsHandler.getGraphics().setFont(titleFont);
        graphicsHandler.getGraphics().setColor(titleColor);

        int x = titleBaseX;

        for (int i = 0; i < titleText.length(); i++) {
            //gets the individual char from the string
            char c = titleText.charAt(i);

            //Calculates the y value of each character using a sin wave
            //used https://stackoverflow.com/questions/8342887/how-do-you-move-an-object-in-a-wavy-pattern to help with this
            int yOffset = (int)(Math.sin((sinTimer + i * bouncePhaseDelay) * bounceSpeed) * bounceAmmount);
    
            // Draw each letter
            graphicsHandler.getGraphics().drawString(String.valueOf(c), x, titleBaseY + yOffset);

            //Make the characters evenly spaces
            x += 41;
        }

        

        graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 20, 20, new Color(255, 184, 0), Color.black, 2);
    }
}
