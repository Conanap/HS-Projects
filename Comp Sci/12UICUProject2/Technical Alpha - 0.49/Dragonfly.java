import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dragonfly here.
 * 
 * @author Roland Li
 * @version (a version number or a date)
 */
public class Dragonfly extends Characters
{
    GreenfootSound cry = new GreenfootSound("Screech.mp3");
    GreenfootSound buzz = new GreenfootSound("FlyBuzz.mp3");
    private int moveMethod; //from 0 to 3
    private int moveCounter; //to switch methods
    private int shootCounter; //to limit firerate

    private boolean aggro = false; //To player sound file when the pig initally finds the player in range
    public Dragonfly(){
        //Stats
        maxHP = 10;
        curHP = maxHP;

        //Movement
        maxSpeed = 4;
        maxSlowDelay = 1; //Very fast acceleration
        maxMoveDelay = 1; //Slows down quickly

        curSpeed = 0; 
        curSlowDelay = 0; 
        curMoveDelay = 0;

        base = new GreenfootImage("Dragonfly.png");
        cry.setVolume(30);
        buzz.setVolume(30);
        setImage(base);
    }

    /**
     * Act - do whatever the Dragonfly wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        wander();
        attackPlayer();
    }

    public void wander(){
        moveCounter++;
        if (moveCounter >= 10){
            moveCounter = 0; //Reset counter
            moveMethod = Greenfoot.getRandomNumber(4);
            buzz.play();
        }
        if (moveMethod == 0){
            moveX(1); //Right
        }
        else if (moveMethod == 1){
            moveX(-1); //Left
        }
        else if (moveMethod == 2){
            moveY(-1); //Up
        }
        else if (moveMethod == 3){
            moveY(1); //Down
        }
    }

    public void moveX(int num){
        //Move if there is no block directly ahead
        if (!moveCollision(num) && !edgeOfWorld(num)){ 
            //Move if there is space ahead
            curMoveDelay++;
            if(curMoveDelay >= maxMoveDelay){ //Slows acceleration rate for a smoother/slower increase
                accelerate(num);
                curMoveDelay = 0; //Reset counter
            }
            setLocation(getX()+ curSpeed, getY());
        }
        else { //Set enemy to facing the other direction
            GreenfootImage flipImage = getImage(); //Get image
            flipImage.mirrorHorizontally(); //Flip image
            setImage(flipImage); //Set image (sadly cannot do in less steps)
        }
    }

    /**
     * Moves in vertically if it doesn't collide with the ground or ceiling
     * Based off moveX()
     *
     * @param num Negatives move up, Positives move down
     */
    private void moveY(int num){
        //Move if there is no block directly ahead
        if (!headCollision() && !touchGround()){ 
            //Move if there is space ahead
            curMoveDelay++;
            if(curMoveDelay >= maxMoveDelay){ //Slows acceleration rate for a smoother/slower increase
                accelerate(num);
                curMoveDelay = 0; //Reset counter
            }
            setLocation(getX(), getY()+curSpeed);
        }
    }

    private void attackPlayer(){
        Actor player = (Actor) getWorld().getObjects(Player.class).get(0); //Find the player in the world
        //Sets which direction to look for
        int yOffset = player.getY() - this.getY();
        int xOffset = player.getX() - this.getX(); ;

        if (xOffset < 200 && xOffset > -200 && yOffset < 200 && yOffset >-200 ){ //If within range
            if (!aggro){
                cry.play();
                aggro = true;
            }
            maxSpeed = 6; //Go faster!
            spitGoo(player.getX(), player.getY());
        }
        else {
            aggro = false;
            maxSpeed = 4;
        }
    }

    private void spitGoo(int x, int y){
        shootCounter++;
        if (shootCounter >= 30){
            shootCounter = 0; //Reset counter
            World currentWorld = getWorld();//Finds current world
            ImpactBomb bomb = new GooShot(x, y-5, 5, 10);//Creates weak bomb
            currentWorld.addObject(bomb, this.getX(), this.getY()); //Spawns bomb at current location
        }
    }

    public void die(){
        getWorld().removeObject(this);
    }
}
