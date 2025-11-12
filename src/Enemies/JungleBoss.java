package Enemies;

import Engine.ImageLoader;
import GameObject.SpriteSheet;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;
import Level.Enemy;

public class JungleBoss extends Enemy{
    private float gravity = .5f;
    private float movementSpeed = 1f;
    private float forzenSpeed = movementSpeed / 4;
    private Direction startFacingDirection;
    private Direction facingDirection;
    private AirGroundState airGroundState;
    private boolean hurt = false;
    private int health = 25;

    public JungleBoss(Point location, Direction facingDirection){
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Skunk.png"), 100, 64), "WALK_LEFT");
        this.startFacingDirection = facingDirection;
        this.initialize();
    }
}
