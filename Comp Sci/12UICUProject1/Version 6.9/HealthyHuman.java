import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Random;

/**
 * Write a description of class HealthyHuman here.
 * 
 * @Kerry, modded by Roland and Albion 
 * @version (a version number or a date)
 */
public class HealthyHuman extends People
{
    private int myWidth;
    private int walkBack; //For when people collect crates
    protected int branch;

    protected int pathNum;
    protected int[] choice = new int[2];
    protected String direction = null;
    protected String direction1 = null;
    protected String direction2 = null;
    protected String direction3 = null;
    protected String direction4 = null;
    protected int zombiex,zombiey,cratex,cratey,shootingTimer,walkingTimer;
    protected boolean barricadeExist = false;
    protected boolean isShooting = false;

    protected int fireRate; //The lower it is, the faster bullets are fired
    protected int range;
    protected int gunSway; 

    /**
     * Constructor to create objects(Healthy humans who are not infected by zombies)
     * 
     */
    public HealthyHuman(){
        this.speed = 2;
        facing = "U";
        myWidth = 10;
        range = 100;
        fireRate = 5;
        gunSway = 3;
    }

    /**
     * Constructor to create objects(Healthy humans who are not infected by zombies)
     * 
     * @param int speed   the speed of the human
     * 
     */
    public HealthyHuman(int speed){
        this();
        this.speed = speed;
    }

    /**
     * Determine which way to go when reachs branches
     */
    public void pathNavigator(){

        int num = Greenfoot.getRandomNumber(2);
        this.branch = num;

    }

    /**
     * Act - do whatever the HealthyHuman wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        List<ResourceCentre> centre = getWorld().getObjects(ResourceCentre.class);
        centre.get(0).update(0,0,2,2);
        if(isShooting == false){
            if (crateLocation()){
                turnTowards(cratex,cratey);
                move(speed);
                walkBack++;
            }
            else if(walkBack > 0){
                move(-speed);
                walkBack--;
            }
            else{
                setRotation(0);
                movingDirection();
                move();
            }
        }
        if(isZombieInRange()== true){
            shooting(loadBullet());
        }else{
            isShooting = false;
        }
        decreaseZombieSpawn();
    }

    /**
     * Makes humans proceed through the street and does not move toward the buildings
     */
    protected void movingDirection(){
        if(getY() <= 274&&direction != "done"){
            facing = "L";
            direction = "done";
        }

        if(getX() <= 701 &&direction1 != "done"){
            pathNavigator();
            //System.out.println(branch);
            if(branch == 1){
                facing = "L";
                pathNum = 2;
                direction1 = "done";
            }else{
                facing = "U";
                pathNum = 1;
                direction1 = "done";
            }
        }

        if(pathNum ==1){
            if(getY() <=69 )
                facing = "L";

            if(getX() <= 490&& direction2 != "done"){
                pathNavigator();
                if(branch == 1){
                    facing ="L";

                    direction2 = "done";
                }else{
                    facing = "D";
                    pathNum =3;
                    direction2 = "done";
                }

            }
        }

        if(pathNum ==3){
            if(getY() >= 186){
                facing = "L";
                pathNum =4;
            }
        }

        if(pathNum ==4){
            if(getX() >=391)
                facing = "L";
        }

        if(pathNum ==2){
            if(getX() <= 400&&direction3 != "done"){
                pathNavigator();
                if(branch == 1){
                    facing = "U";
                    pathNum = 5;
                    direction3 = "done";
                }else{
                    facing = "D";
                    pathNum = 6;
                    direction3 = "done";
                }

            }
        }

        if(pathNum ==5){
            if(getY()<=192)
                facing = "L";
        }

        if(pathNum == 6){
            if(getY()>=367&& direction4 !="done"){
                pathNavigator();
                if(branch == 1 ){
                    facing = "L";
                    direction4 = "done";
                }else{
                    facing = "R";
                    pathNum = 7;
                    direction4 = "done";
                }
            }
        }

        if(pathNum == 7){
            if(getX()>=516)
                facing = "D";
        }
    }

