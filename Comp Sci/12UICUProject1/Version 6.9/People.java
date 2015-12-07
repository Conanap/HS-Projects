import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.lang.Math;


/**
 * Write a description of class People here.
 * 
 * @author Kerry 
 * @version (a version number or a date)
 */
public abstract class People extends Actor
{
    protected int speed;
    protected int currhp;
    protected int maxhp;
    protected String facing;

    protected int zombieX;
    protected int zombieY;
    protected boolean zombieExist = false;

    protected double aimAngle;
    private double aimX;
    private double aimY;

    protected int shootRate;
    protected int count;

    /**
     * If contacts the end of the world, removes this object 
     */
    protected void die(){
        if(getX()<169 ||getY() >450){
            getWorld().removeObject(this);
        }
    }

    /**
     *
     * Roland
     * @param targetType A parameter
     */
    protected int aimAtHuman(int distance){

        List<HealthyHuman> target = getObjectsInRange(distance, HealthyHuman.class);
        if (target.size()!=0){ 
            if (target.get(0).getX() > this.getX()){
                aimX = (double)(this.getX() - target.get(0).getX());
                aimY = (double)(this.getY() - target.get(0).getY());
            }
            else {

            }

            aimAngle = aimY/aimX;
            aimAngle = Math.toDegrees(Math.atan(aimAngle));

            return (int) aimAngle;
        } 
        else {
            return -360; 
            //aimAngle should never be this value
            //-360 is simply a value used by subclasses to check if it should shoot
        }
    }

    /**
     *
     * Roland
     * @param targetType A parameter
     */
    protected int aimAtZombie(int distance){

        List<Zombie> target = getObjectsInRange(distance, Zombie.class);
        if (target.size()!=0){ 
            aimX = (double)(this.getX() - target.get(0).getX());
            aimY = (double)(this.getY() - target.get(0).getY());
            aimAngle = aimY/aimX;

            aimAngle = Math.toDegrees(Math.atan(aimAngle));    

            return (int) aimAngle;
        } 
        else {
            return -360; 
            //aimAngle should never be this value
            //-360 is simply a value used by subclasses to check if it should shoot
        }
    }

    
}
