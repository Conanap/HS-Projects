import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SOS here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SOS extends Buttons
{
    /**
     * Act - do whatever the SOS wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void updateImage() 
    {
        if (isMouseOn)setImage("SOS Button Hover.png");
         else if (!isMouseOn)setImage("SOS Button Normal.png");
    }    
}