    /**
     * Update the image when the human moving and also find the facing direction of the human such 
     * as R(right), L(left),U(up),D(down)
     */
    protected void move(){
        speed = speed; 
        walkingTimer++;
        if(facing == "R")
            move(speed);
        if(facing == "L")
            move(-speed);
        if(facing == "U")
            setLocation(getX(),getY()- speed);
        if(facing == "D")
            setLocation(getX(),getY()+ speed);

        if(walkingTimer == 10)
            setImage("walk1.PNG");
        if(walkingTimer == 20)
            setImage("walk2.PNG");
        if(walkingTimer == 30)
            setImage("walk3.PNG");
        if(walkingTimer == 40)
            setImage("walk4.PNG");
        if(walkingTimer == 50)
            walkingTimer = 0;
    }

    /**
     * Shoots gun toward the zombies
     */
    protected void shooting(Bullet bullet){
        Random shotSpread = new Random();

        if(isZombieInRange() == true){
            isShooting = true;
            shootingTimer++;
            zombieExist = true;
            setImage("fire6.PNG");

            if(barricadeExist == false){
                buildBarricade();
            } 

            if(shootingTimer == 1){
                if(facing == "R")
                    getWorld().addObject(bullet,getX()+5,getY());
                else if(facing == "L")
                    getWorld().addObject(bullet,getX()-5,getY());
                else if(facing == "U")
                    getWorld().addObject(bullet,getX(),getY()-5);
                else if(facing == "D")
                    getWorld().addObject(bullet,getX(),getY()+5);

                List<Zombie> group = getObjectsInRange(range,Zombie.class); 
                zombiex = group.get(0).getX();
                zombiey = group.get(0).getY();
                bullet.turnTowards(zombiex + shotSpread.nextInt(this.gunSway) - this.gunSway,zombiey);   
            }
            if(shootingTimer == fireRate)
                shootingTimer =0;
        }
        else{
            isShooting = false;
        }
    }

    /**
     * Decreases zombie spawn rate of the world if it walks off the screen and into zombie spawn territory.
     *
     */
    protected void decreaseZombieSpawn(){
        TheSimulation world = (TheSimulation)getWorld();
        if (getX() <= 208 || getX() >= 958 || getY() <= 3 || getY() >= 480) { 
            world.decreaseZSpawn(30);
            getWorld().removeObject(this);
        }
    }

    /**
     * Creates and returns a new instance of bullet.
     * Allows shooting() to be a protected method while allowing subclasses of HealthyHuman
     * shoot different types of bullets. Gets overwritten by other classes.
     *
     * Roland
     * @return Instance of Bullet()
     */
    private Bullet loadBullet(){
        Bullet bullet = new Bullet(10); 
        //By not making this a class object, each bullet is unique.
        return bullet;
    }

    /**
     * Build the barricade that can block zombie coming through
     */
    public void buildBarricade(){
        TheSimulation world = (TheSimulation)getWorld();

        Actor Barricade = new Barricade(facing);
        List<ResourceCentre> centre=getWorld().getObjects(ResourceCentre.class);
        if (centre.get(0).getWood() >= 10){
            if(facing == "R")
                getWorld().addObject(Barricade,getX()+30,getY());
            if(facing == "L")
                getWorld().addObject(Barricade,getX()-30,getY());
            if(facing == "U")
                getWorld().addObject(Barricade,getX(),getY()-30);
            if(facing == "D")
                getWorld().addObject(Barricade,getX(),getY()+30);  

            centre.get(0).update(0,2,0,0);
            barricadeExist = true;
        }
    }

    /**
     * Find the current location of zombies
     */
    public void zombieLocation(){
        List<Zombie> group = getObjectsInRange(400,Zombie.class); 
        zombiex = group.get(0).getX();
        zombiey = group.get(0).getY();
    }

    /**Finds crates
     * @author Albion Fung
     */
    private boolean crateLocation()
    {
        List<ResourceCrate> crates = getObjectsInRange(75,ResourceCrate.class);
        if(crates.size()>0)
        {
            cratex=crates.get(0).getX();
            cratey=crates.get(0).getY();
            return true;
        }
        else
            return false;
    }

    /**
     * Detect if there is any Resource centre near this object
     */
    public boolean isResourceCentreInRange(){
        return !getObjectsInRange(50, ResourceCrate.class).isEmpty();  
    }

    /**
     * Detect if there is any zombies near this object
     */
    public boolean isZombieInRange()  
    { 
        return !getObjectsInRange(range, Zombie.class).isEmpty();  
    }  
}

