import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Zombie class provides the behaviour of a zombie.
 * 
 * @author William Wu
 * @version October 2,2014
 */
public abstract class Zombie extends People
{
    private int num1 = Greenfoot.getRandomNumber(2) + 1;//Gets a random number, either 1 or 2.
    private int num2 = Greenfoot.getRandomNumber(2) + 1;//Gets a random number, either 1 or 2.
 
    private GreenfootImage pic = new GreenfootImage("zombie.png");
    private HealthyHuman human;
    private Barricade barricade;
    private Bullet bullet;

    /**
     *Sets the movement. 
     */
    protected void move(){
        List<HealthyHuman> human = getObjectsInRange(100,HealthyHuman.class);  
        //If the distance from human is 100, turns towards them.	
        if(human.size()!= 0)  
        {  
            turnTowards(human.get(0).getX(),human.get(0).getY());
            move(speed-1);
            //-1 To prevent zombies from being too powerful. 
            //Call it "knockback" from bullets being shot at them at close range
        }
        else{
            //There are two pathways for zombies that born in the first spawn place. This determines which way should they go.
            if(num1 == 1){
                if(getY() == 52 && (getX() >= 275 && getX() <= 703))
                    setLocation(getX() + speed,getY());//Moving right.
                if(getX() == 701 + speed && (getY() <= 265 && getY() >= 52))
                    setLocation(getX(),getY() + speed);//Moving down
                if(getY() == 265 + speed && (getX() <= 890 && getX() >= 703))
                    setLocation(getX() + speed,getY());//Moving right.
                if(getX() == 884 + speed && (getY() <= 350 && getY() >= 265))
                    setLocation(getX(),getY() + speed);//Moving down.
            }
            else if(num1  == 2){
                if(getY() == 52 && getX() >= 275 && getX() <= 494)
                    setLocation(getX() + speed,getY());//Moving right
                if(getX() == 494 && getY() <= 175 && getY() >= 52 )
                    setLocation(getX(),getY() + speed);//Moving down.
                if(getY() == 178 && getX() <= 494 && getX() >= 399 )
                    setLocation(getX() - speed,getY());//Moving left.
                if(getX() == 398 && getY() <= 265 && getY() >= 175 )
                    setLocation(getX(),getY() + speed);//Moving down
                if(getY() == 268 && getX() <= 884 && getX() >= 398 )
                    setLocation(getX() + speed,getY());//Moving right.
                if(getX() == 887 && getY() <= 350 && getY() >= 265 )
                    setLocation(getX(),getY() + speed);//Moving down.
            }
            //There are two pathways for zombies that born in the second spawn place. This determines which way should they go.
            if( num2 == 1){
                if(getY() == 175 && (getX() >= 220 && getX() <= 489))
                    setLocation(getX() + speed,getY());//Moving right.
                if(getX() == 490 && (getY() >= 52 && getY() <= 175))
                    setLocation(getX(),getY() - speed);//Moving up.
                if(getY() == 52 && (getX() >= 490 && getX() <= 704))
                    setLocation(getX() + speed,getY());//Moving right.
                if(getX() == 706 && (getY() >= 52 && getY() <= 265))
                    setLocation(getX(),getY() + speed);//Moving down.
                if(getY() == 265 && (getX() <= 885 && getX() >= 704))
                    setLocation(getX() + speed,getY());//Moving right.
                if(getX() == 886 && (getY() >= 265 && getY() <= 350))
                    setLocation(getX(),getY() + speed);//Moving down.
            }
            else if(num2  == 2){
                if(getY() == 175 && (getX() >= 220 && getX() <= 400))
                    setLocation(getX() + speed,getY());//Moving right.
                if(getX() == 397 && (getY() >= 175 && getY() <= 268))
                    setLocation(getX(),getY() + speed);//Moving down.
                if(getY() == 268 && (getX() <= 884 && getX() >= 398))
                    setLocation(getX() + speed,getY());//Moving right.
                if(getX() == 886 && (getY() >= 265 && getY() <= 350))
                    setLocation(getX(),getY() + speed);//Moving down.
            }

            //Pathway for zombies born in third spawn place.
            if(getY() == 354 && getX() >= 233 && getX() <= 401)
                setLocation(getX() + speed,getY());//Moving right.
            if(getX() == 401 && getY() >= 267 && getY() <= 354)
                setLocation(getX(),getY() - speed);//Moving up.
            if(getY() == 267 && getX() <= 884 && getX() >= 401)
                setLocation(getX() + speed,getY());//Moving right.
            if(getX() == 887 && getY() >= 267 && getY() <= 350)
                setLocation(getX(),getY() + speed);//Moving down.

            //Pathway for zombies born in fourth spawn place.
            if(getX() == 526 && (getY() <= 400 && getY() >= 359))
                setLocation(getX(),getY() - speed);//Moving up.
            if(getY() == 358 && (getX() <= 526 && getX() >= 400))
                setLocation(getX() - speed,getY());//Moving left.
            if(getX() == 397 && (getY() >= 267 && getY() <= 358))
                setLocation(getX(),getY() - speed);//Moving up.
            if(getY() == 268 && (getX() <= 884 && getX() >= 397))
                setLocation(getX() + speed,getY());//Moving right.
            if(getX() == 886 && getY() >= 268 && getY() <= 350)
                setLocation(getX(),getY() + speed);//Moving down.
        }
    }

    /**
     * Breaks the barricades.
     */
    protected void breakBarricades(){
        barricade = (Barricade)getOneIntersectingObject(Barricade.class);
        if(barricade != null)
            barricade.decreaseHp(2);
    }
    
    /**
     * Breaks the barricades.
     */
    protected void breakBarricades(int damage){
        barricade = (Barricade)getOneIntersectingObject(Barricade.class);
        if(barricade != null)
            barricade.decreaseHp(damage);
    }

    protected void decreaseCurrHp(int damage){
        currhp = currhp - damage;
        if (currhp < 0){
            currhp = 0;
        }
        if (currhp == 0){
            getWorld().removeObject(this);
        }
    }

    protected void infectHuman(HealthyHuman p){
        if (p != null)
        {
            Zombie z = new NormalZombie();
            z.num1 = this.num1;
            z.num2 = this.num2;
            getWorld().addObject(z, p.getX(), p.getY()); //Spawns a zombie where pedestrian was
            getWorld().removeObject(p); //Removes pedestrian body
        }
    }
}
