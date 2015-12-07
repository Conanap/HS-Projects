import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
 
/**
 * A customizable timer that counts down to zero in seconds. Easy to resize and re-skin.
 * Timer variable ActsPS must be set if the program speed changes from the default (the center point of the slider).
 * <p>
 * Intended for use in shorter mini-games or levels. Use the returnTime() method in another world or object method
 * to trigger events.
 * 
 * @author Roland Li, modified by Albion Fung
 * @version Sept 2014
 */
public class RLTimer extends StatsDisplay
{
    //Variables needed for functions
    private int maxTime; //Maximum time the timer can go up to
    private int curTime; //The time left until the timer reaches zero
    private double curPercentTime; //Current time divided by maximum time. Used for the bar display
    private int ActsPS; //Acts needed per second; varies depending on program speed
    private int curActs; //Number of ticks passed so far

    private int barTimeRemain; //Size of the bar representing time left
    private int barTimePassed; //Size the bar representing time passed

    //Variables and objects needed for asthetic purposes
    //Adjust timer size here if necessary; timer should scale accordingly
    private final int TIMER_WIDTH = 60;
    private final int TIMER_HEIGHT = 92;
    private final String TIMER_END = "Finished!"; //Message that shows when timer reaches zero

    private GreenfootImage timerBar;
    private GreenfootImage timerCover = new GreenfootImage("NeoHourglass.png"); //Cover image for the timer
    //private GreenfootSound timerAlarm = new GreenfootSound("InsertAlarmFile.wav"); //Alarm for when timer hits zero
    private Font font = new Font ("Century Gothic", Font.BOLD, 15); //Font time and ending message is displayed in

    private Color colorBase = new Color (50,230,42); //Color for the "base", or background
    private Color colorRemain = new Color (255,20,3); //Color for the remaining time (the part that decreases)
    private Color colorText = new Color (255,255,255); //Color for the number for displaying the time left
    private Color colorTextAlt = new Color (255,20,20); //Color for the number when time passes a certain thershold
    //Contructors
    /**
     * RLTimer Base constructor; time starts at entered max amount, and can never exceed this amount.
     *
     * @param mxTime Sets the maximum time for the timer
     */
    public RLTimer(int mxTime) {
        maxTime = mxTime;
        curTime = mxTime;
        ActsPS = 60;
        curActs = 0;

        timerBar = new GreenfootImage(TIMER_WIDTH,TIMER_HEIGHT);
        timerBar.setColor(colorBase);
        timerBar.fill();
        //Ensure overlay scales properly if size is changed
        timerCover.scale(TIMER_WIDTH,TIMER_HEIGHT); //Scales cover image to size of the timer
        timerBar.drawImage(timerCover,0,0);

        this.setImage(timerBar);
        timerBar.setFont(font);
        updateDisplay();
    }

    /**
     * RLTimer Constructor that sets both the max and starting times.
     * <p>
     * Intended for situations where time can be increased to amounts higher than what the timer started with,
     * such as a game with many time power-ups.
     *
     * @param mxTime Sets the maximum time for the timer
     * @param crTime Sets the starting time for the timer
     */
    public RLTimer(int mxTime, int crTime) {
        this(mxTime);
        curTime = crTime;
        updateDisplay();
    }

    /**
     * RLTimer Constructor that sets max and starting times, as well as the required number of acts per second.
     * <p>
     * Intended for programs that runs at a defined (not default) speed.
     *
     * @param mxTime Sets the maximum time for the timer
     * @param crTime Sets the starting time for the timer
     * @param aps Sets the number of actions needed for one second to pass; varies depending on program speed
     */
    public RLTimer(int mxTime, int crTime, int aps) {
        this(mxTime);
        curTime = crTime;
        ActsPS = aps;
        updateDisplay();
    }

    //Functions for the timer
    /**
     * Runs the timer.
     */
    public void act() 
    {
        countDown();
    }    

    /**
     * Subtracts a second from curTime everytime the set number of acts is reached, and then runs updateDisplay() 
     * to update the image.
     * <p>
     * Runs timeEndDisplay() once time hits zero
     */
    private void countDown() {
        if (curTime > 0) {
            if (curActs < ActsPS) { //Loops until set number of acts is reached
                curActs++;
            }
            else {
                curActs = 0;
                curTime--;
                updateDisplay(); //Updates display once every time the set number of acts passes
            }
        }
        else {
            timeEndDisplay(); //Shows ending message if time hits zero
        }
    }

