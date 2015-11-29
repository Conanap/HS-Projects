import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Barricade here.
 * 
 * @author Kerry 
 * @version (a version number or a date)
 */
public class Barricade extends Actor
{
    private int maxhp=100;
    private int currhp=100;
    
    /**
     * Constructor to call barricade from HealthyHuman class
     */
    public Barricade(String facing){
        setImage("barricade.png");
        if(facing == "U")
        setRotation(90);
        
        
    }
    /**
     * Act - do whatever the Barricade wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(currhp == 0)
        getWorld().removeObject(this);
    }    
    
    /**
     * Decrease the hp of the barricade as zombies destroy it 
     */
    public void decreaseHp(int amount){
        if(currhp >= 0)
        this.currhp = currhp - amount;
        else if(currhp < 0)
        this.currhp = 0;
    }
    
}
