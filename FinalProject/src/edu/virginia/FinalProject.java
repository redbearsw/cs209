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

public class FinalProject extends Game {

    /* Sprites */
    private Sprite allLevels; //background image
    private Sprite moves; //image that gets populated with moves
    private Sprite hero; //character
    private Sprite select; //highlighted next slot box


    /* Various game states and trackers */
    private int mvsCount; //tracks which move is currently running
    private int numLevs; //total number of levels
    private int currLev; //current level
    private Boolean moving; //true if currently running through moves

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
                this.moves.setPosition(new Point(469,0));
            this.hero = new Sprite("Hero", "character.png");
                this.hero.setPosition(new Point(32,106*5));
                this.hero.setPivotPoint(new Point (this.hero.getUnscaledWidth() / 2, this.hero.getUnscaledHeight() / 2));
            this.select = new Sprite ("Select", "nextPlace.png");
                this.select.setPosition(new Point(499, 53));

        /* Game States*/
            this.mvsCount = 0;
            this.currLev = 1;
            this.numLevs = 3;
            this.moving = false;


        /* Run, Backspace, Clear Buttons (TO BE REPLACED WITH IMAGES) */
            JButton run = new JButton("Run");
            run.setBounds(500, 250, 100, 25);
            run.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    moving = true;
                    runMoves();
                }
            });

            JButton back = new JButton("Back");
            back.setBounds(610,250, 100,25);
            back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (Levels.get(currLev).getMovesTaken().size()!=0) {
                        Levels.get(currLev).getMovesTaken().remove(Levels.get(currLev).getMovesTaken().size() - 1);
                    }
                }
            });
            JButton clear = new JButton("Clear");
            clear.setBounds(720,250, 100,25);
            clear.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Levels.get(currLev).getMovesTaken().clear();
                }
            });

            JButton reset = new JButton("Reset");
            reset.setBounds(830,250, 100,25);
            reset.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    hero.setPosition(new Point(32,106*5));
                    hero.setRotation(0);
                    mvsCount = 0;
                }
            });

        // adds run, clear, back buttons to screen
        super.getScenePanel().add(run);
        super.getScenePanel().add(clear);
        super.getScenePanel().add(back);
        super.getScenePanel().add(reset);

        /* ArrayList of levels */
        this.Levels = new ArrayList<Level>();
        int i;
        for (i = 0; i < numLevs; i++)
            Levels.add(null);


        /* Positions on screen */
            this.sideBarWidth = 24;
            this.mazeWidth = 470;
            this.mazeHeight = 728;
            this.gameWidth = 940;
            this.gameHeight = 728;
            this.charWidth = 96;
            this.charHeight = 103;
            this.sqWidth = 106;
            this.sqHeight = 106;
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
    private ArrayList<Tuple<Boolean, Obstacles>> createInitGrid() {
        ArrayList<Tuple<Boolean, Obstacles>> grid = new ArrayList<Tuple<Boolean, Obstacles>>(24);
        int i;
        for (i = 0; i < 24; i++)
            grid.add(i, new Tuple<Boolean, Obstacles>(true, Obstacles.NOTHING));
        return grid;
    }

    /* Convert position to grid square */
    private int xToCol(int x) {
        if (x < (sideBarWidth + sqWidth) && (x >= sideBarWidth))
            return 0;
        else if (x < (sideBarWidth + (sqWidth * 2)) && x >= (sideBarWidth + sqWidth))
            return 1;
        else if (x < (sideBarWidth + (sqWidth * 3)) && x >= (sideBarWidth + (sqWidth * 2)))
            return 2;
        else if (x < (sideBarWidth + (sqWidth * 4)) && x >= (sideBarWidth + (sqWidth * 3)))
            return 3;
        else
            return -1;
    }

    private int posToGridSquare(Point pos) {
        //0-3
        if (pos.y >= sqHeight * 5 && pos.y < sqHeight * 6) {
            return this.xToCol(pos.x);
        }
            //4-7
        else if (pos.y >= sqHeight * 4 && pos.y < sqHeight * 5)
            return this.xToCol(pos.x) + 4;
            //8-11
        else if (pos.y >= sqHeight * 3 && pos.y < sqHeight * 4)
            return this.xToCol(pos.x) + 8;
            //12-15
        else if (pos.y >= sqHeight * 2 && pos.y < sqHeight * 3)
            return this.xToCol(pos.x) + 12;
            //16-19
        else if (pos.y >= sqHeight && pos.y < sqHeight * 2)
            return this.xToCol(pos.x) + 16;
            //20-23
        else if (pos.y < sqHeight)
            return this.xToCol(pos.x) + 20;
        else
            return -1;
    }

    private Point gridSquareToPos(int sq) {
        int x;
        int y;
        x = sideBarWidth + ((sq % 4) * sqWidth);
        if (sq < 4)
            y = sqHeight * 5;
        else if (sq < 8)
            y = sqHeight * 4;
        else if (sq < 12)
            y = sqHeight * 3;
        else if (sq < 16)
            y = sqHeight * 2;
        else if (sq < 20)
            y = sqHeight;
        else if (sq < 24)
            y = 0;
        else {
            x = -1;
            y = -1;
        }
        return new Point(x, y);
    }
    /* Helper function that returns square in front of hero. Returns -1 if invalid input or fwd square doesn't exist. */
    private int fwdSq() {
        int heroSq = posToGridSquare(this.hero.getPosition());
        if (heroSq >= 0 && heroSq <= 23) {
            ArrayList<Tuple<Boolean, Obstacles>> grid = this.Levels.get(currLev).getCurrGrid();
            if ((hero.getRotation() == 0) && (heroSq < 20)) {
                return heroSq + 4;
            }
            else if ((hero.getRotation() == 90) && (heroSq % 4 != 3)) {
                return heroSq + 1;
            }
            else if ((hero.getRotation() == 180) && (heroSq > 3)) {
                return heroSq - 4;
            }
            else if ((hero.getRotation() == 270) && (heroSq % 4 != 0)) {
                return heroSq - 1;
            }
            else
                return -1;
        }
        else return -1;
    }

    /* Helpers that determine if a move can legally be performed */
    private Boolean legalFwd() {
        int newSq = this.fwdSq();
        if (newSq > -1)
            return this.Levels.get(currLev).getCurrGrid().get(newSq).getX();
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
        ArrayList<Moves> mvs = this.Levels.get(currLev).getMovesTaken();
        if (mvsCount % 30 == 0) {
            switch (mvs.get(mvsCount / 30)) {
                case FORWARD:
                    if (legalFwd())
                        hero.setPosition(this.gridSquareToPos(this.fwdSq()));
                    else
                        moving = false;
                    break;
                case ROTATE:
                    if (hero.getRotation() == 270) {
                        hero.setRotation(0);
                    }
                    else {
                        hero.setRotation(hero.getRotation() + 90);
                    }
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
        }

        this.mvsCount++;

        if ((mvsCount / 30) >= mvs.size()) {
            mvsCount = 0;
            this.moving = false;
        }
    }

    private void drawMovesAvail(Graphics g) {

        /* Move Buttons */
        JButton turn = new JButton(new ImageIcon("resources/turn.png"));
        turn.setBounds(109, 640, 75, 75);
        turn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Levels.get(currLev).getMovesTaken().add(Moves.ROTATE);
            }
        });

        JButton fwd = new JButton(new ImageIcon("resources/forward.png"));
        fwd.setBounds(22, 640, 75, 75);
        fwd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Levels.get(currLev).getMovesTaken().add(Moves.FORWARD);
            }
        });
        if (this.Levels != null) {
            for (int i = 0; i < this.Levels.get(currLev).getMovesAvail().size(); i++) {
                switch (Levels.get(currLev).getMovesAvail().get(i)) {
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
        if (this.Levels != null) {
            ArrayList<Moves> moves = this.Levels.get(currLev).getMovesTaken();
            if(moves.size() == 0){
                this.select.setPosition(new Point(499, 53));
                select.draw(g);
            }
            for(int i = 0; i < moves.size(); i++) {
                switch(moves.get(i)) {
                    case FORWARD:
                        Sprite fw = new Sprite("Forward" + i, "forward.png");
                        fw.setPosition(new Point(497+(i%5)*85,51+94*(i/5)));
                        fw.draw(g);
                        if(this.select!=null){
                            this.select.setPosition(new Point(499+((i+1)%5)*85, 53+94*((i+1)/5)));
                            this.select.draw(g);
                        }
                        break;
                    case ROTATE:
                        Sprite rt = new Sprite("Rotate" + i, "turn.png");
                        rt.setPosition(new Point(490 + (i % 5) * 86, 48 + 89 * (i / 5)));
                        rt.setPosition(new Point(497+(i%5)*85,51+94*(i/5)));
                        rt.draw(g);
                        if(this.select!=null){
                            this.select.setPosition(new Point(499+((i+1)%5)*85, 53+94*((i+1)/5)));
                            this.select.draw(g);
                        }
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
    public void update(ArrayList<Integer> pressedKeys) {
        super.update(pressedKeys);

		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */

        if (moving)
            runMoves();

    }


    @Override
    public void draw(Graphics g) {
        //check characters for null

        super.draw(g);

        if (allLevels != null) allLevels.draw(g);
        if (moves != null) moves.draw(g);
        drawMovesAvail(g);
        drawMovesTaken(g);

        if(hero!=null){hero.draw(g);}

    }

    public static void main(String[] args) {
        edu.virginia.FinalProject game = new edu.virginia.FinalProject();
        game.start();

    }

}


