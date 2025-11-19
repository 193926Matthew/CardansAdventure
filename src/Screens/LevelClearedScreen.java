package Screens;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Sprite;
import GameObject.SpriteSheet;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;
import Tilesets.CommonTileset;
import Utils.Colors;
import Utils.Point;

// This class is for the level cleared screen
public class LevelClearedScreen extends Map {
    
    protected Map background;
    private Sprite cat;
    protected SpriteFont cardansAdventuSpriteFont;
    private float rainbowColorHue = 0f;
    private int sinTimer = 0;
    private int titleBaseX = 50;
    private int titleBaseY = 125;
    private int bounceAmmount = 10;
    private double bounceSpeed = 0.1;
    private int bouncePhaseDelay = 10;
    private int pointerLocationX = 70;
    private int pointerLocationY = 205;
    private int x = 1;
    private int y = 1;
    private int i = 0;
    private int j = 0;
    SpriteSheet cardanSpriteSheet = new SpriteSheet(ImageLoader.load("Cardan.png"), 70, 70);

    public LevelClearedScreen() {

        super("end_level_map.txt", new CommonTileset());

        Point catLocation = getMapTile(7, 6).getLocation().subtractX(35).subtractY(8);
        cat = new Sprite(cardanSpriteSheet.getSprite(1, 0));
        cat.setScale(3);
        cat.setLocation(catLocation.x, catLocation.y);

        initialize();
    }

    public void update () {
        background.update(null);
        
        cat = new Sprite(cardanSpriteSheet.getSprite(1, j));
        
        cat.setScale(3);
        cat.setLocation(x, 310);
        x = x + 5;

        if (j == 3) {
            i = 0;
            j = 0;
        } else {
            i++;
            if (i == 7) {
                j++;
                i = 0;
            }
        }

        rainbowColorHue += 0.001f;
        if (rainbowColorHue > 1.0f) {
            rainbowColorHue = 0.0f;
        }  

        sinTimer++;
    }

    public void initialize() {
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        cardansAdventuSpriteFont = new SpriteFont("LEVEL CLEARED", 200, 75, "ARCADECLASSIC.ttf", 75, Color.getHSBColor(rainbowColorHue, 0.8f, 0.8f));
        cardansAdventuSpriteFont.setOutlineColor(Color.black);
        cardansAdventuSpriteFont.setOutlineThickness(3);    
    }

    public void draw(GraphicsHandler graphicsHandler) {
        if (this.background != null) {
            background.draw(graphicsHandler);
        }
        
        super.draw(graphicsHandler);

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
            graphicsHandler.getGraphics().drawString(String.valueOf(c), x + 80, titleBaseY + yOffset);

            //Make the characters evenly spaces
            x += 41;
        }

        

        // graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 20, 20, new Color(255, 184, 0), Color.black, 2);

        cat.draw(graphicsHandler);
    }
}

