import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Bombs kill specifically pedestrians and ambulances (alien invasion? Illuminati?
 * No one knows)
 * Kills 3 units and then disappears; or disappears when stepped on by Luigi
 * Kills on the way down and affects 3 lanes
 * 
 * @author Jordan Cohen, Modified by Albion Fung
 * @version 2014
 */
public class bomb extends Vehicle
{
    private int speed =3;
    private int laneY, kill =0;//laneY is coordinate of chosen lane; kill to count how many kills the bomb racked up
    public bomb()
    {
        setRotation(90);
        laneY=laneSelect();
    }
    public void act()
    {
        drive();
        checkHitPedestrian();
        checkBomb();
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
        Pedestrian p = (Pedestrian)getOneIntersectingObject(Pedestrian.class);//kills pedestrian
        if (p != null&&p.pickMeUp()==true)
        {
            getWorld().removeObject(p);//remove pedestrian
            kill+=1;//kill count plus 1
        }
        fatLuigi l = (fatLuigi)getOneIntersectingObject(fatLuigi.class);
        if (l != null)
        {
            remove=true;//killed by luigi
        }
        Ambulance a = (Ambulance)getOneIntersectingObject(Ambulance.class);//kills ambulance
        if (a != null)
        {
            //System.out.println("got here");
            getWorld().removeObject(a);//removes ambulance
            kill+=1;//kill count plus 1
        }
        if(kill==3)
        {
            remove=true;// when 3 kills, the bombs get honourary retirement and disappears
        }
    }
    
    private int laneSelect()//to select a lane in which the bomb is to be dropped
    {
        int random = Greenfoot.getRandomNumber(6);
        switch(random)
        {
            case 0: 
            return 79;

            case 1:
            return 127;

            case 2:
            return 175;

            case 3:
            return 222;

            case 4:
            return 272;

            case 5: 
            return 320;
            
            case 6:
            return 10;
        }
        return 0;
    }
    
    private void checkBomb()//stop the bomb when it hits the chosen lane
    {
        if (this.getY() >=laneY)
        {
            speed=0;
        }
    }
}
