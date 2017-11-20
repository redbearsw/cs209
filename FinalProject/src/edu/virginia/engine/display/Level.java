package edu.virginia.engine.display;
import edu.virginia.engine.util.Tuple;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JButton;
import edu.virginia.engine.util.Obstacles;
import edu.virginia.engine.util.Moves;

public class Level {
    /* Level Class fields:
     *
     * initGrid and currGrid are arrays with each index corresponding to a square on the game grid
     * * array goes left to right, bottom to top on grid
     * * the Boolean spot indicates whether that grid square can be entered
     * * the Obstacles spot indicates what object is in that grid square
     * * Obstacles: NOTHING, WALL, ENEMY, BARRICADE, START, GOAL
     * * initGrid does not change, and contains the starting state of the grid for that level
     * * currGrid is updated to reflect the character's actions
     *
     * movesAvail is an array of the buttons that are allowed to be used in that level
     *
     * movesTaken is an array of Moves that correspond to actions the character can take (buttons pressed)
     * * Moves: FORWARD, ROTATE, STAB, COND, LOOP3
     *
     * position indicates where the top left of the grid is in the whole image
     *
     * id indicates which number level this is
     */
    private ArrayList <Tuple <Boolean, Obstacles>> initGrid;
    private ArrayList <Tuple <Boolean, Obstacles>> currGrid;
    private ArrayList <JButton> movesAvail;
    private ArrayList <Moves> movesTaken;
    private Point position;
    private int id;

    /* enum Moves and enum GridSquares */
    //possibilities for move buttons

    //things that can occupy grid squares


    /* Constructor */
    public Level(ArrayList <Tuple <Boolean, Obstacles>> iG, ArrayList <JButton> mvAv, Point position, int id) {
        this.setId(id);
        this.setPosition(position);
        this.setMovesAvail(mvAv);
        this.setMovesTaken(new ArrayList<Moves>());
        this.setInitGrid(iG);
        this.setCurrGrid(iG);

    }

    /* getters and setters */
    public ArrayList <Tuple <Boolean, Obstacles>> getInitGrid() {return this.initGrid;}
    public  void setInitGrid(ArrayList <Tuple <Boolean, Obstacles>> grid) {this.initGrid = grid;}

    public ArrayList <Tuple <Boolean, Obstacles>> getCurrGrid() {return this.currGrid;}
    public  void setCurrGrid(ArrayList <Tuple <Boolean, Obstacles>> grid) {this.currGrid = grid;}

    public ArrayList <JButton> getMovesAvail() {return this.movesAvail;}
    public void setMovesAvail(ArrayList <JButton> moves) {this.movesAvail = moves;}

    public Point getPosition() {return this.position;}
    public void setPosition(Point pos) {this.position = pos;}

    public int getId() {return this.id;}
    public void setId(int n) {this.id = n;}

    public ArrayList<Moves> getMovesTaken() {return this.movesTaken;}
    public void setMovesTaken(ArrayList<Moves> moves) {this.movesTaken = moves;}



    /* updateCurrGrid updates the state of a given grid square */
    void updateCurrGrid(int square, Boolean enter, Obstacles state) {
        this.getCurrGrid().add(square, new Tuple <Boolean, Obstacles> (enter, state));
    }



}
