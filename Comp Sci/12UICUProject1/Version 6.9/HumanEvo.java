import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Evolution bar for humans and determines if they will evolve.
 * 
 * @author Albion Fung
 * @version 0.0.1
 */
public class HumanEvo extends EvolutionBar
{
    /**
     * Constructor, creates the human Evolution bar. No parameters
     */
    public HumanEvo()
    {
        setImage(bar);
    }
    /**
     * actor, constantly updates the bars and determines if they should evolve
     */
    public void act() 
    {
        base();//draw base
        mutating();//determine if they will evelove
        checkMax();//make sure the evolution level is not greater than max
        drawEvo();//draw out the stuff
        drawIdent("Human Evoultion Level");
    }
     
}
