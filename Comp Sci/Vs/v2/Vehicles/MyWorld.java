import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * MyWorld functions as the Controller for this Greenfoot
 * project which helps students understand inheritance
 * 
 * @Jordan Cohen
 * @Feb 2012
 */

/**
 * The superclasses and inheritence reduced a lot of code; for example, the same variable was not 
 * needed to be declared multiple times. The methods that were necessary for all the
 * objects or classes under the superclass could also be directly
 * inherited from the superclass, and thus also reduces a huge amount of unecessary code,
 * without the need to create or import an entirely new class (other than
 * superclass).
 * 
 * It also help the programmers organize the objects and classes in a better format,
 * and is easier to visualize what class is reliant and common to what classes.
 * Albion Fung
 */
public class MyWorld extends World
{
    private int randomize;
    /**
     * Spawn Rates:
     * Lower number means more spawns
     * 3:spawnRate chance per act of spawning a random Vehicle
     * 1:pedSpawn chance per act of spawning a Pedestrian
     */
    private int spawnRate = 320;
    private int pedSpawn = 100;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false); 
    }

    public void act ()
    {
        // spawn pedestrians
        if (Greenfoot.getRandomNumber(pedSpawn) == 1)
        {
            addObject (new Pedestrian(), Greenfoot.getRandomNumber(600), 395);
        }
        // Generate a random number to add a random element
        // to Vehicle spawning
        randomize = Greenfoot.getRandomNumber(spawnRate);
        
        
        // Chose a random lane in case a vehicle spawns
        int lane = Greenfoot.getRandomNumber (6);
        if(randomize==5)
        {
            lane = 6;
        }
        int spawnY = getYPosition (lane);

        // spawn vehicles
        //The spawn rate is halfed, and the vehicles spawn rates are doubled
        // this keeps the original spawn rate, but the halved spawn rate 
        //was desired for the airplane
        if (randomize == 1||randomize == 6)
        {
            // spawn a Car
            addObject (new Car(), 10, spawnY);
        }
        else if (randomize == 2||randomize == 7)
        {
            // spawn a Bus
            addObject (new Bus(), 10, spawnY);
        }

        else if (randomize == 3||randomize == 8)
        {
            // spawn a Car
            addObject (new Ambulance(), 10, spawnY);
        }
        else if (randomize == 4||randomize == 9)//spawning a mushroom!
        {
            addObject(new mushroom(), 10, spawnY);
        }
        else if (randomize == 5)//spawns an airplane
        {
            addObject (new plane(), 10, spawnY);
        }
        
    }
    
    /**
     * Returns the appropriate y coordinate for a given lane
     */
    private int getYPosition (int inLane)
    {
        // Manually input values based on the background graphic
        switch (inLane)
        {
            case 0: 
            return 79;

            case 1:
            return 127;

            case 2:
            return 175;

            case 3:
            return 222;

            case 4:
            return 272;

            case 5: 
            return 320;
            
            case 6:
            return 10;

        }  
        // In case an invalid value is passed in
        return 0;
    }
}

