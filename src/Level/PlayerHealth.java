// package Level;

// import Engine.GraphicsHandler;
// import Engine.ImageLoader;
// import GameObject.GameObject;
// import java.awt.image.BufferedImage;

// public class PlayerHealth extends GameObject {
//     private Player player;
//     private BufferedImage FullHealthBar;
//     private BufferedImage YellowHealthBar;
//     private BufferedImage OrangeHealthBar;
//     private BufferedImage RedHealthBar;

//     private final int screenX = 20;
//     private final int screenY = 20;

//     public PlayerHealth(Player player) {
//         super(ImageLoader.load("FullHealthBar.png"), 20, 20);

//         // load all four bar states
//         this.player = player;
//         this.FullHealthBar = ImageLoader.load("FullHealthBar.png");
//         this.YellowHealthBar = ImageLoader.load("YellowHealthBar.png");
//         this.OrangeHealthBar = ImageLoader.load("OrangeHealthBar.png");
//         this.RedHealthBar = ImageLoader.load("RedHealthBar.png");
//     }

//     @Override
//     public void draw(GraphicsHandler g) {
//         System.out.println("Drawing Health Bar (HP=" + player.getHealth() + ")");

//         BufferedImage barImage = getCurrentHealthImage();

//         // Only draw if the image successfully loaded
//         if (barImage != null) {
//             g.drawImage(barImage, screenX, screenY, barImage.getWidth(), barImage.getHeight());
//         } else {
//             System.out.println("image not loading");
//         }
//     }

//     private BufferedImage getCurrentHealthImage() {
//         int hp = player.getHealth();
//         if (hp > 74) {
//             return FullHealthBar;
//         } else if (hp > 49) {
//             return YellowHealthBar;
//         } else if (hp > 24) {
//             return OrangeHealthBar;
//         } else {
//             return RedHealthBar;
//         }
//     }
//     /*
//      * public void update() {
//      * //idk i just know it needs to update
//      * }
//      */

// }
