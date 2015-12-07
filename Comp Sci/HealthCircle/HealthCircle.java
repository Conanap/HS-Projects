import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 *Circle that represents current health, the inner circle changes color with health, a box that gives a summary of the status with colors, and a mana bar.
 * @Albion Fung
 * @0.0.3
 */
public class HealthCircle extends Actor
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
    private double maxHP, diameter,currentHP,maxManna,currentManna;//declaring variables for maximum HP, maximum diameter, current HP, maximum manna, and current manna
    /**
     * Constructing a new health bar. No parameters,  automatically defaults maximum HP and manna to 100.
     */
    public HealthCircle()//constructor to construct new health circles
    {
        bar = new GreenfootImage(220,150);//defining the greenfoot image called bar
        setImage(bar);
        maxHP=100;//initializing maximum HP and manna
        maxManna=100;
        currentHP=maxHP;//no input from user to specify current manna and hp, and so it is set to max
        currentManna=100;
        base();//drawing base of the health bar
    }
    
    /**
     * Constructing a new health bar. Initializes maximum manna to 100
     * @param imHP Put in the maximum amount of HP you want the object to have.
     */
    public HealthCircle(double imHP)//constructor to construct new health circles with a specified maximum amount of hp
    {
        bar = new GreenfootImage(220,150);//defining the greenfoot image called bar
        setImage(bar);
        maxHP=imHP;//initializing maximum hp to the definition of the user
        currentHP=maxHP;//setting the current hp to max hp
        maxManna=100;//initializing the amount of Manna
        currentManna=maxManna;//initializing amount of current manna
        base();//drawing base of the health bar
    }
    
    /**
     * Constructing a new health bar.
     * @param imHP Put in the maximum amount of HP you want the object to have
     * @param imManna Put in the maximum amount of manna you want the object to have
     */
    public HealthCircle(double imHP, double imManna)//constructor to construct new health circles with a specified maximum amount of hp and manna
    {
        bar = new GreenfootImage(220,150);//defining the greefoot image bar
        setImage(bar);
        maxHP=imHP;//setting maximum hp to definition of user
        currentHP=maxHP;//setting current hp
        maxManna=imManna;//setting maximum manna to definition of user
        currentManna=maxManna;//setting current manna
        base();
    }
    
    /**
     * This method draws and sets the basics of the health bar. Cannot be accessed outside of the class.
     */
    private void base ()//private method, creates the background and the base of the health bar
    {
        bar.setColor(grey);//setting background color of bar
        bar.fill();//filling background
        bar.setColor(white);//setting color ofthe background of the circle, status box, and manna bar to white
        bar.fillOval(15,15,80,80);//drawing base of circle
        bar.fillRect(125,18,75,75);//drawing base of status box
        bar.fillRect(15,110,185,20);//drawing base of manna bar
    }
    
    /**
     * Updating the health bars. Must be called every time you want to change the amount of HP.
     * @param damage If true, it reduces the amount of HP and manna; if false, it increases the amount of HP and manna. Increments by 1.
     */
    public void update(boolean damage, boolean manChange)//update method #1, taking boolean to determine if damage was done or manna was used
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
        if(manChange==true&&currentManna>=0)//determining if manna should be reduced
        {
            currentManna-=1;//reducing manna in increments of 1
        }
        if(manChange==false&&currentManna<=maxManna)
        {
            currentManna+=1;//increasing manna in increments of 1
        }
        checkMax();//calls method checkMax() to confirm that the current hp and manna is not over max hp and manna
        double percent = currentHP/maxHP;//determining percent of HP object has
        diameter = 80*percent;//determining diameter of the inner circle; 80 is the diameter at 100% HP
        int radius =(int)(diameter/2);//determining radius in interger
        xcor=55-radius;//x coordinate of smaller circle, the background circle is 15 units from edge; to keep it centred, we subtract radius of smaller circle from the bigger circle(40) and add 15.
        ycor=xcor;//same reasoning as above, y coordinate of smaller circle
        //int diameter2 = (int) diameter;
        detColor(percent);//calling method detColor() with the percent of health to determine what color the items should be
        bar.fillRect(125,18,75,75);//filling in the status box 
        bar.fillOval(xcor,ycor,radius*2,radius*2);//filling in the smaller circle
        double percentm=currentManna/maxManna;//finding the percent of manna the user has
        mannaLength=(int) (percentm*185);//finding how long the blue overlay bar should be
        bar.setColor(blue);//setting the manna bar color
        bar.fillRect(15,110,mannaLength,20);//filling in the amount of manna the object has
    }
    
    /**
     * Updates the health bars. Must be called everytime you want to change the ammount hP.
     * @param damage Neagtive number to reduce HP, positive number to increase HP. Custom increments, according to this variable.
     * @param manChange Negative number to reduce manna, positive number to increase manna. Custom increments, according to this variable.
     */
    public void update(double damage,double manChange)//update method #2. takes 2 doubles, respectively the change in hp and manna
    {
        base();//drawing the base
        currentHP+=damage;//determining the new current HP
        currentManna+=manChange;//determining the new Current manna
        checkMax();//ensure hp and manna is not larger than max and smaller than 0
        double percent = currentHP/maxHP;//finding percent of HP the object has 
        diameter = 80*percent;//determining the diameter of smaller circle
        int radius =(int)(diameter/2);//determing integer radius
        xcor=55-radius;//x coordinate of smaller circle, the background circle is 15 units from edge; to keep it centred, we subtract radius of smaller circle from the bigger circle(40) and add 15.
        ycor=xcor;//same reason as above, y coordinate of the smaller circle
        detColor(percent);//calling method detColor() to determing the appropiate colors of the objects
        bar.fillRect(125,18,75,75);//filling in status box
        bar.fillOval(xcor,ycor,radius*2,radius*2);//filling in the smaller circle
        double percentm=currentManna/maxManna;//finding percent of manna the object has
        mannaLength=(int) (percentm*185);//determining how long the overlay manna bar should be
        bar.setColor(blue);//setting the overlay manna bar to blue
        bar.fillRect(15,110,mannaLength,20);//filling in the overlay bar
    }
    
    /**
     * Private method, cannot be accessed outside of class. Determines the color of the smaller circle and status box.
     * If you don't like the colors you can change the parameters here
     */
    private void detColor (double percent)//private method to determine what color the smaller circle and status box should be
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
        if(currentManna>maxManna)//if the current manna is larger than the maximum allowed manna, it is changed to maximum manna
            currentManna=maxManna;
        else if(currentManna<0)//if the current manna is les than zero, it is changed to zero
            currentManna=0;
    }
}
