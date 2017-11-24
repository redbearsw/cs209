package edu.virginia;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Level;
import edu.virginia.engine.util.Tuple;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.SoundManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import edu.virginia.engine.util.Obstacles;
import edu.virginia.engine.util.Moves;

public class FinalProject extends Game{

    /* Sprites */
    private Sprite allLevels;
    private Sprite moves;
    private Sprite hero;
    private Sprite forward;
    private Sprite rotate;

    /* Various game states and trackers */
    private int mvsCount;
    private int numLevs;
    private int currLev;
    private Boolean moving;

    /* Move Buttons */
    private JButton turn;
    private JButton fwd;

    /* Run, Backspace, Clear Buttons */
    private JButton run;
    private JButton back;
    private JButton clear;

    /* List of Levels */
    //slot 0 for opening menu?, slot 1 for level 1, etc.
    private ArrayList<Level> Levels;

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
        super("Final Project", 940, 748);

        /* Sprites */
            this.allLevels = new Sprite("All Levels", "levels.png");
            this.moves = new Sprite("Move Board", "moves.png");
                this.moves.setPosition(new Point(470,0));
            this.hero = new Sprite("Hero", "character.png");

        /* Game States*/
            this.mvsCount = 0;
            this.currLev = 1;
            this.numLevs = 3;
            this.moving = false;


