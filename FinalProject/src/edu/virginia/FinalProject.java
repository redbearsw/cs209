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
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import edu.virginia.engine.util.Obstacles;
import edu.virginia.engine.util.Moves;

public class FinalProject extends Game {

    /* Sprites */
    private Sprite allLevels; // background image
    private Sprite moves; // image that gets populated with moves
    private Sprite hero; // character
    private Sprite enemy; // enemy character
    private Sprite select; // highlighted next slot box
    private Sprite oneStar; // one star full
    private Sprite twoStar; // two stars full
    private Sprite threeStar; // three stars full


    /* Various game states and trackers */
    private int speed; // number of frames it takes to run one move, transition to new level
    private int mvsCount; // tracks which move is currently running
    private int runCount; // increments every frame that runMoves is called
    private int numLevs; // total number of levels
    private int currLev; // current level
    private Boolean moving; // true if currently running through moves
    private Boolean winState; // true if the level has been won
    private int transition; // counts # of steps taken when in transition, otherwise 0
    private int movesTaken; // number of moves taken to win (only set upon victory)

    /* List of Levels */
    //slot 1 for level 1, etc.
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

        /* Positions on screen */
        this.sideBarWidth = 24;
        this.mazeWidth = 423;
        this.mazeHeight = 636;
        this.gameWidth = 940;
        this.gameHeight = 728;
        this.charWidth = 96;
        this.charHeight = 103;
        this.sqWidth = 106;
        this.sqHeight = 106;
        this.borderWidth = 32;

        /* Sprites */
        this.allLevels = new Sprite("All Levels", "levels.png");
            this.allLevels.setPosition(new Point (sideBarWidth, 0));
        this.moves = new Sprite("Move Board", "moves.png");
            this.moves.setPosition(new Point(469,0));
        this.hero = new Sprite("Hero", "character.png");
            this.hero.setPosition(new Point(32,106*5));
            this.hero.setPivotPoint(new Point (this.hero.getUnscaledWidth() / 2, this.hero.getUnscaledHeight() / 2));
        this.select = new Sprite ("Select", "nextPlace.png");
            this.select.setPosition(new Point(499, 53));
        this.threeStar = new Sprite("ThreeStar", "threeStar.png");
        this.twoStar = new Sprite ("TwoStar", "twoStar.png");
        this.oneStar = new Sprite("OneStar", "oneStar.png");

        /* Game States*/
        this.speed = 30;
        this.mvsCount = 0;
        this.runCount = 0;
        this.currLev = 1;
        this.numLevs = 3;
        this.moving = false;
        this.winState = false;
        this.transition = 0;

        /* ArrayList of levels */
        this.Levels = new ArrayList<Level>();
        int i;
        for (i = 0; i < numLevs; i++)
            Levels.add(null);



        /* Levels */

        /* Level 1 */
        //initial grid
        ArrayList<Tuple<Boolean, Obstacles>> initGrid1 = createInitGrid();
        initGrid1.set(0, new Tuple<>(true, Obstacles.START));
        initGrid1.set(2, new Tuple<>(false, Obstacles.WALL));
        initGrid1.set(3, new Tuple<>(false, Obstacles.WALL));
        initGrid1.set(6, new Tuple<>(false, Obstacles.WALL));
        initGrid1.set(7, new Tuple<>(false, Obstacles.WALL));
        initGrid1.set(12, new Tuple<>(false, Obstacles.WALL));
        initGrid1.set(16, new Tuple<>(false, Obstacles.WALL));
        initGrid1.set(18, new Tuple<>(false, Obstacles.WALL));
        initGrid1.set(20, new Tuple<>(false, Obstacles.WALL));
        initGrid1.set(21, new Tuple<>(false, Obstacles.WALL));
        initGrid1.set(22, new Tuple<>(false, Obstacles.WALL));
        initGrid1.set(23, new Tuple<>(true, Obstacles.GOAL));

