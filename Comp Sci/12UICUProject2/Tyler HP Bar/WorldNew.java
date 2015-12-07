import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WorldNew here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WorldNew extends World
{
    HPBar hp;
    /**
     * Constructor for objects of class WorldNew.
     * 
     */
    public WorldNew()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        hp = new HPBar(100, 100, true);
        addObject(hp, 300, 200);
    }
    
    public void act()
    {
        if(Greenfoot.isKeyDown("up")){
            hp.increase();
        }else if(Greenfoot.isKeyDown("down")){
            hp.decrease();
        }
    }
}
