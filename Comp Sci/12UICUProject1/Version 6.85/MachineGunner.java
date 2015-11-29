import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class MachineGunner here.
 * 
 * @author Roland
 * @version (a version number or a date)
 */
public class MachineGunner extends HealthyHuman
{
    public MachineGunner(){
        this.speed = 3;
        facing = "U";
        range = 100;
        fireRate = 2;
        gunSway = 10;
    }
 
    /**
     * Act - do whatever the MachineGunner wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        List<ResourceCentre> centre = getWorld().getObjects(ResourceCentre.class);
        centre.get(0).update(0,0,5,10);
        if(isShooting == false){
            movingDirection();
            move();
        }
        if(isZombieInRange()== true){
            setImage("fire6.PNG");
            shooting(loadBullet());
        }else{
            isShooting = false;
        }
        decreaseZombieSpawn();
    }

    private Bullet loadBullet(){
        Bullet bullet = new HeatSeekingBullet(8);
        return bullet;
    }
}
