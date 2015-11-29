import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 *Circle that represents current health, the inner circle changes color with health. Modified version for the simulation
 * @Albion Fung
 * @0.0.3.OOP1
 */
public class SmallDrop extends StatsDisplay
{
    //declaration of instance variables
    private GreenfootImage bar;//creating new greenfoot image
    private Color grey = new Color(50,50,50);//initializing colors
    private Color green = new Color(0,255,0);
    private Color red = new Color(255,0,0);
    private Color orange = new Color(255,196,0);
    private Color yellow = new Color(255,255,0);
    private Color white = new Color(255,255,255);
    private Color blue = new Color(0,0,255);
    private int xcor,ycor,mannaLength;//declaring variables for the values of the centre circle and the length of the blue manna bar overlay
    private double maxHP, diameter,currentHP;//declaring variables for maximum HP, maximum diameter, current HP
    private ResourceCrate crate;
    /**
     * Constructing a new health bar. No parameters,  automatically defaults maximum HP and manna to 100.
     */
    public SmallDrop()//constructor to construct new health circles
    {
        bar = new GreenfootImage(40,40);//defining the greenfoot image called bar
        setImage(bar);
        maxHP=1000;//initializing maximum HP
        currentHP=maxHP;//set starting HP
        base();//drawing base of the health bar
    }
    
    public void act()
    {
        update(true);
    }
    
    /**
     * Constructing a new health bar. Initializes maximum manna to 100
     * @param imHP Put in the maximum amount of HP you want the object to have.
     */
    public SmallDrop(double imHP)//constructor to construct new health circles with a specified maximum amount of hp
    {
        bar = new GreenfootImage(50,50);//defining the greenfoot image called bar
        setImage(bar);
        bar.setTransparency(150);
        maxHP=imHP;//initializing maximum hp to the definition of the user
        currentHP=maxHP;//setting the current hp to max hp
        base();//drawing base of the health bar
    }

    /**
     * This method draws and sets the basics of the health bar. Cannot be accessed outside of the class.
     */
    private void base ()//private method, creates the background and the base of the health bar
    {
        bar.setColor(white);//setting color ofthe background of the circle, status box, and manna bar to white
        bar.fillOval(3,4,20,20);//drawing base of circle
    }

    /**
     * Updating the health bars. Must be called every time you want to change the amount of HP.
     * @param damage If true, it reduces the amount of HP and manna; if false, it increases the amount of HP and manna. Increments by 1.
     */
    public void update(boolean damage)//update method #1, taking boolean to determine if damage was done was used
    {
        base();//drawing the base
        if(damage==true&&currentHP>=0)//determining if HP should be reduced
        {
            currentHP-=1;//reducing HP in increments of 1
        }
        else if(damage==false&&currentHP<=maxHP)//determining if HP should be increased
        { 
            currentHP+=1;//increasing HP in increments of 1
        }
        checkMax();//calls method checkMax() to confirm that the current hp is not over max hp
        double percent = currentHP/maxHP;//determining percent of HP object has
        diameter = 20*percent;//determining diameter of the inner circle; 80 is the diameter at 100% HP
        int radius =(int)(diameter/2);//determining radius in interger
        xcor=14-radius;//x coordinate of smaller circle, the background circle is 15 units from edge; to keep it centred, we subtract radius of smaller circle from the bigger circle(40) and add 15.
        ycor=xcor;//same reasoning as above, y coordinate of smaller circle
        //int diameter2 = (int) diameter;
        detColor(percent);//calling method detColor() with the percent of health to determine what color the items should be
        bar.fillOval(xcor,ycor,radius*2,radius*2);//filling in the smaller circle
        if(currentHP<=0)
        {
            crate = new ResourceCrate(10,10);
            getWorld().addObject(crate,this.getX(),this.getY());
            getWorld().removeObject(this);
        }
        
    }

    /**
     * Private method, cannot be accessed outside of class. Determines the color of the smaller circle and status box.
     * If you don't like the colors you can change the parameters here
     */
    private void detColor (double percent)//private method to determine what color the smaller circle 
    {
        if(percent>.80)//if more than 80% hleath, the objects will be green
            bar.setColor(green);
        else if(percent>.60)//if less than 80% and more than 60%, objects will be yellow
            bar.setColor(yellow);
        else if(percent>.40)//if less than 60% and more than 40%, objects will be orange
            bar.setColor(orange);
        else//if less than 40%, objects will be red
            bar.setColor(red);
    }

    /**
     * Private method, to prevent the circle from being larger than intended (max diameter) or having negative numbers, which will cause delays and inaccurate display of
     * health and status. The same is true for numbers larger than max HP or max Manna possible.
     */
    private void checkMax ()
    {
        if(currentHP>maxHP)//if the new current HP is larger than the maximum allowed HP, it is changed to the maximum HP
            currentHP=maxHP;
        else if(currentHP<0)//if the new current HP is les than zero, it is changed to zero
            currentHP=0;
    }
}
