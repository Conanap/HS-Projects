import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Moves forward and creates a single "Explosion" behind itself.
 * 
 * @author Roland
 * @version (a version number or a date)
 */
public class Bomber extends Plane
{
    public Bomber(){
        speed = 5;
        bombCounter = 120;
    }

    public Bomber(int timer){
        speed = 5;
        bombCounter = timer;
    }

    /**
     * Act - do whatever the Plane wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move();
        bomb();
        removeAtBorder();
    }

    public void bomb(){
        bombCounter--;
        if (bombCounter == 0){
            getWorld().addObject(bomb, getX()+50, getY());
        }
    }
}
