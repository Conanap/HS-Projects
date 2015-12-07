import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FadeToLoadingScreen here.
 * 
 * @author (Jasper Tu) 
 * @version (January 2015)
 */
public class FadeToLoadingScreen extends FaderType2
{
    private GreenfootSound menu = new GreenfootSound("MainMenuTrack.mp3");
    private Intro myWorld;

    public FadeToLoadingScreen()
    {
        img.setTransparency(transparency = transparency + 50);
    }

    public void fade()
    {
        try
        {
            if(transparency2 < 475)
            {
                transparency2++;
                transparency++;
                img.setTransparency(transparency / 2);
                setImage(img);
            }
            else if (transparency2 == 475)
            {
                Greenfoot.setWorld(new LoadingScreen2()); // (myWorld.getTheme()));
            }
        }
        catch (IllegalArgumentException e)
        {
        }
    }
}
