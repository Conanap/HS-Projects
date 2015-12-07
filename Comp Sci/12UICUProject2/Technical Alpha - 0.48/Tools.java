import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * The tool superclass.
 * 
 * @author (Tyler Zhang) 
 * @version (Jan 2015)
 */
public abstract class Tools extends Actor
{
    protected String name;
    protected int dmg;
    protected int health;
    protected int healthMax;
    protected int range;
    protected int speed;
    protected int holdCounter;
    protected int healthCounter;
    protected boolean mouseDown;
    protected boolean canDig;
    
    protected GreenfootImage img;
    protected Player target;
    
    private Block previousBlock;

    /**
     * Follow the cursor.
     */
    protected void followMouse()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (target != null){
            if(target.getMoveDir()){
                setLocation(target.getX() -2, target.getY()+5);
            }else{
                setLocation(target.getX() +2, target.getY()+5);
            }
            if(getRotation() <= 90 || getRotation() >= 270){
                target.setMoveDir(true);
            }else{
                target.setMoveDir(false);
            }
        }else{
            destroy();
        }

        if (mouse != null) 
        {  
            if (previousBlock != null){ //Prevent nullpointer
                previousBlock.unhighlight(); //Update the previously highlighted block
            }
            turnTowards(mouse.getX(), mouse.getY());
            List <Block> blocks = getWorld().getObjectsAt(mouse.getX(), mouse.getY(), Block.class);
            if(blocks.size() != 0)
            {
                previousBlock = blocks.get(0); //Store the current block the mouse is on
                if(getDistance(previousBlock) < range)
                {
                    previousBlock.highlight(true);
                }else{
                    previousBlock.highlight(false);
                }

                if(canDig){ //Only allows block destruction if this boolean is true; changed by toolbar through mutator
                    if(mouseDown && getDistance(previousBlock) < range){
                        if(holdCounter > speed){
                            previousBlock.damage(dmg);
                            animate();
                            loseHealth();
                            holdCounter = 0;
                        }else{
                            holdCounter++;
                        }
                    }else if (Greenfoot.mouseClicked(previousBlock) && getDistance(previousBlock) < range){
                        previousBlock.damage(dmg);
                        animate();
                        loseHealth();
                    }
                }

                if(Greenfoot.mousePressed(previousBlock)) {
                    mouseDown = true;
                }
                else if(Greenfoot.mouseClicked(null) || Greenfoot.mouseMoved(null)) {
                    mouseDown = false;
                    holdCounter = 0;
                }
            }
        }
    }

    /**
     * Decrease the health of the tool by one.
     */
    public void loseHealth()
    {
        if(healthCounter > 10){
            health--;
            healthCounter = 0;
        }else{
            healthCounter++;
        }
        if(health <= 0){
            destroy();
        }
    }

    /**
     * Increase the health of the tool.
     * @param amt The amount to restore by.
     */
    public void heal(int amt)
    {
        health += amt;
        if(health > healthMax){
            health = healthMax;
        }
    }

    protected void animate()
    {
        if(this.getRotation() <= 90 || this.getRotation() >= 270){
            turn(10);
        }else{
            turn(-10);
        }
    }

    public void changeDig (boolean diggable){
        canDig = diggable;
    }

    /**
     * Destroy the tool.
     */
    public void destroy()
    {
        getWorld().removeObject(this);
    }

    /**
     * Return the distance from this to another object.
     * 
     * @return double the distance of between two actors.
     */
    protected double getDistance(Actor actor) {
        return Math.hypot(actor.getX() - getX(), actor.getY() - getY());
    }
}
