import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Ambulance extends Vehicle
{
    private int speed = 4;
    private boolean remove=false;//making sure the ambulance won't get removed right away
    public void act ()
    {
        
        drive();
        checkHitPedestrian();
        checkAndRemove(remove);//added a boolean to check if there was anything specific that needs to remove the ambulance
    }

    public void drive ()
    {
        move (speed);
    }

    public void checkHitPedestrian ()
    {
        // Check collision for a pedestrian one pixel ahead
        // of the Vehicle
        Pedestrian p = (Pedestrian)getOneObjectAtOffset((this.getImage().getWidth() / 2) + 1, 0, Pedestrian.class);
        if (p != null)
        {
            p.healMe();
        }
        fatLuigi l = (fatLuigi)getOneObjectAtOffset((this.getImage().getWidth() / 2) + 1, 0, fatLuigi.class);
        if (l != null)
        {
            remove=true;//killed by luigi
        }
    }
}
