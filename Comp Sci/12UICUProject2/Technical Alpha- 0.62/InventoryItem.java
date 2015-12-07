import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.lang.NullPointerException;
/**
 * Write a description of class InventoryItem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InventoryItem extends Slots
{
    private Test world;
    private StringDisplay stackDisplay;
    private DurationDisplay durationDisplay;    
    private HoverText hover;

    private int itemID;
    private int stacks;
    private int maxDuration;
    private int duration;
    private int price;
    private boolean pickedUp;
    private boolean stackable;
    private boolean breakable; // Can have a duration and be used;
    private boolean hovering = false;
    private int originalX;
    private int originalY;
    public InventoryItem(int ID) {
        super();
        stackDisplay = new StringDisplay(20,18,22,Color.BLACK);
        durationDisplay = new DurationDisplay();
        pickedUp = false;
        hovering = false;
        itemID = ID;
        stacks = 1;
        update();
        duration = maxDuration;         
    }

    public InventoryItem(int ID, int stack) {
        super();
        stackDisplay = new StringDisplay(20,18,22,Color.BLACK);
        durationDisplay = new DurationDisplay();
        pickedUp = false;
        hovering = false;
        itemID = ID;
        stacks = stack;
        update();
        duration = maxDuration;
        updateStacks();
    }

    /**
     * Act - do whatever the Inventory wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        world = (Test)getWorld();
        stackDisplay.setLocation(getX()-10,getY()-14);
        durationDisplay.setLocation(getX(),getY()+26);
        if (Greenfoot.mouseClicked(this)) {
            if (!world.getShopInv().isShowing()){
                if (pickedUp == false) {
                    originalX = this.getX();
                    originalY = this.getY();
                    pickedUp = true;
                    getWorld().removeObject(hover);
                    this.hovering=false;
                    world.removeObject(this);
                    world.addObject(this,originalX,originalY);
                } else {
                    if (getOneIntersectingObject(InventoryItem.class) != null) {
                        InventoryItem swap = (InventoryItem)getOneIntersectingObject(InventoryItem.class);
                        if (swap.getOneIntersectingObject(TrashSlot.class) != null) {
                            swap.clearStackDisplay();
                            swap.clearDurationDisplay();
                            world.removeObject(swap);                            
                            trashInteraction();
                        } else if (swap.getID() == itemID && isStackable()){
                            swap.addToStack(stacks);
                            removeReferences();                         
                            clearStackDisplay();
                            getWorld().removeObject(this);
                        } else {
                            int myID = itemID;
                            int myStack = stacks;
                            int myDuration = duration;
                            setID(swap.getID());
                            swap.setID(myID);                            
                            setStacks(swap.getStacks());
                            swap.setStacks(myStack);
                            setDuration(swap.getDuration());
                            swap.setDuration(myDuration);
                        }
                    } else if (getOneIntersectingObject (InventorySlot.class) != null) {
                        inventorySlotInteraction();
                    } else if (getOneIntersectingObject (ToolbarSlot.class) != null) {
                        toolbarSlotInteraction();
                    } else if (getOneIntersectingObject (TrashSlot.class) != null) {
                        trashInteraction();
                    } else if (getOneIntersectingObject (RepairSlot.class) != null) {
                        repairInteraction();
                    }
                }
            } else {
                world.getShopInv().selling(this);
            }            
        }
        checkPickedUp();
        checkDepleted();
    }   

    public void addedToWorld(World Test) {
        Test.addObject(stackDisplay,getX()-10,getY()-14);
        Test.addObject(durationDisplay,getX(),getY()+26);
        updateDuration();
        updateStacks();
    }

    public void clearStackDisplay() {
        try
        {
            if (stackDisplay != null) getWorld().removeObject(stackDisplay);
        }
        catch(NullPointerException e){}
    }

    public void clearDurationDisplay() {
        try
        {
            if (durationDisplay != null) getWorld().removeObject(durationDisplay);
        }
        catch(NullPointerException e){}
    }

    public void hideStackDisplay(boolean trueFalse){
        if (trueFalse) {
            stackDisplay.getImage().setTransparency(0);
        } else {
            stackDisplay.getImage().setTransparency(255);
        }
    }

    public void hideDurationDisplay(boolean trueFalse){
        if (trueFalse) {
            durationDisplay.getImage().setTransparency(0);
        } else {
            durationDisplay.getImage().setTransparency(255);
        }
    }

    private void trashInteraction() {
        TrashSlot trash = (TrashSlot)getOneIntersectingObject(TrashSlot.class);
        setLocation(trash.getX(), trash.getY());
        trash.newItem(this);
        pickedUp = false;
        removeReferences();
        if (world.getPlayer().getInventory().getRepair() != null)world.getPlayer().getInventory().getRepair().removeItem(this);
    }

    private void repairInteraction() {
        RepairSlot repair = (RepairSlot)getOneIntersectingObject(RepairSlot.class);
        setLocation(repair.getX(), repair.getY());
        world.getPlayer().getToolBar().removeItem(this);
        repair.newItem(this);
        removeReferences();
        pickedUp = false;
    }

    private void inventorySlotInteraction() {
        InventorySlot emptySlot = (InventorySlot)getOneIntersectingObject(InventorySlot.class);
        setLocation(emptySlot.getX(), emptySlot.getY());
        pickedUp = false;
        removeReferences();
        world.getPlayer().getInventory().addItem(this);
        if (world.getPlayer().getInventory().getRepair() != null)world.getPlayer().getInventory().getRepair().removeItem(this);
    }

    private void toolbarSlotInteraction() {
        ToolbarSlot emptySlot = (ToolbarSlot)getOneIntersectingObject(ToolbarSlot.class);
        setLocation(emptySlot.getX(), emptySlot.getY());
        pickedUp = false;
        removeReferences();
        world.getPlayer().getToolBar().addItem(this);
        if (world.getPlayer().getInventory().getRepair() != null)world.getPlayer().getInventory().getRepair().removeItem(this);
    }

    private void removeReferences() {
        world.getPlayer().getInventory().removeItem(this);
        world.getPlayer().getToolBar().removeItem(this);
    }

    public boolean isStackable() {
        return stackable;
    }

    public boolean isBreakable() {
        return breakable;  
    } 

    public boolean isDamaged() {
        if (duration != maxDuration) return true;
        return false;              
    }

    public int getPrice() {
        return price;
    }

    public int getID() {
        return itemID;
    }

    public int getStacks() {
        return stacks;
    }

    public int getDuration() {
        return duration;
    }

    public void setID(int ID) {
        itemID = ID;
        update();
    }

    public void setStacks(int stack) {
        stacks = stack;
        updateStacks();
    }

    public void setDuration(int value) {
        duration = value;
        updateDuration();
    }

    public void addToStack(int value) {
        if (stacks + value >= 0) { stacks+=value; updateStacks();}
        checkDepleted();
    }

    public boolean decreaseDuration(int value) {
        if (duration - value <= maxDuration) {
            duration-=value;
            updateDuration();
            return true;
        }
        return false;
    }

    private void checkPickedUp() throws NullPointerException {
        try {
            if (pickedUp) {
                getImage().setTransparency(255);
                setLocation(Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
            }
        } catch (NullPointerException e){}
    }

    private void checkDepleted() {
        world = (Test)getWorld();
        if (stacks <= 0) {
            world.getPlayer().getInventory().removeItem(this);
            world.getPlayer().getToolBar().removeItem(this);
            world.removeObject(this);
        }
        if (duration <= 0 && breakable) {
            world.getPlayer().getInventory().removeItem(this);
            world.getPlayer().getToolBar().removeItem(this);
            clearDurationDisplay();
            world.removeObject(this);
        }
    }

    private void update() {
        hover = new HoverText(itemID);
        if (itemID == 0) {
            this.setImage("Torch.png");
            stackable = true;
            breakable = false;
            price = 2;
            maxDuration = 0;
        } else if (itemID == 1) {
            this.setImage("Bomb.png");
            stackable = true;
            breakable = false;
            price = 10;
            maxDuration = 0;
        } else if (itemID == 2) {
            this.setImage("rope slot.png");
            stackable = true;
            breakable = false;
            price = 1;
            maxDuration = 0;
        } else if (itemID == 9) {
            this.setImage("Charcoal.png");
            stackable = false;
            breakable = false;
            price = 1;
            maxDuration = 0;
        } else if (itemID == 10) {
            this.setImage("iron ingot.png");
            stackable = false;
            breakable = false;
            price = 2;
            maxDuration = 0;
        } else if (itemID == 11) {
            this.setImage("silver ingot.png");
            stackable = false;
            breakable = false;
            price = 5;
            maxDuration = 0;
        } else if (itemID == 12) {
            this.setImage("gold ingot.png");
            stackable = false;
            breakable = false;
            price = 12;
            maxDuration = 0;
        } else if (itemID == 13) {
            this.setImage("diamond ingot.png");
            stackable = false;
            breakable = false;
            price = 28;
            maxDuration = 0;
        } else if (itemID == 14) {
            this.setImage("Emerald Pickup.png");
            stackable = false;
            breakable = false;
            price = 40;
            maxDuration = 0;
        } else if (itemID == 15) {
            this.setImage("lapis pickup.png");
            stackable = false;
            breakable = false;
            price = 75;
            maxDuration = 0;
        } else if (itemID == 20) {
            this.setImage("Iron Pickaxe.png");
            stackable = false;
            breakable = true;
            price = 10;
            maxDuration = 30;
        } else if (itemID == 22) {
            this.setImage("Silver Pickaxe.png");
            stackable = false;
            breakable = true;
            price = 33;
            maxDuration = 50;
        } else if (itemID == 24) {
            this.setImage("Gold Pickaxe.png");
            stackable = false;
            breakable = true;
            price = 40;
            maxDuration = 70;
        } else if (itemID == 26) {
            this.setImage("Diamond Pickaxe.png");
            stackable = false;
            breakable = true;
            price = 55;
            maxDuration = 90;
        } else if (itemID == 21) {
            this.setImage("Iron Drill.png");
            stackable = false;
            breakable = true;
            price = 25;
            maxDuration = 40;
        } else if (itemID == 23) {
            this.setImage("Silver Drill.png");
            stackable = false;
            breakable = true;
            price = 50;
            maxDuration = 60;
        } else if (itemID == 25) {
            this.setImage("Gold Drill.png");
            stackable = false;
            breakable = true;
            price = 60;
            maxDuration = 80;
        } else if (itemID == 27) {
            this.setImage("Diamond Drill.png");
            stackable = false;
            breakable = true;
            price = 75;
            maxDuration = 100;
        } else if (itemID == 28) {
            this.setImage("Iron Sword.png");
            stackable = false;
            breakable = false;
            price = 10;
            maxDuration = 0;
        } else if (itemID == 29) {
            this.setImage("Silver Sword.png");
            stackable = false;
            breakable = false;
            price = 10;
            maxDuration = 0;
        } else if (itemID == 30) {
            this.setImage("Gold Sword.png");
            stackable = false;
            breakable = false;
            price = 10;
            maxDuration = 0;
        } else if (itemID == 31) {
            this.setImage("Diamond Sword.png");
            stackable = false;
            breakable = false;
            price = 10;
            maxDuration = 0;
        } else {
            this.setImage(emptyImage);
            stackable = false;
            price = 0;
            maxDuration = 0;
        }
    }

    private void updateStacks() {
        if (stackable && stacks > 1) {
            stackDisplay.updateValue(stacks);
            hideStackDisplay(false);
        } else {
            stackDisplay.updateValue(1);
            hideStackDisplay(true);
        }
    }

    private void updateDuration() {
        if (breakable && duration > 0) {
            durationDisplay.setPercentage((double)duration/(double)maxDuration);
            hideDurationDisplay(false);
        } else {
            durationDisplay.setPercentage(0);
            hideDurationDisplay(true);
        }
    }

    public void checkHover()
    {
        if(Greenfoot.mouseMoved(this)&&!this.hovering)
        {
            this.hovering=true;	
            getWorld().addObject(hover,this.getX()+122,this.getY()+hover.getHeight());
        }
        else if(Greenfoot.mouseMoved(null)&&hovering&&!Greenfoot.mouseMoved(this))
        {
            getWorld().removeObject(hover);
            this.hovering=false;
        }
    }
}