        /* Run, Backspace, Clear Buttons (TO BE REPLACED WITH IMAGES) */
            this.run = new JButton("r");
            run.setBounds(500,250, 100,25);
            run.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    runMoves();
                }
            });

            this.back = new JButton("Back");
            back.setBounds(610,250, 100,25);
            back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (Levels.get(currLev).getMovesTaken()!=null) {
                        Levels.get(currLev).getMovesTaken().remove(Levels.get(currLev).getMovesTaken().size() - 1);
                    }
                }
            });
            this.clear = new JButton("Clear");
            clear.setBounds(720,250, 100,25);
            clear.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Levels.get(currLev).getMovesTaken().clear();
                }
            });
        // adds run, clear, back buttons to screen
        super.getScenePanel().add(run);
        super.getScenePanel().add(clear);
        super.getScenePanel().add(back);

        /* ArrayList of levels */
            this.Levels = new ArrayList<Level> ();
            int i;
            for (i = 0; i < numLevs; i ++)
                Levels.add(null);


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
            ArrayList<Moves> mvsAvail = new ArrayList<>();
            mvsAvail.add(Moves.FORWARD);
            mvsAvail.add(Moves.ROTATE);

            //position
            Point position = new Point(0, 0);

            //creating level 1
            Level lev1 = new Level(initGrid, mvsAvail, position, 1);

            this.Levels.add(1, lev1);

        /* Level 2 */

        /* Level 3 */


    }

    /* Create and zero out initial grid */
    private ArrayList<Tuple <Boolean, Obstacles>> createInitGrid() {
        ArrayList <Tuple <Boolean, Obstacles>> grid = new ArrayList <Tuple <Boolean, Obstacles>>(24);
        int i;
        for (i = 0; i < 24; i++)
            grid.add(i, new Tuple<Boolean, Obstacles>(true, Obstacles.NOTHING));
        return grid;
    }

    /* Convert position to grid square */
    private int xToCol(int x) {
        if (x < (sideBarWidth + (sqWidth * 2)) && x > (sideBarWidth + sqWidth))
            return 0;
        if (x < (sideBarWidth + (sqWidth * 3)) && x > (sideBarWidth + (sqWidth * 2)))
            return 1;
        if (x < (sideBarWidth + (sqWidth * 4)) && x > (sideBarWidth + (sqWidth * 3)))
            return 2;
        if (x < (sideBarWidth + (sqWidth * 5)) && x > (sideBarWidth + (sqWidth * 4)))
            return 3;
        else return -1;
    }
    private int posToGridSquare(Point pos) {
        //0-3
        if (pos.y < sqHeight * 5 && pos.y > sqHeight * 6)
             return xToCol(pos.x);
        //4-7
        else if (pos.y < sqHeight * 4 && pos.y > sqHeight * 5)
            return xToCol(pos.x) + 4;
        //8-11
        else if (pos.y < sqHeight * 3 && pos.y > sqHeight * 4)
            return xToCol(pos.x) + 8;
        //12-15
        else if (pos.y < sqHeight * 2 && pos.y > sqHeight * 3)
            return xToCol(pos.x) + 12;
        //16-19
        else if (pos.y < sqHeight && pos.y > sqHeight * 2)
            return xToCol(pos.x) + 16;
        //20-23
        else if (pos.y > sqHeight)
            return xToCol(pos.x) + 20;
        else
            return -1;
    }

    /* Helpers that determine if a move can legally be performed */
    private Boolean legalFwd() {
        int heroSq = posToGridSquare(this.hero.getPosition());
        if (heroSq >= 0 || heroSq <= 23) {
            //TODO: determine which way he's facing and check in correct direction
            if (this.Levels.get(currLev - 1).getCurrGrid().get(heroSq + 4).getX())
                return true;
            else
                return false;
        }
        else
            return false;
    }

    private Boolean legalStab() {
        //TODO: implement this
        return false;
    }

    private Boolean legalCond() {
        //TODO: implement this
        return false;
    }

    private Boolean legalLoop() {
        //TODO: implement this
        return false;
    }

    /* Run through list of moves, performing one move per frame */
    private void runMoves() {
        ArrayList<Moves> mvs = this.Levels.get(currLev - 1).getMovesTaken();
        if (mvsCount > mvs.size()) {
            mvsCount = 0;
            this.moving = false;
        }
        switch(mvs.get(mvsCount)) {
            case FORWARD:
                //legalFwd()
                //move up
                break;
            case ROTATE:
                //animate
                break;
            case STAB:
                //legalStab()
                //animate
                //update enemy state and
                break;
            case COND:
                //legalCond()
                break;
            case LOOP3:
                //legalLoop()
                break;
            default:
                break;
        }
        //iterate through movesTaken (one update at a time, use mvsCount)
        //update character's pos based on mvsCount and Levels[currLev - 1].getMovesTaken()

        this.mvsCount++;
    }

    public void drawMovesAvail(Graphics g) {

        /* Move Buttons */
        JButton turn = new JButton(new ImageIcon("resources/turn.png"));
        turn.setBounds(109,640, 75,75);
        turn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Levels.get(currLev).getMovesTaken().add(Moves.ROTATE);
            }
        });

        JButton fwd = new JButton(new ImageIcon("resources/forward.png"));
        fwd.setBounds(22,640, 75,75);
        fwd.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Levels.get(currLev).getMovesTaken().add(Moves.FORWARD);
            }
        });
        if(this.Levels != null) {
            for(int i=0; i<this.Levels.get(currLev).getMovesAvail().size(); i++){
                switch(Levels.get(currLev).getMovesAvail().get(i)) {
                    case FORWARD:
                        super.getScenePanel().add(fwd);
                        break;
                    case ROTATE:
                        super.getScenePanel().add(turn);
                        break;
                    case STAB:

                        break;
                    case COND:

                        break;
                    case LOOP3:

                        break;
                    default:
                        break;
                }
            }
        }

    }

    public void drawMovesTaken(Graphics g) {
        if(this.Levels!= null){
            ArrayList<Moves> moves = this.Levels.get(currLev).getMovesTaken();
            for(int i=0; i<moves.size(); i++) {
                switch(moves.get(i)) {
                    case FORWARD:
                        Sprite fw = new Sprite("Foward" + i, "forward.png");
                        fw.setPosition(new Point(490+(i%5)*86,48+89*(i/5)));
                        fw.draw(g);
                        break;
                    case ROTATE:
                        Sprite rt = new Sprite("Rotate" + i, "turn.png");
                        rt.setPosition(new Point(490+(i%5)*86,48+89*(i/5)));
                        rt.draw(g);
                        break;
                    case STAB:

                        break;
                    case COND:

                        break;
                    case LOOP3:

                        break;
                    default:
                        break;
                }
            }


        }
    }

    @Override
    public void update(ArrayList<Integer> pressedKeys){
        super.update(pressedKeys);

		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */

//        if(clicked on run button) {
//            runningMovesState = true;
//            runMoves();
//        }
    }


    @Override
    public void draw(Graphics g) {
        //check characters for null

        super.draw(g);

        if (allLevels != null) allLevels.draw(g);
        if (moves != null) moves.draw(g);
        drawMovesAvail(g);
        drawMovesTaken(g);

    }

    public static void main(String[] args) {
        edu.virginia.FinalProject game = new edu.virginia.FinalProject();
        game.start();
    }

}
