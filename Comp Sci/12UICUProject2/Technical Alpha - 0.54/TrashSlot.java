import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TrashSlot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TrashSlot extends Slots
{
    GreenfootImage trashIcon;
    InventoryItem item;
    public TrashSlot() {
        super();
        trashIcon = new GreenfootImage("1280x720 Trash Button Normal.png");
        setImage(emptyImage);
        getImage().setTransparency(255);
        getImage().drawImage(trashIcon, 0, 0);
    }
    
    public void addedToWorld(World Test) {
        if (item != null)Test.addObject(item, getX(), getY());
    }
    
    public void clearAsset() {
        if (item != null) {item.clearStackDisplay(); item.clearDurationDisplay(); getWorld().removeObject(item);}
    }
        
    public void newItem (InventoryItem newItem) {
        item = newItem;
    }
}