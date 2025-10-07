package EnhancedMapTiles;
import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import GameObject.Rectangle;
import Level.EnhancedMapTile;
import Level.Player;
import Level.PlayerState;
import Level.TileType;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.image.BufferedImage;

public class FallingPlatform extends EnhancedMapTile {
    
    private Point startLocation;
    private Point endLocation;
    private float movementSpeed = 1f;
    private Direction startDirection;
    private Direction direction;
    private boolean isFalling = false;

    public FallingPlatform(BufferedImage image, Point startLocation, Point endLocation, TileType tileType, float scale, Rectangle bounds, Direction startDirection) {
        super(startLocation.x, startLocation.y, new FrameBuilder(image).withBounds(bounds).withScale(scale).build(), tileType);
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startDirection = startDirection;
        this.initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        direction = startDirection;
        movementSpeed = 1f;
        isFalling = false;
    }

    @Override
    public void update(Player player) {

        Timer timer = new Timer();

        // Check if the player is standing on top of the platform with a 5 pixel tolerance
        boolean playerOnPlatform = 
        player.getBounds().getY2() <= this.getBounds().getY1() + 1 &&
        player.getBounds().getY2() >= this.getBounds().getY1() - 1 && 
        player.getBounds().getX2() > this.getBounds().getX1() &&
        player.getBounds().getX1() < this.getBounds().getX2();

        // move platform up or down based on its current direction
        if(playerOnPlatform){
            this.isFalling = true;
        }
        int moveAmountY = 0;
        if (isFalling) {
            moveAmountY += movementSpeed;
            //adds acceleration to the falling platform
            movementSpeed += 0.1;
        }

        moveY(moveAmountY);


        // Move the players Y with the vertical platform 
        if (playerOnPlatform) {
            player.setY(player.getY() + moveAmountY);
            player.setIsOnPlatform(true);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    initialize();
                }
            }, 6000);
        }

        super.update(player);
    }



    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
