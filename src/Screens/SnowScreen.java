package Screens;

import java.awt.Color;
import Engine.GraphicsHandler;
import Engine.Key;
import Engine.Keyboard;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Hitbox;
import Level.Map;
import Level.Player;
import Level.PlayerListener;
import Maps.SnowMap;
import Players.Cat;
import SpriteFont.SpriteFont;

// This class is for when the platformer game is actually being played
public class SnowScreen extends Screen implements PlayerListener {
    protected ScreenCoordinator screenCoordinator;
    protected static Map map;
    protected Player player;
    protected Hitbox hitbox;
    protected SnowScreenState playLevelScreenState;
    protected int screenTimer;
    protected LevelClearedScreen levelClearedScreen;
    protected LevelLoseScreen levelLoseScreen;
    protected boolean levelCompletedStateChangeStart;
    protected SpriteFont lives;

    // popup
    // --- Power-up display text ---
    private SpriteFont powerUpText;
    private SpriteFont powerUpTextLine2;
    private SpriteFont powerUpTexLine3;
    private SpriteFont powerUpTextLine4;
   
    private long powerUpTextStartTime;
    private boolean showPowerUpText = false;
    private final long POWERUP_TEXT_DURATION = 2000; // milliseconds


    public SnowScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // define/setup map
        map = new SnowMap();

        // System.out.print("Start");
        // setup player
        this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.player.setMap(map);
        this.player.addListener(this);
        this.hitbox = new Hitbox(player.getLocation());
        map.addHitbox(this.hitbox);

        levelClearedScreen = new LevelClearedScreen(false);
        levelLoseScreen = new LevelLoseScreen(this);

        this.playLevelScreenState = SnowScreenState.RUNNING;
        this.lives = new SpriteFont("health: " + player.getHealth(), -1, 1, "Arial", 40, new Color(255, 0, 0));

    }

    public void update() {
        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
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
                
                if (showPowerUpText && System.currentTimeMillis() - powerUpTextStartTime > POWERUP_TEXT_DURATION) {
                    showPowerUpText = false;
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
        switch (playLevelScreenState) {
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

       //powerup popup
        if (showPowerUpText && powerUpText != null) {
            powerUpText.draw(graphicsHandler);
        if (powerUpTextLine2 != null) {
            powerUpTextLine2.draw(graphicsHandler);
            }
        }

        

    }

    public SnowScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }

    @Override
    public void onLevelCompleted() {
        if (playLevelScreenState != SnowScreenState.LEVEL_COMPLETED) {
            playLevelScreenState = SnowScreenState.LEVEL_COMPLETED;
            levelCompletedStateChangeStart = true;
        }
    }

    @Override
    public void onDeath() {
        if (playLevelScreenState != SnowScreenState.LEVEL_LOSE) {
            playLevelScreenState = SnowScreenState.LEVEL_LOSE;
        }
    }

    public void resetLevel() {
        initialize();
    }

    public void resetToCheckpoint() {
        playLevelScreenState = SnowScreenState.RUNNING;
    }

    //this does what initialize but it works with checkpoint
    public void resetcheckTEST() {
            map = new SnowMap();

            System.out.print("Start again");
            // setup player
            /*
             * 
             * //this.player = new Cat(player.respawnPoint.x, player.respawnPoint.y);
                Commented this portion out because resetting the player instance
                resulted in a total reset of the power ups collected,
                resetting the location of the player works the same
                and maintains the users powerups status 
             */
            this.player.setLocation(player.respawnPoint.x,player.respawnPoint.y);
            this.player.setMap(map);
            this.player.addListener(this);
            this.hitbox = new Hitbox(player.getLocation());
            map.addHitbox(this.hitbox);

            levelClearedScreen = new LevelClearedScreen(false);
            levelLoseScreen = new LevelLoseScreen(this);

            this.playLevelScreenState = SnowScreenState.RUNNING;
            this.lives = new SpriteFont("health: " + player.getHealth(), -1, 1, "Arial", 40, new Color(255, 0, 0));
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

    public void GoBackToLobby(){
        screenCoordinator.setGameState(GameState.SNOWBOSS);
    }
    // method to show power-up text popup
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
                "Press W arrow again to double jump!",
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
        }else if (message.contains("Poison Ball")){
            powerUpText = new SpriteFont(
                message,
                300,  // X position (adjust to center for your resolution)
                150,  // Y position (near top of screen)
                "Arial",
                30,
                Color.MAGENTA
            );
            powerUpText.setOutlineColor(Color.BLACK);
            powerUpText.setOutlineThickness(3);

            powerUpTextLine2 = new SpriteFont(
                "Press O to shoot poison balls!",
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
        }else if (message.contains("Speed Boost")){
            powerUpText = new SpriteFont(
                message,
                300,  // X position (adjust to center for your resolution)
                150,  // Y position (near top of screen)
                "Arial",
                30,
                Color.YELLOW
            );
            powerUpText.setOutlineColor(Color.BLACK);
            powerUpText.setOutlineThickness(3);

            powerUpTextLine2 = new SpriteFont(
                "Speed boost automatically activated when available",
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
        }else if (message.contains("Fire Ball")){
            powerUpText = new SpriteFont(
                message,
                300,  // X position (adjust to center for your resolution)
                150,  // Y position (near top of screen)
                "Arial",
                30,
                Color.RED
            );
            powerUpText.setOutlineColor(Color.BLACK);
            powerUpText.setOutlineThickness(3);

            powerUpTextLine2 = new SpriteFont(
                "Press B to shoot fire balls",
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


    // This enum represents the different states this screen can be in
    private enum SnowScreenState {
        RUNNING, LEVEL_COMPLETED, LEVEL_LOSE
    }

    @Override
    public void onOpeningCutsceneCompleted() {
    }
}
