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

import java.awt.image.BufferedImage;

public class VerticalMovingPlatform extends EnhancedMapTile {
    
    private Point startLocation;
    private Point endLocation;
    private float movementSpeed = 1f;
    private Direction startDirection;
    private Direction direction;

    public VerticalMovingPlatform(BufferedImage image, Point startLocation, Point endLocation, TileType tileType, float scale, Rectangle bounds, Direction startDirection) {
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
    }

    @Override
    public void update(Player player) {

        float startBound = startLocation.y;
        float endBound = endLocation.y;

        // move platform up or down based on its current direction
        int moveAmountY = 0;
        if (direction == Direction.UP) {
            moveAmountY += movementSpeed;
        } else if (direction == Direction.DOWN) {
            moveAmountY -= movementSpeed;
        }

        moveY(moveAmountY);

        // Check if the player is standing on top of the platform with a 5 pixel tolerance
        boolean playerOnPlatform = 
        player.getBounds().getY2() <= this.getBounds().getY1() + 5 &&
        player.getBounds().getY2() >= this.getBounds().getY1() - 5 && 
        player.getBounds().getX2() > this.getBounds().getX1() &&
        player.getBounds().getX1() < this.getBounds().getX2();


        // Move the players Y with the vertical platform 
        if (playerOnPlatform) {
            player.moveY(moveAmountY); 
            player.setIsOnPlatform(true);
        }
        

        //If the tile reaches the height of the map it moves down
        //if the tile reaches its start position it moves up
        if (getY1() + getHeight() >= endBound) {
            float difference = endBound - (getY1() + getHeight());
            moveY(-difference);
            moveAmountY -= difference;
            direction = Direction.DOWN;
        } else if (getY1() <= startBound) {
            float difference = startBound - getY1();
            moveY(difference);
            moveAmountY += difference;
            direction = Direction.UP;
        }

        super.update(player);
    }



    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
