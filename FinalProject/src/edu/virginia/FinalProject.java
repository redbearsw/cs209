package edu.virginia;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Game;
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

    public static void main(String[] args) {
        edu.virginia.FinalProject game = new edu.virginia.FinalProject();
        game.start();
    }

}