    /**
     * Updates the size and text of the bar based off the amount of time left in comparison to the maximum amount.
     *
     */
    private void updateDisplay() {
        curPercentTime = (double) curTime/maxTime;
        //Cast into an interger to accomidate for GreenfootImage method fillRect
        barTimeRemain = (int) (curPercentTime * TIMER_HEIGHT); 
        barTimePassed = TIMER_HEIGHT - barTimeRemain;

        timerBar.setColor(colorBase);
        timerBar.fillRect(0,0,TIMER_WIDTH,barTimePassed);
        timerBar.setColor(colorRemain);
        timerBar.fillRect(0,barTimePassed,TIMER_WIDTH,barTimeRemain);
        timerBar.drawImage(timerCover,0,0);
        if (curTime >= 10) { 
            //Text changes color if it passes the above threshold.
            timerBar.setColor(colorText);
        }
        else {
            timerBar.setColor(colorTextAlt);
        }

        //Makes the time display in the bottom left corner relative to the sive of the timer
        //Adjusts for any change in size
        timerBar.drawString(""+curTime,TIMER_WIDTH/30,TIMER_HEIGHT/10*9);
    }

    /**
     * Display shown when timer hits zero. Flashes ending message in two alternating colors.
     * <p>
     * Preset message is "Times Up!"
     */
    private void timeEndDisplay() {
        timerBar.setColor(colorBase);
        timerBar.fillRect(0,0,TIMER_WIDTH,barTimePassed);
        timerBar.drawImage(timerCover,0,0);
        //Alternates color of the TIMER_END message periodicly
        if (curActs < 20){ //Adjust number here to make text alternate faster or slower
            curActs++;
            timerBar.setColor(colorText);
        }
        else if (curActs < 40){
            curActs++;
            timerBar.setColor(colorTextAlt);
        }
        else {
            curActs = 0;
        }
        timerBar.drawString(TIMER_END, (TIMER_WIDTH-TIMER_END.length()*7)/2, TIMER_HEIGHT/2); 
        //Adjust the amount multipled to TIMER_END.length() based off the average pixel size per letter. Centers text.

        //timerAlarm.playLoop();
        //Uncomment and insert sound file if desired. Disabled by default for sake of sanity
    }

    //Accessors and Mutators
    /**
     * Changes time to set amount. Amount defaults to maxTime if entered amount exceeds it, and defaults to 0 if amount is negative.
     * @param newTime Desired time
     */
    public void setTime(int newTime) {
        if (newTime <= maxTime && newTime >= 0) { //This runs first for efficiency; amount most likely will update here
            curTime = newTime;
        }
        else if (newTime > maxTime) {
            curTime = maxTime;
        }
        else {
            curTime = 0;
        }
        updateDisplay();
    }

    /**
     * Sets both the maximum time and the current time.
     * <p>
     * Useful for situations such as new levels where more time is allotted.
     * @param newTime Desired time
     * @param newMax Desired max time limit
     */
    public void setTime(int newTime, int newMax) {
        maxTime = newMax;
        this.setTime(newTime); //Runs above mutator which will update the display
    }

    /**
     * Increases current time by entered amount, but not more than the set maximum; time defaults to max if total exceeds it.
     * @param addTime Amount to add
     */
    public void increaseTime(int addTime) {
        if (curTime + addTime >= maxTime) {
            curTime = maxTime;
        }
        else {
            curTime += addTime;
        }
        updateDisplay();
    }

    /**
     * Decreases current time by entered amount, but not to less than 0; time defaults to zero if total is negative.
     * @param addTime Amount to decrease
     */
    public void decreaseTime(int decTime) {
        if (curTime - decTime <= 0) {
            curTime = 0;
        }
        else {
            curTime -= decTime;
        }
        updateDisplay();
    }

    /**
     * Changes Acts Per Second count; scale according to how the speed is set for the program.
     * Allows for a more accurate time counter- 
     * defaulted to 60 for base speed (middle preset).
     */
    public void setAPS(int newAPS) {
        ActsPS = newAPS;
    }

    /**
     * Sets current time back to the maximum. Easy and quick way to reset the timer without needing to input an interger.
     */
    public void resetTime() {
        curTime = maxTime;
    }

    /**
     * Returns the time remaining. Use in another object or the world to trigger events
     * that occur once the timer hits zero or another set time.
     */
    public int returnTime() {
        return curTime;
    }
}
