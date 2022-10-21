package gremlins;

import processing.core.PApplet;
import processing.data.JSONObject;
import processing.data.JSONArray;


import java.io.*;
import java.util.ArrayList;

public class App extends PApplet {
    //game format
    public static final int WIDTH = 720;
    public static final int HEIGHT = 720;
    public static final int SPRITESIZE = 20;
    public static final int BOTTOMBAR = 60;
    public static final int FPS = 60;

    ArrayList<Level> levels = new ArrayList<>();
    private Integer levelNum = 0;
    private Level currentLevel;
    private int pressCount;
    /**
     * Initialise the setting of the window size.
    */
    public void settings() {
        this.size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
    */
    public void setup() {
        this.frameRate(FPS);

        // Load images during setup
        //this.slime = loadImage(this.getClass().getResource("slime.png").getPath().replace("%20", " "));
        //this.fireball = loadImage(this.getClass().getResource("fireball.png").getPath().replace("%20", " "));
        
        //load wizard
        //this.Wizard.addPlayerPositions();
        
        JSONObject conf = PApplet.loadJSONObject(new File("config.json"));
        JSONArray levelSetup = conf.getJSONArray("levels");
        /*for (i:conf["levels"]){
            levels[i] = new level(i);
            ++i;
        }*/
        for (int i = 0; i < levelSetup.size(); ++i){
            JSONObject newLevel = levelSetup.getJSONObject(i);
            Level setLevel = new Level();
            setLevel.addLevel(newLevel, this);
            System.out.println(setLevel.getEnemyCooldown());

            levels.add(setLevel);
        }   
        this.background(239, 129, 0);
        this.text("Lives:", 20, 690);

        Integer numOfLevels = levels.size();
        String strnumOfLevels = numOfLevels.toString();
        Integer displaylevelNum = levelNum + 1;
        String strLevelNum = displaylevelNum.toString();
        this.text("Level: " + strLevelNum + "/" + strnumOfLevels, 180, 690);

        this.currentLevel = levels.get(levelNum);
        this.currentLevel.drawTiles(this);
    }


    /**
     * Receive key pressed signal from the keyboard.
    */
    public void keyPressed(){
        /*if (pressCount > 0){
            this.currentLevel.keyPressed(this);
        }
        ++pressCount;*/
        this.currentLevel.keyPressed(this);

    }
    
    /**
     * Receive key released signal from the keyboard.
    */
    public void keyReleased(){
        pressCount = 0;
    }


    /**
     * Draw all elements in the game by current frame. 
	 */
    public void draw() {
        //end game handling

        //format info bar

        //draw map
        this.currentLevel.draw(this);
    }


    public static void main(String[] args) {
        PApplet.main("gremlins.App");
    }
}
