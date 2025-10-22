
package Level;
import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntity;
import Level.MapEntityStatus;
import Level.Player;
import Utils.Direction;
import Utils.Point;
import java.util.HashMap;
import java.util.List;

// This class is for the fireball enemy that the DinosaurEnemy class shoots out
// it will travel in a straight line (x axis) for a set time before disappearing
// it will disappear early if it collides with a solid map tile
public class IceBall extends EnhancedMapTile{
    private float movementSpeed;
    private int existenceFrames;
    private MapEntityStatus mapEntityStatus;
    private float verticalSpeed = 0;
    private float gravity = 0.3f;
    private List<Enemy> enemies;
    private boolean enemyHitByIceBall = false;

    public IceBall(Point location, float movementSpeed, int existenceFrames, List<Enemy> enemies) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("iceBall.png"), 15, 15), TileType.PASSABLE);

        this.movementSpeed = movementSpeed;
        this.currentAnimationName = "DEFAULT";
        this.enemies = enemies;
        // how long the iceBall will exist for before disappearing
        this.existenceFrames = existenceFrames;

    }

    @Override
    public void update(Player player) {
        // if timer is up, set map entity status to REMOVED
        // the camera class will see this next frame and remove it permanently from the map
        if (existenceFrames == 0 ) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        } else {
            // move fireball forward
            moveXHandleCollision(movementSpeed);
            verticalSpeed += gravity;
            moveYHandleCollision(verticalSpeed);
            for(Enemy enemy: enemies){
                //System.out.println("Iceball state: " + enemyHitByIceBall);

               // this.enemyHitByIceBall = false;
                if(this.intersects(enemy)){
                    //System.out.println("Enemy attacked!");
                   //System.out.println("Collision detected with enemy: " + enemy);
                    this.enemyHitByIceBall = true;
                    enemy.setIceBallHitStatus(true);
                    //System.out.println("Enemy hit status: " + enemy.getIceBallHitStatus());
                    
                    for (MapEntity entity : map.getEnemies()) {
                        if (entity instanceof Enemy && this.intersects(entity)) {
                            //System.out.println(enemy.getMovementSpeed());
                            float movementSpeed = enemy.getMovementSpeed();
                            //System.out.println("Iceball class state: " + enemyHitByIceBall);

                            movementSpeed *= 0.75f;
                            enemy.setMovementSpeed(movementSpeed);
                            //System.out.println("Ice state" + enemyHitByIceBall);
                    }
                    //enemy.mapEntityStatus = MapEntityStatus.REMOVED;
                    this.mapEntityStatus = MapEntityStatus.REMOVED;
                }
                existenceFrames--;
            }


            }

        }

            //System.out.println("Update called. enemyHitByIceBall = " + enemyHitByIceBall);


        }
       




    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if iceBall collides with anything solid on the x axis, it is removed
        if (hasCollided) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    @Override 
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith){
        if(hasCollided && direction == Direction.DOWN){
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    public MapEntityStatus getMapEntityStatus(){
        return mapEntityStatus;
    }
    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(1)
                            .withBounds(1, 1, 5, 2)
                            .build()
            });
        }};
    }

     @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    public boolean enemyHit(){
        if(enemyHitByIceBall == true){
            //System.out.println("enemyHit() called. Value = " + enemyHitByIceBall);
           // System.out.println("Iceball class! running: " + enemyHitByIceBall);
            return true;

        }else{
           // System.out.println("Iceball class! running: " + enemyHitByIceBall);
           // System.out.println("enemyHit() called. Value = " + enemyHitByIceBall);
            return false;
        }
    }



}
