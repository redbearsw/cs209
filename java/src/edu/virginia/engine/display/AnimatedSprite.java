package edu.virginia.engine.display;

import edu.virginia.engine.util.GameClock;


import java.awt.image.BufferedImage;
import java.awt.Point;
import java.util.ArrayList;

public class AnimatedSprite extends Sprite {

    private ArrayList<animation> animations;
    private Boolean playing;
    private String fileName;
    private ArrayList<BufferedImage> frames;
    private int currentFrame;
    private int startFrame;
    private int endFrame;
    static final int DEFAULT_ANIMATION_SPEED = 60;
    private int animationSpeed;
    private GameClock gameClock;

    public void AnimatedSprite(String id, String fn, Point pos) {
        this.initGameClock();
    }

    public void initGameClock() {
        if (this.gameClock == null)
            this.gameClock = new GameClock();
    }
}
