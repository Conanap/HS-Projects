import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BuyButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BuyButton extends Buttons
{
    public void updateImage() {
        if (isMouseOn)setImage("1280x720 Buy Button Hover.png");
        else if (!isMouseOn)setImage("1280x720 Buy Button Normal.png");
    }
}
