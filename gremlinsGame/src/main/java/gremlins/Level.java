package gremlins;
import java.io.*;
import java.util.Scanner;
import processing.data.JSONObject;
import java.io.FileNotFoundException;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
public class Level{
    private double wizard_cooldown;
    private double enemy_cooldown;
    private int lives;
    private String[][] strGrid = new String[33][36];

    private Tile[][] tiles = new Tile[33][36];
    private ArrayList<Gremlin> gremlins = new ArrayList<>();
    private List<PImage> images = new ArrayList<>();
    private Wizard wizard;

    public void addLevel(JSONObject levelSetup, PApplet app){
        strGrid = getGridFromJSON(levelSetup.getString("layout"));
        this.wizard_cooldown = levelSetup.getDouble("wizard_cooldown");
        this.enemy_cooldown = levelSetup.getDouble("enemy_cooldown");  
        createTiles(strGrid, app);
        createEntities(strGrid, app);
        this.wizard.addSprites(app);      
    }

    public String[][] getGridFromJSON(String path){
        String[][] grid = new String[33][36];
        File fobj = new  File(path);
        try{
            Scanner scan = new Scanner(fobj);
            int y = 0;
            while (scan.hasNextLine()){
                String[] str = scan.nextLine().split("");
                for (int x = 0; x <= 35; ++x){
                    grid[y] = str;                   
                }
                ++y;
            }
            scan.close();
            return grid;

        } catch (FileNotFoundException e){
            e.printStackTrace();
            return new String[][] {{}};
        }
    }

    public void draw(PApplet app){
        //drawTiles(app);
        drawGremlins(app);
        this.wizard.draw(app);
    }

    public void createTiles(String[][] strGrid, PApplet app){
        for (int y = 0; y < strGrid.length; ++y) {
            for (int x = 0; x < strGrid[y].length; ++x) {
                if (strGrid[y][x].equals("X")){
                    tiles[y][x] = new Stonewall(x, y, app);
                } else if (strGrid[y][x].equals("B")){
                    tiles[y][x] = new Brickwall(x, y, app);
                } else if (strGrid[y][x].equals("E")){
                    tiles[y][x] = new Exit(x, y, app);
                }
            }
        }
    }
    public void createEntities(String[][] strGrid, PApplet app){
        for (int y = 0; y < strGrid.length; y++) {
            for (int x = 0; x < strGrid[y].length; x++) {
                if (strGrid[y][x].equals("W")){
                    wizard = new Wizard(x, y, app);
                } else if (strGrid[y][x].equals("G")){
                    Gremlin gremlin = new Gremlin(x, y, app);
                    gremlins.add(gremlin);
                }
            }
        }
    }
    public void drawTiles(PApplet app){
        for (int y = 0; y < tiles.length; ++y){
            for (int x = 0; x < tiles.length; ++ x){
                if (tiles[y][x] == null){
                    continue;
                } else {
                    tiles[y][x].draw(app);
                }
            }
        }
    }
    public void drawGremlins(PApplet app){
        for (int i = 0; i < gremlins.size(); ++i){
            if (gremlins.get(i) == null){
                continue;
            } else {
                gremlins.get(i).draw(app);
            }
        }
    }
        /**
     * Receive key pressed signal from the keyboard.
    */
    public void keyPressed(PApplet app){
        //Left = 37
        // Up = 38
        // Right = 39
        // Down = 40
        int[] coord = this.wizard.getCoord();
        int y = coord[0];
        int x = coord[1];
        int[] leftTile = {y,x-1};
        int[] rightTile = {y, x+1};
        int[] upTile = {y-1, x};
        int[] downTile = {y+1, x};
        if (app.keyCode == 37) {
            if (this.wizard.validTile(leftTile, tiles)) {
                this.wizard.setCoord(leftTile);
                this.wizard.setOrientation("LEFT", app);
            }
        }
        if (app.keyCode == 39) {
            if (this.wizard.validTile(rightTile, tiles)) {
                this.wizard.setCoord(rightTile);
                this.wizard.setOrientation("RIGHT", app);
            }
        }
        if (app.keyCode == 38) {
            if (this.wizard.validTile(upTile, tiles)) {
                this.wizard.setCoord(upTile);
                this.wizard.setOrientation("UP", app);
            }
        }
        if (app.keyCode == 40) {
            if (this.wizard.validTile(downTile, tiles)) {
                this.wizard.setCoord(downTile);
                this.wizard.setOrientation("DOWN", app);
            }
        }
        checkSquare(app);

    }
    public void checkSquare(PApplet app){
        for (int i = 0; i < this.gremlins.size(); ++i){
            if (wizard.x == this.gremlins.get(i).getCoords()[1] && wizard.y == this.gremlins.get(i).getCoords()[0]){
                --lives;
                updateLives(app);
            }
        }
    }
    public void updateLives(PApplet app){
        for (int i = 0; i < lives; ++ i){
            app.image(app.loadImage(app.getClass().getResource("wizard0.png").getPath().replace("%20", " ")), 60, 690 + i*20);
        }
    }
    public String[][] getGrid(){
        return this.strGrid;
    }
    public int getLives(){
        return lives;
    }
    public double getWizardCooldown(){
        return wizard_cooldown;
    }
    public double getEnemyCooldown(){
        return enemy_cooldown;
    }
}