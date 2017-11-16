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
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class FinalProject extends Game{

    /* Variables to keep track of where things are on the screen */

    //side bar
    private int sideBarWidth = 640;
    private int sideBarHeight = 992;

    //maze
    private int mazeWidth = 576;
    private int mazeHeight = 864;

    //whole game
    private int gameWidth = 1280;
    private int gameHeight = 992;

    //character
    private int charWidth = 96;
    private int charHeight = 103;

    //grid squares
    private int sqWidth = 144;
    private int sqHeight = 144;

    //gray border
    private int borderWidth = 32;

    ImageIcon btnImage = new ImageIcon("resources/turn.png");
    JButton btn = new JButton(btnImage);


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
            btn.setBounds(0,0,100,100);
            super.getScenePanel().add(btn);




    }

    public ArrayList<Tuple <Boolean, Integer>> createInitGrid() {
        ArrayList <Tuple <Boolean, Integer>> grid = new ArrayList <Tuple <Boolean, Integer>>(24);
        int i;
        for (i = 0; i < 24; i++)
            grid.add(i, new Tuple<Boolean, Integer>(true, 0));
        return grid;
    }

    public void createLevels() {
        /* Level 1 */

        //initial grid
        ArrayList <Tuple <Boolean, Integer>> initGrid = createInitGrid();
        initGrid.set(2, new Tuple<Boolean, Integer>(false, 1));
        initGrid.set(3, new Tuple<Boolean, Integer>(false, 1));
        initGrid.set(6, new Tuple<Boolean, Integer>(false, 1));
        initGrid.set(7, new Tuple<Boolean, Integer>(false, 1));
        initGrid.set(12, new Tuple<Boolean, Integer>(false, 1));
        initGrid.set(16, new Tuple<Boolean, Integer>(false, 1));
        initGrid.set(18, new Tuple<Boolean, Integer>(false, 1));
        initGrid.set(23, new Tuple<Boolean, Integer>(true, 5));

        //available moves
        ArrayList <Integer> movesAvail = new ArrayList <Integer> ();
        movesAvail.add(0);
        movesAvail.add(1);

        //position
        Point position = new Point (0, 0);

        //id
        int id = 1;

        //creating level 1
        Level lev1 = new Level(initGrid, movesAvail, position, id);
    }

    public static void main(String[] args) {
        edu.virginia.FinalProject game = new edu.virginia.FinalProject();
        game.createLevels();
        game.start();

    }

}
