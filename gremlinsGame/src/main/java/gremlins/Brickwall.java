package gremlins;
import processing.core.PApplet;

public class Brickwall extends Tile {
    private boolean b = false;
    private int brokenNum;
    public Brickwall(int x, int y, PApplet app){
        super(app.loadImage(app.getClass().getResource("brickwall.png").getPath().replace("%20", " ")), x, y);
        brokenNum = 0;
    }
    public boolean isBroke(){
        return false;
    }

    public void setImage(PApplet app){
        if (brokenNum > 0) {
            Integer broken = brokenNum - 1;
            String strBroken = broken.toString();
            super.image = app.loadImage(app.getClass().getResource("brickwall_destroyed" + strBroken + ".png").getPath().replace("%20", " "));
        }
    }

    public void setBrokeness(int brokenNum, PApplet app){
        this.brokenNum = brokenNum;
        setImage(app);
    }
    /*public void break(){
        if (brokeness <= 4){
            ++brokeness;
        }
    }*/
}