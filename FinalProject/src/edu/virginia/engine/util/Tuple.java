package edu.virginia.engine.util;

public class Tuple <X, Y> {
    public X x;
    public Y y;
    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }
    public setX(X x) { this.x = x;}
    public setY(Y y) {this.y = y;}
}
