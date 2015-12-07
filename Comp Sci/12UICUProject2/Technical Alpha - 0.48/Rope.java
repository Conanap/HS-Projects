import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Rope here.
 * 
 * @author Roland Li
 * @version (a version number or a date)
 */
public abstract class Rope extends Throwables
{
    protected boolean mobile;
    protected int extraFallTime;//Amount of time rope spends decelerating/fall downwards
    protected int curLength, maxLength;
    protected int bounceCount;
    protected int IDNum;

    protected int xCo,yCo; 
    GreenfootImage baseImg = new GreenfootImage("rope.png");

    protected void extend(int amount){
        if (!touchGround() && amount > 0){
            curLength += amount;
            if (curLength > maxLength){ 
                curLength = maxLength; //Resets value to max
                //Don't call bounce here to prevent flying ropes from bouncing in air
            }
            GreenfootImage newImage = new GreenfootImage (baseImg.getWidth(), curLength); //Create a new image of the appropriate length
            newImage.drawImage(baseImg, 0, 0); //Draw the base image onto it
            this.setImage(newImage); //Set the rope's image to this image
        }
    }

    protected boolean touchGround(){
        int offset = this.getImage().getHeight()/2;

        if (getOneObjectAtOffset(0, offset, Block.class) != null){    
            return true; //Touching a block because object found below 
        }

        return false; //Not touching a block
    }

    protected void resetPosition(int x, int y){ //Makes sure the rope latches to the spot it was made
        int adjust = this.getImage().getHeight()/2;
        setLocation(x, y + adjust);
    }

    public int returnX(){
        return getX();
    }
    
    public int returnY(){
        return getY();
    }
    
    public int returnCLength(){
        return curLength;
    }
    
    public int returnMLength(){
        return maxLength;
    }
    
    public int returnID(){
        return IDNum;
    }
}
