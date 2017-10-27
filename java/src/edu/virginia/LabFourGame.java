package edu.virginia;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import java.awt.Graphics;
import java.awt.Point;

    /**
     * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
     * although, for now, it won't be a very fun game :)
     */

    public class LabFourGame extends Game {

        /* Create a sprite object for our game. We'll use sun */
        Sprite blank = new Sprite("Screen", "blank.png");
        Sprite sun = new Sprite("Sun", "sun.png");
    /* Create the Sprites*/

        Sprite planet = new Sprite("Planet", "planet.png");
        Sprite planet2 = new Sprite("Planet2", "planet2.png");
        Sprite moon2 = new Sprite("Moon2", "moon2.png");
        Sprite moon = new Sprite("Moon1", "moon1.png"); //child of planet


        /**
         * Constructor. See constructor in Game.java for details on the parameters given
         */
        public LabFourGame() {
            super("Lab Four Simulator", 500, 500);
        }


        public void addObjects() {

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
            if (sun == null) return;

            //frame count
            if (sun.getCount() < 30) {
                sun.setCount(sun.getCount() + 1);
            }

        /* setting planet and moon orbits*/
            planet.setRotation(planet.getRotation() - 2);
            planet2.setRotation(planet2.getRotation() + 1);
            moon2.setRotation(moon2.getRotation() + 5);
            moon.setRotation(moon.getRotation() - 7);

        /* zoom in or out */
            if (pressedKeys.contains(KeyEvent.VK_Q)) {
                blank.setScaleX(blank.getScaleX() * 1.1);
                blank.setScaleY(blank.getScaleY() * 1.1);


            }

            if (pressedKeys.contains(KeyEvent.VK_W)) {

                blank.setScaleX(blank.getScaleX() * .9);
                blank.setScaleY(blank.getScaleY() * .9);
            }

        /* panning around system */
            if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
                sun.setPosition(new Point(sun.getPosition().x, sun.getPosition().y - 5));
            }
            if (pressedKeys.contains(KeyEvent.VK_UP)) {
                sun.setPosition(new Point(sun.getPosition().x, sun.getPosition().y + 5));
            }
            if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
                sun.setPosition(new Point(sun.getPosition().x - 5, sun.getPosition().y));
            }
            if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
                sun.setPosition(new Point(sun.getPosition().x + 5, sun.getPosition().y));
            }

		/* rotation counterclockwise and clockwise of system */
            if (pressedKeys.contains(KeyEvent.VK_A)) {
                sun.setRotation(sun.getRotation() - 10);
            }
            if (pressedKeys.contains(KeyEvent.VK_S)) {
                sun.setRotation(sun.getRotation() + 10);
            }

        }

        /**
         * Engine automatically invokes draw() every frame as well. If we want to make sure sun gets drawn to
         * the screen, we need to make sure to override this method and call sun's draw method.
         */
        @Override
        public void draw(Graphics g) {
            super.draw(g);

		/* Same, just check for null in case a frame gets thrown in before sun is initialized */
            if (blank != null) blank.draw(g);
        }

        /**
         * Quick main class that simply creates an instance of our game and starts the timer
         * that calls update() and draw() every frame
         */
        public static void main(String[] args) {
            edu.virginia.LabFourSimulator game = new edu.virginia.LabFourSimulator();
            game.addPlanets();
            game.start();

        }
    }


