package edu.virginia.engine.display;

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

    // recursive draw tree
    public void drawChildren(ArrayList<DisplayObject> c) {
        if (this.children == c){
            return;
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

           
			/* Actually draw the image, perform the pivot point translation here */

//            if(this.getVisible()) {
//                g2d.drawImage(super.getDisplayImage(), 0 , 0, (int) (getUnscaledWidth()),
//                        (int) (getUnscaledHeight()),null);
//            }

			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
            reverseTransformations(g2d);
        }
    }


}
