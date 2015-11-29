import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Normal zombie class provides the behaviour of a normal zombie.
 * 
 * @author William Wu
 * @version October 2,2014
 */
public class NormalZombie extends Zombie
{
    GreenfootImage pic = new GreenfootImage("zombie.png");
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
     * Constructs a normal zombie with speed of 3 and hit point of 100.
     */
    public NormalZombie(){
        speed = 3;//Sets speed to 3.
        currhp = 100;//Sets hit point to 100.
        pic.scale(15,15);
        this.setImage(pic);
    }

    /**
     * Zombie dies.
     */
    public void die(){
        getWorld().removeObject(this);
    }
}