import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LoadingScreen here.
 * 
 * @author (Jasper Tu) 
 * @version (January 2015)
 */
public class LoadingScreen2 extends World
{
    private int counter = 0;  
    private boolean exists = true;  
    private GreenfootSound menu = new GreenfootSound("MainMenuTrack.mp3");
    private Tracker tracker;

    /**
     * Constructor for objects of class LoadingScreen.
     * 
     */
    public LoadingScreen2()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1); 
        tracker = new Tracker();
    }

    public void act()
    {
        timer();
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
     * Counts up until a particular integer value, and then switches the screen into another world.
     * Essentially determines how long the loading screen will last.
     */
    public void timer()
    {
        if(exists == true)
        {  
            if(counter < 500)  
            {  
                counter++;  
            }  
            else 
            {  
                Greenfoot.setWorld(new Test());  
                exists = false;  
            }  
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
        menu.stop();
    }
}