        //available moves
        ArrayList<Moves> mvsAvail1 = new ArrayList<>();
        mvsAvail1.add(Moves.FORWARD);
        mvsAvail1.add(Moves.ROTATE);

        //position
        Point pos1 = new Point(0, 2 * (mazeHeight - sqHeight));

        //creating level 1
        Level lev1 = new Level(initGrid1, mvsAvail1, pos1, 1, 12, 0 ,23);

        this.Levels.add(1, lev1);

        /* Level 2 */
        //initial grid
        ArrayList<Tuple<Boolean, Obstacles>> initGrid2 = createInitGrid();
        initGrid2.set(3, new Tuple<>(true, Obstacles.START));
        initGrid2.set(0, new Tuple<>(false, Obstacles.WALL));
        initGrid2.set(1, new Tuple<>(false, Obstacles.WALL));
        initGrid2.set(4, new Tuple<>(false, Obstacles.WALL));
        initGrid2.set(7, new Tuple<>(false, Obstacles.WALL));
        initGrid2.set(8, new Tuple<>(false, Obstacles.WALL));
        initGrid2.set(10, new Tuple<>(false, Obstacles.WALL));
        initGrid2.set(11, new Tuple<>(false, Obstacles.WALL));
        initGrid2.set(20, new Tuple<>(false, Obstacles.WALL));
        initGrid2.set(12, new Tuple<>(false, Obstacles.WALL));
        initGrid2.set(14, new Tuple<>(false, Obstacles.WALL));
        initGrid2.set(15, new Tuple<>(false, Obstacles.WALL));
        initGrid2.set(16, new Tuple<>(false, Obstacles.WALL));
        initGrid2.set(18, new Tuple<>(false, Obstacles.WALL));
        initGrid2.set(19, new Tuple<>(false, Obstacles.WALL));
        initGrid2.set(20, new Tuple<>(false, Obstacles.WALL));
        initGrid2.set(23, new Tuple<>(false, Obstacles.WALL));
        initGrid2.set(22, new Tuple<>(true, Obstacles.GOAL));
        initGrid2.set(13, new Tuple<>(false, Obstacles.BARRICADE));

        //available moves
        ArrayList<Moves> mvsAvail2 = new ArrayList<>();
        mvsAvail2.add(Moves.FORWARD);
        mvsAvail2.add(Moves.ROTATE);
        mvsAvail2.add(Moves.STAB);
        mvsAvail2.add(Moves.LOOP3);

        //position
        Point pos2 = new Point(0, mazeHeight - sqHeight);

        //creating level 2
        Level lev2 = new Level(initGrid2, mvsAvail2, pos2, 2, 14, 3, 22);

        this.Levels.add(2, lev2);

        /* Level 3 */
        //initial grid
        ArrayList<Tuple<Boolean, Obstacles>> initGrid3 = createInitGrid();
        initGrid3.set(2, new Tuple<>(true, Obstacles.START));
        initGrid3.set(0, new Tuple<>(false, Obstacles.WALL));
        initGrid3.set(3, new Tuple<>(false, Obstacles.WALL));
        initGrid3.set(8, new Tuple<>(false, Obstacles.WALL));
        initGrid3.set(10, new Tuple<>(false, Obstacles.WALL));
        initGrid3.set(11, new Tuple<>(false, Obstacles.WALL));
        initGrid3.set(12, new Tuple<>(false, Obstacles.WALL));
        initGrid3.set(14, new Tuple<>(false, Obstacles.WALL));
        initGrid3.set(15, new Tuple<>(false, Obstacles.WALL));
        initGrid3.set(16, new Tuple<>(false, Obstacles.WALL));
        initGrid3.set(18, new Tuple<>(false, Obstacles.WALL));
        initGrid3.set(19, new Tuple<>(false, Obstacles.WALL));
        initGrid3.set(16, new Tuple<>(false, Obstacles.WALL));
        initGrid3.set(18, new Tuple<>(false, Obstacles.WALL));
        initGrid3.set(19, new Tuple<>(false, Obstacles.WALL));
        initGrid3.set(17, new Tuple<>(false, Obstacles.ENEMY));
        initGrid3.set(9, new Tuple<>(false, Obstacles.ENEMY));
        initGrid3.set(20, new Tuple<>(true, Obstacles.GOAL));


