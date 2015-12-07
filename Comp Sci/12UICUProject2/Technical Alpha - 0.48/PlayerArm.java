import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
// import java.awt.Color;
/**
 * Write a description of class PlayerArm here.
 * 
 * @author Tyler Zhang
 * @version Jan 2015
 */
public class PlayerArm extends Tools
{
    //     Color color = new Color(0,0,0);
    GreenfootImage currentImage;
    int imageX;
    int imageY;
    int flippedImageX;
    int flippedImageY;
    public PlayerArm(Player tar)
    {
        currentImage = new GreenfootImage("Initial Arm.png");
        name = "bare hands";
        dmg = 2;
        healthMax = 10000000; //Infinite health.
        health = healthMax;
        range = 100;
        speed = 5;
        holdCounter = 0;
        healthCounter = 0;
        target = tar;
        img = new GreenfootImage(48, 28);
        imageX = 18;
        imageY = 0;
        flippedImageX = 18;
        flippedImageY = 0;
        img.drawImage(currentImage, 18, 0);
        setImage(img);
    }

    public void act() 
    {
        GreenfootImage tmpt = currentImage;
        img.clear();
        //         img.setColor(color);
        //         img.fill();
        if(!target.getMoveDir()){
            tmpt.mirrorVertically();
            img.drawImage(tmpt, flippedImageX, flippedImageY);
        } else { 
            img.drawImage(tmpt, imageX, imageY);
        }
        setImage(img);
        followMouse();
    }

    public void update(int tp, int health)
    {
        switch(tp){
            case 0:
            name = "bare hands";
            dmg = 2;
            currentImage = new GreenfootImage("Initial Arm.png");
            imageX = 18;
            imageY = 13;
            flippedImageX = 18;
            flippedImageY = 9;
            break;

            case 1:
            name = "iron pick";
            dmg = 3;
            currentImage = new GreenfootImage("Arm + Pickaxe Iron.png");
            imageX = 18;
            imageY = 2;
            flippedImageX = 18;
            flippedImageY = 6;
            break;

            case 2:
            name = "iron drill";
            dmg = 5;
            currentImage = new GreenfootImage("Arm + Drill Iron.png");
            imageX = 18;
            imageY = 10;
            flippedImageX = 18;
            flippedImageY = 4;
            break;

            case 3:
            name = "silver pick";
            dmg = 6;
            currentImage = new GreenfootImage("Arm + Pickaxe Silver.png");
            imageX = 18;
            imageY = 2;
            flippedImageX = 18;
            flippedImageY = 6;
            break;

            case 4:
            name = "silver drill";
            dmg = 8;
            currentImage = new GreenfootImage("Arm + Drill Silver.png");
            imageX = 18;
            imageY = 10;
            flippedImageX = 18;
            flippedImageY = 4;
            break;

            case 5:
            name = "gold pick";
            dmg = 10;
            currentImage = new GreenfootImage("Arm + Pickaxe Gold.png");
            imageX = 18;
            imageY = 2;
            flippedImageX = 18;
            flippedImageY = 6;
            break;

            case 6:
            name = "gold drill";
            dmg = 15;
            currentImage = new GreenfootImage("Arm + Drill Gold.png");
            imageX = 18;
            imageY = 10;
            flippedImageX = 18;
            flippedImageY = 4;
            break;

            case 7:
            name = "diamond pick";
            dmg = 20;
            currentImage = new GreenfootImage("Arm + Pickaxe Diamond.png");
            imageX = 18;
            imageY = 2;
            flippedImageX = 18;
            flippedImageY = 6;
            break;

            case 8:
            name = "diamond drill";
            dmg = 30;        
            currentImage = new GreenfootImage("Arm + Drill Diamond.png");
            imageX = 18;
            imageY = 8;
            flippedImageX = 18;
            flippedImageY = 4;
            break;

            case 9:
            name = "torch";
            dmg = 0;        
            currentImage = new GreenfootImage("Arm + Torch.png");
            imageX = 18;
            imageY = -4;
            flippedImageX = 18;
            flippedImageY = 6;
            break;

            case 10:
            name = "bomb";
            dmg = 0;        
            currentImage = new GreenfootImage("Arm + Bomb.png");
            imageX = 18;
            imageY = 4;
            flippedImageX = 18;
            flippedImageY = 6;
            break;

            case 11:
            name = "rope";
            dmg = 0;        
            currentImage = new GreenfootImage("Arm + Rope.png");
            imageX = 18;
            imageY = 6;
            flippedImageX = 18;
            flippedImageY = 64;
            break;

            case 12:
            name = "Iron Sword";
            dmg = 0;        
            currentImage = new GreenfootImage("Arm + Sword Iron.png");
            imageX = 19;
            imageY = -4;
            flippedImageX = 19;
            flippedImageY = 7;
            break;

            case 13:
            name = "Silver Sword";
            dmg = 0;        
            currentImage = new GreenfootImage("Arm + Sword Silver.png");
            imageX = 19;
            imageY = -4;
            flippedImageX = 19;
            flippedImageY = 7;
            break;

            case 14:
            name = "Gold Sword";
            dmg = 0;        
            currentImage = new GreenfootImage("Arm + Sword Gold.png");
            imageX = 19;
            imageY = -4;
            flippedImageX = 19;
            flippedImageY = 7;
            break;

            case 15:
            name = "Diamond Sword";
            dmg = 0;        
            currentImage = new GreenfootImage("Arm + Sword Diamond.png");
            imageX = 19;
            imageY = -4;
            flippedImageX = 19;
            flippedImageY = 7;
            break;

            default:
            break;
        }
    }
}