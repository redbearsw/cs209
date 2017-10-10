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
        this.setId(id);
        this.setImage(fn);
        this.setPosition(pos);
        this.setAnimationSpeed(1);
    }

    public void initGameClock() {
        if (this.gameClock == null)
            this.gameClock = new GameClock();
    }

    private void setAnimationSpeed(spd) {
        this.animationSpeed = spd;
    }
    private void addFrame(String imageName) {
        if (imageName == null) {
            return;
        }
        BufferedImage frame = readImage(imageName);
        frames.add(frame);
    }
    private animation getAnimation(String id){
        int i;
        for (i = 0; i < this.animations.size(); i++) {
            if(animations[i].id.equals(id)) {
                return animations[i];
            }
            return null;
        }
    }

    private void animate(Animation an){
        this.setStartFrame(an.getStartFrame());
        this.setEndFrame(an.getEndFrame());
    }

    private animation animate(String id) {
        return getAnimation(id);
    }
    
    private void animate(startFrame st, endFrame end) {
        this.setStartFrame(st);
        this.setEndFrame(end);
    }

}