        //available moves
        ArrayList<Moves> mvsAvail3 = new ArrayList<>();
        mvsAvail3.add(Moves.FORWARD);
        mvsAvail3.add(Moves.ROTATE);
        mvsAvail3.add(Moves.STAB);
        mvsAvail3.add(Moves.LOOP3);
        mvsAvail3.add(Moves.COND);

        //position
        Point pos3 = new Point(0, 0);

        //creating level 3
        Level lev3 = new Level(initGrid3, mvsAvail3, pos3, 3, 14, 2, 20);

        this.Levels.add(3, lev3);

    }

    /* Create and zero out initial grid */
    private ArrayList<Tuple<Boolean, Obstacles>> createInitGrid() {
        ArrayList<Tuple<Boolean, Obstacles>> grid = new ArrayList<Tuple<Boolean, Obstacles>>(24);
        int i;
        for (i = 0; i < 24; i++)
            grid.add(i, new Tuple<Boolean, Obstacles>(true, Obstacles.NOTHING));
        return grid;
    }

    /* Helper that returns column given x coordinate */
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

    /* Converts a global position into its grid square */
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

    /* Converts a grid square into the global position of its top left corner */
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
    //checks if forward move is legal
    private Boolean legalFwd() {
        int newSq = this.fwdSq();
        if (newSq > -1)
            return this.Levels.get(currLev).getCurrGrid().get(newSq).getX();
        else
            return false;
    }

    //checks if stab is legal
    private Boolean legalStab() {
        return (this.Levels.get(currLev).getCurrGrid().get(this.fwdSq()).getY() == Obstacles.ENEMY ||
                this.Levels.get(currLev).getCurrGrid().get(this.fwdSq()).getY() == Obstacles.BARRICADE);
    }

    //checks if loop is legal
    private Boolean legalLoop() {
        //TODO: implement this
        return false;
    }

    //checks if conditional is legal
    private Boolean legalCond() {
        //TODO: implement this
        return false;
    }


    /* Updates game state to run through moves. Moves get executed in this.speed number of frames */
    private void runMoves() {
        ArrayList<Moves> mvs = this.Levels.get(currLev).getMovesTaken();
        if (mvs != null && !mvs.isEmpty()) {
            switch (mvs.get(mvsCount)) {
                case FORWARD:
                    if (runCount % speed == 0) {
                        if (legalFwd()) {
                            hero.setPosition(this.gridSquareToPos(this.fwdSq()));

                            // for more levels, need to write a find goal function
                            if (this.posToGridSquare(hero.getPosition()) == 23) {
                                winState = true;
                                movesTaken = mvs.size();
                            }
                        } else
                            moving = false;
                    } else if (runCount % speed == speed - 1)
                        mvsCount += 1;
                    break;
                case ROTATE:
                    if (runCount % speed == 0) {
                        if (hero.getRotation() == 270) {
                            hero.setRotation(0);
                        } else {
                            hero.setRotation(hero.getRotation() + 90);
                        }
                    } else if (runCount % speed == speed - 1)
                        mvsCount += 1;

                    break;
                case STAB:
                    if (runCount % speed == 0) {
                        if (this.legalStab()) {
                            //stabbing animation
                            this.Levels.get(currLev).getCurrGrid().get(this.fwdSq()).setX(true);
                            this.Levels.get(currLev).getCurrGrid().get(this.fwdSq()).setY(Obstacles.NOTHING);
                            if (this.Levels.get(currLev).getCurrGrid().get(this.fwdSq()).getY() == Obstacles.ENEMY) {
                                this.enemy.setVisible(false);
                            } else if (this.Levels.get(currLev).getCurrGrid().get(this.fwdSq()).getY() == Obstacles.BARRICADE) {
                                //update barricade image to next image
                            }
                        }
                    }
                    else {
                        //falling over animation
                        //lost game
                    }
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

            this.runCount++;

            //set mvsCount to 0 and moving to false when all moves complete
            if (mvsCount >= mvs.size()) {
                mvsCount = 0;
                this.moving = false;
            }

        }
    }

    /* Draws the buttons for the moves that are available in the current level */
    private void drawMovesAvail(Graphics g) {

        int left = 10;
        int gap = 12;
        int width = 75;
        int height = 75;
        int top = 640;

        /* Move Buttons */
        JButton fwd = new JButton(new ImageIcon("resources/forward.png"));
        fwd.setBounds(left + gap, top, width, height);
        fwd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Levels.get(currLev).getMovesTaken().add(Moves.FORWARD);
            }
        });

        JButton turn = new JButton(new ImageIcon("resources/turn.png"));
        turn.setBounds(left + width + (2 * gap), top, width, height);
        turn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Levels.get(currLev).getMovesTaken().add(Moves.ROTATE);
            }
        });

        JButton stab = new JButton("Stab");
        stab.setBounds(left + (2 * width) + (3 * gap), top, width, height);
        stab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Levels.get(currLev).getMovesTaken().add(Moves.STAB);
            }
        });

        JButton loop = new JButton ("X3");
        loop.setBounds(left + (3 * width) + (4 * gap), top, width, height);
        loop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Levels.get(currLev).getMovesTaken().add(Moves.LOOP3);
            }
        });

        JButton condStab = new JButton("Cond Stab");
        condStab.setBounds(left + (4 * width) + (5 * gap), top, width, height);
        condStab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Levels.get(currLev).getMovesTaken().add(Moves.COND);
            }
        });




        if (this.Levels != null && this.Levels.get(currLev) != null) {
            for (int i = 0; i < this.Levels.get(currLev).getMovesAvail().size(); i++) {
                switch (Levels.get(currLev).getMovesAvail().get(i)) {
                    case FORWARD:
                        super.getScenePanel().add(fwd);
                        break;
                    case ROTATE:
                        super.getScenePanel().add(turn);
                        break;
                    case STAB:
                        super.getScenePanel().add(stab);
                        break;
                    case COND:
                        super.getScenePanel().add(condStab);
                        break;
                    case LOOP3:
                        super.getScenePanel().add(loop);
                        break;
                    default:
                        break;
                }
            }
        }

    }

    /* Draws the moves that have been taken in the grid */
    public void drawMovesTaken(Graphics g) {
        if (this.Levels != null && this.Levels.get(currLev) != null) {
            ArrayList<Moves> moves = this.Levels.get(currLev).getMovesTaken();
            if(moves.size() == 0){
                this.select.setPosition(new Point(499, 53));
                select.draw(g);
            }
            for(int i = 0; i < moves.size(); i++) {
                switch(moves.get(i)) {
                    case FORWARD:
                        Sprite fw = new Sprite("Forward" + i, "forward.png");
                        fw.setPosition(new Point(497 + (i % 5) * 85,51 + 94 * (i/5)));
                        fw.draw(g);
                        if(this.select!=null){
                            this.select.setPosition(new Point(499 + ((i + 1) % 5) * 85, 53 + 94 * ((i + 1)/5)));
                            this.select.draw(g);
                        }
                        break;
                    case ROTATE:
                        Sprite rt = new Sprite("Rotate" + i, "turn.png");
                        rt.setPosition(new Point(490 + (i % 5) * 86, 48 + 89 * (i / 5)));
                        rt.setPosition(new Point(497 + (i % 5) * 85,51 + 94 * (i/5)));
                        rt.draw(g);
                        if(this.select!=null){
                            this.select.setPosition(new Point(499 + ((i + 1) % 5) * 85, 53 + 94 * ((i + 1)/5)));
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

    /* Displays score on victory */
    public void onVictory(Graphics g) {
        // display score
        int bS = this.Levels.get(currLev).getBestScore();
        if(movesTaken <= bS){
            if(threeStar != null){
                threeStar.draw(g);
            }
        } else if (movesTaken < bS + 2) {
            if(twoStar != null){twoStar.draw(g);}
        } else {
            if(oneStar != null){oneStar.draw(g);}
        }

        // display next and restart buttons
        JButton next = new JButton("Next");
        next.setBounds(430, 250 , 100, 25);


        JButton restart = new JButton("Restart");
        restart.setBounds(0,0, 100, 25);

        super.getScenePanel().add(next);
        super.getScenePanel().add(restart);



        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currLev < numLevs)
                    transition = 1;
                    winState = false;
            }
        });

        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hero.setPosition(gridSquareToPos(Levels.get(currLev).getStartSquare()));
                hero.setRotation(0);
                runCount = 0;
                winState = false;
            }});

