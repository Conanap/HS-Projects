import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Buttons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Buttons extends Actor
{
    protected boolean isMouseOn = false;
    public abstract void updateImage();
    /**
     * Act - do whatever the Buttons wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (Greenfoot.mouseMoved(this) && !isMouseOn) {
            isMouseOn = true;
            updateImage();
        }
        if (isMouseOn && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) {
            isMouseOn = false;
            updateImage();
        }
    }    
}
