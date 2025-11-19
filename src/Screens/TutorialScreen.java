package Screens;
import Engine.Screen;
import EnhancedMapTiles.JungleEnter;
import EnhancedMapTiles.SnowEnter;
import Engine.GraphicsHandler;
import Engine.Key;
import Engine.Keyboard;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.PlayerListener;
import Level.EnhancedMapTile;
import Level.Hitbox;
import Level.Map;
import Level.Player;
import Maps.TutorialMap;
import Players.Cat;
import SpriteFont.SpriteFont;
import java.awt.Color;
import Utils.Point;


public class TutorialScreen extends Screen implements PlayerListener {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected Hitbox hitbox;
    protected TutorialScreenState tutorialscreenState;
    protected int screenTimer;
    protected LevelClearedScreen levelClearedScreen;
    protected LevelLoseScreen levelLoseScreen;
    protected boolean levelCompletedStateChangeStart;
    protected SpriteFont lives;

    public TutorialScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        map = new Maps.TutorialMap();
        this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.player.setMap(map);
        this.player.addListener(this);
        this.hitbox = new Hitbox(player.getLocation());
        map.addHitbox(this.hitbox);

        levelClearedScreen = new LevelClearedScreen();
        //levelLoseScreen = new LevelLoseScreen(this);

        this.tutorialscreenState = TutorialScreenState.RUNNING;
        this.lives = new SpriteFont("health: " + player.getHealth(), -1, 1, "Arial", 40, new Color(255, 0, 0));

    }

    @Override
    public void update() {
        switch (tutorialscreenState) {
            // if level is "running" update player and map to keep game logic for the
            // platformer level going
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
                

                // code for the level enter tiles. have for jungle and snow
                // if want to add more, just add a new enhancedtile and copy from the other
                // tiles
                break;
            // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                if (levelCompletedStateChangeStart) {
                    screenTimer = 130;
                    levelCompletedStateChangeStart = false;
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
                levelLoseScreen.update();
                break;
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        switch (tutorialscreenState) {
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
        if (tutorialscreenState != TutorialScreenState.LEVEL_COMPLETED) {
            tutorialscreenState = TutorialScreenState.LEVEL_COMPLETED;
            levelCompletedStateChangeStart = true;
        }
    }

    @Override
    public void onDeath() {
        if (tutorialscreenState != TutorialScreenState.LEVEL_LOSE) {
            tutorialscreenState = TutorialScreenState.LEVEL_LOSE;
        }
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

    public void GoBackToLobby(){
        screenCoordinator.setGameState(GameState.LOBBY);
    }

    private enum TutorialScreenState {
        RUNNING, LEVEL_COMPLETED, LEVEL_LOSE
    }

    @Override
    public void onOpeningCutsceneCompleted() {
    }
}
