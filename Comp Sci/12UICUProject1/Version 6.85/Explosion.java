import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Explosion here.
 * 
 * @author Roland
 * @version (a version number or a date)
 */
public class Explosion extends Actor
{
    private int frameNum;
    private GreenfootSound explosion = new GreenfootSound("Explosion.mp3");

    public Explosion(){
        frameNum = 0;
    }

    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        killPeople();
        animation();
    }

    public void animation() {
        if (frameNum == 0){
            explosion.play();
        }
        if (frameNum >= 16){
            frameNum = 1; 
            //For classes that reuses an explosion instance like MultiBomber
            getWorld().removeObject(this);
        }
        frameNum++;
        this.setImage("explosion (" + frameNum + ").png");
    }

    /**
     * Method killHumans
     * Kills all humans, including zombies, that are within it's radius.
     */
    public void killPeople() {
        int range = 75;
        List<People> p = getObjectsInRange(range,People.class);
        if (p.size() != 0)
        {
            for (int i = 0; i <p.size(); i++){
                if (p.get(i) instanceof GiantZombie == false){ 
                    //Giant zombies are immune to explosives
                    getWorld().removeObject(p.get(i)); 
                    //Removes each object of the People class within the radius
                }
            }
        }
    }
}