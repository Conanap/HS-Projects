import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creates necessary methods to draw evolution bars and determine if the species should evolve.
 * 
 * @author Albion Fung
 * @version 0.0.1
 */
public abstract class EvolutionBar extends StatsDisplay
{
    protected GreenfootImage bar = new GreenfootImage(155,20);//creating new greenfoot image
    protected int evoLength,evoChance = 5000;
    protected double evoPer, currentEvo=0, maxEvo=3;
    protected boolean evoOrNot;
    /**
     * Draws the base of the bars
     */
    protected void base ()//private method, creates the background and the base of the health bar
    {
        bar.setColor(grey);//setting background color of bar
        bar.fill();//filling background
        bar.setColor(white);//setting color ofthe background of the circle, status box, and manna bar to white
        bar.fillRect(2,2,150,20);//drawing base of health bar
    }
    /**
     * Draws the overlay of the bar
     */
    protected void drawEvo()
    {
        evoPer=currentEvo/maxEvo;//finding evo percent
        bar.setColor(red);
        evoLength=(int)(150*evoPer);//finding how long the bar with color should be
        bar.fillRect(2,2,evoLength,20);
    }
    /**
     * Determines if they should evolve
     */
    protected void mutating()
    {
        int ch=Greenfoot.getRandomNumber(evoChance);//determine if they evolve
        if(ch==4)
        {
            evoOrNot=true;
            currentEvo+=1;
        }
    }
    /**
     * Draws the string to help identify which bar is for which organisms
     */
    protected void drawIdent(String barType)
    {
        bar.setColor(black);
        bar.drawString(barType,10,15);
    }
    /**
     * Check if the evolution level is above maximum evolution level, and stop it's chance of evolving further.
     */
    protected void checkMax()
    {
        if (currentEvo>=maxEvo)
        {
            currentEvo=maxEvo;
            evoChance=1;
        }
    }
    /**
     * Call the method to get the evolution level
     * @return int returns the level of the evolution
     */
    public int getEvoLv()
    {
        return (int)currentEvo;
    }
}
