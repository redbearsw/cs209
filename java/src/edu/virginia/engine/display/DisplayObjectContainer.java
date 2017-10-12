package edu.virginia.engine.display;
import java.util.ArrayList;

public class DisplayObjectContainer extends DisplayObject{
    private ArrayList<DisplayObject> children;

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
    }

    //rmvAtIndex
    public DisplayObject rmvAtIndex(int i) {
        return this.children.remove(i);
    }

    //rmvAll
    public DisplayObject rmvAll() {
        int i;
        for(i = 0; i < this.children.size(); i++) {
            rmvAtIndex(i);
        }
    }

}
