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
     *
     * bestScore indicates the best score one could get on this level
     */
    private ArrayList <Tuple <Boolean, Obstacles>> initGrid;
    private ArrayList <Tuple <Boolean, Obstacles>> currGrid;
    private ArrayList <Moves> movesAvail;
    private ArrayList <Moves> movesTaken;
    private Point position;
    private int id;
    private int bestScore;
    private int winSquare;
    private int startSquare;
    private int moveLimit;

    /* enum Moves and enum GridSquares */
    //possibilities for move buttons

    //things that can occupy grid squares


    /* Constructor */
    public Level(ArrayList <Tuple <Boolean, Obstacles>> iG, ArrayList <Moves> mvAv, Point position, int id, int bestScore, int moveLimit, int strt, int end) {
        this.setId(id);
        this.setPosition(position);
        this.setMovesAvail(mvAv);
        this.setMovesTaken(new ArrayList<Moves>());
        this.setInitGrid(iG);
        this.setCurrGrid(iG);
        this.setBestScore(bestScore);
        this.setWinSquare(end);
        this.setStartSquare(strt);
        this.setMoveLimit(moveLimit);

    }

    /* getters and setters */
    public ArrayList <Tuple <Boolean, Obstacles>> getInitGrid() {return this.initGrid;}
    public  void setInitGrid(ArrayList <Tuple <Boolean, Obstacles>> grid) {
        this.initGrid = new ArrayList<>();
        int i;
        for (i = 0; i < grid.size(); i++) {
            if (i < initGrid.size())
                this.initGrid.set(i, new Tuple<>(grid.get(i).getX(), grid.get(i).getY()));
            else
                this.initGrid.add(i, new Tuple<>(grid.get(i).getX(), grid.get(i).getY()));
        }

    }

    public ArrayList <Tuple <Boolean, Obstacles>> getCurrGrid() {return this.currGrid;}
    public  void setCurrGrid(ArrayList <Tuple <Boolean, Obstacles>> grid) {
        this.currGrid = new ArrayList<>();

        int i;
        for (i = 0; i < grid.size(); i++) {
            if (i < currGrid.size())
                this.currGrid.set(i, new Tuple<>(grid.get(i).getX(), grid.get(i).getY()));
            else
                this.currGrid.add(i, new Tuple<>(grid.get(i).getX(), grid.get(i).getY()));
        }
    }

    public ArrayList <Moves> getMovesAvail() {return this.movesAvail;}
    public void setMovesAvail(ArrayList <Moves> moves) {this.movesAvail = moves;}

    public Point getPosition() {return this.position;}
    public void setPosition(Point pos) {this.position = pos;}

    public int getId() {return this.id;}
    public void setId(int n) {this.id = n;}

    public ArrayList<Moves> getMovesTaken() {return this.movesTaken;}
    public void setMovesTaken(ArrayList<Moves> moves) {this.movesTaken = moves;}

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public void setWinSquare(int sq) {this.winSquare = sq;}
    public int getWinSquare() {return this.winSquare;}

    public void setStartSquare(int sq) {this.startSquare = sq;}
    public int getStartSquare() {return this.startSquare;}

    public void setMoveLimit(int lm) {this.moveLimit = lm;}

    public int getMoveLimit() {
        return moveLimit;
    }

    /* updateCurrGrid updates the state of a given grid square */
    void updateCurrGrid(int square, Boolean enter, Obstacles state) {
        this.getCurrGrid().add(square, new Tuple <Boolean, Obstacles> (enter, state));
    }



}
