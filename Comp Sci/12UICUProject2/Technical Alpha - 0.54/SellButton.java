import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SellButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SellButton extends Buttons
{
    public void updateImage() {
        if (isMouseOn)setImage("1280x720 Sell Button Hover.png");
         else if (!isMouseOn)setImage("1280x720 Sell Button Normal.png");
    }    
}
