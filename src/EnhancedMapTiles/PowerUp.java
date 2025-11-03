package EnhancedMapTiles;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Screens.PlayLevelScreen;
import Utils.Point;
import java.util.HashMap;
import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Game.GameState;
import Game.ScreenCoordinator;
import Game.Game;




public class PowerUp extends EnhancedMapTile{

//keeps track of name of powerup that will be used
    private String currentPowerName;
//tracks whether user has made contact or not
    private Boolean madeContact = false;
    private static GameState currentGameState = ScreenCoordinator.returnCurrentGameState();

//tracks how many times user has made contact, so when touched once will disappear
    private int counter = 0;

    public PowerUp(Point location, String powerName, String imageName){
        super(location.x, location.y, new SpriteSheet(ImageLoader.load(imageName), 15, 15), TileType.PASSABLE);
        currentPowerName = powerName;
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (!madeContact && intersects(player)) {
            madeContact = true;
            String message = "";

            if(currentPowerName.equals("Double Jump")){
                player.setHasDoubleJump(true);
                message = "Got Double Jump";

            }else if(currentPowerName.equals("Ice Ball")){
                player.setHasIceBall(true);
                message = "Got Ice Ball";

            }

            if (ScreenCoordinator.getCurrentScreen() instanceof PlayLevelScreen) {
                PlayLevelScreen pls = (PlayLevelScreen) ScreenCoordinator.getCurrentScreen();
                pls.showPowerUpText(message);
            }
        }
        if(counter == 1){
            //System.out.println("Checking collision for: " + currentPowerName + " | MadeContact: " + madeContact + " | Intersects: " + intersects(player));
            //System.out.println("Collected PowerUp: " + currentPowerName);
            //System.out.println("Current GameState " + currentGameState);
            counter+=1;
        }
    }


    //Raster issues previously due to animations, will work now
    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                        .withScale(1)
                        .withBounds(3, 3, 9, 9)
                        .build(),
                /* 
                new FrameBuilder(spriteSheet.getSprite(0, 1), 40)
                        .withScale(1)
                        .withBounds(1, 1, 14, 14)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 40)
                        .withScale(1)
                        .withBounds(1, 1, 14, 14)
                        .build()
                */
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler){
        if(!madeContact){
            super.draw(graphicsHandler);


        }
    }

    public boolean returnCollectedState(){
        return madeContact;
    }

    public String returnPowerUpName(){
        return currentPowerName;
    }


    //checks if screen has changed
    public static boolean checkScreenChange(){
        if(currentGameState != ScreenCoordinator.returnCurrentGameState()){
            return true;
        }else{
            return false;
        }
    }
    
}
