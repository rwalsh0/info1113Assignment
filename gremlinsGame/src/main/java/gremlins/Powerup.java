package gremlins;
import processing.core.PApplet;

public class Powerup extends Entity{
    public Powerup(int x,int y, PApplet app){
        super(app.loadImage(app.getClass().getResource("slime.png").getPath().replace("%20", " ")),x,y);
    }

}