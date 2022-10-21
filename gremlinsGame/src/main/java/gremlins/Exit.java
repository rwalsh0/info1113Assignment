package gremlins;
import processing.core.PApplet;

public class Exit extends Tile{
    private boolean b = false;
    public Exit(int x, int y, PApplet app){
        super(app.loadImage(app.getClass().getResource("slime.png").getPath().replace("%20", " ")), x, y);
    } 
    public boolean getB(){
        return b;
    }
}