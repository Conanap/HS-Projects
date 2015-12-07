import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Charger extends Characters
{
    GreenfootSound grunt = new GreenfootSound("PigGrunt.mp3");

    private boolean aggro = false; //To player sound file when the pig initally finds the player in range
    public Charger(){
        //Stats
        maxHP = 50;
        curHP = maxHP;

        //Movement
        maxSpeed = 2;
        maxSlowDelay = 8; //Causes increase in forward acceleration to happen once per 8 acts (hard to slow down)
        maxMoveDelay = 5;
        maxFallSpeed = 30;
        maxFallDelay = 1; //No delay, falls downward faster because heavy

        moveDir = true;

        curSpeed = 0; 
        curSlowDelay = 0; 
        curMoveDelay = 0;
        curFallSpeed = 1;
        curFallDelay = 0;
        curJumpCount = 0;

        base = new GreenfootImage("Hound.png");
        grunt.setVolume(30);
        setImage(base);
    }

    /**
     * Act - do whatever the Enemies wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (moveDir){
            moveX(1); //Move left
        }
        else {
            moveX(-1); //Move right
        }
        chargePlayer();
        decelerate();
        gravity();
    }

    public void moveX(int num){ //Inherited from abstract, cant be public
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
        else { //Set enemy to be moving in the other direction
            if (moveDir) {
                moveDir = false;
            }
            else {moveDir = true;}
            GreenfootImage flipImage = getImage(); //Get image
            flipImage.mirrorHorizontally(); //Flip image
            setImage(flipImage); //Set image (sadly cannot do in less steps)
        }
    }

    private void chargePlayer(){
        Actor player = (Actor) getWorld().getObjects(Player.class).get(0); //Find the player in the world
        //Sets which direction to look for
        int yOffset = player.getY() - this.getY();
        int xOffset;
        if (moveDir){ //Moving right
            xOffset = player.getX() - this.getX();
        }
        else {
            xOffset = this.getX() - player.getX();
        }
        if (xOffset < 400 && xOffset > 0 && yOffset < 20 && yOffset >-20 ){ //If within range
            if (!aggro){
                grunt.play();
                aggro = true;
            }
            maxSpeed = 5; //Go faster!
            maxMoveDelay = 3; //Accelerate faster!
            maxSlowDelay = 20; //Harder to slow down!
            dealDamage(); //Hurt player!
        }
        else {
            aggro = false;
            maxSpeed = 2;
            maxMoveDelay = 5; //Accelerate slower
            maxSlowDelay = 4;
        }
    }

    private void dealDamage(){
        Player player = (Player)getOneIntersectingObject(Player.class);
        if (player!=null){
            player.damage(20);
            hitDamage.play();
        }
    }

    public void die(){
        getWorld().removeObject(this);
    }
}
