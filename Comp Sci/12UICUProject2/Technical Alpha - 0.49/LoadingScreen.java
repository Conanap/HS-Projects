import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.geom.Arc2D;
import java.lang.Math;

/**HPBar is a Greenfoot Actor that display the percentage value in a circular bar.
 * The color of the bar is going to be adjusted(from green to red) basing on the current percentage of hp. (loaded data)
 * 
 * @author Tyler Zhang, modified by Albion Fung
 * @version Jan 2014
 */
public class LoadingScreen extends Actor
{
    
    //loading screen
    private GreenfootImage background;

    /**
     * Creates a HPBar with the default hp value, 100 and set the current HP value to full.
     */
    public LoadingScreen()
    {
        background = new GreenfootImage("Loading Screen.png");
        setImage(background);
    }

    /**
     * Creates a LoadingScreen given a max hp and real-time hp. 
     * 
     * @param max     The maximum amount of hp that the player can have.
     * @param current The current amount of hp that the player has.
     */
    public LoadingScreen(int max, int current)
    {
        background = new GreenfootImage("Loading Screen.png");
        setImage(background);
    }

    /**
     * Creates a HPBar given a max hp and real-time hp and set whether the hp bar will self destruct when the hp run out.
     * 
     * @param max      The maximum amount of hp that the player can have.
     * @param current  The current amount of hp that the player has.
     * @paran destroy Whether to destroy the widget when hp run out or not.
     */
    public LoadingScreen(int max, int current, boolean destroy)
    {
        background = new GreenfootImage("Loading Screen.png");
        setImage(background);
    }

    
}
