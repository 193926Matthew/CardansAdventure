package Screens;

import Enemies.Hitbox;
import Engine.GraphicsHandler;

import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Enemy;
import Level.Map;
import Level.Player;
import Level.PlayerListener;
import Maps.LobbyMap;
import Players.Cat;

public class LobbyScreen extends Screen implements PlayerListener{
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected Hitbox hitbox;
    protected LobbyScreenState lobbyScreenState;
    protected int screenTimer;
    protected LevelClearedScreen levelClearedScreen;
    protected LevelLoseScreen levelLoseScreen;
    protected boolean levelCompletedStateChangeStart;


    public LobbyScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        this.map = new LobbyMap();

        // setup player
        this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.hitbox = new Hitbox(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y, player);
        this.player.setMap(map);
        this.hitbox.setMap(map);
        this.player.addListener(this);

        levelClearedScreen = new LevelClearedScreen();
        //levelLoseScreen = new LevelLoseScreen(this);

        this.lobbyScreenState = LobbyScreenState.RUNNING;
    }   

    public void update() {
                // based on screen state, perform specific actions
        switch (lobbyScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player, hitbox);
                hitbox.update();
                
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
                        goBackToMenu();
                    }
                }
                break;
            // wait on level lose screen to make a decision (either resets level or sends player back to main menu)
            case LEVEL_LOSE:
                levelLoseScreen.update();
                break;
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (lobbyScreenState) {
            case RUNNING:
                map.draw(graphicsHandler);
                player.draw(graphicsHandler);
                hitbox.draw(graphicsHandler);
                break;
            case LEVEL_COMPLETED:
                levelClearedScreen.draw(graphicsHandler);
                break;
            case LEVEL_LOSE:
                levelLoseScreen.draw(graphicsHandler);
                break;
        }
    }

        public LobbyScreenState getLobbyScreenState() {
        return lobbyScreenState;
    }

    @Override
    public void onLevelCompleted() {
        if (lobbyScreenState != LobbyScreenState.LEVEL_COMPLETED) {
            lobbyScreenState = LobbyScreenState.LEVEL_COMPLETED;
            levelCompletedStateChangeStart = true;
        }
    }

    @Override
    public void onDeath() {
        if (lobbyScreenState != LobbyScreenState.LEVEL_LOSE) {
            lobbyScreenState = LobbyScreenState.LEVEL_LOSE;
        }
    }

    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

    // This enum represents the different states this screen can be in
    private enum LobbyScreenState {
        RUNNING, LEVEL_COMPLETED, LEVEL_LOSE
    }


}
