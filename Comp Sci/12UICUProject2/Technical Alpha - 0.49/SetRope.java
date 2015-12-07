import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SetRope here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SetRope extends Rope
{

    public SetRope () {
        curSpeed = 5; //Speed of rope lowering
        maxSpeed = 10;
        maxLength = 250;
    }
    
    public SetRope (boolean changeAdd, int ID) {
        curSpeed = 5; //Speed of rope lowering
        maxSpeed = 10;
        maxLength = 250;
        IDNum=ID;
    }

    public SetRope(int mLength){
        curSpeed = 5; //Speed of rope lowering
        maxLength = mLength;
    }

    public SetRope(int mLength, int cLength){
        this(mLength);
        curLength = cLength;
    }

    public SetRope(int mLength, int cLength, int ID){
        this(mLength,cLength);
        IDNum = ID;
    }

    public void addedToWorld(World currentWorld){
        xCo = getX();
        yCo = getY();
    }

    /**
     * Act - do whatever the SetRope wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //Makes rope accelerate in dropping speed
        if (curSpeed < maxSpeed){curSpeed++;} 
        extend(curSpeed);
        resetPosition(xCo, yCo);
    }    
}
