import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.lang.Math;

/**
 * Should be a subclass of zombie, inherits HP etc.
 * 
 * Shoot an ExplosiveRound at nearest human within its radius. 
 * 
 * @author Roland 
 * @version (a version number or a date)
 */
public class GiantZombie extends Zombie
{
    private GreenfootImage base = new GreenfootImage("GiantZombie.png");
    public GiantZombie(){
        shootRate = 50;
        count = 0;
        currhp = 1000;
        base.scale(160,160);
        setImage(base);
    }

    public GiantZombie(int rate){
        this();
        shootRate = rate;
    }
 
    /**
     * Act - do whatever the GiantZombie wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        shootAtHuman();

    }

    public void shootAtHuman(){
        count++;
        ExplosiveRound round = new ExplosiveRound();
        if (count >= shootRate){
            aimAngle = (aimAtHuman(300));
            if (aimAngle != -360){
                getWorld().addObject(round, getX()+30, getY());
                round.setRotation((int)aimAngle);

                count = 0;
            }
        }
    }
}
