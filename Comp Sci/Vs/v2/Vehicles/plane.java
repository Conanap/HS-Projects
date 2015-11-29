import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creates a plane having a fly by. The aircraft may by chance drop bombs.
 * The aircraft itself cannot interact with any objects. (it's not travelling on the 
 * roadd)
 * 
 * @author Jordan Cohen, Modified by Albion Fung 
 * @version 2014
 */
public class plane extends Vehicle
{
    private int bombSpawn=70;//initializing the spawn rate of bombs
    public plane ()
    {
        speed = 12;
    }
    public void act() 
    {
        // Add your action code here.
        dropBomb();//see if it should drop a bomb!
        drive();
        checkHitPedestrian();
        checkAndRemove(remove);
    }
    public void drive()
    {
        move(speed);
    }
    public void checkHitPedestrian ()
    {
        //the method is required to exist as plane extends vehicles
        
    }
    /**
     * decides if the plane should drop a bomb
     */
    public void dropBomb()
    {
        int random = Greenfoot.getRandomNumber(bombSpawn);
        if(random==10)// random number chosen to compare; 1/70 chance to drop a bomb
        {
            getWorld().addObject(new bomb(),this.getX(),15);//adding a new bomb
        }
    }
}
