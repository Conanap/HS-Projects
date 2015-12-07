import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FadeToPrologue4 here.
 * 
 * @author (Jasper Tu) 
 * @version (January 2015)
 */
public class FadeToPrologue4 extends FaderType2
{
    private GreenfootSound menu = new GreenfootSound("MainMenuTrack.mp3");
    private Intro myWorld;

    public FadeToPrologue4()
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
                Greenfoot.setWorld(new Prologue4(menu)); // (myWorld.getTheme()));
            }
        }
        catch (IllegalArgumentException e)
        {
        }
    }
}
