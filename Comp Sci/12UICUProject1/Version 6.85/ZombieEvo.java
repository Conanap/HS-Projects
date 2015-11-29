import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ZombieEvo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ZombieEvo extends EvolutionBar
{
    public ZombieEvo() 
    {
        setImage(bar);
    }
     
    public void act()
    {
        base();
        mutating();
        checkMax();
        drawEvo();
        drawIdent("Zombie Evolution Level");
    }
}
