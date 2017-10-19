package edu.virginia;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.Point;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;

import edu.virginia.engine.display.Animation;

import edu.virginia.engine.display.AnimatedSprite;


/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabFourSimulator extends Game {

    /* Create a sprite object for our game. We'll use sun */

    Sprite sun = new Sprite("Sun", "Artboard 1.png");
    Sprite planet = new Sprite("Planet", "planet.png");


    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     */
    public LabFourSimulator() {
        super("Lab Four Simulator", 800, 400);
    }


    public void addPlanets() {
        sun.setPosition(new Point (300,150));
        sun.setPivotPoint(new Point (sun.getUnscaledHeight()/2, sun.getUnscaledWidth()/2));
        sun.addChild(planet);
        planet.setPosition(new Point (-20,30));

    }
    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     */
    @Override
    public void update(ArrayList<Integer> pressedKeys) {
        super.update(pressedKeys);

		/* Make sure sun is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
        if (sun != null) sun.update(pressedKeys);

        if (sun.getCount() < 30) {
            sun.setCount(sun.getCount() + 1);
        }
       planet.setRotation(planet.getRotation() - 1);


        /* scale mario */
        if (pressedKeys.contains(KeyEvent.VK_Q)) {
            sun.setScaleX(sun.getScaleX() * 1.1);
            sun.setScaleY(sun.getScaleY() * 1.1);
            
        }
        if (pressedKeys.contains(KeyEvent.VK_W)) {
            sun.setScaleX(sun.getScaleX() * .9);
            sun.setScaleY(sun.getScaleY() * .9);

        }
        if(pressedKeys.contains(KeyEvent.VK_DOWN)){
            sun.setPosition(new Point(sun.getPosition().x, sun.getPosition().y-5));
        }
        if(pressedKeys.contains(KeyEvent.VK_UP)){
            sun.setPosition(new Point(sun.getPosition().x, sun.getPosition().y+5));
        }
        if(pressedKeys.contains(KeyEvent.VK_RIGHT)){
            sun.setPosition(new Point(sun.getPosition().x-5, sun.getPosition().y));
        }
        if(pressedKeys.contains(KeyEvent.VK_LEFT)){
            sun.setPosition(new Point(sun.getPosition().x+5, sun.getPosition().y));
        }

		/* rotation counterclockwise and clockwise */
        if(pressedKeys.contains(KeyEvent.VK_A)){
            sun.setRotation(sun.getRotation() - 10);
        }
        if(pressedKeys.contains(KeyEvent.VK_S)) {
            sun.setRotation(sun.getRotation() + 10);
        }

        planet.setPivotPoint(new Point (-planet.getPosition().x + sun.getUnscaledWidth()/2,
                -planet.getPosition().y + sun.getUnscaledHeight()/2));

    }

    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure sun gets drawn to
     * the screen, we need to make sure to override this method and call sun's draw method.
     */
    @Override
    public void draw(Graphics g) {
        super.draw(g);

		/* Same, just check for null in case a frame gets thrown in before sun is initialized */
        if (sun != null) sun.draw(g);
    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     */
    public static void main(String[] args) {
        LabFourSimulator game = new LabFourSimulator();
        game.addPlanets();

        game.start();


    }
}
