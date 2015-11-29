import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Plane here.
 * 
 * @author Roland
 * @version (a version number or a date)
 */
public abstract class Plane extends Actor
{
    protected int speed;
    protected int bombCounter;
    
    protected Explosion bomb = new Explosion();
    
    protected void move(){
        setLocation(getX()-speed, getY());
    }
    
    abstract void bomb();
        
    protected void removeAtBorder(){
        if (getX() <= 208) {
            getWorld().removeObject(this);
        }
    }
}
