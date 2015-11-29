import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * fatLuigi Class
 * 
 * Super version of pedestrian, is larger, green,
 * and immune to everything. A pedestrian becomes fat Luigi by eating (hitting)
 * a mushroom
 * 
 * @Author Jordan Cohen, Modified by Albion Fung
 * @Version 2014
 */

public class fatLuigi extends Pedestrians
{
    // Instance variables
    private int startSpeed = 4;
    // Constructor for fatLuigi
    public fatLuigi()
    {
        // figure out own width (related to checking if at world's edge)
        GreenfootImage g = this.getImage();
        myWidth = g.getWidth();
        // Set current healthy status to true (alive and moving)
        healthy = true;
        // Set initial speed
        speed = startSpeed;
    }

    // act() method - called by Greenfoot at every
    // "act" or step of execution
    public void act() 
    {
        // move upwards
        setLocation (getX(), getY() - speed);
        // check if I'm at the edge of the world,
        // and if so, remove myself
        if (atWorldEdge())
        {
            getWorld().removeObject(this);
        }
    }    

}
