import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A mushroom! It acts like a vehicle, but either revives a dead
 * pedestrian or makes a pedestrian a fat luigi. Disappears after 1 use
 * 
 * @author Jordan Cohen, Modified by Albion Fung 
 * @version 2014
 */
public class mushroom extends Vehicle
{
    public mushroom ()
    {
        speed = 2;
    }
    public void act() 
    {
        // Add your action code here.
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
        // Check collision for a pedestrian one pixel ahead
        // of the Vehicle
        int offset = 25;

        Pedestrian p = (Pedestrian)getOneObjectAtOffset(offset, 0, Pedestrian.class);
        if (p != null&&p.pickMeUp()==true)//if pickMeUp= true, the pedestrian is still alive
        //this is to ensure it doesn't make a dead person a luigi
        {
            //System.out.println("got here");
            int yPos=p.getY();
            int xPos=p.getX();
            getWorld().removeObject(p);
            getWorld().addObject(new fatLuigi(),xPos,yPos);
            remove=true;
        }
        else if (p!=null&&p.pickMeUp()==false)
        {
            p.healMe();
            remove=true;// after healing, the mushroom should be removed
        }
    }
}
