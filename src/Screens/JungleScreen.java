package Screens;

import Engine.Screen;
import EnhancedMapTiles.BackToLobby;
import EnhancedMapTiles.JungleEnter;
import EnhancedMapTiles.SnowEnter;

import java.awt.Color;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.Keyboard;
import Game.ScreenCoordinator;
import Level.EnhancedMapTile;
import Level.Hitbox;
import Level.Map;
import Level.Player;
import Level.PlayerListener;
import Maps.DesertMap;
import Maps.JungleMap;
import Players.Cat;
import SpriteFont.SpriteFont;
import Maps.SnowMap;
import Game.GameState;

public class JungleScreen extends Screen implements PlayerListener {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected Hitbox hitbox;
    protected JungleScreenState jungleScreenState;
    protected int screenTimer;
    protected LevelClearedScreen levelClearedScreen;
    protected LevelLoseScreen levelLoseScreen;
    protected boolean levelCompletedStateChangeStart;
    protected SpriteFont lives;


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
        this.hitbox = new Hitbox(player.getLocation());
        map.addHitbox(this.hitbox);

        levelClearedScreen = new LevelClearedScreen();
        levelLoseScreen = new LevelLoseScreen(this);
        //levelLoseScreen = new LevelLoseScreen(this);
         this.lives = new SpriteFont("health: " + player.getHealth(), -1, 1, "Arial", 40, new Color(255, 0, 0));

        this.jungleScreenState = JungleScreenState.RUNNING;
    }

    @Override
    public void update() {
        switch (jungleScreenState) {
            case RUNNING:
                player.update();
                map.update(player);
                                
                    if (hitbox == null) {
                        hitbox = new Hitbox(player.getLocation());
                        map.addHitbox(hitbox);
                    }
                

                if (hitbox != null) {
                    hitbox.update(player);

                    if (hitbox.isRemoved()) {
                        hitbox = null;
                    }
                }

                for (EnhancedMapTile tile : map.getEnhancedMapTiles()) {
                    if (tile instanceof BackToLobby) {
                        BackToLobby lobby = (BackToLobby) tile;
                        if (lobby.getTriggerCode() == 3) {
                            screenCoordinator.setGameState(GameState.LOBBY);
                        }
                    }
                }

                break;  
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        switch (jungleScreenState) {
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

     public void resetcheckTEST() {
            map = new JungleMap();

            System.out.print("Start again");
            // setup player
            /* 
            Commented this portion out because resetting the player instance
                resulted in a total reset of the power ups collected,
                resetting the location of the player works the same
                and maintains the users powerups status 
             this.player = new Cat(player.respawnPoint.x, player.respawnPoint.y);
                
             */
            this.player.setLocation(player.respawnPoint.x, player.respawnPoint.y);
            this.player.setMap(map);
            this.player.addListener(this);
            this.hitbox = new Hitbox(player.getLocation());
            map.addHitbox(this.hitbox);

            levelClearedScreen = new LevelClearedScreen();
            levelLoseScreen = new LevelLoseScreen(this);

            this.jungleScreenState = jungleScreenState.RUNNING;
            this.lives = new SpriteFont("health: " + player.getHealth(), -1, 1, "Arial", 40, new Color(255, 0, 0));
    }

     public void GoBackToLobby(){
        screenCoordinator.setGameState(GameState.LOBBY);
    }

     @Override
     public void onOpeningCutsceneCompleted() {
     }
}
