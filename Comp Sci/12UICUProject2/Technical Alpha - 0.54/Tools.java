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
    protected int range;
    protected int speed;
    protected int holdCounter;
    protected boolean mouseDown;
    protected boolean canDig;
    protected boolean isWeapon;

    protected GreenfootImage img;
    protected Player target;

    private Block previousBlock;
    protected GreenfootSound drillSound = new GreenfootSound("DrillSound.mp3");
    protected GreenfootSound pickSound = new GreenfootSound("PickSound.mp3");
    protected GreenfootSound fistSound = new GreenfootSound("FistSound.mp3");

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
            if(isWeapon)
            {
                turnTowards(mouse.getX(), mouse.getY());
                List <Characters> enemies = getWorld().getObjectsAt(mouse.getX(), mouse.getY(), Characters.class);
                if(enemies.size() != 0)
                {
                    Characters enemy = enemies.get(0);
                    if(mouseDown && getDistance(enemy) < range){
                        if(holdCounter > speed){
                            enemy.damage(dmg);
                            animate();
                            if (target.getToolBar().getSelected() != null) target.getToolBar().getSelected().decreaseDuration(1);
                            holdCounter = 0;
                        }else{
                            holdCounter++;
                        }
                        if (name.endsWith("drill")){
                            drillSound.play();
                        }
                        else if (name.endsWith("pick")){
                            pickSound.play();
                        }
                        else if (name.endsWith("hands")){
                            fistSound.play();
                        }
                    }else if (Greenfoot.mouseClicked(enemy) && getDistance(enemy) < range){
                        enemy.damage(dmg);
                        animate();
                        if (target.getToolBar().getSelected() != null) target.getToolBar().getSelected().decreaseDuration(1);
                    }

                    if(Greenfoot.mousePressed(enemy)) {
                        mouseDown = true;
                    }
                    else if(Greenfoot.mouseClicked(null) || Greenfoot.mouseMoved(null)) {
                        mouseDown = false;
                        holdCounter = 0;
                    }
                }

            }else
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
                                if (target.getToolBar().getSelected() != null) target.getToolBar().getSelected().decreaseDuration(1);
                                holdCounter = 0;
                            }else{
                                holdCounter++;
                            }
                        }else if (Greenfoot.mouseClicked(previousBlock) && getDistance(previousBlock) < range){
                            previousBlock.damage(dmg);
                            animate();
                            if (target.getToolBar().getSelected() != null) target.getToolBar().getSelected().decreaseDuration(1);
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
