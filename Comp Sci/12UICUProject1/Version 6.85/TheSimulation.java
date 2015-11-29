import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
 
/**
 * Write a description of class TheSimulation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TheSimulation extends World
{
    private int zombiePop;
    private int zombieSpawn;
    private int zombieSpawnLimit; //Used to stop zombieSpawn rate from being too slow

    private int humanPop;
    private int humanSpawn;
    private int humanSpawnLimit;

    private int food;
    private int wood;
    private int water;
    private int gameTime;
    private int days;
    private boolean gameOver;

    private HealthyHuman[] humans = new HealthyHuman[100];
    private Zombie[] zombies = new Zombie[100];

    private int numberOfActs;

    private int eventCounter; //Counter to limit length of events

    private Random random = new Random();
    private int randomAct;

    private TextDisplay displayHPop = new TextDisplay("Population");
    private TextDisplay displayZPop = new TextDisplay("Population", -1);
    private TextDisplay displayZSpawn = new TextDisplay("Spawn Rate", " SPZ", 3);
    private TextDisplay displayFood = new TextDisplay("Food", 200);
    private TextDisplay displayWood = new TextDisplay("Wood", 50);
    private TextDisplay displayWater = new TextDisplay("Water",200);
    private TextDisplay displayTime = new TextDisplay("Time", -1);
    private TextDisplay displayDays = new TextDisplay("Days", -1);

    private EvolutionBar evoHuman = new HumanEvo();
    private EvolutionBar evoZombie = new ZombieEvo();

    private RLTimer worldTime = new RLTimer(300, 275);

    private String starterMessage = "Zombies are invading! Protect the center until a vaccine can be made!";
    private NewsBar newsBar = new NewsBar(starterMessage);

    private DropTimer drop1 = new DropTimer(5000);
    private DropTimer drop2 = new DropTimer(5000);
    private SmallDrop drop3 = new SmallDrop(5000);
    private ResourceCentre centre = new ResourceCentre(150000);

    /**
     * Constructor for objects of class TheSimulation.
     * 
     */
    public TheSimulation()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 480, 1); 

        prepare();
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     * 
     * Roland
     */
    private void prepare()
    {

        addObject(newsBar, 394, 458);
        addObject(displayHPop,82,375);
        addObject(displayZPop,82,170);
        addObject(displayZSpawn,82,138);
        addObject(displayFood,82,400);
        addObject(displayWood,82,425);
        addObject(displayWater,82,450);
        addObject(displayTime,149,256);
        addObject(displayDays,149,289);

        addObject(worldTime,31,272);
        addObject(evoHuman,70,207);
        addObject(evoZombie,70,332);

        addObject(drop1,670,15);
        addObject(drop2,586,320);
        addObject(drop3,446,228);
        addObject(centre,878,416);

        zombieSpawn = 400;
        zombieSpawnLimit = 1000;
        humanSpawn = 900;
        humanSpawnLimit = 1250;
        numberOfActs = 1;
        eventCounter = 0;

        wood = 50;
        food = 50000;
        water = 50000;

        gameOver = false;

        addObject(new HealthyHuman(0), 875, 350); //Do not move from entrance. Guard humans.
        addObject(new HealthyHuman(0), 889, 350);
        setPaintOrder(ResourceCentre.class, HealthyHuman.class); 
        //Ensures humans/zombies don't appear on top of the center
        //Ensures humans remain at the top of barricades
    }

    public void act() {
        if (gameOver == false) {
            numberOfActs++;
            evolve();
            spawnZombies();
            spawnHumans();
            randomEvents();
            updateDisplays();
            checkGameStatus();
        }
    }

    private void spawnZombies(){
        if (numberOfActs % zombieSpawn == 0){
            int spawnLevel = random.nextInt(1+evoZombie.getEvoLv());
            Zombie zombie = new NormalZombie(); //Pre-filled to prevent error
            //Sets type of zombie spawned
            if (spawnLevel == 0){
                zombie = new NormalZombie();
            }
            else if (spawnLevel == 1){
                zombie = new FastZombie();
            }
            else if (spawnLevel == 2){
                zombie = new HighHPZombie();
            }
            else if (spawnLevel == 3){
                zombie = new HighDamageZombie();
            }

            //Spawns type of zombie in one of four locations
            int temp = random.nextInt(4);
            if (temp == 0){
                addObject (zombie, 275, 52);
            }
            if (temp == 1){
                addObject (zombie, 220, 175);
            }
            if (temp == 2){
                addObject (zombie, 233, 354);
            }
            if (temp == 3){
                addObject (zombie, 526, 400);
            }
        }
        setZombieSpawnLimit();
    }

    /**
     * Prevents zombie spawn rates from being too slow.
     * Allows for rates to vary, while keeping the simulation become progressively more intense as time passes.
     * 
     * Roland
     */
    private void setZombieSpawnLimit(){
        zombieSpawnLimit = 500 - evoZombie.getEvoLv()*125;
        if (zombieSpawn > zombieSpawnLimit){
            zombieSpawn = zombieSpawnLimit;
        }
    }

    /**
     * Periodically adds new humans near vaccine center
     *
     */
    private void spawnHumans(){
        if (numberOfActs % humanSpawn == 0){
            int spawnLevel = random.nextInt(1+evoZombie.getEvoLv());
            HealthyHuman human = new HealthyHuman();
            if(spawnLevel == 0){
                human = new HealthyHuman();
            }
            else if (spawnLevel == 1){
                human = new MachineGunner();
            }
            else if (spawnLevel == 2) {
                human = new HeavyArmedSoldier();
            }
            else if (spawnLevel == 3){ //Spawns planes as well as the human
                addObject(new Bomber(110),960,184);
                addObject(new Bomber(90),960,184);
                addObject(new Bomber(110),960,357);
                addObject(new Bomber(90),960,357);
            }
            addObject(human, 882, 375);
        }
        setHumanSpawnLimit();
    }

    /**
     * Prevents zombie spawn rates from being too slow.
     * Allows for rates to vary, while keeping the simulation become progressively more intense as time passes.
     * 
     * Roland
     */
    private void setHumanSpawnLimit(){
        humanSpawnLimit = 1000 - evoHuman.getEvoLv()*100;
        if (humanSpawn > humanSpawnLimit){
            humanSpawn = humanSpawnLimit;
        }
    }

    private void spawnResources(){

    }

    /**
     * Method randomEvents
     *
     * Roland
     */
    private void randomEvents(){
        //every 1800acts (about 30secs), a random event happens

        if (numberOfActs%1800 == 0) {
            //random
            randomAct = random.nextInt(10)+ 1;//Random num generater
        }
        else if (eventCounter == 0) { 
            randomAct = 0; //Stops the event if counter hits zero
        }

        if (randomAct == 1){
            newsBar.changeMessage("A hoard of zombies are approaching!");
            if (eventCounter == 0){
                eventCounter = 50;
                zombieSpawn = 5;
            }
            eventCounter--;
            if (eventCounter == 0){
                zombieSpawn = 800; //Returns to default
            }
        }
        else if(randomAct == 2){
            List<ResourceCrate> crates = getObjects(ResourceCrate.class);
            if (crates.size() !=0){
                newsBar.changeMessage("The crates were rigged! All resource crates are gone!");
                for (int i = 0; i < crates.size(); i++){
                    addObject(new Explosion(), crates.get(i).getX(), crates.get(i).getY());
                    removeObject(crates.get(i));
                }
            }
        }
        else if (randomAct == 3){
            //Tornado
        }

    }

    private void evolve(){
        if (numberOfActs%3600 == 0){ //Happens once each "day"
            Random side = new Random();
            int temp = side.nextInt(2);
            if (temp == 0){
                //Zombie
            }
            else{
                //Human
            }

            //Special day three event
            if (numberOfActs%(3600*3) == 0){
                newsBar.changeMessage("Holy $%#@! A giant zombie demon has spawned!");
            }
        }
    }

    /**
     * Method checkFood
     * Reduces human speed if too low. If it hits zero, people randomly start dying. Set as a new event.
     */
    private void checkFood(){
        //Add

    }

    private void spawnPlanes() {
        //Only at human evolution level 3, random
    }

    private void spawnGiants(){
        //Only at zombie evolution level 3, random
    }

    private void updateDisplays(){
        //Spawn Rate
        displayZSpawn.updateNumber((int)zombieSpawn/60);
        //Time
        gameTime = (int)numberOfActs/60;
        displayTime.updateNumber(gameTime);
        //Days
        days = (int)numberOfActs/3600; //1 Minute = 1 day;
        displayDays.updateNumber(days);
        //Food
        food=(int)centre.getFood();
        displayFood.updateNumber(food);
        //Wood
        wood=(int)centre.getWood();
        displayWood.updateNumber(wood);
        water=(int)centre.getWater();
        displayWater.updateNumber(water);
        //Populations
        countCurrentPop();
    }

    /**
     * Method countCurrentPop
     * Sets numbers for zombie and human population based off number of them in the world
     * 
     * Roland
     */
    public void countCurrentPop() {
        List listZ = getObjects(Zombie.class);
        zombiePop = listZ.size();

        List listH = getObjects(HealthyHuman.class);
        humanPop = listH.size();

        displayZPop.updateNumber(zombiePop);
        displayHPop.updateNumber(humanPop);
    }

    /**
     * Checks whether the game has ended. Game ends if the centre dies or when worldTime reaches zero.
     * Changes gameOver = true if game is finished.
     */
    private void checkGameStatus(){
        if (centre.checkAlive() == false){
            //Blow up potentially screen blocking crates
            List<ResourceCrate> crates = getObjects(ResourceCrate.class);
            if (crates.size() !=0){
                for (int i = 0; i < crates.size(); i++){
                    addObject(new Explosion(), crates.get(i).getX(), crates.get(i).getY());
                    removeObject(crates.get(i));
                }
            }
            
            newsBar.changeMessage("The vaccine center was destroyed! The world is doomed!");

            addObject(new MultiBomber(), 960, 100);
            addObject(new MultiBomber(), 960, 200);
            addObject(new MultiBomber(), 960, 300);
            addObject(new MultiBomber(), 960, 400);
            addObject(new MultiBomber(), 960, 500);
            setBackground("WorldMapLose.png");
            gameOver = true;
        }
        else{
            if (worldTime.returnTime() <= 0) {
                setBackground("WorldMapWin.png");
                gameOver = true;
                removeObjects(getObjects(null));
            }
        }
    }

    /**
     * "Decreases" zombie spawn by increasing interval of time between a zombie spawn.
     * Used by humans who cross world borders
     * @param amount Amount
     */
    public void decreaseZSpawn(int amount){
        zombieSpawn += amount;
    }
}
