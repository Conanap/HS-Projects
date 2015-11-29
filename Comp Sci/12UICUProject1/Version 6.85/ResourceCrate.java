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
    public ResourceCrate(int x,int y) 
    {
        heliDrop();
        bar.scale(40,40);
        setImage(bar);
        barBase.setTransparency(0);
        barBase.drawImage(bar,0,0);
    }
 
    public void act()
    {
        checkForHuman();
    }
    
    private void checkForHuman()
    {
        int range = 40;
        List<HealthyHuman> human = getObjectsInRange(range, HealthyHuman.class);
        if(human.size()!=0)
            pickedItUp();

    }
    //determines type of supply dropped
    private int heliDrop()
    {
        int supplyType = Greenfoot.getRandomNumber(2);
        return supplyType;
    }

    private void pickedItUp()
    {
        int a=0,b=0,c=0,sup;
        sup=heliDrop();
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

        List<ResourceCentre> centre = getWorld().getObjects(ResourceCentre.class);
        if(centre.size()!=0)
            centre.get(0).update(0,a,b,c);
        //centre.update(false,a,b,c);
        getWorld().addObject(new DropTimer(),this.getX(),this.getY());
        getWorld().removeObject(this);
    }
}
