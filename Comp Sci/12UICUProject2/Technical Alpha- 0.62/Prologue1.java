import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * First Prologue screen.
 * 
 * @author (Jasper Tu) 
 * @version (January 2015)
 */
public class Prologue1 extends World
{  
    private FadeToPrologue2 img = new FadeToPrologue2();
    private GreenfootSound menu;
    private Tracker tracker;

    /**
     * Constructor for objects of class Prologue1.
     * 
     */
    public Prologue1(GreenfootSound menu)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1); 
        this.menu = menu;
        tracker = new Tracker();
        prepare();
    }

    /**
     * Act - do whatever LoadingScreen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        img.fade();
        if (tracker.hit (10)) {
            generateEmbers ();
            tracker.clear ();
        } else {
            tracker.increase ();
        }
    }

    /**
     * Mimics the animation of burning embers.
     */
    private void generateEmbers ()
    {
        int ranNum = Greenfoot.getRandomNumber (3);
        BurningEmbers [] w = new BurningEmbers [ranNum];

        for (int i = 0; i < ranNum; i++) {
            int ranX = getWidth ();
            int ranY = Greenfoot.getRandomNumber (getHeight ());
            w [i] = new BurningEmbers ();
            addObject (w [i], ranX, ranY);
        }   
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        addObject(img, 640, 360);
    }

    /**
     * Starts playing the main menu theme.
     */
    public void started()  
    {  
        menu.playLoop();  
    }

    /**
     * Stops playing the main menu theme when the simulation is not running.
     */
    public void stopped()
    {
        menu.pause();
    }
}