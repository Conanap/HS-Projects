import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Bus extends Vehicle
{
    private int speed = 3;
    private boolean remove = false;//new boolean to see if the bus should be removed
    public void act()
    {
        drive();
        checkHitPedestrian();
        checkAndRemove(remove);
    }

    public void drive()
    {
        move (speed);
    }

    public void checkHitPedestrian ()
    {
        // Check collision for a pedestrian one pixel ahead
        // of the Vehicle
        Pedestrian p = (Pedestrian)getOneObjectAtOffset((this.getImage().getWidth() / 2) + 1, 0, Pedestrian.class);//Picks up pedestrians
        if (p != null&&p.pickMeUp()==true)
        {
            getWorld().removeObject(p);//removes the pedestrian so it looks like the bus picked it up
        }
        fatLuigi l = (fatLuigi)getOneObjectAtOffset((this.getImage().getWidth() / 2) + 1, 0, fatLuigi.class);//killed by Mr. Luigi
        if (l != null)
        {
            //System.out.println("got here");
            remove=true;// the bus breaks and gets removed
        }
    }
}
