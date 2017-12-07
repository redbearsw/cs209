package edu.virginia;

import edu.virginia.engine.display.*;
import edu.virginia.engine.util.Tuple;
import edu.virginia.engine.util.SoundManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import edu.virginia.engine.util.Obstacles;
import edu.virginia.engine.util.Moves;

public class FinalProject extends Game {

    /* Sprites */
    private Sprite allLevels; // background image
    private Sprite moves; // image that gets populated with moves
    private AnimatedSprite hero; // character
    private Sprite select; // highlighted next slot box
    private Sprite oneStar; // one star full
    private Sprite twoStar; // two stars full
    private Sprite threeStar; // three stars full
    private Sprite barricade; // barricade
    private Sprite background; // grey background
    private Sprite endFrame;

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
    private int randomNum1;
    private int randomNum2;
    private int frameCount;

    /* List of Levels */
    //slot 1 for level 1, etc.
    private ArrayList<Level> Levels;

    /* Variables to keep track of where things are on the screen */
    private int sideBarWidth;

    private int mazeWidth;
    private int mazeHeight;

    private int gameWidth;
    private int gameHeight;

    private int charWidth;
    private int charHeight;

    private int sqWidth;
    private int sqHeight;

    private int borderWidth;

    /* Music and Sound Effects */

    private SoundManager sounds = new SoundManager();

    /* JButtons */
    private JButton run;
    private JButton back;
    private JButton clear;
    private JButton restart;
    private JButton next;

