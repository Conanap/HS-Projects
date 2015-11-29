import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Sniper here.
 * 
 * @author Kerry
 * @version (a version number or a date)
 */
public class HeavyArmedSoldier extends HealthyHuman
{
    /**
     * Constructor of this object,HeavyArmedSolider
     */

    public HeavyArmedSoldier(){
        this.speed = 1;
        facing = "U";
        range = 250;
        fireRate = 100;
        gunSway = 1;
        setImage("attack.PNG");
    }
     
    /**
     * Update the new image of the object when it is moving
     */
    public void move(){
        speed = speed; 
        walkingTimer++;
       
        if(facing == "L")
            move(-speed);
        if(facing == "U")
            setLocation(getX(),getY()- speed);
       
        if(walkingTimer == 10)
            setImage("w1.PNG");
        if(walkingTimer == 20)
            setImage("w2.PNG");
        if(walkingTimer == 30)
            setImage("w3.PNG");
        if(walkingTimer == 40)
            setImage("w4.PNG");
        if(walkingTimer == 50)
            walkingTimer = 0;
    }

 
    /**
     * Act - do whatever the Sniper wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment. 
     */
    public void act(){
        List<ResourceCentre> centre = getWorld().getObjects(ResourceCentre.class);
        centre.get(0).update(0,0,7,12);
        if(isShooting == false){
            movingDirection();
            move();
        }
        if(isZombieInRange()== true){
            setImage("attack.PNG");
            shooting(loadBullet());
        }else{
            isShooting = false;
        }
        decreaseZombieSpawn();
    }    
    /**
     * Call the bullet with particular damage that will be shot from this object
     */
    private Bullet loadBullet(){
        Bullet bullet = new SuperBullet(100);
        return bullet;
    }
}
