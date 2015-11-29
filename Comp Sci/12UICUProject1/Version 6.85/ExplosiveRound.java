import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Simple class created by other classes. Moves forward and explodes
 * on contact with another object.
 * 
 * @author Roland
 * @version (a version number or a date)
 */
public class ExplosiveRound extends Actor
{
    private int speed;
    private int flyTime = 100;
    private Explosion boom = new Explosion();

    public ExplosiveRound(){
        speed = 5;
    }

    public ExplosiveRound(int inputSpeed){
        speed = inputSpeed;
    }

    /**
     * Act - do whatever the ExplosiveRound wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(speed);
        removeAtBorder();
    }

    private void removeAtBorder(){
        if (getX() <= 208) {
            getWorld().removeObject(this);
        }
        else if (getX() >= 958){
            getWorld().removeObject(this);
        }
        else if(getY() <= 1) {
            getWorld().removeObject(this);
        }
        else if(getY() >= 480){
            getWorld().removeObject(this);
        }
        else {
            explodeAtContact(); 
            //Only can explode if it hasn't already been removed
            //Prevents crashes
        }
    }
    
       /**
     * Method explodeAtContact
     * Checks if there are any intersecting objects with this one.
     * If there are, ExplosiveRound is removed and replaced with an explosion.
     * 
     * @return The return value
     */
    private void explodeAtContact(){
        flyTime--;
        if (flyTime <= 0 ||
           getOneIntersectingObject(HealthyHuman.class) != null){
            getWorld().addObject(boom, getX(), getY());
            getWorld().removeObject(this);
        }
    }
}
