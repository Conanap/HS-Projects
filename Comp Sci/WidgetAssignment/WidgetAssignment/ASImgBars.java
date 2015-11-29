import greenfoot.*;  
import java.awt.Color;
/**
 * ASImgBars is a Greenfoot Actor that serves the purpose of informing a user of stats.
 * Stats currently include how many lives or mana points a user has.
 * The bar with images on top is designed for a 600 * 400 pixels scenario.
 *<p>
 * The stats are displayed through the use of images.
 * <p>
 * Hearts represent lives and potions represent mana.
 * 
 * @author Ahrenn Sivananthan
 * @version Sept 2014
 */

public class ASImgBars extends Actor  
{  
    private int fullLives = 15; // max lives allowed
    private int lives; // current lives remaining  
    private int fullMana = 15; // max mana allowed  
    private int mana; // current mana remaining  
    private double height; // height of image for hearts
    private int heightTwo; // height of image for mana potions  

    /**
     * Constructs a bar that displays number of lives using hearts. Initializes the values for lives and image height as well.
     *
     * @param numLives The user sets the value for max lives to 15 here. 
     * @param imgHeight The user sets the value for heart height to 32.0 here.
     *      
     */
    public ASImgBars(int numLives, double imgHeight)  
    {  
        lives = numLives; // sets initial number of lives
        fullLives = lives; //sets max lives permitted
        height = imgHeight; //saves height of hearts 
        updateImage(); //creates initial image
    }  

    /**
     * Constructs a bar that displays amount of mana using potions. Initializes the values for mana and image height as well.
     *
     * @param numMana The user sets the value for max mana to 15 here. 
     * @param imgHeight The user sets the value for potion height to 32 here.                  
     */
    public ASImgBars(int numMana, int imgHeight)  
    {  
        mana = numMana; // sets initial number of mana   
        fullMana = mana; // sets max mana permitted
        heightTwo = imgHeight; //saves height of mana 
        updateImageTwo(); //creates initial image
    }  

    /**
     * Checks to see if current number of lives is above zero so that  
     * decreasing lives will change nothing if lives are equal to zero.
     * If lives are above zero then the number of lives can and will be decreased (by specified amount) upon running of method.
     * Runs updateImage() method to reconfigure number of hearts to match number of lives.
     *
     * @param livesDown Needs to be inputted when running the method. Determines how many lives to subtract.
     * @return int  Returns number of lives as an integer (after subtraction), otherwise returns 0.
     *                      
     */
    // Method runs when lives are lost.
    public int updateDecrease(int livesDown)  
    {  
        if (lives == 0) {
            lives = 0; //makes sure lives do not go below zero.
        }
        else{
            lives -= livesDown; //decrease number of lives remaining 
        }
        updateImage(); //update initial image of lives
        return lives;
    }  

    /**
     *  Checks to see if current amount of mana is above zero so that  
     * decreasing mana will change nothing if mana is equal to zero.
     * If mana is above zero then the amount of mana can and will be decreased (by specified amount) upon running of method.
     * Runs updateImageTwo() method to reconfigure number of potions to match amount of mana.
     *
     * @param manaDown Needs to be inputted when running the method. Determines how much mana to subtract.
     * @return int  Returns amount of mana as an integer (after subtraction), otherwise returns 0.
     *                      
     */
    // Method runs when mana is lost
    public int updateDecrease(double manaDown)  
    {  
        if (mana == 0){
            mana = 0; //makes sure mana does not go below zero.
        }
        else{
            mana -= manaDown; // decrease number of mana points remaining
        }
        updateImageTwo(); // updates initial image of mana  
        return mana;
    }  

    /**
     *  Checks to see if current number of lives is below fifteen so that  
     * increasing lives will change nothing if lives are equal to fifteen already (max amount).
     * If lives are below fifteen then the number of lives can and will be increased (by specified amount) upon running of method.
     * Runs updateImage() method to reconfigure number of hearts to match number of lives.
     *
     * @param livesUp Needs to be inputted when running the method. Determines how many lives to add.
     * @return int  Returns number of lives as an integer (after addition), otherwise returns 15.
     *                      
     */
    // Method runs when lives are gained.
    public int updateIncrease(int livesUp)  
    {  
        if (lives == fullLives){
            lives = 15; // sets max lives equal to fifteen
        }
        else{
            lives += livesUp; // increases number of lives remaining by the amount requested
        }
        updateImage(); //updates initial image of lives.
        return lives;
    }  