    /* Constructor */
    public FinalProject() {
        super("Final Project", 940, 748);

        /* Positions on screen */
        this.sideBarWidth = 24;
        this.mazeWidth = 423;
        this.mazeHeight = 636;
        this.gameWidth = 940;
        this.gameHeight = 728;
        this.charWidth = 103;
        this.charHeight = 127;
        this.sqWidth = 106;
        this.sqHeight = 106;
        this.borderWidth = 32;

        /* Sprites */
        this.background = new Sprite ("background", "background.png");
        this.allLevels = new Sprite("All Levels", "levels.png");
            this.allLevels.setPosition(new Point (sideBarWidth, 0));
        this.moves = new Sprite("Move Board", "moves.png");
            this.moves.setPosition(new Point(469,0));
        this.hero = new AnimatedSprite("Hero", "frame00.png");
            hero.setPosition(new Point(this.gridSquareToPos(0).x + 1, this.gridSquareToPos(0).y -21));
            this.hero.setPivotPoint(new Point (52, 75));
        this.select = new Sprite ("Select", "nextPlace.png");
            this.select.setPosition(new Point(499, 53));
        this.threeStar = new Sprite("ThreeStar", "threeStar.png");
        this.twoStar = new Sprite ("TwoStar", "twoStar.png");
        this.oneStar = new Sprite("OneStar", "oneStar.png");
        this.barricade = new Sprite("barricade1", "barricade1.png");
            this.barricade.setPosition(gridSquareToPos(13));
        this.endFrame = new Sprite("endFrame", "end.png");




        /* Populating Animation Frames */
        /* Stab */

        this.hero.addFrame("frame00.png");
        this.hero.addFrame("frame01.png");
        this.hero.addFrame("frame02.png");
        this.hero.addFrame("frame03.png");
        this.hero.addFrame("frame04.png");
        this.hero.addFrame("frame05.png");
        this.hero.addFrame("frame06.png");
        this.hero.addFrame("frame07.png");
        this.hero.addFrame("frame08.png");
        this.hero.addFrame("frame09.png");
        this.hero.addFrame("frame10.png");
        this.hero.addFrame("frame11.png");
        this.hero.addFrame("frame12.png");

        Animation an = new Animation("stab", 0, 11);
        hero.setAnimations(an);

        /* confused */
        this.hero.addFrame("confusedframe00.png");
        this.hero.addFrame("confusedframe01.png");
        this.hero.addFrame("confusedframe02.png");
        this.hero.addFrame("confusedframe03.png");
        this.hero.addFrame("confusedframe04.png");
        this.hero.addFrame("confusedframe05.png");
        this.hero.addFrame("confusedframe06.png");
        this.hero.addFrame("confusedframe07.png");
        this.hero.addFrame("confusedframe08.png");
        this.hero.addFrame("confusedframe09.png");
        this.hero.addFrame("confusedframe10.png");
        this.hero.addFrame("confusedframe11.png");
        this.hero.addFrame("confusedframe12.png");

        an = new Animation("confused", 12, 23);
        hero.setAnimations(an);

        /* Adding Sound Effects */
        sounds.LoadSoundEffect("Huh", "huh.wav");

        /* Game States*/
        this.speed = 15;
        this.mvsCount = 0;
        this.runCount = 0;
        this.currLev = 1;
        this.numLevs = 3;
        this.moving = false;
        this.winState = false;
        this.transition = 0;
        this.frameCount = 0;

         /* Run, Backspace, Clear Buttons */
        // create buttons
        this.run = new JButton(new ImageIcon("resources/play.png"));
        run.setBounds(500, 420, 93, 67);
        run.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Levels.get(currLev).getMovesTaken() != null && !Levels.get(currLev).getMovesTaken().isEmpty() && !moving) {
                    moving = true;
                    frameCount = 0;
                }
            }
        });

        this.back = new JButton(new ImageIcon("resources/backspace.png"));
        back.setBounds(610,420, 93,67);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Levels.get(currLev).getMovesTaken().size()!=0 && !moving) {
                    if(Levels.get(currLev).getMovesTaken().get(Levels.get(currLev).getMovesTaken().size()-1)
                            == Moves.ENDLOOP) {
                        Levels.get(currLev).getMovesTaken().remove(Levels.get(currLev).getMovesTaken().size() - 1);
                        Levels.get(currLev).getMovesTaken().remove(Levels.get(currLev).getMovesTaken().size() - 1);
                        Levels.get(currLev).getMovesTaken().remove(Levels.get(currLev).getMovesTaken().size() - 1);
                        Levels.get(currLev).getMovesTaken().remove(Levels.get(currLev).getMovesTaken().size() - 1);
                    } else {
                        Levels.get(currLev).getMovesTaken().remove(Levels.get(currLev).getMovesTaken().size() - 1);
                    }
                }
            }
        });
        this.clear = new JButton(new ImageIcon("resources/clear.png"));
        clear.setBounds(720,420, 93,67);
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!moving) {
                    Levels.get(currLev).getMovesTaken().clear();
                }
            }
        });


        // adds run, clear, back buttons to screen
        super.getScenePanel().add(run);
        super.getScenePanel().add(clear);
        super.getScenePanel().add(back);

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
        Level lev1 = new Level(initGrid1, mvsAvail1, pos1, 1, 12, 20,0 ,23);

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
        Level lev2 = new Level(initGrid2, mvsAvail2, pos2, 2, 16, 19,3, 22);

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
        Level lev3 = new Level(initGrid3, mvsAvail3, pos3, 3, 15, 16,2, 22);

        this.Levels.add(3, lev3);

        // restart and next buttons
         /* next and restart buttons */
        next = new JButton(new ImageIcon("resources/next.png"));
        next.setBounds(360, 450, 75, 75);
        super.getScenePanel().add(next);
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currLev < numLevs)
                    transition = 1;
                winState = false;
                mvsCount = 0;
            }
        });

        restart = new JButton(new ImageIcon("resources/restart.png"));
        restart.setBounds(480,450, 75, 75);
        super.getScenePanel().add(restart);

        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetPos();
                runCount = 0;
                winState = false;
                mvsCount = 0;
                barricade.setVisible(true);
                barricade.setDisplayImage("barricade1.png");
                barricade.setId("barricade1");
                restart.setVisible(false);
                next.setVisible(false);
                Levels.get(currLev).setCurrGrid(Levels.get(currLev).getInitGrid());
            }
        });
        restart.setVisible(false);
        next.setVisible(false);

    }


    /* Create and zero out initial grid */
    private ArrayList<Tuple<Boolean, Obstacles>> createInitGrid() {
        ArrayList<Tuple<Boolean, Obstacles>> grid = new ArrayList<Tuple<Boolean, Obstacles>>(24);
        int i;
        for (i = 0; i < 24; i++)
            grid.add(i, new Tuple<Boolean, Obstacles>(true, Obstacles.NOTHING));
        return grid;
    }

    /* Resets Hero's position to grid one */
    private void resetPos(){
        hero.setPosition(new Point(gridSquareToPos(Levels.get(currLev).getStartSquare()).x,
                gridSquareToPos(Levels.get(currLev).getStartSquare()).y - 21));
        this.hero.setRotation(0);
        Levels.get(currLev).setCurrGrid(Levels.get(currLev).getInitGrid());
        barricade.setVisible(true);
        barricade.setDisplayImage("barricade1.png");
        barricade.setId("barricade1");

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
        int heroSq = posToGridSquare(new Point (this.hero.getPosition().x, this.hero.getPosition().y + 25));
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
        if (newSq > -1) {
            return this.Levels.get(currLev).getCurrGrid().get(newSq).getX();
        }
        else
            return false;
    }

    //checks if stab is legal
    private Boolean legalStab() {
        Tuple <Boolean, Obstacles> nxtSq = this.Levels.get(currLev).getCurrGrid().get(this.fwdSq());
        return (nxtSq.getY() == Obstacles.ENEMY ||
                nxtSq.getY() == Obstacles.BARRICADE);
    }

    //checks if loop is legal
    private Boolean legalLoop() {
        if(mvsCount + 1 < this.Levels.get(currLev).getMovesTaken().size()) {
            Moves mv = this.Levels.get(currLev).getMovesTaken().get(mvsCount + 1);
            return ((mv != null) && (mv != Moves.COND) && (mv != Moves.LOOP3));
        }
        return false;
    }

    //checks if conditional is legal
    private Boolean legalCond() {
        Moves mv = this.Levels.get(currLev).getMovesTaken().get(mvsCount + 1);
        return ((mv != null) && (mv != Moves.COND));
    }

    /* Updates game state to run through moves. Moves get executed in speed number of frames */
    private void runMoves() {
        ArrayList<Moves> mvs = this.Levels.get(currLev).getMovesTaken();
        if (mvs != null && !mvs.isEmpty()) {
            switch (mvs.get(mvsCount)) {
                case FORWARD:
                    if (runCount % speed == 0) {
                        if (legalFwd()) {
                            hero.setPosition(this.gridSquareToPos(this.fwdSq()));
                            hero.setPosition(new Point (hero.getPosition().x, hero.getPosition().y - 21));
                            if (this.posToGridSquare(new Point (hero.getPosition().x, hero.getPosition().y + 24))
                                    == this.Levels.get(currLev).getWinSquare()) {
                                winState = true;
                            }
                        } else {
                            this.hero.setPlaying(true);
                            this.hero.animate("confused");
                            sounds.PlaySoundEffect("Huh");
                            moving = false;
                        }
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
                    if (runCount % speed == speed - 1)
                        mvsCount += 1;
                    if (runCount % speed == 0) {

                        if (this.legalStab()) {
                            Tuple<Boolean, Obstacles> nxtSq = this.Levels.get(currLev).getCurrGrid().get(this.fwdSq());

                                this.hero.setPlaying(true);
                                this.hero.animate("stab");

                                if (nxtSq.getY() == Obstacles.ENEMY) {
                                    nxtSq.setX(true);
                                    nxtSq.setY(Obstacles.NOTHING);
                                }
                            if (nxtSq.getY() == Obstacles.BARRICADE) {

                                if(this.barricade.getId().equals("barricade1")) {
                                    this.barricade.setId("barricade2");
                                    this.barricade.setDisplayImage("barricade2.png");
                                } else if(this.barricade.getId().equals("barricade2")){
                                    this.barricade.setId("barricade3");
                                    this.barricade.setDisplayImage("barricade3.png");
                                } else {
                                    barricade.setVisible(false);
                                    nxtSq.setX(true);
                                    nxtSq.setY(Obstacles.NOTHING);

                                }
                            }
                        }
                    else {
                            this.hero.setPlaying(true);
                            this.hero.animate("confused");
                            sounds.PlaySoundEffect("Huh");
                            moving = false;
                        }
                    }
                    break;
                case LOOP3:
                    if (this.legalLoop()) {
                        Moves inner = mvs.get(mvsCount + 1);
                        mvs.add(mvsCount + 1, inner);
                        mvs.add(mvsCount + 1, inner); // adds move 2x because it's already there once
                        mvs.add(mvsCount + 4, Moves.ENDLOOP);
                        runCount += speed - 1;
                        mvsCount += 1;
                    }
                    else {
                        this.hero.setPlaying(true);
                        this.hero.animate("confused");
                        sounds.PlaySoundEffect("Huh");
                        moving = false;
                    }
                    break;
                case COND:
                    if (legalCond()) {
                        if (runCount % speed == 0) {
                            Tuple <Boolean, Obstacles> nxtSq = this.Levels.get(currLev).getCurrGrid().get(this.fwdSq());
                            //if enemy, run next move
                            if (nxtSq.getY() == Obstacles.ENEMY && !nxtSq.getX()) {
                                mvsCount += 1;
                                runCount -= 1;
                            }

                            else {
                                nxtSq.setY(Obstacles.ENEMY);
                                nxtSq.setX(true);
                                mvsCount += 2;
                                runCount -= 1;
                            }
                        }
                    }
                    else {
                        this.hero.setPlaying(true);
                        this.hero.animate("confused");
                        sounds.PlaySoundEffect("Huh");
                        moving = false;
                    }
                    break;
                case ENDLOOP:
                    mvsCount += 1;
                    runCount -= 1;
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
                if(resetLoop(new ArrayList<> (Levels.get(currLev).getMovesTaken())).size()
                        < Levels.get(currLev).getMoveLimit()) {
                    Levels.get(currLev).getMovesTaken().add(Moves.FORWARD);
                }

            }
        });

        JButton turn = new JButton(new ImageIcon("resources/turn.png"));
        turn.setBounds(left + width + (2 * gap), top, width, height);
        turn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(resetLoop(new ArrayList<> (Levels.get(currLev).getMovesTaken())).size()
                        < Levels.get(currLev).getMoveLimit()) {
                    Levels.get(currLev).getMovesTaken().add(Moves.ROTATE);
                }
            }
        });

        JButton stab = new JButton(new ImageIcon("resources/stab.png"));
        stab.setBounds(left + (2 * width) + (3 * gap), top, width, height);
        stab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(resetLoop(new ArrayList<> (Levels.get(currLev).getMovesTaken())).size()
                        < Levels.get(currLev).getMoveLimit()) {
                    Levels.get(currLev).getMovesTaken().add(Moves.STAB);
                }
            }
        });

        JButton loop = new JButton (new ImageIcon("resources/loop.png"));
        loop.setBounds(left + (3 * width) + (4 * gap), top, width, height);
        loop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(resetLoop(new ArrayList<> (Levels.get(currLev).getMovesTaken())).size()
                        < Levels.get(currLev).getMoveLimit()) {
                    Levels.get(currLev).getMovesTaken().add(Moves.LOOP3);
                }
            }
        });

        JButton condStab = new JButton(new ImageIcon("resources/conditional.png"));
        condStab.setBounds(left + (4 * width) + (5 * gap), top, width, height);
        condStab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(resetLoop(new ArrayList<> (Levels.get(currLev).getMovesTaken())).size()
                        < Levels.get(currLev).getMoveLimit()) {
                    Levels.get(currLev).getMovesTaken().add(Moves.COND);
                }
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
    /* Draw Moves Taken helper - to be used on loops*/
    private ArrayList<Integer> indexOfAll(Object obj, ArrayList list){
        ArrayList<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++)
            if(obj.equals(list.get(i)))
                indexList.add(i);
        return indexList;
    }
    /* Draw Moves Taken helper - removes extra moves added by runMoves */
    private ArrayList<Moves> resetLoop(ArrayList<Moves> moves){

        ArrayList<Integer> loopLocs = indexOfAll(Moves.LOOP3,moves);

        int size = loopLocs.size();
        for(int i = 0; i<size; i++)
        {
            loopLocs = indexOfAll(Moves.LOOP3,moves);
            if(loopLocs.get(i)+4 <= moves.size()-1){
                if(moves.get(loopLocs.get(i)+4) == Moves.ENDLOOP) {
                    moves.remove(loopLocs.get(i)+2);
                    moves.remove(loopLocs.get(i)+2);
                    moves.remove(loopLocs.get(i)+2);
                }
            }

        }

        return moves;
    }
    /* Draws the moves that have been taken in the grid */
    public void drawMovesTaken(Graphics g) {

        int sqDraw = 0;

        if (this.Levels != null && this.Levels.get(currLev) != null) {
            ArrayList<Moves> moves = new ArrayList<>(this.Levels.get(currLev).getMovesTaken());
            moves = resetLoop(moves);

            if(moves.size() == 0){
                this.select.setPosition(new Point(499, 53));
                select.draw(g);
            }
            if(endFrame !=null){
                endFrame.setPosition(new Point (497+(((this.Levels.get(currLev).getMoveLimit())-1)%5)*85+75,
                        45 + 94 * 3));
                endFrame.draw(g);
            }
            for(int i = 0; i < moves.size(); i++) {
                switch (moves.get(i)) {
                    case FORWARD:
                        Sprite fw = new Sprite("Forward" + i, "forward.png");
                        fw.setPosition(new Point(497 + (sqDraw % 5) * 85, 51 + 94 * (sqDraw / 5)));
                        if(i - 1 >= 0) {
                            if (moves.get(i - 1) == Moves.LOOP3) {
                                fw.setScaleX(.5);
                                fw.setScaleY(.5);
                                if (fw.getPosition().x >= 497 && fw.getPosition().x < 572) {
                                    fw.setPosition(new Point(fw.getPosition().x + 18 + 340, fw.getPosition().y + 18 - 94));

                                }
                                else {
                                    fw.setPosition(new Point(fw.getPosition().x + 18 - 85, fw.getPosition().y + 18));
                                }

                                sqDraw--;

                            }
                        }
                        if(i - 1 >= 0) {
                            if (moves.get(i - 1) == Moves.COND) {
                                fw.setScaleX(.3);
                                fw.setScaleY(.3);
                                if (fw.getPosition().x >= 497 && fw.getPosition().x < 572) {
                                    fw.setPosition(new Point(fw.getPosition().x + 44 + 340, fw.getPosition().y + 45 - 94));

                                }
                                else {
                                    fw.setPosition(new Point(fw.getPosition().x + 44 - 85, fw.getPosition().y + 45));
                                }

                                sqDraw--;

                            }
                        }
                        fw.draw(g);
                        break;
                    case ROTATE:
                        Sprite rt = new Sprite("Rotate" + i, "turn.png");
                        rt.setPosition(new Point(497 + (sqDraw % 5) * 85, 51 + 94 * (sqDraw / 5)));
                        if(i-1 >= 0) {
                            if (moves.get(i - 1) == Moves.LOOP3) {
                                rt.setScaleX(.5);
                                rt.setScaleY(.5);
                                if (rt.getPosition().x >= 497 && rt.getPosition().x < 572) {
                                    rt.setPosition(new Point(rt.getPosition().x + 18 + 340, rt.getPosition().y + 18 - 94));

                                }
                                else {
                                    rt.setPosition(new Point(rt.getPosition().x + 18 - 85, rt.getPosition().y + 18));
                                }
                                sqDraw--;
                            }
                        }
                        if(i - 1 >= 0) {
                            if (moves.get(i - 1) == Moves.COND) {
                                rt.setScaleX(.3);
                                rt.setScaleY(.3);
                                if (rt.getPosition().x >= 497 && rt.getPosition().x < 572) {
                                    rt.setPosition(new Point(rt.getPosition().x + 44 + 340, rt.getPosition().y + 45 - 94));

                                }
                                else {
                                    rt.setPosition(new Point(rt.getPosition().x + 44 - 85, rt.getPosition().y + 45));
                                }

                                sqDraw--;

                            }
                        }
                        rt.draw(g);

                        break;
                    case STAB:
                        Sprite st = new Sprite("Stab" + i, "stab.png");
                        st.setPosition(new Point(497 + (sqDraw % 5) * 85, 51 + 94 * (sqDraw / 5)));
                        if(i-1 >= 0) {
                            if (moves.get(i - 1) == Moves.LOOP3) {
                                sqDraw--;
                                st.setScaleX(.5);
                                st.setScaleY(.5);
                                if (st.getPosition().x >= 497 && st.getPosition().x < 572) {
                                    st.setPosition(new Point(st.getPosition().x + 18 + 340, st.getPosition().y + 18 - 94));

                                }
                                else {
                                    st.setPosition(new Point(st.getPosition().x + 18 - 85, st.getPosition().y + 18));
                                }

                            }
                        }
                        if(i - 1 >= 0) {
                            if (moves.get(i - 1) == Moves.COND) {
                                st.setScaleX(.3);
                                st.setScaleY(.3);
                                if (st.getPosition().x >= 497 && st.getPosition().x < 572) {
                                    st.setPosition(new Point(st.getPosition().x + 44 + 340, st.getPosition().y + 45 - 94));

                                }
                                else {
                                    st.setPosition(new Point(st.getPosition().x + 44 - 85, st.getPosition().y + 45));
                                }

                                sqDraw--;

                            }
                        }
                        st.draw(g);

                        break;
                    case COND:
                        Sprite cd = new Sprite("Conditional" + i, "conditional.png");
                        cd.setPosition(new Point(497 + (sqDraw % 5) * 85, 51 + 94 * (sqDraw / 5)));
                        cd.draw(g);
                        break;
                    case LOOP3:
                        Sprite lp = new Sprite("Loop" + i, "loop.png");
                        lp.setPosition(new Point(497 + (sqDraw % 5) * 85, 51 + 94 * (sqDraw / 5)));
                        lp.draw(g);
                        break;
                    default:
                        break;
                }
                    if (this.select != null) {
                        this.select.setPosition(new Point(499 + ((sqDraw + 1) % 5) * 85, 53 + 94 * ((sqDraw + 1) / 5)));
                        this.select.draw(g);
                    }
                    sqDraw++;
                }



        }
    }


    /* Displays score on victory */
    public void onVictory(Graphics g) {
        // resetting loops in moves taken to properly account for score
        ArrayList<Moves> finalScr = resetLoop(new ArrayList<>(this.Levels.get(currLev).getMovesTaken()));
        int bS = this.Levels.get(currLev).getBestScore();
        if (finalScr.size() <= bS) {
            if (threeStar != null) {
                threeStar.draw(g);
            }
        } else if (finalScr.size() < bS + 2) {
            if (twoStar != null) {
                twoStar.draw(g);
            }
        } else {
            if (oneStar != null) {
                oneStar.draw(g);
            }
        }
        moving = false;

        run.setVisible(false);
        clear.setVisible(false);
        back.setVisible(false);

        // display next and restart buttons
            restart.setVisible(true);
            next.setVisible(true);
            if(currLev == 3){
                next.setVisible(false);
            }
    }


    @Override
    public void update(ArrayList<Integer> pressedKeys) {
        super.update(pressedKeys);

        if (moving!=null && moving) {
            runMoves();
        }
        if (transition != 0){
            restart.setVisible(false);
            next.setVisible(false);
            super.getScenePanel().add(run);
            super.getScenePanel().add(clear);
            super.getScenePanel().add(back);
        }

        if (transition > (speed * 2)) {
            if (currLev < 3)
                currLev += 1;
            hero.setRotation(0);
            transition = 0;
            mvsCount = 0;
            runCount = 0;
        }
        frameCount++;
        if(frameCount % speed == 0 && !moving && !winState && transition==0){
            resetPos();
            runCount = 0;
            mvsCount = 0;
            this.Levels.get(currLev).setMovesTaken(resetLoop(this.Levels.get(currLev).getMovesTaken()));
            this.Levels.get(currLev).setCurrGrid(this.Levels.get(currLev).getInitGrid());

        }

        super.getScenePanel().revalidate();
        super.getScenePanel().repaint();
    }


    @Override
    public void draw(Graphics g) {

       super.draw(g);

        if(background!=null) {background.draw(g);}
       // offset for background and hero during transition
        int offset = ((mazeHeight - sqHeight) * transition / (speed * 2));

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
                       this.gridSquareToPos(this.Levels.get(currLev).getWinSquare()).y + offset -21));
           }
           hero.draw(g);
       }

       // draw enemy in level 3

        if (currLev == 3) {
            ArrayList <Tuple<Boolean, Obstacles>> grid = this.Levels.get(currLev).getCurrGrid();

            if (grid.get(9).getY() == Obstacles.ENEMY) {

                //enemy sprite
                Sprite enemy1 = new Sprite("Enemy1", "enemy.png");

                //generate new number every 30 frames, unless in the middle of dealing with a conditional block
                if ((frameCount % speed == 0)) {
                    Random numGen = new Random();
                    if (mvsCount < 1 || this.Levels.get(currLev).getMovesTaken().get(mvsCount - 1) != Moves.COND || this.fwdSq() != 8) {
                        randomNum1 = numGen.nextInt(2);
                    }
                }

                // set enemy positions to randomly chosen square
                enemy1.setPosition(gridSquareToPos(8 + randomNum1));

                // set square occupied by enemy to true, unoccupied square to false
                grid.get(8 + randomNum1).setX(false);
                grid.get(8 + randomNum1).setY(Obstacles.ENEMY);


                grid.get(8 + (1 - randomNum1)).setX(true);
                grid.get(8 + (1 - randomNum1)).setY(Obstacles.ENEMY);

                // if enemy still an obstacle in that square (i.e. not removed by STAB), it's visible
                if (grid.get(8 + randomNum1).getY() == Obstacles.ENEMY)
                    enemy1.setVisible(true);
                else
                    enemy1.setVisible(false);

                // draw enemies
                enemy1.draw(g);


            }
            if (grid.get(17).getY() == Obstacles.ENEMY) {

                //enemy sprite
                Sprite enemy2 = new Sprite("Enemy2", "enemy.png");

                //generate new number every 30 frames, unless in the middle of dealing with a conditional block
                if ((frameCount % speed == 0)) {
                    Random numGen = new Random();
                    if (mvsCount < 1 || this.Levels.get(currLev).getMovesTaken().get(mvsCount - 1) != Moves.COND || this.fwdSq() != 17) {
                        randomNum2 = numGen.nextInt(2);
                    }
                }

                // set enemy positions to randomly chosen square
                enemy2.setPosition(gridSquareToPos(16 + randomNum2));

                // set square occupied by enemy to true, unoccupied square to false
                grid.get(16 + randomNum2).setX(false);
                grid.get(16 + randomNum2).setY(Obstacles.ENEMY);


                grid.get(16 + (1 - randomNum2)).setX(true);
                grid.get(16 + (1 - randomNum2)).setY(Obstacles.ENEMY);

                // if enemy still an obstacle in that square (i.e. not removed by STAB), it's visible
                if (grid.get(16 + randomNum2).getY() == Obstacles.ENEMY)
                    enemy2.setVisible(true);
                else
                    enemy2.setVisible(false);

                // draw enemies
                enemy2.draw(g);
            }

        }

        // draws barricade on level 3
        if(currLev == 2){
            barricade.draw(g);
        }

       // draw all moves
       if (moves != null)
           moves.draw(g);
       drawMovesAvail(g);
       drawMovesTaken(g);

       // display score and transition buttons on victory
//        if(run!=null && clear!=null && back!=null) {
//            super.getScenePanel().add(run);
//            super.getScenePanel().add(clear);
//            super.getScenePanel().add(back);
//
//        }

        if (winState) {
            onVictory(g);
            mvsCount = 0;
        } else {
            if(clear!=null && back!=null && run!=null) {
                clear.setVisible(true);
                back.setVisible(true);
                run.setVisible(true);
            }
        }
    }

    public static void main(String[] args) {
        edu.virginia.FinalProject game = new edu.virginia.FinalProject();
        game.start();

    }

}


