package Screens;

import Engine.Screen;
import EnhancedMapTiles.BackToLobby;
import Engine.GraphicsHandler;
import Game.ScreenCoordinator;
import Level.EnhancedMapTile;
import Level.Map;
import Level.Player;
import Level.PlayerListener;
import Maps.JungleMap;
import Players.Cat;
import Game.GameState;

public class JungleScreen extends Screen implements PlayerListener {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected JungleScreenState jungleScreenState;
    protected int screenTimer;
    protected LevelClearedScreen levelClearedScreen;
    protected LevelLoseScreen levelLoseScreen;
    protected boolean levelCompletedStateChangeStart;

    public JungleScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        map = new JungleMap();

        // setup player
        this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.player.setMap(map);
        this.player.addListener(this);

        levelClearedScreen = new LevelClearedScreen();
        //levelLoseScreen = new LevelLoseScreen(this);

        this.jungleScreenState = JungleScreenState.RUNNING;
    }

    @Override
    public void update() {
        switch (jungleScreenState) {
            case RUNNING:
                player.update();
                map.update(player);

                for (EnhancedMapTile tile : map.getEnhancedMapTiles()){
                    BackToLobby backToLobby = (BackToLobby) tile;   
                    if (backToLobby.getTriggerCode() == 3) {
                        screenCoordinator.setGameState(GameState.LOBBY);
                    }
                }
                break;
        
            default:
                break;
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        switch (jungleScreenState) {
            case RUNNING:
                map.draw(graphicsHandler);
                player.draw(graphicsHandler);
                break;
            case LEVEL_COMPLETED:
                levelClearedScreen.draw(graphicsHandler);
                break;
            case LEVEL_LOSE:
                levelLoseScreen.draw(graphicsHandler);
                break;
        }
    }

    public JungleScreenState getJungleScreenState() {
        return jungleScreenState;
    }

    private enum JungleScreenState {
        RUNNING,
        LEVEL_COMPLETED,
        LEVEL_LOSE
    }

    @Override
    public void onLevelCompleted() {
        if (jungleScreenState != JungleScreenState.LEVEL_COMPLETED) {
            jungleScreenState = JungleScreenState.LEVEL_COMPLETED;
            levelCompletedStateChangeStart = true;
        }
    }

    @Override
    public void onDeath() {
        if (jungleScreenState != JungleScreenState.LEVEL_LOSE) {
            jungleScreenState = JungleScreenState.LEVEL_LOSE;
        }
    }

    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

}
