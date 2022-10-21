package gremlins;
import processing.core.PImage;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
public class Wizard extends Entity {
    private String orientation = "RIGHT";
    private Map<String, PImage> sprites = new HashMap();

    public Wizard(int x, int y, PApplet app){
        super(app.loadImage(app.getClass().getResource("wizard0.png").getPath().replace("%20", " ")), x, y);
    }

    public void setCoord(int[] coord){
        super.y = coord[0];
        super.x = coord[1];
    }

    public int[] getCoord(){
        int[] coords = new int[2];
        coords[0] = super.y;
        coords[1] = super.x;
        return coords;
    }
    public boolean validTile(int[] coord, Tile[][] tiles){
        if (tiles[coord[0]][coord[1]] != null){
            return false;
        }

        return true;
    }
    public void setOrientation(String orientation, PApplet app){
        this.orientation = orientation;
        setImage();
    }

    private PImage getSprite(String orientation){
        return this.sprites.get(orientation);
    }
    private void setImage(){
        super.image = getSprite(this.orientation);
    }

    public void addSprites(PApplet app){
        this.sprites.put("UP", app.loadImage(app.getClass().getResource("wizard2.png").getPath().replace("%20", " ")));
        this.sprites.put("DOWN", app.loadImage(app.getClass().getResource("wizard3.png").getPath().replace("%20", " ")));
        this.sprites.put("LEFT", app.loadImage(app.getClass().getResource("wizard0.png").getPath().replace("%20", " ")));
        this.sprites.put("RIGHT", app.loadImage(app.getClass().getResource("wizard1.png").getPath().replace("%20", " ")));

    }
}