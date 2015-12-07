import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Intro here.
 * 
 * @author (Jasper Tu) 
 * @version (January 2015)
 */
public class Intro extends World
{
    private FadeToMainMenu img = new FadeToMainMenu();
    private GreenfootSound menu = new GreenfootSound ("MainMenuTrack.mp3");
    //     private int vol;
    //     private long nextTime, timeInterval;

    /**
     * Constructor for objects of class Intro.
     * 
     */
    public Intro()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1); 
        prepare();
    }

    /**
     * Act - do whatever LoadingScreen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        img.fade();
        //         long now;
        //         now = System.currentTimeMillis();
        //         if(now > nextTime)
        //         {
        //             if(Greenfoot.getRandomNumber(100) > 50)
        //             {
        //                 vol = vol + 30;
        //                 if(vol > 100)
        //                 {
        //                     vol = 0;
        //                 }
        //             }
        //             else
        //             {
        //                 vol = vol - 30;
        //                 if(vol < 0)
        //                 {
        //                     vol = 0;
        //                 }
        //             }
        //             menu.setVolume(vol);
        //             nextTime = nextTime + timeInterval;
        //         }
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
        //         vol = 20;
        //         menu.setVolume(vol);
        //         nextTime = System.currentTimeMillis();
        //         timeInterval = 1000;
        menu.playLoop();  
    }

    /**
     * Stops playing the main menu theme when the simulation is not running.
     */
    public void stopped()
    {
        menu.pause();
    }

    /** 
     * Accessor that returns the GreenfootSound theme
     * 
     * @return GreenfootSound   the music theme being played
     */
    public GreenfootSound getTheme() {
        return menu;
    }
}