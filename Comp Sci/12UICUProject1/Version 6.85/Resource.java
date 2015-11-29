import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class governs the amount of resources available to the people.
 * 
 * @author Albion Fung
 * @version 0.0.1
 */
public class Resource extends ActionScene
{
    int water, food;
    /**
     * The method governs the amount of food
     * @param fConsump the amount of food consumed by the person
     * @param drop If there was an air drop for resources, supplies resource to max
     */
    public int food(int fConsump, boolean drop)
    {
        if (drop)
            food=100;
        food-=fConsump;
        return food;
    }

    /**
     * The method governs the amount of water
     * @param wConsump The amount of water consumed by the person
     * @param drop If there was an air drop for resources, supplies resource to max
     */
    public int water(int wConsump, boolean drop)
    {
        if(drop)
            water=100;
        water-=wConsump;
        return water;
    }

    public int[] getResource()
    {
        int[] resource = new int [2];
        resource[0]=water;
        resource[1]=food;
        return resource;
    }
    
    public void heliDrop()
    {
        
    }
}
