package edu.virginia.engine.display;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.AlphaComposite;

import javax.imageio.ImageIO;


public class DisplayObjectContainer extends DisplayObject{

    private ArrayList<DisplayObject> children;

    // constructors (same as DisplayObject)
    public DisplayObjectContainer(String id) {
        this.setId(id);
        this.setPosition(new Point (0, 0));
        this.setPivotPoint(new Point (0, 0));
        this.setRotation(0);
        this.setVisible(true);
        this.setAlpha(1.0f);
        this.setOldAlpha(0.0f);
        this.setScaleX(1.0);
        this.setScaleY(1.0);
        this.setCount(30);
        this.children = new ArrayList<DisplayObject>();
    }

    public DisplayObjectContainer(String id, String fileName) {
        this.setId(id);
        this.setImage(fileName);
        this.setPosition(new Point (0, 0));
        this.setPivotPoint(new Point (0, 0));
        this.setRotation(0);
        this.setVisible(true);
        this.setAlpha(1.0f);
        this.setOldAlpha(0.0f);
        this.setScaleX(1.0);
        this.setScaleY(1.0);
        this.setCount(30);
        this.children = new ArrayList<DisplayObject>();
    }



    // Returns true iff given display object is already a child of this container
    public Boolean contains(DisplayObject DO) {
        return this.children.contains(DO);
    }

    // Returns child given the index of it in children
    public DisplayObject getChild(int index) {
        return this.children.get(index);
    }

    // Returns child given its id
    public DisplayObject getChildId(String id) {
        int size = this.children.size();
        int i;
        for (i = 0; i<size; i++) {
            if(this.children.get(i).getId().equals(id)) {
                return this.children.get(i);
            }
        }
        System.out.println("CHILD DOES NOT EXIST");
        return null;

    }
    
    // update

    public void update(ArrayList<Integer> pressedKeys) {


        super.update(pressedKeys);

        if (children!=null) {
            for (int i = 0; i < children.size(); i++) {

                children.get(i).update(pressedKeys);
            }
        }

    }

    // draw
    public void draw(Graphics g) {

        if (super.getDisplayImage() != null) {

			/*
			 * Get the graphics and apply this objects transformations
			 * (rotation, etc.)
			 */
            super.draw(g);

            Graphics2D g2d = (Graphics2D) g;
            applyTransformations(g2d);
            if(children!=null) {
                for (int i = 0; i < children.size(); i++) {
                    children.get(i).draw(g);
                }
            }

            reverseTransformations(g2d);
        }
    }



    //getter
    public ArrayList<DisplayObject> getChildren() {
        return this.children;
    }

    //add methods

    //addChild adds Child to the end of the ArrayList
    public void addChild(DisplayObject obj) {
        this.children.add(obj);
    }

    //addAtIndex
    public void addAtIndex(int i, DisplayObject obj) {
        this.children.add(i, obj);
    }

    //remove methods

    //rmvChild
    public DisplayObject rmvChild(String id) {
        int i;
        int sz = this.children.size();
        for(i = 0; i < sz; i++){
            if(id == this.children.get(i).getId()) {
                DisplayObject child = this.children.get(i);
                this.children.set(i, this.children.set(sz - 1, this.children.get(sz - 1)));
                this.children.set(sz, null);
                return child;
            }
        }
        System.out.println("CHILD DOES NOT EXIST, RETURNING NULL");
        return null;
    }

    //rmvAtIndex
    public DisplayObject rmvAtIndex(int i) {
        return this.children.remove(i);
    }

    //rmvAll
    public void rmvAll() {
        int i;
        for(i = 0; i < this.children.size(); i++) {
            rmvAtIndex(i);
        }
    }

    public DisplayObject findChild(String id) {
        int i;
        int sz = this.children.size();
        for (i = 0; i < sz; i++) {
            if (id == this.children.get(i).getId()) {
                return this.getChild(i);
            }
        }
        return null;
    }
    public void printArray(int[] anArray) {
        for (int i = 0; i < anArray.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(anArray[i]);
            }
    }


    //find methods

    //findChild at given index
    public DisplayObject findChild(int index) {
        return this.children.get(index);
        }

