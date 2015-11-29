import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Background is a Greenfoot World that adds the stat tracking objects to itself.
 * Within the world the user can create specific objects such as the heart bar and mana bar.
 * The stats are designed in a 600 * 400 pixels scenario.
 * Specific methods from the ASImgBars class can be accessed.
 * Using key presses lives and mana values can be controlled
 * 
 * @author Ahrenn Sivananthan
 * @version Sept 2014
 */
public class Background extends World
{
    private ASImgBars hearts;//private object of a lives bar.
    private ASImgBars manaPots;//private object of a mana bar.      

    /**
     * Construct a world where the stat bars can be controlled and displayed to users.
     *
     * @param hearts The object of hearts created with value for lives and object height. 
     * @param manaPots The object of mana potions created with value for mana and object height
     *						
     */
    public Background()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        hearts = new ASImgBars (15,32.0);//hearts object created using ASImgBars constructor for lives. 
        manaPots = new ASImgBars(15,32);//manaPots object created using ASImgBars constructor for mana.        
        addObject(hearts, 281, 36);//Adds hearts object to the world.       
        addObject(manaPots,281,69);//Adds manaPots object to the world.
    }

    /**
     *	Uses update methods from ASImgBars to manipulate number of lives and amount of mana.
     * Key Presses of left and right are associated with hearts (representing lives)
     * Key Presses of up and down are associated with potions (representing mana)
     * Runs updateDecrease and updateIncrease methods to change values.
     *
     * @return void	No return used.
     *						
     */
    // Method runs when arrow keys are pressed.
    public void act ()
    {
        if (Greenfoot.isKeyDown ("left"))//checks if left arrow key is being pressed
        {
            hearts.updateDecrease(1);//runs update method to decrease number of lives by 1(value can be changed based on number of lives to be subtracted).
            Greenfoot.delay(8);//Slight delay to slow down heart removal speed.            
        }

        if (Greenfoot.isKeyDown ("right"))//checks if right arrow key is being pressed
        {
            hearts.updateIncrease(1);//runs update method to increase number of lives by 1(value can be changed based on number of lives to be added).
            Greenfoot.delay(8);//Slight delay to slow down heart addition speed.            
        }

        if (Greenfoot.isKeyDown ("down"))//checks if down arrow key is being pressed
        {
            manaPots.updateDecrease(1.0);//runs update method to decrease amount of mana by 1(value can be changed based on amount of mana to be subtracted).
            Greenfoot.delay(8);//Slight delay to slow down mana removal speed.        
        }

        if (Greenfoot.isKeyDown ("up"))//checks if up arrow key is being pressed
        {
            manaPots.updateIncrease(1.0);//runs update method to increase amount of mana by 1(value can be changed based on amount of mana to be added).
            Greenfoot.delay(8);//Slight delay to slow down mana addition speed.                 
        }
    }
}
