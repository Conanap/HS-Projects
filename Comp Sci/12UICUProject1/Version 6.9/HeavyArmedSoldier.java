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
        range = 350;
        fireRate = 100;
        gunSway = 1;
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
            shooting(loadBullet());
        }else{
            isShooting = false;
        }
        decreaseZombieSpawn();
    }    

    private Bullet loadBullet(){
        Bullet bullet = new SuperBullet(250);
        return bullet;
    }
}