    public int[] applyRotate(int[] coords) {
        //retrieve l and theta
        double theta = Math.toRadians(this.getRotation());
        Point globPos = this.localToGlobal(this.getPosition());
        Point globPiv = this.localToGlobal(this.getPivotPoint());
        double l = Math.sqrt(Math.pow(globPos.x - globPiv.x, 2) + Math.pow(globPos.y - globPiv.y, 2));
        //calculate x and y
        double x = l * Math.sin(theta);
        double y = l - (l * Math.cos(theta));

        //shift boundaries based on rotation
        coords[0] += (int) x;
        coords[1] += (int) x;
        coords[2] += (int) y;
        coords[3] += (int) y;

        return coords;

    }


    public int[] getHitbox() {

        //find hitbox
        DisplayObject hb = this.findChild(0);
        //set up array
        int[] coords = new int[4];

        //find initial x and y boundaries of hitbox
        int x1 = hb.getPosition().x;
        int x2 = x1 + hb.getUnscaledWidth();
        int y1 = hb.getPosition().y;
        int y2 = y1 + hb.getUnscaledHeight();

        // System.out.println("LOCAL COORDS - X1: " + x1 + "X2: " + x2 + "Y1: " + y1 + "Y2: " + y2);

        //convert boundaries to global coordinates
        Point xy1 = new Point (this.getPosition().x + x1, this.getPosition().y + y1);
        Point xy2 = new Point (this.getPosition().x + x2, this.getPosition().y + y2);
        //Point xy1 = hb.localToGlobal(new Point (x1, y1));
        //Point xy2 = hb.localToGlobal(new Point (x2, y2));
        x1 = xy1.x;
        x2 = xy2.x;
        y1 = xy1.y;
        y2 = xy2.y;

        // System.out.println("GLOBAL COORDS - X1: " + x1 + "X2: " + x2 + "Y1: " + y1 + "Y2: " + y2);
        //store in array
        coords[0] = x1;
        coords[1] = x2;
        coords[2] = y1;
        coords[3] = y2;

        //rotate boundaries if necessary
        coords = this.applyRotate(coords);

        //convert to scaled if necessary
        coords = this.applyScale(coords);

        //reorder array if rotation has flipped the shape
        //reorder x's
        if(coords[0] > coords[1]) {
            x1 = coords[1];
            coords[1] = coords[0];
            coords[0] = x1;
        }
        //reorder y's
        if(coords[2] > coords[3]) {
            y1 = coords[3];
            coords[3] = coords[2];
            coords[2] = y1;
        }

        return coords;
    }

    public int[] applyScale(int[] coords){

        if (coords.length != 4){
            System.out.println("ERROR: NOT FULL SET OF COORDS");
        }
        int[] scaledCoords;
        int xdiff;
        int ydiff;
        scaledCoords = new int[4];

        xdiff = (this.getScaledWidth(this.getScaleX()) - this.getUnscaledWidth())/2;
        ydiff = (this.getScaledHeight(this.getScaleY()) - this.getUnscaledHeight())/2;

        scaledCoords[0] = coords[0] - xdiff;
        scaledCoords[1] = coords[1] - xdiff;
        scaledCoords[2] = coords[2] - ydiff;
        scaledCoords[3] = coords[3] - ydiff;

        return scaledCoords;
    }

    public boolean collidesWith(DisplayObjectContainer other){
        int [] myHitbox;
        int [] otherHitbox;
        myHitbox = new int[4];
        otherHitbox = new int[4];

        otherHitbox = other.getHitbox();
        myHitbox = this.getHitbox();

        if(otherHitbox[0] >= myHitbox[0] && (otherHitbox[0] <= myHitbox[1])){
            if(otherHitbox[2] >= myHitbox[2] && (otherHitbox[2] <= myHitbox[3])) {
                return true;
            }
        }
        if(otherHitbox[1] >= myHitbox[0] && (otherHitbox[1] <= myHitbox[1])){
            if(otherHitbox[2] >= myHitbox[2] && (otherHitbox[2] <= myHitbox[3])) {
                return true;
            }
        }

        if(otherHitbox[1] >= myHitbox[0] && (otherHitbox[1] <= myHitbox[1])){
            if(otherHitbox[3] >= myHitbox[2] && (otherHitbox[3] <= myHitbox[3])) {
                return true;
            }
        }

        if(otherHitbox[0] >= myHitbox[0] && (otherHitbox[0] <= myHitbox[1])){
            if(otherHitbox[3] >= myHitbox[2] && (otherHitbox[3] <= myHitbox[3])) {
                return true;
            }
        }

        return false;

    }


}
