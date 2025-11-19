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
import Maps.StartingCutsceneMap;
import Game.GameState;

public class OpeningCutScenceScreen extends Screen implements PlayerListener {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected Hitbox hitbox;
    protected OpeningCutScenceScreenState openingCutScenceScreenState;
    protected int screenTimer;
    protected LevelClearedScreen levelClearedScreen;
    protected LevelLoseScreen levelLoseScreen;
    protected boolean levelCompletedStateChangeStart;
    protected SpriteFont lives;
    protected SpriteFont openingCutSceneMessage;


    public OpeningCutScenceScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        map = new StartingCutsceneMap();
        // setup player
        this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.player.setMap(map);
        this.player.addListener(this);
        player.startOpeningScene();
        this.hitbox = new Hitbox(player.getLocation());
        map.addHitbox(this.hitbox);

        //levelLoseScreen = new LevelLoseScreen(this);
        this.lives = new SpriteFont("health: " + player.getHealth(), -1, 1, "Arial", 40, new Color(255, 0, 0));
        openingCutSceneMessage = new SpriteFont("I need to help save my friends", 100,250 ,"ARCADECLASSIC",40, Color.WHITE);
        openingCutSceneMessage.setOutlineColor(Color.BLACK);
        openingCutSceneMessage.setOutlineThickness(3);

        this.openingCutScenceScreenState = OpeningCutScenceScreenState.RUNNING;
    }

    @Override
    public void update() {
        switch (openingCutScenceScreenState) {
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

                 // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                    if (levelCompletedStateChangeStart) {
                        screenTimer = 130;
                        levelCompletedStateChangeStart = false;
                    } else {
                        levelClearedScreen.update();
                        screenTimer--;
                        if (screenTimer == 0) {
                            GoToTutorial();
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
        switch (openingCutScenceScreenState) {
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

        openingCutSceneMessage.setText("I need to help save my friends");
        if(player.getDisplayOpeningText()){
            openingCutSceneMessage.draw(graphicsHandler);
        }

    }


    public OpeningCutScenceScreenState getOpeningCutScenceScreenState() {
        return openingCutScenceScreenState;
    }

    private enum OpeningCutScenceScreenState {
        RUNNING,
        LEVEL_COMPLETED,
        LEVEL_LOSE
    }

    @Override
    public void onLevelCompleted() {
        if (openingCutScenceScreenState != OpeningCutScenceScreenState.LEVEL_COMPLETED) {
            openingCutScenceScreenState = OpeningCutScenceScreenState.LEVEL_COMPLETED;
            levelCompletedStateChangeStart = true;
        }
    }

    @Override
    public void onDeath() {
        if (openingCutScenceScreenState != OpeningCutScenceScreenState.LEVEL_LOSE) {
            openingCutScenceScreenState = OpeningCutScenceScreenState.LEVEL_LOSE;
        }
    }

    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

     public void resetcheckTEST() {
            map = new StartingCutsceneMap();

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

            this.openingCutScenceScreenState = OpeningCutScenceScreenState.RUNNING;
            this.lives = new SpriteFont("health: " + player.getHealth(), -1, 1, "Arial", 40, new Color(255, 0, 0));
    }

     public void GoToTutorial(){
        screenCoordinator.setGameState(GameState.TUTORIAL);
    }

     @Override
     public void onOpeningCutsceneCompleted() {
        System.out.println("FIRE");
        screenCoordinator.setGameState(GameState.TUTORIAL);
     }
}
