import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 *Bars that show the amount of resources left.
 * @Albion Fung
 * @0.0.4
 */
public class ResourceCentre extends ActionScene
{ 
    //declaration of instance variables
    private GreenfootImage bar;//creating new greenfoot image
    private int healthLength,woodLength,foodLength,waterLength;//declaring variables for the values of thehealth bar overlay
    private double maxHP,currentHP,maxwood,maxfood,maxwater,wood,food,water;//declaring variables for maximum HP, maximum diameter, current HP
    private boolean alive;
    private double damage;
    /**
     * Constructing a new health bar. No parameters,  automatically defaults maximum HP and manna to 100.
     */
    public ResourceCentre(int imHP)//constructor to construct new health circles
    {
        bar = new GreenfootImage("resourceCenter.png");//defining the greenfoot image called bar
        setImage(bar);
        maxHP=imHP;//initializing maximum HP
        currentHP=maxHP;//no input from user to specify current manna and hp, and so it is set to max
        base();//drawing base of the health bar
        wood = 700;
        maxwood=700;
        food = 50000;
        maxfood=50000;
        maxwater=50000;
        water = 50000;
        drawHealth();
        drawIdent();
    }
 
    /**
     * This method draws and sets the basics of the health bar. Cannot be accessed outside of the class.
     */
    private void base ()//private method, creates the background and the base of the health bar
    {

        bar.setColor(white);//setting color ofthe background of the circle, status box, and manna bar to white
        bar.fillRect(30,5,110,15);//drawing base of health bar
        bar.fillRect(30,25,110,15);
        bar.fillRect(30,45,110,15);
        bar.fillRect(30,65,110,15);
    }

    public void act(){
        checkZombies();
        checkMax();
        base();
        drawHealth();
        drawIdent();
    }

    /**
     * Updating the health bars. Must be called every time you want to change the amount of HP.
     * @param damage If 0, no change; damage changes with the integer inputted 
     * @param inwd Change to wood. If 0, no change; if 1, resupplies wood (when crate is picked up); if 2, wood is consumed (call from creating barricade)
     * @param infd Chagne to food. If 0, ''; if 1, resupplies food(crate pick up); if 2, food consumed (call from act() in human)
     * @param inwt Change to water. If 0, ''; if 1, resupplies water(crate);if 2, water consumed (call from act() in human)
     */
    public void update(double damage, int inwd, int infd, int inwt)//update method #1, taking boolean to determine if damage was done 
    {
        base();//drawing the base
        checkMax();
        change(damage,inwd,infd,inwt);
        drawHealth();
        drawIdent();
    }

    /**
     * Check if its alive
     * @return boolean If true, the world is still alive, else game over.
     */
    public boolean checkAlive()
    {
        if(this.currentHP>0)
            return true;
        else
            return false;
    }

    private void drawHealth()
    {
        double hpPer = currentHP/maxHP;
        double wdPer = wood/maxwood;
        double wtPer = water/maxwater;
        double fdPer = food/maxfood;
        detColor(hpPer);
        bar.fillRect(30,5,((int)(110*hpPer)),15);
        detColor(wdPer);
        bar.fillRect(30,25,((int)(110*wdPer)),15);
        detColor(wtPer);
        bar.fillRect(30,45,((int)(110*wtPer)),15);
        detColor(fdPer);
        bar.fillRect(30,65,((int)(110*fdPer)),15);
    }

    /**
     * checkMax();//calls method checkMax() to confirm that the current hp and manna is not over max hp and manna
    double percent = currentHP/maxHP;//determining percent of HP object has
    //int diameter2 = (int) diameter;
    detColor(percent);//calling method detColor() with the percent of health to determine what color the items should be
    healthLength=(int) (percent*130);//finding how long the blue overlay bar should be
    bar.fillRect(5,5,healthLength,10);//filling in the amount of manna the object has
     */
    private void drawIdent()
    {
        bar.setColor(black);
        bar.drawString("Health",43,15);
        bar.drawString("Wood",45,35);
        bar.drawString("Water",45,55);
        bar.drawString("Food",46,75);
    }

    /**
     * Private method, cannot be accessed outside of class. Determines the color of the smaller circle and status box.
     * If you don't like the colors you can change the parameters here
     */
    private void detColor (double percent)//private method to determine what color the smaller circle and status box should be
    {
        if(percent>.80)//if more than 80% hleath, the objects will be green
            bar.setColor(green);
        else if(percent>.60)//if less than 80% and more than 60%, objects will be yellow
            bar.setColor(yellow);
        else if(percent>.40)//if less than 60% and more than 40%, objects will be orange
            bar.setColor(orange);
        else//if less than 40%, objects will be red
            bar.setColor(red);
    }

    /**
     * Private method, to prevent the circle from being larger than intended (max diameter) or having negative numbers, which will cause delays and inaccurate display of
     * health and status. The same is true for numbers larger than max HP or max Manna possible.
     */
    private void checkMax ()
    {
        if(currentHP>maxHP)//if the new current HP is larger than the maximum allowed HP, it is changed to the maximum HP
            currentHP=maxHP;
        else if(currentHP<0)//if the new current HP is les than zero, it is changed to zero
            currentHP=0;
        
        if(wood>maxwood)
            wood=maxwood;
        else if(wood<0)
            wood=0;

        if(food>maxfood)
            food=maxfood;
        else if(food<0)
            food=0;

        if(water>maxwater)
            water=maxwater;
        else if(water<0)
            water=0;
    }

    private void change(double damage, int inwd, int infd, int inwt)
    {
        if(damage > 0)//determining if HP should be reduced
            currentHP-=damage;//reducing HP in increments of 1
        if(inwd==1)
            wood+=200;
        else if (inwd==2)
            wood-=10;
        if(infd==1)
            food+=700;
        else if(infd==2)
            food-=2;
        if(inwt==1)
            water+=700;
        else if(inwt==2)
            water-=2;
        checkMax();
    }

    private void checkZombies()

    //Roland
    {
        int range = 80;
        List<NormalZombie>nz = getObjectsInRange(range, Zombie.class);
        if (nz.size()!= 0)
        {
            damage = 2*(nz.size()+1);
            update(damage,0,0,0);
        }
        else{
            damage = 0;
        }
        List<FastZombie>fz = getObjectsInRange(range, Zombie.class);
        if (fz.size()!= 0)
        {
            damage = 0.5*(fz.size()+1);
            update(damage,0,0,0);
        }
        else{
            damage = 0;
        }
        List<HighDamageZombie>hdz = getObjectsInRange(range, Zombie.class);
        if (hdz.size()!= 0)
        {
            damage = 6*(hdz.size()+1);
            update(damage,0,0,0);
        }
        else{
            damage = 0;
        }
        List<GiantZombie>gz = getObjectsInRange(range, Zombie.class);
        if (gz.size()!= 0)
        {
            damage = 15*(gz.size()+1);
            update(damage,0,0,0);
        }
        else{
            damage = 0;
        }
        List<HighHPZombie>hhpz = getObjectsInRange(range, Zombie.class);
        if (hhpz.size()!= 0)
        {
            damage = 1*(hhpz.size()+1);
            update(damage,0,0,0);
        }
        else{
            damage = 0;
        }
    }
    
    public double getWood()
    {
        return wood;
    }
    public double getFood()
    {
        return food;
    }
    public double getWater()
    {
        return water;
    }
    public double getHealth()
    {
        return currentHP;
    }
}
