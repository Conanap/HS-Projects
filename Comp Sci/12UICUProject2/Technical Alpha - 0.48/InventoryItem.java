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

    private int itemID;
    private int stacks;
    private int price;
    private int sellPrice;
    private boolean pickedUp;
    private boolean stackable;
    private boolean hovering=false;

    private int originalX;
    private int originalY;

    private HoverText hover;
    public InventoryItem(int ID) {
        super();
        stackDisplay = new StringDisplay(20,18,22,Color.BLACK);
        pickedUp = false;
        itemID = ID;
        hover = new HoverText(getID());
        stacks = 1;
        update();
    }

    public InventoryItem(int ID, int stack) {
        super();
        stackDisplay = new StringDisplay(20,18,22,Color.BLACK);
        pickedUp = false;
        itemID = ID;
        stacks = stack;
        update();
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
        if (Greenfoot.mouseClicked(this)) {
            if (!world.getShopInv().isShowing()){
                if (pickedUp == false) {
                    originalX = this.getX();
                    originalY = this.getY();
                    pickedUp = true;
                    world.removeObject(this);
                    world.addObject(this,originalX,originalY);
                } else {
                    if (getOneIntersectingObject(InventoryItem.class) != null) {
                        InventoryItem swap = (InventoryItem)getOneIntersectingObject(InventoryItem.class);
                        if (swap.getOneIntersectingObject(TrashSlot.class) != null) {
                            world.getPlayer().getInventory().removeItem(this);   
                            world.getPlayer().getToolBar().removeItem(this);
                            swap.clearDisplay();
                            world.removeObject(swap);                            
                            trashInteraction();
                        } else if (swap.getID() == itemID && isStackable()){
                            swap.addToStack(stacks);
                            clearDisplay();
                            getWorld().removeObject(this);
                        } else {
                            int myID = itemID;
                            int myStack = stacks;
                            setID(swap.getID());
                            swap.setID(myID);
                            setStacks(swap.getStacks());
                            swap.setStacks(myStack);
                        }
                    } else if (getOneIntersectingObject (InventorySlot.class) != null) {
                        inventorySlotInteraction();
                    } else if (getOneIntersectingObject (ToolbarSlot.class) != null) {
                        toolbarSlotInteraction();
                    } else if (getOneIntersectingObject (TrashSlot.class) != null) {
                        trashInteraction();
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
    }

    public void clearDisplay() {
        if (stackDisplay != null) getWorld().removeObject(stackDisplay);
    }

    public void hideDisplay(boolean trueFalse){
        if (trueFalse) {
            stackDisplay.getImage().setTransparency(0);
        } else {
            stackDisplay.getImage().setTransparency(255);
        }
    }

    private void trashInteraction() {
        TrashSlot trash = (TrashSlot)getOneIntersectingObject(TrashSlot.class);
        setLocation(trash.getX(), trash.getY());
        trash.newItem(this);
        pickedUp = false;
        world.getPlayer().getInventory().removeItem(this);   
        world.getPlayer().getToolBar().removeItem(this);
        world.getPlayer().getToolBar().removeItem(this);
    }

    private void inventorySlotInteraction() {
        InventorySlot emptySlot = (InventorySlot)getOneIntersectingObject(InventorySlot.class);
        setLocation(emptySlot.getX(), emptySlot.getY());
        pickedUp = false;
        world.getPlayer().getInventory().removeItem(this);   
        world.getPlayer().getToolBar().removeItem(this);
        world.getPlayer().getInventory().addItem(this);
    }

    private void toolbarSlotInteraction() {
        ToolbarSlot emptySlot = (ToolbarSlot)getOneIntersectingObject(ToolbarSlot.class);
        setLocation(emptySlot.getX(), emptySlot.getY());
        pickedUp = false;
        world.getPlayer().getInventory().removeItem(this);
        world.getPlayer().getToolBar().removeItem(this);
        world.getPlayer().getToolBar().addItem(this);
    }

    public boolean isStackable() {
        return stackable;
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

    public void setID(int ID) {
        itemID = ID;
        update();
    }

    public void setStacks(int stack) {
        stacks = stack;
        updateStacks();
    }

    public void addToStack(int value) {
        if (stacks + value >= 0) { stacks+=value; updateStacks();}
        checkDepleted();
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
    }

    private void update() {
        if (itemID == 0) {
            this.setImage("Torch.png");
            stackable = true;
            price = 2;
        } else if (itemID == 1) {
            this.setImage("Bomb.png");
            stackable = true;
            price = 10;
        } else if (itemID == 2) {
            this.setImage("rope slot.png");
            stackable = true;
            price = 1;
        } else if (itemID == 9) {
            this.setImage("Charcoal.png");
            stackable = false;
            price = 1;
        } else if (itemID == 10) {
            this.setImage("iron ingot.png");
            stackable = false;
            price = 2;
        } else if (itemID == 11) {
            this.setImage("silver ingot.png");
            stackable = false;
            price = 5;
        } else if (itemID == 12) {
            this.setImage("gold ingot.png");
            stackable = false;
            price = 12;
        } else if (itemID == 13) {
            this.setImage("diamond ingot.png");
            stackable = false;
            price = 28;
        } else if (itemID == 14) {
            this.setImage("emerald.png");
            stackable = false;
            price = 40;
        } else if (itemID == 15) {
            this.setImage("lapis pickup.png");
            stackable = false;
            price = 75;
        } else if (itemID == 20) {
            this.setImage("Iron Pickaxe.png");
            stackable = false;
            price = 10;
        } else if (itemID == 22) {
            this.setImage("Silver Pickaxe.png");
            stackable = false;
            price = 1;
        } else if (itemID == 24) {
            this.setImage("Gold Pickaxe.png");
            stackable = false;
            price = 1;
        } else if (itemID == 26) {
            this.setImage("Diamond Pickaxe.png");
            stackable = false;
            price = 1;
        } else if (itemID == 21) {
            this.setImage("Iron Drill.png");
            stackable = false;
            price = 1;
        } else if (itemID == 23) {
            this.setImage("Silver Drill.png");
            stackable = false;
            price = 1;
        } else if (itemID == 25) {
            this.setImage("Gold Drill.png");
            stackable = false;
            price = 1;
        } else if (itemID == 27) {
            this.setImage("Diamond Drill.png");
            stackable = false;
            price = 1;
        } else if (itemID == 28) {
            this.setImage("Iron Sword.png");
            stackable = false;
            price = 1;
        } else if (itemID == 29) {
            this.setImage("Silver Sword.png");
            stackable = false;
            price = 1;
        } else if (itemID == 30) {
            this.setImage("Gold Sword.png");
            stackable = false;
            price = 1;
        } else if (itemID == 31) {
            this.setImage("Diamond Sword.png");
            stackable = false;
            price = 1;
        } else {
            this.setImage(emptyImage);
            stackable = false;
            price = 0;
        }
    }

    private void updateStacks() {
        if (stackable && stacks > 1) {
            stackDisplay.updateValue(stacks);
        } else {
            clearDisplay();
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
