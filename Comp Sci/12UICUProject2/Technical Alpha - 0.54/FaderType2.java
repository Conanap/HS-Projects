import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Parent class of second type of faders for screen transitional purposes.
 * 
 * @author (Jasper Tu) 
 * @version (January 2015)
 */
abstract class FaderType2 extends Actor
{
    protected GreenfootImage img = new GreenfootImage("1280x720 Black.png");
    protected int transparency = 0;
    protected int transparency2 = 0;

    abstract void fade();
}
