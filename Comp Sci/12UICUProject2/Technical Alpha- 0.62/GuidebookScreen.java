import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Screen that serves as a brief guide for user, enlightening them in regards to game items, buildings, enemies, & block types.
 * 
 * @author (Jasper Tu) 
 * @version (January 2015)
 */
public class GuidebookScreen extends World
{
    private GreenfootSound menu;
    private Tracker tracker; 

    /**
     * Constructor for objects of class GuidebookScreen.
     * 
     */
    public GuidebookScreen(GreenfootSound menu)
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
        Guidebook guidebook2 = new Guidebook();
        addObject(guidebook2, 198, 72);
        removeObject(guidebook2);
        ReturnToMenu returntomenu = new ReturnToMenu();
        addObject(returntomenu, 121, 87);
        returntomenu.setLocation(150, 53);
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
