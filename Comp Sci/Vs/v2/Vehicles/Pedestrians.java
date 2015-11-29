import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pedestrians here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Pedestrians extends Actor
{
    protected int myWidth;
    protected boolean healthy;//this is to return a value to see if the pedestrian is dead or not
    protected int speed;
    protected int startSpeed;
    /**
     * Act - do whatever the Pedestrians wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public boolean atWorldEdge()
    {
        if (getX() < -(myWidth / 2) || getX() > getWorld().getWidth() + (myWidth / 2))
            return true;
        else if (getY() < -(myWidth / 2) || getY () > getWorld().getHeight() + (myWidth / 2))
            return true;
        else
            return false;
    }
}
