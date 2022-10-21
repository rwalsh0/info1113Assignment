package gremlins;
import processing.core.PImage;
import processing.core.PApplet;

abstract class Tile{
    protected int x;
    protected int y;
    protected PImage image;
    protected boolean b = false;

    public Tile(PImage image, int x, int y){
        this.image = image;
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void draw(PApplet app){
        app.image(this.image, x*20, y*20);
    }
}