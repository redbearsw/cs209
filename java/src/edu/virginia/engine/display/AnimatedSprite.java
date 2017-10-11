package edu.virginia.engine.display;

import edu.virginia.engine.util.GameClock;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimatedSprite extends Sprite {

    private ArrayList<Animation> animations;
    private Boolean playing;
    private String fileName;
    private ArrayList<BufferedImage> frames;
    private int currentFrame;
    private int startFrame;
    private int endFrame;
    static final int DEFAULT_ANIMATION_SPEED = 60;
    private int animationSpeed;
    private GameClock gameClock;
    private int frameCount;

    public void AnimatedSprite(String id, String fn, Point pos) {
        this.initGameClock();
        this.setId(id);
        this.setImage(fn);
        this.setPosition(pos);
        this.setAnimationSpeed(60);
    }

    public void initGameClock() {
        if (this.gameClock == null)
            this.gameClock = new GameClock();
    }

    private void setAnimationSpeed(int spd) {
        this.animationSpeed = spd;
    }
    private void addFrame(String imageName) {
        if (imageName == null) {
            return;
        }
        BufferedImage frame = readImage(imageName);
        frames.add(frame);
    }

    // getter/setter for animations

    private Animation getAnimation(String id){
        int i;
        for (i = 0; i < this.animations.size(); i++) {
            if(animations[i].id.equals(id)) {
                return animations[i];
            }
            return null;
        }
    }

    private Animation setAnimation(String id, int sF, int eF){
        new Animation(id, sF, eF);
    }

    // getters/setters for frames

    public void setStartFrame(int sF) {this.startFrame = sF;}
    public int getStartFrame() {return this.startFrame;}
    public void setEndFrame(int eF) {this.endFrame = eF;}
    public int getEndFrame() {return this.endFrame;}
    public void setCurrentFrame(int cF) {this.currentFrame = cF;}
    public int getCurrentFrame() {return this.currentFrame;}

    public void setCount(int c) {this.frameCount = c;}
    public int getCount() {return this.frameCount;}

    private void animate(Animation an){
        this.setStartFrame(an.getStartFrame());
        this.setEndFrame(an.getEndFrame());
    }

    private Animation animate(String id) {
        return getAnimation(id);
    }
    
    private void animate(int st, int end) {
        this.setStartFrame(st);
        this.setEndFrame(end);
    }

    public void draw(Graphics g) {

        Animation an = getAnimation(super.getId());
        int sf = an.getStartFrame();
        int ef = an.getEndFrame();
        int cf = this.getCurrentFrame();
        BufferedImage frame = super.getDisplayImage();
        if (super.getFrameCount() % this.animationSpeed == 0) {

            frame = this.frames.get(cf);
            if (cf == ef) {
                this.setCurrentFrame(sf - 1);
            }
            this.setCurrentFrame(this.getCurrentFrame() + 1);
        }

            Graphics2D g2d = (Graphics2D) g;
            applyTransformations(g2d);

			/* Actually draw the image, perform the pivot point translation here */
            if(super.getVisible()) {
                g2d.drawImage(frame, 0, 0, (int) (getUnscaledWidth()),
                        (int) (getUnscaledHeight()), null);
            }

			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
            reverseTransformations(g2d);

        }

}

