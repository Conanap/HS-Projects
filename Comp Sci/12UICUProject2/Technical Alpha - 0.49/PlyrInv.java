import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.awt.Color;
/**
 * Write a description of class PlyrInv here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlyrInv extends Inventory
{
    private InventoryItem[][] itemArray;
    private InventorySlot[][] slotArray;
    private TrashSlot trash;
    private RepairSlot repair;
    private StringDisplay coinDisplay;
    private boolean repairOpen;
    public PlyrInv() {
        super();
        repairOpen = false;
        trash = new TrashSlot();
        repair = new RepairSlot();
        coinDisplay = new StringDisplay(100,40,40,Color.YELLOW);
        itemArray = new InventoryItem[6][10];
        slotArray = new InventorySlot[6][10];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                slotArray[i][j] = new InventorySlot();
            }
        }
        itemArray[0][0] = new InventoryItem(0);
        itemArray[0][1] = new InventoryItem(1);
        itemArray[0][2] = new InventoryItem(2);
        itemArray[1][0] = new InventoryItem(28);
        itemArray[1][1] = new InventoryItem(29);
        itemArray[1][2] = new InventoryItem(30);
        itemArray[1][3] = new InventoryItem(31);
        itemArray[2][0] = new InventoryItem(20);
        itemArray[2][1] = new InventoryItem(22);
        itemArray[2][2] = new InventoryItem(24);
        itemArray[2][3] = new InventoryItem(26);
        itemArray[3][0] = new InventoryItem(21);
        itemArray[3][1] = new InventoryItem(23);
        itemArray[3][2] = new InventoryItem(25);
        itemArray[3][3] = new InventoryItem(27);
    }

    public void addedToWorld(World Test) {
        fillInvSlots(Test);
        fillInvItems(Test);
        Test.addObject(trash, 638, 235);
        Test.addObject(coinDisplay, 644, 380);
    }

    public void setShowing(boolean showing){
        this.showing = showing;
    }

    public void removeAssets() {
        emptyInvSlots();
        trash.clearAsset();
        getWorld().removeObject(trash);
        getWorld().removeObject(coinDisplay);
    }

    private void fillInvSlots(World Test) {
        int x = 69;
        int y = 185;
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 6; j++){
                if (i >= 7 && i <= 9) {
                    Test.addObject(slotArray[j][i], 69 + 50*(i)+(-6+i), 185 + 50*(j));
                } 
                else
                {
                    Test.addObject(slotArray[j][i], 69 + 50*(i), 185 + 50*(j));
                }     
            }
        }
    }

    private void fillInvItems(World Test) {
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 6; j++){
                if (itemArray[j][i] != null) {
                    Test.addObject(itemArray[j][i], slotArray[j][i].getX(), slotArray[j][i].getY());
                }
            }
        }
    }

    private void emptyInvSlots() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                if (itemArray[i][j] != null) {itemArray[i][j].clearStackDisplay(); itemArray[i][j].clearDurationDisplay(); getWorld().removeObject(itemArray[i][j]);}
                getWorld().removeObject(slotArray[i][j]);
            }
        }
    }

    public void removeItem(InventoryItem item) {
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 10; j++) {
                if (itemArray[i][j] == item) {
                    itemArray[i][j] = null;
                }
            }
        }
    }

    public boolean addItem(int ID) {
        InventoryItem newItem = new InventoryItem(ID);
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 10; j++) {
                if (newItem.isStackable() && itemArray[i][j] != null && itemArray[i][j].getID() == ID) {
                    itemArray[i][j].addToStack(1);
                    return true;
                }
            }
        }  
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 10; j++) {
                if  (itemArray[i][j] == null) {
                    itemArray[i][j] = newItem;
                    getWorld().addObject(itemArray[i][j], slotArray[i][j].getX(), slotArray[i][j].getY());
                    return true;
                }
            }
        }
        return false;
    }

    public void addItem(InventoryItem item) {
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 10; j++) {
                if (item.isStackable() && itemArray[i][j] != null && itemArray[i][j].getID() == item.getID()) {
                    InventoryItem newItem = item;
                    itemArray[i][j].addToStack(item.getStacks());
                    item.clearStackDisplay();
                    item.clearDurationDisplay();
                    getWorld().removeObject(item);
                    return;
                }
            }
        }  
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 10; j++) {
                if (slotArray[i][j].getX() == item.getX() && slotArray[i][j].getY() == item.getY()) {
                    itemArray[i][j] = item;
                    return;
                }
            }
        }
    }

    public void addItem(int x, int y, int ID, int stacks, int duration) {
        InventoryItem newItem = new InventoryItem(ID);
        itemArray[x][y] = newItem;
        newItem.setStacks(stacks);
        newItem.setDuration(duration);
    }

    public void updateCoinDisplay(int coins) {
        coinDisplay.updateValue(coins);
    }

    public boolean getRepairOpen() {
        return repairOpen;
    }

    public RepairSlot getRepair() {
        return repair;
    }

    public void toggleRepairShop(boolean trueFalse) {
        if (trueFalse) { 
            this.setImage("Repair Pop-up.png");
            //trash.clearAsset();
            getWorld().removeObject(trash);
            getWorld().addObject(repair, 637,238);
            repairOpen = true;
        } else {
            this.setImage("1280x720 Player Inventory for Shop.png");
            getWorld().addObject(trash, 638, 235);
            getWorld().removeObject(repair);
            repair.clearAsset();
            repairOpen = false;
        }
    } 
    //change
    public InventoryItem[][] getItemArray() {
        return itemArray;
    }
    public int[][][] getItemArrayInfo() {
        int[][][] array = new int[6][10][3];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                if (itemArray[i][j] != null) {
                    array[i][j][0] = itemArray[i][j].getID();
                    array[i][j][1] = itemArray[i][j].getStacks();
                    array[i][j][2] = itemArray[i][j].getDuration();
                } else {
                    array[i][j][0] = 99;
                    array[i][j][1] = 99;
                    array[i][j][2] = 99;
                }
            }
        }
        return array;
    }
}