    /**
     *  Checks to see if current amount of mana is below fifteen so that  
     * increasing mana will change nothing if mana is equal to fifteen already (max amount).
     * If mana is below fifteen then the amount of mana can and will be increased (by specified amount) upon running of method.
     * Runs updateImageTwo() method to reconfigure number of potions to match amount of mana.
     *
     * @param manaUp Needs to be inputted when running the method. Determines how much mana to add.
     * @return int  Returns amount of mana as an integer (after addition), otherwise returns 15.
     *                      
     */
    // Method runs when mana is gained.
    public int updateIncrease(double manaUp)  
    {  
        if (mana == fullMana) {
            mana = fullMana; // sets max mana equal to fifteen
        }
        else{
            mana += manaUp; // increases number of mana points remaining 
        }
        updateImageTwo(); //updates initial image of mana
        return mana;
    }  

    /**
     *  Creates a base image for the hearts (representing lives) to be placed onto
     *  Sets background and border (of bar) to white so it is invisible.
     * Creates a hearts object that stores the image for hearts.
     * Uses a for loop to draw the hearts onto the base image accurate to a properly formatted size.
     * Runs setImage() method (Greenfoot built in) to set the base image to have the right number of hearts on top of it.
     *
     *                      
     */
    // Method runs to update number of hearts on screen.
    public void updateImage()  
    {  
        int heightOne = (int)height;//value of height for hearts recasted
        GreenfootImage image = new GreenfootImage(fullLives*heightOne+64, heightOne); // create the image's object
        Color borderColorOne = new Color(255,255,255);//creates border color of white
        image.drawRect(0, 0, image.getWidth()-1, image.getHeight()-1); // frame added to the object
        GreenfootImage hearts = new GreenfootImage("hearts_Ahrenn.png"); // obtains image of heart  
        hearts.scale(4*heightOne/6, 4*heightOne/6); // resizes image of heart
        image.setColor(borderColorOne);//sets color of image to white background
        image.fill();// fills background with white
        for (int i=0; i<lives; i++){
            image.drawImage(hearts, 4*+4+i*9*4,4); // adds a specific number of hearts to image based on number of lives remaining
        }
        setImage(image); //Actually makes the image look like how the values above desire it to be.
    }  

    /**
     *  Creates a base image for the potions (representing mana) to be placed onto.
     *  Sets background and border (of bar) to white so it is invisible.
     * Creates a potions object that stores the image for potions.
     * Uses a for loop to draw the potions onto the base image accurate to a properly formatted size.
     * Runs setImage() method (Greenfoot built in) to set the base image to have the right number of potions on top of it.
     *
     */
    // Method runs to update number of mana potions on screen.
    public void updateImageTwo()
    {  
        GreenfootImage imageTwo = new GreenfootImage(fullMana*heightTwo+64, heightTwo); // create the image's object
        Color borderColorTwo = new Color(255,255,255);//creates border color of white
        imageTwo.drawRect(0, 0, imageTwo.getWidth()-1, imageTwo.getHeight()-1); // frame added to the object
        GreenfootImage potions = new GreenfootImage("mana_Ahrenn.png"); // obtains image of potion
        potions.scale(4*heightTwo/6, 4*heightTwo/6); // resizes image of potion
        imageTwo.setColor(borderColorTwo);//sets color of image to white background
        imageTwo.fill();// fills background with white
        for (int i=0; i<mana; i++){
            imageTwo.drawImage(potions, 4*+4+i*9*4,4); // adds a specific number of potions to image based on amount of mana remaining
        }
        setImage(imageTwo); //Actually makes the image look like how the values above desire it to be.
    }  
}  

