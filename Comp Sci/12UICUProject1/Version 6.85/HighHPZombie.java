import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Zombie class provides the behaviour of a zombie.
 * 
 * @author William Wu
 * @version October 2,2014
 */
public class HighHPZombie extends Zombie
{
    GreenfootImage pic = new GreenfootImage("HighHPZombie.png");
    private HealthyHuman human;
    private Barricade barricade;
    private ResourceCentre centre;
    private Bullet bullet;
    /**
     *A zombie can move and die.
     */
    public void act() 
    {
        human = (HealthyHuman)getOneIntersectingObject(HealthyHuman.class);
        barricade = (Barricade)getOneIntersectingObject(Barricade.class);
        centre = (ResourceCentre)getOneIntersectingObject(ResourceCentre.class);
        //If there is no human, if so keep moving, otherwise stop moving.
        if(barricade == null && centre == null)
            move();
        //Infects humans.
        infectHuman(human);
        breakBarricades();
    }    
 
    /**
     * Constructs a zombie with speed of 3 and hit point of 100.
     */
    public HighHPZombie(){
        speed = 2;//Sets speed to 3.
        currhp = 1000;//Sets hit point to 100.
        pic.scale(30,30);
        setImage(pic);//Sets image.
    }

    /**
     * Zombie dies.
     */
    public void die(){
        getWorld().removeObject(this);
    }
}