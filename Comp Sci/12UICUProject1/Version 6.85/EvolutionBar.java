import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class evolutionBar here.
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
     * Act - do whatever the evolutionBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected void base ()//private method, creates the background and the base of the health bar
    {
        bar.setColor(grey);//setting background color of bar
        bar.fill();//filling background
        bar.setColor(white);//setting color ofthe background of the circle, status box, and manna bar to white
        bar.fillRect(2,2,150,20);//drawing base of health bar
    }
 
    protected void drawEvo()
    {
        evoPer=currentEvo/maxEvo;
        bar.setColor(red);
        evoLength=(int)(150*evoPer);
        bar.fillRect(2,2,evoLength,20);
    }

    protected void mutating()
    {
        int ch=Greenfoot.getRandomNumber(evoChance);
        if(ch==4)
        {
            evoOrNot=true;
            currentEvo+=1;
        }
    }

    protected void drawIdent(String barType)
    {
        bar.setColor(black);
        bar.drawString(barType,10,15);
    }

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
