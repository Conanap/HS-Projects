import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Controls here.
 * 
 * @author (Jasper Tu) 
 * @version (January 2015)
 */
public class Controls extends World
{
    private GreenfootSound menu;
    private Tracker tracker; 

    /**
     * Constructor for objects of class Controls.
     * 
     */
    public Controls(GreenfootSound menu)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1); 
        this.menu = menu;
        tracker = new Tracker();
        prepare();
    }

    /**
     * When the game is run, embers are generated! Yay!
     */
    public void act () 
    {
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
        ReturnToMenu returntomenu = new ReturnToMenu();
        addObject(returntomenu, 155, 63);
        returntomenu.setLocation(152, 55);
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