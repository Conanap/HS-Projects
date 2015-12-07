import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;

/**
 * Write a description of class Healthbar here.
 * 
 * @author Roland Li
 * @version (a version number or a date)
 */
public class HealthBar extends Actor
{
    private int xCo, yCo; //Coordinates that the bottom of the image should stay on
    private int maxSize, curSize;
    private int baseSize; //The amount of the bar that will be
    //remaining even if curSize = 0
    private int baseWidth;//Width of bar   

    private int frameNum; //Goes from 1-6
    private int curLoop, maxLoop; //Used to reduce animation speed

    private GreenfootImage base = new GreenfootImage("candle (1).png");
    private GreenfootImage sizedImg; //To initalize images of new sizes to draw the base onto

    private Font font = new Font ("Century Gothic", Font.BOLD, 20); //Font displayed in

    public HealthBar(int max, int x, int y){
        maxSize = max;
        curSize = maxSize;
        baseSize = 100;
        baseWidth = 80;
        frameNum = 1;
        maxLoop = 4;

        xCo = x;
        yCo = y;

        setImage(base);
        update(0);
    }

    public void act(){
        update(0);
        //Put here instead of in update, so if it is called by the player,
        //the framerate stays constant
        frameNum++;
        curLoop++;
    }

    public void update(int num){
        curSize += num;
        if (curSize > maxSize){
            curSize = maxSize;
        }
        else if (curSize < 0){
            curSize = 0;
        }

        sizedImg = new GreenfootImage (baseWidth, baseSize+curSize);

        double sizeRatio;
        if (curSize != 0){
            sizeRatio = maxSize/curSize;}
        else { sizeRatio = 0;}
        GreenfootImage frame; //The new frame to update to

        if(curLoop == 4){
            if (sizeRatio > 0.6){
                frame = new GreenfootImage("candle (" + frameNum + ").png");
            }
            else if (sizeRatio > 0.2){
                frame = new GreenfootImage("candle (" + frameNum + ").png"); //Add different types
            }
            else{
                frame = new GreenfootImage("candle (" + frameNum + ").png");;
            }

            sizedImg.drawImage(frame, 0,0);
            curLoop = 0;
        }
        else { 
            //To immediately change the size of the candle even if this act the candle's animation doesn't change
            sizedImg.drawImage(this.getImage(), 0, 0); 
        }

        this.setImage(sizedImg);
        sizedImg.setFont(font);
        if (curSize > maxSize/4){
            sizedImg.setColor(Color.WHITE);
        } 
        else{
            sizedImg.setColor(Color.RED);
        }
        sizedImg.drawString(""+curSize, 5, sizedImg.getHeight() - 10); //Draw the number value on the bottom
        resetLocation();
        if (frameNum >= 6){ //Reset frame number for next loop
            frameNum = 1;
        }
    }

    public void resetLocation(){
        int y = yCo -(curSize + baseSize)/2; //Finds the point where the base should lie
        setLocation(xCo, y);
    }
}