//        if (!winState) {
//            getScenePanel().remove(next);
//            getScenePanel().remove(restart);
//        }

    }


    @Override
    public void update(ArrayList<Integer> pressedKeys) {
        super.update(pressedKeys);

        if (moving)
            runMoves();
        if (transition != 0)
                super.getScenePanel().removeAll();
        if (transition > speed) {
            if (currLev < 3)
                currLev += 1;
            transition = 0;
        }

    }


    @Override
    public void draw(Graphics g) {

       super.draw(g);

       // offset for background and hero during transition
        int offset = ((mazeHeight - sqHeight) * transition / speed);

        // draw background image for current level
        if (this.Levels != null && this.Levels.get(currLev) != null) {
           if (allLevels != null) {
               Point src = this.Levels.get(currLev).getPosition();

               // crops image to correct maze or transitioning maze
               BufferedImage subImg = allLevels.getDisplayImage().getSubimage(src.x,
                       src.y - offset, mazeWidth, mazeHeight);
               Sprite lev = new Sprite("level");
               lev.setImage(subImg);
               lev.setPosition(allLevels.getPosition());
               lev.draw(g);

               //increment transition
               if (transition != 0) {
                   transition += 1;
               }
            }
       }

       // draw hero, moving it with background if transitioning
        if (hero != null) {
           if (transition > 0) {
               hero.setPosition(new Point(hero.getPosition().x,
                       this.gridSquareToPos(this.Levels.get(currLev).getWinSquare()).y + offset));
           }
           hero.draw(g);
       }

       // draw all moves
       if (moves != null)
           moves.draw(g);
       drawMovesAvail(g);
       drawMovesTaken(g);

       // display score and transition buttons on victory
       if(winState)
           onVictory(g);

       /* Run, Backspace, Clear Buttons (TO BE REPLACED WITH IMAGES) */
       // create buttons
       JButton run = new JButton("Run");
       run.setBounds(500, 250, 100, 25);
       run.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               if (Levels.get(currLev).getMovesTaken() != null && !Levels.get(currLev).getMovesTaken().isEmpty())
                   moving = true;
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
               hero.setPosition(gridSquareToPos(Levels.get(currLev).getStartSquare()));
               hero.setRotation(0);
               runCount = 0;
           }
       });

       // adds run, clear, back buttons to screen
        super.getScenePanel().add(run);
        super.getScenePanel().add(clear);
        super.getScenePanel().add(back);
        super.getScenePanel().add(reset);
    }

    public static void main(String[] args) {
        edu.virginia.FinalProject game = new edu.virginia.FinalProject();
        game.start();

    }

}


