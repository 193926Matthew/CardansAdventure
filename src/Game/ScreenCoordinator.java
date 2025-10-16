package Game;

import Screens.CreditsScreen;
import Screens.JungleScreen;
//import Screens.JungleScreen;
import Screens.LobbyScreen;
import Screens.MenuScreen;
import Screens.PlayLevelScreen;

import Engine.*;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;

/*
 * Based on the current game state, this class determines which Screen should be shown
 * There can only be one "currentScreen", although screens can have "nested" screens
 */
public class ScreenCoordinator extends Screen {

	    public KeyLocker keyLocker = new KeyLocker();
        protected ScreenCoordinator screenCoordinator;
		int i = 0;
	// currently shown Screen
	protected Screen currentScreen = new DefaultScreen();

	// keep track of gameState so ScreenCoordinator knows which Screen to show
	public GameState gameState;
	public static GameState currentGameState;
	protected GameState previousGameState;

	public GameState getGameState() {
		currentGameState = gameState;
		return gameState;
	}

	public static GameState returnCurrentGameState(){
		return currentGameState;
	}

	// Other Screens can set the gameState of this class to force it to change the currentScreen
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
		currentGameState = gameState;
	}

	@Override
	public void initialize() {
		// start game off with Menu Screen
		gameState = GameState.MENU;

		/* 
		if (i == 0) {
			gameState = GameState.MENU;
			i = i + 1;
		} else {
			gameState = GameState.LOBBY;
		}
		*/
        
	}

	@Override
	public void update() {

	/* 
	System.out.println(gameState);
	if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
        initialize();
    }
	*/
		do {
			// if previousGameState does not equal gameState, it means there was a change in gameState
			// this triggers ScreenCoordinator to bring up a new Screen based on what the gameState is
			if (previousGameState != gameState) {
				switch(gameState) {
					case MENU:
						currentScreen = new MenuScreen(this);
						break;
					case LEVEL:
						currentScreen = new PlayLevelScreen(this);
						break;
					case CREDITS:
						currentScreen = new CreditsScreen(this);
						break;
					case LOBBY:
						currentScreen = new LobbyScreen(this);
						break;
					case JUNGLE:
						currentScreen = new JungleScreen(this);
						break;
				}
				currentScreen.initialize();
			}
			previousGameState = gameState;

			// call the update method for the currentScreen
			currentScreen.update();
		} while (previousGameState != gameState);
	}

	@Override
	public void draw(GraphicsHandler graphicsHandler) {
		// call the draw method for the currentScreen
		currentScreen.draw(graphicsHandler);
	}
}
