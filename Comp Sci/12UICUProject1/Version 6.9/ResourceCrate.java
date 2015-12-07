import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import java.util.List;
/**
 * Creates a resource crate from the helicopter drop. Maybe a either 1 of 3 items:
 * Water, food, or vaccine.
 * 
 * @author Albion Fung 
 * @version 0.0.1
 */
public class ResourceCrate extends ActionScene
{
    private GreenfootImage barBase = new GreenfootImage(30,30);
    private GreenfootImage bar = new GreenfootImage("crate.png");
    /**
     *Constructs a resource crate. No parameter needed
     */
    public ResourceCrate() 
    {
        heliDrop();
        bar.scale(40,40);//scales the image smaller
        setImage(bar);
        barBase.setTransparency(0);
        barBase.drawImage(bar,0,0);
    }
    /**
     * actor, continuously checks if humans are around to pick it up
     */
    public void act()
    {
        checkForHuman();
    }
    /**
     * checks if humans are around to pick it up
     */
    private void checkForHuman()
    {
        //the method creates a list of humans around it in its pick up range, and calls pickedItUp() when a human is close enough to pick it up
        int range = 40;
        List<HealthyHuman> human = getObjectsInRange(range, HealthyHuman.class);
        if(human.size()!=0)
            pickedItUp();

    }
    /**
     * Determines type of supply
     */
    private int heliDrop()
    {
        int supplyType = Greenfoot.getRandomNumber(2);
        return supplyType;
    }
    /**
     * Supplies the resource Centre with resource and puts a timer back in place when a human picks up the crate
     */
    private void pickedItUp()
    {
        int a=0,b=0,c=0,sup;
        sup=heliDrop();
        //uisng the supply type determined, it updates the respective resources
        if (sup==0)
            a=1;
        else if(sup==1)
        {
            b=1;
            c=1;
        }
        else if(sup==2)
        {
            a=1;
            b=1;
            c=1;
        }

        List<ResourceCentre> centre = getWorld().getObjects(ResourceCentre.class);//gets the resource centre and updates the resource in that
        if(centre.size()!=0)
            centre.get(0).update(0,a,b,c);
        if(this.getX()==446)//if the crate was on the smaller drop platform, a smaller timer that fits the platform shows up
            getWorld().addObject(new SmallDrop(),this.getX(),this.getY());
        else
            getWorld().addObject(new DropTimer(),this.getX(),this.getY());
            getWorld().removeObject(this);
    }
}
