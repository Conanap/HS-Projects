import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RepairSlot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RepairSlot extends Slots
{
    private GreenfootImage repairIcon;
    private InventoryItem item;
    private Test world;
    private int delay;
    public RepairSlot() {
        super();
        setImage("Repair Icon.png");        
    }

    public void act() {
        delay++;
        if (item != null && item.isBreakable() && delay >= 3 && item.decreaseDuration(-1)) {
            world = (Test)getWorld();
            world.getPlayer().addCoins(-1);
            delay = 0;
        }
    }

    public void addedToWorld(World Test) {
        if (item != null)Test.addObject(item, getX(), getY());
    }

    public void clearAsset() {
        if (item != null) {
            world = (Test)getWorld();  
            item.clearStackDisplay();
            item.clearDurationDisplay();
            world.getPlayer().getInventory().addItem(item.getID());
            world.removeObject(item);
            item = null;
        }
    }

    public void removeItem(InventoryItem newItem) {
        if (item == newItem) item = null;
    }

    public void newItem (InventoryItem newItem) {
        item = newItem;
    }
}