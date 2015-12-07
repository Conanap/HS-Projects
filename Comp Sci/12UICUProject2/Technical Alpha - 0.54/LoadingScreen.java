import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.geom.Arc2D;
import java.lang.Math;

/**HPBar is a Greenfoot Actor that display the percentage value in a circular bar.
 * The color of the bar is going to be adjusted(from green to red) basing on the current percentage of hp. (loaded data)
 * 
 * @author Tyler Zhang, modified by Albion Fung
 * @version Jan 2014
 */
public class LoadingScreen extends Actor
{
    //Declare instance variables.
    private GreenfootImage bar; 
    private int maxHP;
    private int curHP;
    private double curPercentHP;
    private boolean selfDestroy;

    //Set constant value
    private final int BAR_RADIUS = 50;
    private final int CONTAIN_SIZE = 100;
    
    //loading screen
    private GreenfootImage background;

    /**
     * Creates a HPBar with the default hp value, 100 and set the current HP value to full.
     */
    public LoadingScreen()
    {
        bar = new GreenfootImage(CONTAIN_SIZE, CONTAIN_SIZE); // Initialize the GreenfootImage of the bar.
        background = new GreenfootImage("Loading Screen.png");
        //setImage(background);

        maxHP = 100; //Set the default value for maxHP.
        curHP = maxHP; //Set the default value for curHP.
        selfDestroy = false; //Set the default value of selfDestroy.

        curPercentHP = calcPer(); //Calculate the percent ratio of curHP to maxHP.
        update(curPercentHP); //Update the image of the HPBar.
    }

    /**
     * Creates a LoadingScreen given a max hp and real-time hp. 
     * 
     * @param max     The maximum amount of hp that the player can have.
     * @param current The current amount of hp that the player has.
     */
    public LoadingScreen(int max, int current)
    {
        bar = new GreenfootImage(CONTAIN_SIZE, CONTAIN_SIZE); // Initialize the GreenfootImage of the bar.

        background = new GreenfootImage("Loading Screen.png");
        //setImage(background);
        
        maxHP = max; //Take in the value of maxHP.
        curHP = current; //Take in the value of curHP.
        selfDestroy = false; //Set the default value of selfDestroy.

        if (curHP > maxHP)
        {
            curHP = maxHP; //If the value of currHP is greater than the maxHP value, set curHP as maxHP.
        }

        curPercentHP = calcPer(); //Calculate the percent ratio of curHP to maxHP.
        update(curPercentHP); //Update the image of the HPBar.
    }

    /**
     * Creates a HPBar given a max hp and real-time hp and set whether the hp bar will self destruct when the hp run out.
     * 
     * @param max      The maximum amount of hp that the player can have.
     * @param current  The current amount of hp that the player has.
     * @paran destroy Whether to destroy the widget when hp run out or not.
     */
    public LoadingScreen(int max, int current, boolean destroy)
    {
        bar = new GreenfootImage(CONTAIN_SIZE, CONTAIN_SIZE); // Initialize the GreenfootImage of the bar.

        background = new GreenfootImage("Loading Screen.png");
        //setImage(background);
        
        maxHP = max; //Take in the value of maxHP.
        curHP = current; //Take in the value of curHP.
        selfDestroy = destroy; //Set the value of selfDestroy.

        if (curHP > maxHP)
        {
            curHP = maxHP; //If the value of currHP is greater than the maxHP value, set curHP as maxHP.
        }

        curPercentHP = calcPer(); //Calculate the percent ratio of curHP to maxHP.
        update(curPercentHP); //Update the image of the HPBar.
    }

    /**
     * Act - Check to see if whether to increase or decrease the hp depending on key press.
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (selfDestroy && curPercentHP == 0){
            this.getWorld().removeObject(this);
        }
    }    

    /**
     * Calculate the percentage ratio of current hp to max hp.
     * 
     * @return double The current percentage value of curHP to maxHP.
     */
    private double calcPer()
    {
        if (curHP > maxHP){ // Make sure the maximum value of percentage doesn't exceed 100.
            curHP = maxHP;
            curPercentHP = 100;
            return curPercentHP;
        }else if (curHP <= 0){ // Make sure the minimum value of percentage doesn't go below 0.
            curHP = 0;
            curPercentHP = 0;
            return curPercentHP;
        }else{ // Return a double carrying the percentage ratio.
            curPercentHP = (double)curHP / (double)maxHP * 100;
            curPercentHP = (double)Math.round(curPercentHP * 100) / 100;
            return curPercentHP;
        }
    }

    /**
     * Increment the current hp value by 1.
     */
    public void increase(){
        curHP++;
        update(calcPer()); // Uodate the image of bar with the new percentage ratio.
    }

    /**
     * Increment the current hp value by a certain value.
     * 
     * @param n the value that the hp will increase by.
     */
    public void increase(int n){
        curHP += n; //increase the curHP by n.
        update(calcPer()); // Uodate the image of bar with the new percentage ratio.
    }

    /**
     * Decrement the current hp value by 1.
     */
    public void decrease(){
        curHP--;
        update(calcPer());// Uodate the image of bar with the new percentage ratio.
    }

