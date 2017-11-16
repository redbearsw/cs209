package edu.virginia;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Level;
import edu.virginia.engine.util.Tuple;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.SoundManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class FinalProject extends Game{

    /* Variables to keep track of where things are on the screen */
    private int gameWidth;
    private int gameHeight;
    private int screenWidth;
    private int movesWidth;
    private int borderWidth;
    private int borderHeight;
    private int mazeWidth;
    private int mazeHeight;



    /* getters and setters */
    public int getGameWidth() {return this.gameWidth;}
    public void setGameWidth(int w) {this.gameWidth = w;}

    public int getGameHeight() {return this.gameHeight;}
    public void setGameHeight(int h) {this.gameHeight = h;}

    public int getScreenWidth() {return this.screenWidth;}
    public void setScreenWidth(int w) {this.screenWidth = w;}

    public int getMovesWidth() {return this.movesWidth;}
    public void setMovesWidth(int w) {this.movesWidth = w;}

    public int getBorderWidth() {return this.borderWidth;}
    public void setBorderWidth(int w) {this.borderWidth = w;}

    public int getBorderHeight() {return this.borderHeight;}
    public void setBorderHeight(int h) {this.borderHeight = h;}

    public int getMazeWidth() {return this.mazeWidth;}
    public void setMazeWidth(int w) {this.mazeWidth = w;}

    public int getMazeHeight() {return this.mazeHeight;}
    public void setMazeHeight(int w) {this.mazeHeight = w;}

    /* Constructor */
    public FinalProject() {
        super("Final Project", 500, 500);
    }

    @Override
    public void update(ArrayList<Integer> pressedKeys){
        super.update(pressedKeys);

		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */


    }

    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
     * the screen, we need to make sure to override this method and call mario's draw method.
     * */
    @Override
    public void draw(Graphics g) {
        //check characters for null
            super.draw(g);
    }

    public void createLevels() {
        //Level 1
        ArrayList <Tuple <Boolean, Integer>> initGrid = new ArrayList();
        ArrayList <Integer> movesAvail = new ArrayList();
        Point position = new Point (0, 0);
        String id = "level1";
        Level lev1 = Level(initGrid, movesAvail, position, id);
    }

    public static void main(String[] args) {
        edu.virginia.FinalProject game = new edu.virginia.FinalProject();
        game.createLevels();
        game.start();
    }

}
