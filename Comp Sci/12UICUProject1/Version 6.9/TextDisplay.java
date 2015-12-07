import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font;
import java.awt.Color;

/**
 * Write a description of class TextDisplay here.
 * 
 * @author Roland Li
 * @version (a version number or a date)
 */
public class TextDisplay extends StatsDisplay
{
    private String message;
    private String unit;
    private int value;
    private int checkValue;

    //Text is smaller than others
    private Font font = new Font ("Century Gothic", Font.BOLD, 14); 
    private GreenfootImage base = new GreenfootImage(150,30);
    private Color colorText = new Color (255,255,255); 
    private Color colorTextAlt = new Color (255,20,20);

    public TextDisplay(String title){
        message = title;
        value = 0;

        setImage(base);
        base.setFont(font);
        base.setColor(colorText);
        updateDisplay();
    }

    public TextDisplay(String title, String secondary){
        this(title);
        unit = secondary;
    }

    public TextDisplay(String title, int threshold){
        this(title);
        checkValue = threshold;
    }

    public TextDisplay(String title, String secondary, int threshold){
        this(title);
        unit = secondary;
        checkValue = threshold;
    }

    /**
     * Act - do whatever the TextDisplay wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        updateDisplay();
    }

    public void updateNumber(int num){
        value = num;
    }

    public void addNumber(int num){
        value = value+num;
    }

    public void subNumber(int num){
        value = value-num;
        if (value < 0){
            value = 0;
        }
    }

    private void updateDisplay() {
        if (value <= checkValue){
            base.setColor(colorTextAlt);
        }
        else{
            base.setColor(colorText);
        }

        base.clear();
        if (unit!=null){
            base.drawString(message + ": " + value + unit,0,20);
        }
        else{
            base.drawString(message + ": " + value,0,20);
        }
    }
}