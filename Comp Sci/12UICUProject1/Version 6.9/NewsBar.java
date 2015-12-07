import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;

/**
 * Displays a string which scrolls from right to left. 
 * String stops playing once it reaches the end of the left side.
 * 
 * @author Roland Li 
 * @version (a version number or a date)
 */
public class NewsBar extends ActionScene
{
    private String message;
    private String newMessage;
    private int messageX; //X location of message

    private Font font = new Font ("Century Gothic", Font.BOLD, 20);

    private Color colorBase = new Color (90,90,90);
    private Color colorBaseAlt = new Color (0,90,90);

    private Color colorText = new Color (255,255,255); 
    private Color colorTextAlt = new Color (255,20,20); 

    private GreenfootImage newsBar;
    private GreenfootImage newsBarCover = new GreenfootImage("NewsBar.png");
    private GreenfootSound siren = new GreenfootSound("SirenNoise.wav");
    private final int BAR_WIDTH = 465;
    private final int BAR_HEIGHT = 40;

    public NewsBar() {
        newsBar = new GreenfootImage(BAR_WIDTH, BAR_HEIGHT);
        newsBar.setColor(colorBase);
        newsBar.fill();

        messageX = BAR_WIDTH; //Message starts at the edge of the bar 

        this.setImage(newsBar);
        newsBar.setFont(font);

        newsBarCover.scale(BAR_WIDTH, BAR_HEIGHT);
        newsBar.drawImage(newsBarCover,0,0); //Draws the overlay
    }
    
    public NewsBar(String text){
        this();
        message = text;
    }

    /**
     * Act - do whatever the NewsBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
            updateBar();
    }

    /**
     * Method updateBar
     * Causes the message to scroll to the left side of the bar.
     */
    private void updateBar() {
        if (messageX <= 0 - message.length()*12) { 
            //Message is only switched once the old message is out of sight
            switchMessage(); 
            //Once a message has crossed the screen of the news bar, a new one only appears
            //If a new message is inputted
        }
        else{
            newsBar.setColor(colorBase);
            newsBar.fill(); //Resets the image to cover the previous String

            newsBar.setColor(colorText);
            newsBar.drawString(message,messageX,28);
            newsBar.drawImage(newsBarCover,0,0);

            messageX = messageX-2; //Moves the message to the left
        }
    }

    /**
     * Method switchMessage
     * Switches the message if a new message is inputed, then resets the location of the message so it scrolls.
     * 
     */
    private void switchMessage() {
        if (newMessage != null){
            message = newMessage;
            newMessage = null;
            messageX = BAR_WIDTH + 10;
            siren.play();
        }
    }

    /**
     * Method changeString
     * Stores new message for later
     * 
     * @param Message to output next
     * 
     */
    public void changeMessage(String input) {
        newMessage = input;
    }
}
