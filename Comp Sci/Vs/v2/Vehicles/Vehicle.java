import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Vehicle extends Actor
{   
    protected int speed;
    protected boolean remove;
    /**
     * Abstract method declarations: 
     * This means that all Vehicles must have a
     * drive() method and a checkHitPedestrian() with the same signature:
     * E.g: public void drive ();
     */
    public abstract void drive ();
    
    public abstract void checkHitPedestrian ();
    
    protected void checkAndRemove(boolean remove)//added a boolean to check if the vehicle should be removed
    {
        if (getX() < -100 || getX() > 700)
        {
            getWorld().removeObject(this);
        }
        else if(remove==true)//under certain circumstances, a vehicle needs to be removed even if it is still in the world
        //eg getting stepped on by a Luigi
            getWorld().removeObject(this);
    }
}










