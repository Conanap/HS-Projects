import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FaderToGuidebookScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FaderToGuidebookScreen extends FaderType1
{
    private GreenfootSound menu = new GreenfootSound("MainMenuTrack.mp3");
    private Intro myWorld;

    /**
     * Act - do whatever the Fader wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        fade(fadeLevel = fadeLevel + 10);
        if (fadeLevel > 255)
        {
            Greenfoot.setWorld(new GuidebookScreen(menu)); // (myWorld.getTheme()));
        }
    }    

    public FaderToGuidebookScreen()
    {
        fade(0);
    }
}
