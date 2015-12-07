import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Prologue2 here.
 * 
 * @author (Jasper Tu) 
 * @version (January 2015)
 */
public class Prologue2 extends World
{
    private FadeToPrologue3 img = new FadeToPrologue3();
    private GreenfootSound menu;
    private Tracker tracker;

    /**
     * Constructor for objects of class Prologue2.
     * 
     */
    public Prologue2(GreenfootSound menu)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1); 
        this.menu = menu;
        tracker = new Tracker();
        prepare();
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        addObject(img, 640, 360);
    }

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
