import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Inventory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Inventory extends Actor
{
    protected boolean showing;
    
    public abstract void setShowing(boolean showing);
    
    public Inventory() {
        showing = false;
    } 

    public boolean isShowing() {
        return showing;  
    }
}
