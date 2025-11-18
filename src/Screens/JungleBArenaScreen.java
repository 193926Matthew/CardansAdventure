package Screens;

import Engine.Screen;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.Keyboard;
import Game.ScreenCoordinator;

import Level.Hitbox;
import Level.Map;
import Level.Player;
import Level.PlayerListener;
import Maps.JungleBossArena;

import Players.Cat;
import SpriteFont.SpriteFont;
import Maps.SnowMap;
import Game.GameState;
import java.awt.Color;


public class JungleBArenaScreen extends Screen implements PlayerListener{
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected Hitbox hitbox;
    protected JBAScreenState JBAscreenstate;
    protected int screenTimer;
    protected LevelClearedScreen levelClearedScreen;
    protected LevelLoseScreen levelLoseScreen;
    protected boolean levelCompletedStateChangeStart;
    protected SpriteFont lives;

    public JungleBArenaScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

        @Override
    public void initialize() {
        map = new JungleBossArena();

        // setup player
        this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.player.setMap(map);
        this.player.addListener(this);
        this.hitbox = new Hitbox(player.getLocation());
        map.addHitbox(this.hitbox);

        levelClearedScreen = new LevelClearedScreen();
        levelLoseScreen = new LevelLoseScreen(this);

        this.JBAscreenstate = JBAScreenState.RUNNING;
        this.lives = new SpriteFont("health: " + player.getHealth(), -1, 1, "Arial", 40, new Color(255, 0, 0));
    }

    public void update() {
        // based on screen state, perform specific actions
        switch (JBAscreenstate) {
            case RUNNING:
                player.update();
                map.update(player);
                if (Keyboard.isKeyDown(Key.LEFT) || Keyboard.isKeyDown(Key.RIGHT)) {
                    if (hitbox == null) {
                        hitbox = new Hitbox(player.getLocation());
                        map.addHitbox(hitbox);
                    }
                }

                if (hitbox != null) {
                    hitbox.update(player);

                    if (hitbox.isRemoved()) {
                        hitbox = null;
                    }
                }
                break;
            // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                
                if (levelCompletedStateChangeStart) {
                    screenTimer = 130;
                    levelCompletedStateChangeStart = false;
                    levelClearedScreen.update();
                } else {
                    levelClearedScreen.update();
                    screenTimer--;
                    if (screenTimer == 0) {
                        GoBackToLobby();
                    }
                }
                break;
            // wait on level lose screen to make a decision (either resets level or sends
            // player back to main menu)
            case LEVEL_LOSE:
                resetcheckTEST();
            break;
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (JBAscreenstate) {
            case RUNNING:
                map.draw(graphicsHandler);
                player.draw(graphicsHandler);
                if (hitbox != null) {
                    hitbox.draw(graphicsHandler);
                }
                break;
            case LEVEL_COMPLETED:
                levelClearedScreen.draw(graphicsHandler);
                break;
            case LEVEL_LOSE:
                levelLoseScreen.draw(graphicsHandler);
                break;
        }
        lives.setText("Health: " + player.getHealth());
        lives.draw(graphicsHandler);

    }


    @Override
    public void onLevelCompleted() {
        if (JBAscreenstate != JBAScreenState.LEVEL_COMPLETED) {
            JBAscreenstate = JBAScreenState.LEVEL_COMPLETED;
            levelCompletedStateChangeStart = true;
        }
    }

    @Override
    public void onDeath() {
        if (JBAscreenstate != JBAScreenState.LEVEL_LOSE) {
            JBAscreenstate = JBAScreenState.LEVEL_LOSE;
        }
    }

        public void resetLevel() {
        initialize();
    }

    public void resetToCheckpoint() {
        JBAscreenstate = JBAScreenState.RUNNING;
    }

    //this does what initialize but it works with checkpoint
    public void resetcheckTEST() {
            map = new JungleBossArena();

            System.out.print("Start again");
            // setup player
            this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
            this.player.setMap(map);
            this.player.addListener(this);
            this.hitbox = new Hitbox(player.getLocation());
            map.addHitbox(this.hitbox);

            levelClearedScreen = new LevelClearedScreen();
            levelLoseScreen = new LevelLoseScreen(this);

            this.JBAscreenstate = JBAscreenstate.RUNNING;
            this.lives = new SpriteFont("health: " + player.getHealth(), -1, 1, "Arial", 40, new Color(255, 0, 0));
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

    public void GoBackToLobby(){
        screenCoordinator.setGameState(GameState.LOBBY);
    }

    // method to show power-up text popup
    /* 
    public void showPowerUpText(String message) {
        if (message.contains("Double Jump")){
            powerUpText = new SpriteFont(
                message,
                300,  
                100,  
                "Arial",
                30,
                Color.ORANGE
            );
            powerUpText.setOutlineColor(Color.BLACK);
            powerUpText.setOutlineThickness(3);

            // SECOND LINE
            powerUpTextLine2 = new SpriteFont(
                "Press UP arrow again to double jump!",
                300,
                140,  
                "Arial",
                20,
                Color.WHITE
            );
            powerUpTextLine2.setOutlineColor(Color.BLACK);
            powerUpTextLine2.setOutlineThickness(2);

            powerUpTextStartTime = System.currentTimeMillis();
            showPowerUpText = true;
            
        } else if (message.contains("Ice Ball")){
            powerUpText = new SpriteFont(
                message,
                300,  // X position (adjust to center for your resolution)
                150,  // Y position (near top of screen)
                "Arial",
                30,
                Color.CYAN
            );
            powerUpText.setOutlineColor(Color.BLACK);
            powerUpText.setOutlineThickness(3);

            powerUpTextLine2 = new SpriteFont(
                "Press I to shoot ice balls!",
                300,
                190,
                "Arial",
                20,
                Color.WHITE
            );
            powerUpTextLine2.setOutlineColor(Color.BLACK);
            powerUpTextLine2.setOutlineThickness(2);

            powerUpTextStartTime = System.currentTimeMillis();
            showPowerUpText = true;
        }   
    }
    */

    public JBAScreenState getJBAScreenState() {
        return JBAscreenstate;
    }


    private enum JBAScreenState {
        RUNNING,
        LEVEL_COMPLETED,
        LEVEL_LOSE
    }
}   
