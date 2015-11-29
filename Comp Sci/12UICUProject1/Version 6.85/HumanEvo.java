import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HumanEvo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HumanEvo extends EvolutionBar
{
    public HumanEvo()
    {
        setImage(bar);
    }
    
    public void act() 
    {
        base();
        mutating();
        checkMax();
        drawEvo();
        drawIdent("Human Evoultion Level");
    }
     
}
