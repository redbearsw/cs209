package edu.virginia;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Level;
import edu.virginia.engine.util.Tuple;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.SoundManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import edu.virginia.engine.util.Obstacles;
import edu.virginia.engine.util.Moves;

public class FinalProject extends Game{

    /* Sprites */
    Sprite allLevels;
    Sprite moves;
    Sprite hero;

    /* Various game states and trackers */
    int mvsCount;
    int currLev;

    /* Move Buttons */
    JButton turn;
    JButton fwd;

    /* List of Levels */
    ArrayList<Level> Levels;

    /* Variables to keep track of where things are on the screen */
    private int sideBarWidth;
    private int sideBarHeight;

    private int mazeWidth;
    private int mazeHeight;

    private int gameWidth;
    private int gameHeight;

    private int charWidth;
    private int charHeight;

    private int sqWidth;
    private int sqHeight;

    private int borderWidth;



    /* Constructor */
    public FinalProject() {
        super("Final Project", 940, 728);

        /* Sprites */
            this.allLevels = new Sprite("All Levels", "levels.png");
            this.moves = new Sprite("Move Board", "moves.png");
                this.moves.setPosition(new Point(470,0));
            this.hero = new Sprite("Hero", "character.png");

        /* Game States*/
            this.mvsCount = 0;
            this.currLev = 1;

        /* Move Buttons */
            this.turn = new JButton(new ImageIcon("resources/turn.png"));
            this.fwd = new JButton(new ImageIcon("resources/forward.png"));

        /* ArrayList of levels */
            this.Levels = new ArrayList<Level> ();

        /* Positions on screen */
            this.sideBarWidth = 470;
            this.sideBarHeight = 728;
            this.mazeWidth = 470;
            this.mazeHeight = 728;
            this.gameWidth = 940;
            this.gameHeight = 728;
            this.charWidth = 96;
            this.charHeight = 103;
            this.sqWidth = 117;
            this.sqHeight = 117;
            this.borderWidth = 32;

        /* Levels */
            /* Level 1 */
            //initial grid
            ArrayList<Tuple<Boolean, Obstacles>> initGrid = createInitGrid();
            initGrid.set(2, new Tuple<Boolean, Obstacles>(false, Obstacles.WALL));
            initGrid.set(3, new Tuple<Boolean, Obstacles>(false, Obstacles.WALL));
            initGrid.set(6, new Tuple<Boolean, Obstacles>(false, Obstacles.WALL));
            initGrid.set(7, new Tuple<Boolean, Obstacles>(false, Obstacles.WALL));
            initGrid.set(12, new Tuple<Boolean, Obstacles>(false, Obstacles.WALL));
            initGrid.set(16, new Tuple<Boolean, Obstacles>(false, Obstacles.WALL));
            initGrid.set(18, new Tuple<Boolean, Obstacles>(false, Obstacles.WALL));
            initGrid.set(23, new Tuple<Boolean, Obstacles>(true, Obstacles.GOAL));

            //available moves
            ArrayList<JButton> mvsAvail = new ArrayList<JButton>();
            mvsAvail.add(turn);
            mvsAvail.add(fwd);

            //position
            Point position = new Point(0, 0);

            //creating level 1
            Level lev1 = new Level(initGrid, mvsAvail, position, 1);

            this.Levels.add(lev1);

        /* Level 2 */

        /* Level 3 */


    }

    /* Create and zero out initial grid */
    public ArrayList<Tuple <Boolean, Obstacles>> createInitGrid() {
        ArrayList <Tuple <Boolean, Obstacles>> grid = new ArrayList <Tuple <Boolean, Obstacles>>(24);
        int i;
        for (i = 0; i < 24; i++)
            grid.add(i, new Tuple<Boolean, Obstacles>(true, Obstacles.NOTHING));
        return grid;
    }

    /* Run through list of moves, performing one move per frame */
    public void runMoves() {
        
        //iterate through movesTaken (one update at a time, use mvsCount)
        //update character's pos based on mvsCount and Levels[currLev].getMovesTaken()


    }

    @Override
    public void update(ArrayList<Integer> pressedKeys){
        super.update(pressedKeys);

		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */

        //if(clicked on run button) {runMoves();}
    }


    @Override
    public void draw(Graphics g) {
        //check characters for null

        super.draw(g);
        if (turn != null) {
            turn.setBounds(0, 0, 100, 100);
            super.getScenePanel().add(turn);
        }
        if (fwd != null) {
            turn.setBounds(0, 0, 100, 100);
            super.getScenePanel().add(turn);
        }

        if (allLevels != null) allLevels.draw(g);
        if (moves != null) moves.draw(g);

    }

    public static void main(String[] args) {
        edu.virginia.FinalProject game = new edu.virginia.FinalProject();
        game.start();
    }

}
