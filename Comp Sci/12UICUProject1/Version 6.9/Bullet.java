import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util. List;
/**
 * Write a description of class Bullet here.
 * 
 * @author Roland, Kerry 
 * @version (a version number or a date)
 */
public class Bullet extends Actor
{
    protected int speed;
    protected int damage;
    protected int flyTime;
 
    /**
     * Constructor to call this object, Bullet
     */
    public Bullet(){
        damage = 10;
        speed = 5;
        flyTime = 80;
    }

    /**
     * Other constructor to call bullet with damage
     * 
     * @param int damage  number of damage of this bullet
     */
    public Bullet (int damage){
        this.damage = damage;
        speed = 5;
        flyTime = 80;
    }

    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    { move(speed);
      removeAtBorder();
    }    

    /**
     * When the bullet reaches the world border, or has flown
     * past it's flyTime, removes the object. Otherwise, runs
     * hurtZombie()
     * 
     * Roland
     */
    protected void removeAtBorder(){
        if (getX() <= 208 || getX() >= 958 || getY() <= 3 || getY() >= 480) { 
            getWorld().removeObject(this);
        }
        else {
            flyTime--;
            if (flyTime <= 0){
                getWorld().removeObject(this);
            }
            else{
                hurtZombie();
            }
        }
    }

    /**
     * As it hits the zombies, decrease the hp of the zombies
     */
    protected void hurtZombie(){
        int range = 15;
        List<Zombie> target = getObjectsInRange(range, Zombie.class);
        if (target.size()!= 0)
        {
            target.get(0).decreaseCurrHp(damage);
            getWorld().removeObject(this);
        }
    }
}
