import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spider here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spider extends Characters
{
    GreenfootSound hiss = new GreenfootSound("SpiderHiss.mp3");

    private boolean aggro = false; //To attack player if in range
    public Spider(){
        //Stats
        maxHP = 20;
        curHP = maxHP;

        //Movement
        //Does not follow typical vertical/horizontal movement conventions

        base = new GreenfootImage("Spider.png");
        base.scale(30,20);
        setImage(base);

        hiss.setVolume(30);
    }

    /**
     * Act - do whatever the Enemies wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {      
        chargePlayer();
        climbUp();
        dealDamage();
    }

    public void moveX(int num){
        //Does not move horizontally
    }

    public void climbUp(){
        chargePlayer();
        if(!aggro){
            if (!headCollision()){ //Keep climbing up until head collides
                setLocation(getX(), getY()-1);
            }
        }
    }

    public void chargePlayer(){
        Actor player = (Actor) getWorld().getObjects(Player.class).get(0); //Find the player in the world

        int yOffset = player.getY() - this.getY();
        int xOffset = player.getX() - this.getX();
        if (yOffset < 400 && yOffset > 0  && xOffset < 20 && xOffset > -20){ //If within range and directly below
            if (!aggro){
                hiss.play();
                aggro = true;
            }
            setLocation(getX(), getY()+4);
        }
        else {
            aggro = false;
        }
    }

    public void dealDamage(){
        Player player = (Player)getOneIntersectingObject(Player.class);
        if (player!=null){
            player.damage(15);
            hitDamage.play();
            //Attach to player
            setLocation(player.getX(), player.getY());
        }
    }

    public void die(){
        getWorld().removeObject(this);
    }   
}