    /**
     * Decrement the current hp value by a certain value.
     * 
     * @param n the value that the hp will decrease by.
     */
    public void decrease(int n){
        curHP -= n; // Decrease the value of curHP by n.
        update(calcPer());// Uodate the image of bar with the new percentage ratio.
    }

    /**
     * Generate a HSB color from green to red basing on the precentage ratio of hp.
     * 
     * @param per The percentage ratio of curHP to maxHP.
     * @return Color The HSB color that corespond to the percent ratio.
     */
    public Color getColor(double per)
    {
        double H = per / 100 * 0.4; // Hue (note 0.4 = Green, see huge chart below)
        double S = 0.9; // Saturation
        double B = 0.9; // Brightness

        return Color.getHSBColor((float)H, (float)S, (float)B);
    }

    /**
     * Update the HPBar with a new adjustment. The value will be either added to or subtracted from the curHP value basing on the value of boolean upDown.
     * 
     * @param dif The adjustment value that will be apply to curHP
     * @param upDown True for increase HP, false for decrease.
     */
    public void update (int dif, boolean upDown)
    {
        if(upDown){
            increase(dif);
        }else{
            decrease(dif);
        }
    }

    /**
     * Update the HPBar with a new hp value. The new hp value will be set as curHP.
     * 
     * @param current The new current hp value.
     */
    public void update (int current)
    {
        curHP = current;
        update(calcPer());// Uodate the image of bar with the new percentage ratio.
    }

    /**
     * Expects new HP percent and reprint the image of the HP Bar.
     * 
     * @param per The percentage ratio of curHP to maxHP.
     */
    public void update (double per)
    {
        background = new GreenfootImage ("Loading Screen.png");
        curPercentHP = per; //Store the value of new percentage ratio.
        curHP = (int)(maxHP * per / 100);
        bar.clear(); // Clear the image in bar
        drawImage(per); // Draw the new image
        this.setImage(bar);
        background.drawImage(bar,580,470);
        this.setImage(background);
    }

    /**
     * Update the image of the HPBar.
     * 
     * @param per The percentage ratio of curHP to maxHP.
     */
    private void drawImage(double per)
    {
        //         int[] polyX = {0,37,50,63,100,88,100,88,100,63,50,37,0,12,0,12}; //Draw a polygonal background for the spikes around the circle.
        //         int[] polyY = {0,12,0,12,0,38,50,63,100,88,100,88,100,63,50,37};
        //         bar.setColor(Color.BLACK);
        //         bar.fillPolygon(polyX, polyY, 16);

        Color newCol = getColor(per); // Get the HSB scale color for fill color.

        bar.setColor(Color.BLACK); // Draw the black outer ring.
        bar.fillOval(0,0,CONTAIN_SIZE,CONTAIN_SIZE);

        bar.setColor(newCol); // Draw the colored outer ring.
        bar.fillOval(4,4,CONTAIN_SIZE-8,CONTAIN_SIZE-8);

        bar.setColor(Color.BLACK); // Draw the black outer ring.
        bar.fillOval(6,6,CONTAIN_SIZE-12,CONTAIN_SIZE-12);

        bar.setColor(newCol); 
        double angle = 360 * per / 100; // Get the angle of arc basing on the percent ratio.
        bar.fillShape(new Arc2D.Double(9, 9, (double)CONTAIN_SIZE - 18, (double)CONTAIN_SIZE - 18, 0, angle, Arc2D.PIE));

        bar.setColor(Color.WHITE);
        bar.drawLine(12, CONTAIN_SIZE / 2, CONTAIN_SIZE - 12, CONTAIN_SIZE / 2);
        bar.drawLine(CONTAIN_SIZE / 2, 12, CONTAIN_SIZE / 2, CONTAIN_SIZE - 12);
        bar.drawLine(25, 25, CONTAIN_SIZE - 25, CONTAIN_SIZE - 25);
        bar.drawLine(25, CONTAIN_SIZE - 25, CONTAIN_SIZE - 25, 25);

        bar.setColor(Color.BLACK); // Draw the inner black ring.
        bar.fillOval(22, 22, CONTAIN_SIZE - 44, CONTAIN_SIZE - 44);

        bar.setColor(newCol); // Draw the inner colored ring.
        bar.fillOval(25, 25, CONTAIN_SIZE - 50, CONTAIN_SIZE - 50);

        bar.setColor(Color.BLACK); // Draw the inner black circle.
        bar.fillOval(27, 27, CONTAIN_SIZE - 54, CONTAIN_SIZE - 54);

        GreenfootImage text = new GreenfootImage(Integer.toString((int)per) + "%", 20, newCol, new Color(0,0,0,0)); // Add the value of percent to the center of cir
        if((int)per == 100){
            bar.drawImage(text, CONTAIN_SIZE / 2 - 18, CONTAIN_SIZE / 2 - 10);
        }else if((int)per < 10){
            bar.drawImage(text, CONTAIN_SIZE / 2 - 10, CONTAIN_SIZE / 2 - 10);
        }else{
            bar.drawImage(text, CONTAIN_SIZE / 2 - 14, CONTAIN_SIZE / 2 - 10);
        }

    }
}
