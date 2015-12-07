import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class ImpactBomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ImpactBomb extends Bomb
{
    protected int xCo, yCo; //Since setting turnTowards in the constructor doesn't work

    GreenfootImage baseImg = new GreenfootImage("tnt.png");

    public ImpactBomb () {
        maxSpeed = 60;
        maxTime = 150; //Time before bomb automatically explodes
        baseDamage = 9;
        radius = 30;

        curSpeed = maxSpeed;
        curTime = 0;
        grav = 1;
        setImage(baseImg);
        explosion.setVolume(30);
    }

    public ImpactBomb (int x, int y) {
        this();
        xCo = x;
        yCo = y;
    }

    public ImpactBomb (int x, int y, int damage, int radius) {
        this(x,y);
        this.baseDamage = damage;
        this.radius = radius;
    }

    public void addedToWorld(World currentWorld){
        turnTowards (xCo, yCo);

        //Sets the speed based off how far away the set distance is

        //Find the distance between the current location and the location to move towards
        //and then find the ratio between this distance and the world size
        double x = getX() - xCo;
        double y = getY() - yCo;
        x = x/currentWorld.getWidth(); 
        y = y/currentWorld.getHeight();
        if (x < 0){ x = x*-1;} //Make sure the values are positive
        if (y < 0) { y = y*-1;}
        //Sets the speed to the maxSpeed
        curSpeed = (int) (maxSpeed*((x+y)/2));
        if (curSpeed > 10){
            curSpeed = 10; //To limit the throwing range of the bomb
        }
    }

    /**
     * Act - do whatever the Bomb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (!exploding){fly();}
        else{ explode();}
    }

    private void fly(){
        move(curSpeed);
        gravity();

        curTime++;
        if (curTime >= maxTime){
            exploding = true;
            dealDamage(); //Happens only once
            explode();
        }
        else {
            checkCollision();
        }
    }
    
    protected void gravity(){
        int gravRounded = (int)grav / 5; 
        grav++;
        //To make the decent more gradual due to the rounding;
        //increases 1 pixel drop roughly once per second
        setLocation(getX(), getY() + gravRounded);
    }

    private void checkCollision(){
        if (curTime >  5){ //Prevents bomb from immediately blowing up on the player if there is a block behind them
            List<Actor> objects = getIntersectingObjects(Actor.class);
            if (objects.size() != 0){
                boolean blowUp = true;  
                //To check if the objects in the list are on the "safe list"
                for (Actor entities: objects){
                    if (entities instanceof Player){ blowUp = false; }
                    if (entities instanceof Rope){ blowUp = false; }
                    if (entities instanceof Torch){ blowUp = false; }
                    if (entities instanceof building) {blowUp = false; }
                }
                if (blowUp){ 
                    dealDamage();
                    exploding = true;
                    explode();
                }
            }
        }
    }
}
