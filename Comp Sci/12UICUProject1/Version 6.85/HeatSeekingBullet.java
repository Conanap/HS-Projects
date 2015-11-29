import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util. List;
/**
 * Write a description of class HeatSeekingBullet here.
 * 
 * @author Kerry
 * @version (a version number or a date)
 */
public class HeatSeekingBullet extends Bullet
{
    int speed = 5;
     
    public HeatSeekingBullet (int damageAmount){
        this.damage = damageAmount;
        speed = 4;
        flyTime = 50;
    }
    
    /**
     * Act - do whatever the HeatSeekingBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(speed);
        track();
        removeAtBorder();
    }

    public void track(){
        String target;  
        List<Zombie> group = getObjectsInRange(400,Zombie.class);  
        if(group.size()>0){  
            Actor zombie = group.get(0);  
            int targetX = zombie.getX();  
            int targetY = zombie.getY();  
            move(speed);
            turnTowards(targetX,targetY);
            if(getX() == targetX && getY() == targetY)
                getWorld().removeObject(this);
        }
    }
}
