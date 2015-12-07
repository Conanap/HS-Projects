import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.IllegalArgumentException;

/**
 * Write a description of class Blackie here.
 * 
 * @author (Jasper Tu) 
 * @version (January 2015)
 */
public class FadeToPrologue2 extends FaderType2
{
    private GreenfootSound menu = new GreenfootSound("MainMenuTrack.mp3");
    private Intro myWorld;

    public FadeToPrologue2()
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
                Greenfoot.setWorld(new Prologue2(menu)); // (myWorld.getTheme()));
            }
        }
        catch (IllegalArgumentException e)
        {
        }
    }
}