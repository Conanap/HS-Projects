import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Evolution bar for zombies, and determines if they should evolve.
 * 
 * @author Albion Fung
 * @version 0.0.1
 */
public class ZombieEvo extends EvolutionBar
{
    /**
     * Constructs a zombie evolution bar. No parameters
     */
    public ZombieEvo() 
    {
        setImage(bar);
    }
     /**
      * Constantly updates the bars and determine if they should evolve
      */
    public void act()
    {
        base();//draw base
        mutating();//determine if they evolve
        checkMax();//make sure they cant evolve once they hit max level
        drawEvo();//draw out the stuff
        drawIdent("Zombie Evolution Level");
    }
}
