import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class superBullet here.
 * 
 * @Kerry  
 * @version (a version number or a date)
 */
public class SuperBullet extends Bullet
{
    private GreenfootImage pic = new GreenfootImage("superBullet.PNG"); 
    /**
     * Constructor of this object,SuperBullet which is called by HeavyArmedSolider class
     */
    public SuperBullet(){
        speed = 5;
        damage = 100;
        flyTime = 200;
        pic.scale(7,3);
        setImage(pic);
    }
    /**
     * Constructor of this object,SuperBullet which is called by HeavyArmedSolider class
     * with parameter of damage 
     * 
     * @param int damage  number of damage of this bullet
     */
    public SuperBullet(int damage){
        this.damage = damage;
        speed = 5;
        flyTime = 200;
    }
     
    /**
     * Act - do whatever the superBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(speed);
        removeAtBorder();
        
    }    
}
