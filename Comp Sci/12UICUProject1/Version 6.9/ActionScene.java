import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Superclass for all objects that are not a human or zombie existing 
 * 
 * @author Albion Fung, Rolan Li
 * @version 0.0.1
 */
public abstract class ActionScene extends Actor
{
    protected int human, zombie, water, food, wood,research;
    protected Color green = new Color (0,255,0);
    protected Color red = new Color (255,0,0);
    protected Color orange = new Color (255,196,0);
    protected Color darkOrange = new Color (255,128,0);
    protected Color darkGreen = new Color (0,204,0);
    protected Color darkRed = new Color (204,0,0);
    protected Color darkBrown = new Color (51,25,0);
    protected Color brown = new Color (153,76,0);
    protected Color yellow = new Color(255,255,0);
    protected Color white = new Color(255,255,255);
    protected Color blue = new Color(0,0,255);
    protected Color black = new Color (0,0,0);
    protected Color grey = new Color(50,50,50);
}
