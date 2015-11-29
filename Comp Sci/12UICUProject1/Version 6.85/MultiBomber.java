import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Moves forward and reates multiple "Explosion"s behind 
 * itself at a set interval.
 * 
 * @author Roland 
 * @version (a version number or a date)
 */
public class MultiBomber extends Plane
{
    private int totalBombs;
    private int bombWait;
    
    public MultiBomber(){
        speed = 3;
        totalBombs = 3;
        bombCounter = 40;
        bombWait = bombCounter;
    }

    public MultiBomber(int timer){
        this();
        bombCounter = timer;
        bombWait = bombCounter;
    }
    
    public MultiBomber(int timer, int bombs){
        this(timer);
        totalBombs = bombs;
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
        if (bombCounter == 0 && totalBombs != 0){
            getWorld().addObject(bomb, getX()+50, getY());
            bombCounter = bombWait;
        }
    }  
}
