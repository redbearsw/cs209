package edu.virginia;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.SoundManager;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import java.awt.Graphics;
import java.awt.Point;
    /**
     * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
     * although, for now, it won't be a very fun game :)
     */

    public class LabFourGame extends Game {

        /* Create the Sprites*/
        Sprite mario = new Sprite("Mario", "MarioSS.png");
        Sprite mario_hb = new Sprite("mario_hb", "MarioSS_hb.png");
        Sprite sun = new Sprite("Sun", "sun.png");
        Sprite sun_hb = new Sprite("sun_hb", "sun_hb.png");
        Sprite planet = new Sprite("Planet", "planet.png");
        Sprite planet2 = new Sprite("Planet2", "planet.png");
        Sprite planet3 = new Sprite("Planet3", "planet.png");
        Sprite planet4 = new Sprite("Planet4", "planet.png");
        Sprite planet_hb = new Sprite("planet_hb", "planet_hb.png");
        Sprite planet2_hb = new Sprite("planet2_hb", "planet_hb.png");
        Sprite planet3_hb = new Sprite("planet3_hb", "planet_hb.png");
        Sprite planet4_hb = new Sprite("planet4_hb", "planet_hb.png");

        /* Score */

        private int score = 100;


        /* Game State - True: Playing False: Over */

        private boolean gameState = false;
        private boolean beginning = true;

        /* Music and Sound Effects */

        private SoundManager sounds = new SoundManager();


        /**
         * Constructor. See constructor in Game.java for details on the parameters given
         */
        public LabFourGame() {
            super("Lab Four Game", 500, 500);
        }


        public void addObjects() {


            //add planets as sun's children
            mario.addChild(mario_hb);

            sun.addChild(sun_hb);
            sun.addChild(planet);
//            sun.addChild(planet2);
//            sun.addChild(planet3);
//            sun.addChild(planet4);

            //set positions of sun and planets
            sun.setPosition(new Point (250, 250));
            planet.setPosition(new Point (50, 75));
//            planet2.setPosition(new Point (250, 140));
//            planet3.setPosition(new Point (-200, -200));
//            planet4.setPosition(new Point (-100, -100));

            //set pivot point of each planet to the sun's center
            planet.setPivotPoint(new Point (sun.getUnscaledWidth()/2 - planet.getPosition().x,
                    sun.getUnscaledWidth()/2 - planet.getPosition().y));
//            planet2.setPivotPoint(new Point (sun.getUnscaledWidth()/2 - planet2.getPosition().x,
//                    sun.getUnscaledWidth()/2 - planet2.getPosition().y));
//            planet3.setPivotPoint(new Point (sun.getUnscaledWidth()/2 - planet3.getPosition().x,
//                    sun.getUnscaledWidth()/2 - planet3.getPosition().y));
//            planet4.setPivotPoint(new Point (sun.getUnscaledWidth()/2 - planet4.getPosition().x,
//                    sun.getUnscaledWidth()/2 - planet4.getPosition().y));

            //scale images
            sun.setScaleX(.5);
            sun.setScaleY(.5);
            mario.setScaleX(.6);
            mario.setScaleY(.6);

            //add hitboxes as children and set their positions
            mario.addAtIndex(0, mario_hb);
            sun.addAtIndex(0, sun_hb);

            planet.addAtIndex(0, planet_hb);
//            planet2.addAtIndex(0, planet2_hb);
//            planet3.addAtIndex(0, planet3_hb);
//            planet4.addAtIndex(0, planet4_hb);

            planet.addAtIndex(0, planet_hb);
            planet2.addAtIndex(0, planet2_hb);
            planet3.addAtIndex(0, planet3_hb);
            planet4.addAtIndex(0, planet4_hb);

            /* Load in sounds */
            sounds.LoadMusic("Theme", "theme.wav");
            sounds.LoadSoundEffect("Game Over", "gameover.wav");
            sounds.LoadSoundEffect("Crash", "crash.wav");


        }

        /**
         * Engine will automatically call this update method once per frame and pass to us
         * the set of keys (as strings) that are currently being pressed down
         */
        @Override
        public void update(ArrayList<Integer> pressedKeys) {
            super.update(pressedKeys);

            if (mario != null && sun != null) {
		/* Make sure sun is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		if (pressedKeys.contains(KeyEvent.VK_ENTER)) {
            System.out.println(gameState);
            beginning = false;
            gameState = true;
        }
		if (gameState) {
                if (mario != null) mario.update(pressedKeys);

                if (mario.getCount() < 30) {
                    mario.setCount(mario.getCount() + 1);
                }


                mario.setFrameCount(mario.getFrameCount() + 1);

                //set planet rotations
//                planet.setRotation(planet.getRotation() - 2);
//                planet2.setRotation(planet2.getRotation() + 1);
//                planet3.setRotation(planet3.getRotation() + 2);
//                planet4.setRotation(planet4.getRotation() - 3);

                // start the game


		/* arrow key presses */
                if (pressedKeys.contains(KeyEvent.VK_UP)) {
                    mario.setPosition(new Point(mario.getPosition().x, mario.getPosition().y - 5));
                }
                if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
                    mario.setPosition(new Point(mario.getPosition().x, mario.getPosition().y + 5));
                }
                if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
                    mario.setPosition(new Point(mario.getPosition().x - 5, mario.getPosition().y));
                }
                if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
                    mario.setPosition(new Point(mario.getPosition().x + 5, mario.getPosition().y));
                }


		/* IJKL presses */
                if (pressedKeys.contains(KeyEvent.VK_I)) {
                    mario.setPivotPoint(new Point(mario.getPivotPoint().x, mario.getPivotPoint().y - 5));
                }
                if (pressedKeys.contains(KeyEvent.VK_K)) {
                    mario.setPivotPoint(new Point(mario.getPivotPoint().x, mario.getPivotPoint().y + 5));
                }
                if (pressedKeys.contains(KeyEvent.VK_J)) {
                    mario.setPivotPoint(new Point(mario.getPivotPoint().x - 5, mario.getPivotPoint().y));
                }
                if (pressedKeys.contains(KeyEvent.VK_L)) {
                    mario.setPivotPoint(new Point(mario.getPivotPoint().x + 5, mario.getPivotPoint().y));
                }

		/* rotation counterclockwise and clockwise */
                if (pressedKeys.contains(KeyEvent.VK_Q)) {
                    System.out.println("ROTATING");
                    mario.setRotation(mario.getRotation() - 10);
                }
                if (pressedKeys.contains(KeyEvent.VK_W)) {
                    mario.setRotation(mario.getRotation() + 10);
                }
		/* set visibility */
                if (pressedKeys.contains(KeyEvent.VK_V)) {
                    if (mario.getCount() == 30) {
                        mario.setVisible(!mario.getVisible());
                        mario.setCount(0);
                    }

                }
		/* set alpha */
                if (pressedKeys.contains(KeyEvent.VK_Z)) {
                    if (mario.getAlpha() >= 1.0f) {
                        mario.setAlpha(1.0f);
                    } else {
                        if (mario.getAlpha() * 1.1f >= 1.0f) {
                            mario.setAlpha(1.0f);
                        } else {
                            mario.setAlpha(mario.getAlpha() * 1.1f);
                        }
                    }
                }

                if (pressedKeys.contains(KeyEvent.VK_X)) {
                    mario.setAlpha(mario.getAlpha() * .9f);
                }

		/* scale mario */
                if (pressedKeys.contains(KeyEvent.VK_A)) {
                    mario.setScaleX(mario.getScaleX() * 1.1);
                    mario.setScaleY(mario.getScaleY() * 1.1);
                }
                if (pressedKeys.contains(KeyEvent.VK_S)) {
                    mario.setScaleX(mario.getScaleX() * .9);
                    mario.setScaleY(mario.getScaleY() * .9);
                }

                System.out.println("Mario 1: ");
                mario.printArray(mario.getHitbox());
                System.out.println("\nplanet: ");
                planet.printArray(planet.getHitbox());
                System.out.println("\nsun: ");
                sun.printArray(sun.getHitbox());

                /* Checking for collisions */
                int i; int sz = sun.getChildren().size();
                for (i = 1; i < sz; i++) {
                    if (mario.collidesWith(sun.getChild(i)) || sun.getChild(i).collidesWith(mario)) {
                        score -= 1;
                        System.out.println("COLLISION");
                        sounds.PlaySoundEffect("Crash");
                    }
                }
                if (mario.collidesWith(sun) || sun.collidesWith(mario)) {
                     gameState = false;
                }

                if(score<=0){
                    gameState = false;
                    sounds.StopMusic();
                    sounds.PlaySoundEffect("Game Over");
                }
                System.out.println("Score: " + score + "\n");
            }
            }
        }


        /**
         * Engine automatically invokes draw() every frame as well. If we want to make sure sun gets drawn to
         * the screen, we need to make sure to override this method and call sun's draw method.
         */
        @Override
        public void draw(Graphics g) {
            if (gameState) {
                super.draw(g);

		/* Same, just check for null in case a frame gets thrown in before sun is initialized */
                if (mario != null) mario.draw(g);
                if (sun != null) sun.draw(g);
                String scorestr = Integer.toString(score);
                g.drawString(scorestr, 450, 50);
            } else {
                if (beginning) {
                    g.drawString("GET MARIO TO THE SUN WHILE AVOIDING ASTEROIDS", 50, 230);
                    g.drawString("PRESS ENTER TO START", 215, 250);

                } else if (score <= 0){
                    g.drawString("GAME OVER :(", 215, 250);
                } else {
                    g.drawString("YOU WIN! :)", 215, 250);
                }
            }

        }

        /**
         * Quick main class that simply creates an instance of our game and starts the timer
         * that calls update() and draw() every frame
         */
        public static void main(String[] args) {
            edu.virginia.LabFourGame game = new edu.virginia.LabFourGame();
            game.addObjects();;
            game.start();
            game.sounds.PlayMusic();

        }
    }


