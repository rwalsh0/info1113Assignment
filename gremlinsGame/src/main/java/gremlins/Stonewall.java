package gremlins;
import processing.core.PApplet;

public class Stonewall extends Tile {
    private boolean b = false;
    public Stonewall(int x, int y, PApplet app){
        super(app.loadImage(app.getClass().getResource("stonewall.png").getPath().replace("%20", " ")), x, y);
    }
    public boolean getB(){
        return b;
    }

}