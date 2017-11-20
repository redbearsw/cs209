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
    Sprite allLevels = new Sprite("All Levels", "levels.png");

    Sprite moves = new Sprite("Move Board", "moves.png");

    Sprite hero = new Sprite("Hero", "character.png");


    /* Move Buttons */
    JButton turn = new JButton(new ImageIcon("resources/turn.png"));
    JButton fwd = new JButton(new ImageIcon("resources/forward.png"));


    /* List of Levels */
    ArrayList<Level> Levels = new ArrayList<Level> ();


    /* Variables to keep track of where things are on the screen */
    //side bar
    private int sideBarWidth = 470;
    private int sideBarHeight = 728;

    //maze
    private int mazeWidth = 470;
    private int mazeHeight = 728;

    //whole game
    private int gameWidth = 940;
    private int gameHeight = 728;

    //character
    private int charWidth = 96;
    private int charHeight = 103;

    //grid squares
    private int sqWidth = 117;
    private int sqHeight = 117;

    //gray border
    private int borderWidth = 32;

    /* Constructor */
    public FinalProject() {
        super("Final Project", 940, 728);

        /* Permanent Items */
        moves.setPosition(new Point(470,0));

        /* Creating Levels */
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

        Levels.add(lev1);

    /* Level 2 */

    /* Level 3 */
    }

    //Helper to create and zero out initial grid
    public ArrayList<Tuple <Boolean, Obstacles>> createInitGrid() {
        ArrayList <Tuple <Boolean, Obstacles>> grid = new ArrayList <Tuple <Boolean, Obstacles>>(24);
        int i;
        for (i = 0; i < 24; i++)
            grid.add(i, new Tuple<Boolean, Obstacles>(true, Obstacles.NOTHING));
        return grid;
    }

    @Override
    public void update(ArrayList<Integer> pressedKeys){
        super.update(pressedKeys);

		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */

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
