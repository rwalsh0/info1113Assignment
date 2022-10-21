package gremlins;

import processing.core.PImage;
import processing.core.PApplet;

abstract class Entity{
    protected int x;
    protected int y;
    protected PImage image;

    public Entity(PImage image, int x, int y){
        this.image = image;
        this.x = x;
        this.y = y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void draw(PApplet app){
        app.image(this.image, this.x*20, this.y*20);
    }
    
}