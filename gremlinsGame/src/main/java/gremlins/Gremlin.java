package gremlins;
import processing.core.PApplet;
import java.util.Random;

public class Gremlin extends Entity{
    private static final Random randomGenerator = new Random();

    public Gremlin(int x, int y, PApplet app){
        super(app.loadImage(app.getClass().getResource("gremlin.png").getPath().replace("%20", " ")), x, y);
    }
    public int[] getCoords(){
        int[] coords = {this.y, this.x};
        return coords;
    }
}