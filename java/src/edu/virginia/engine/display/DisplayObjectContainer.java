package edu.virginia.engine.display;
import java.util.ArrayList;

import java.awt.*;
import java.util.ArrayList;

public class DisplayObjectContainer extends DisplayObject{

    private ArrayList<DisplayObject> children;


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

        for (int i = 0; i < children.size(); i++) {
            children.get(i).update(pressedKeys);
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

            for (int i = 0; i < children.size(); i++) {
                children.get(i).draw(g);
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

}
