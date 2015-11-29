import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Pedestrian Class
 * 
 * Moves mindlessly up the road, reacting to cars
 * that hit it, ambulances that heal it, and potentially
 * buses that pick it up.
 * 
 * @Author Jordan Cohen
 * @Version 2011
 */

public class Pedestrian extends Pedestrians
{
    // Instance variables
    private int startSpeed = 2;
    // Constructor
    public Pedestrian()
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

    /**
     * Method causes this Pedestrian to stop moving
     * and appear to fall over
     */
    public void knockMeOver ()
    {
            speed = 0;
            setRotation (90);
            healthy=false;
    }

    /**
     * Method causes this pedestrian to "heal" - regain
     * upright position and start moving again
     */
    public void healMe ()
    {
        speed = startSpeed;//set the pedestrian's speed to original so he can move again
        setRotation (0);
        healthy=true;//making the pedestrian healthy again
    }

    /**
     * Method causes the pedestrian to get picked up
     * by the bus - not yet implemented
     */
    public boolean pickMeUp()//makes the pedestrian disapear (as if he was picked up) when they hit a bus
    {
        if (healthy)//only pick up if he is not dead
            return true;
        else//won't pick up dead bodies
            return false;
    }
    
}
