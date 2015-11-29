import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Car extends Vehicle
{   
    private boolean remove = false;//initializing the variable so the car doesn't get removed right away
    public Car ()
    {
        speed = 5;
    }
    
    public void act()
    {
        
        drive();
        checkHitPedestrian();
        checkAndRemove(remove);
    }
    
    /**
     * Method that deals with movement
     */
    public void drive() 
    {
        move (speed);
    }    

    /**
     * check if I hit a Pedestrian, and if so, act
     * accordingly
     */
    public void checkHitPedestrian ()
    {
        // Check collision for a pedestrian one pixel ahead
        // of the Vehicle
        int offset = 30;

        Pedestrian p = (Pedestrian)getOneObjectAtOffset(offset, 0, Pedestrian.class);
        if (p != null)
        {
            //System.out.println("got here");
            p.knockMeOver();
        }
        fatLuigi l = (fatLuigi)getOneObjectAtOffset(offset, 0, fatLuigi.class);//check if it is hitting a luigi
        if (l != null)
        {
            //System.out.println("got here");
            remove=true;//the car breaks down and needs to be removed when it hits a luigi
        }
    }
}
