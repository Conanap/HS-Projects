import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *World I called yay. I didn't know what to call it. Used to put and test the health bars.
 * 
 * @author Albion Fung
 * @Sept 18, 2014
 */
public class WorldYay extends World
{
    HealthCircle hc, hc2;//creating 2 health bars to test the update methods
    private boolean j;//random variables created to test the update methods
    private double h;
    /**
     * Constructor for objects of class WorldYay.
     * 
     */
    public WorldYay()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        hc = new HealthCircle(100);//creating 2 health bars with 100 health
        hc2 = new HealthCircle(100);
        addObject(hc,50,50);//adding the 2 objects
        addObject(hc2,50,50);
        h=100;//initializing h as maxHP 
    }
    public void act()
    {
        
        if(Greenfoot.isKeyDown("down"))
        {
         j = true;//there is damage done and losing manna
         h=-1;
         hc2.update(j,j);//updating 
         hc.update(h,h);//updating
        }
        else if(Greenfoot.isKeyDown("up"))
        {
         j = false;//gaining health and manna
         h=1;
         hc2.update(j,j);//updating
         hc.update(h,h);//updating
        }
        
    }
}
