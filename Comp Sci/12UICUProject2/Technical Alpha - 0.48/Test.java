import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.FileNotFoundException;
import java.util.List;
import java.lang.IllegalStateException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/**
 * Write a description of class Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Test extends World
{
    private Player player = new Player();
    private int jumpHold,xSpawn,ySpawn;
    private worldGen generator = new worldGen();
    private LoadingScreen loading = new LoadingScreen(3001,1); //Pictures that are classes; so that they are drawn ontop of all objects
    private boolean loadedWorld=false, mapLoaded=false;
    private int [] [] map;
    ShopInv shopInv;
    private int shopDelay;
    private miniMap min;

    private building shopping = new building(0);
    private building hotel = new building(1);
    private building repair = new building(2);
    private ArrayList<Integer> itemxSpawn= new ArrayList<Integer>(), itemySpawn= new ArrayList<Integer>(), itemx= new ArrayList<Integer>(),itemy= new ArrayList<Integer>(),item= new ArrayList<Integer>();
    private HoverText hove;
    /**
     * Constructor for objects of class Test.
     * 
     */
    public Test()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1); 
        setPaintOrder(LoadingScreen.class, HoverText.class, HealthBar.class, StringDisplay.class, InventoryItem.class, ShopItem.class, TrashSlot.class, InventorySlot.class,
            Buttons.class, PlyrInv.class, ShopInv.class, miniMap.class, ToolbarSlot.class, ToolBar.class, 
            Tools.class, Player.class, Block.class,Rope.class,Torch.class,Bomb.class, building.class,worldGen.class);

        addObject(loading,640,360);
        shopInv = new ShopInv();
        shopDelay = 0;
        setBackground("sky.png");
    }

    public void act(){
        if(!mapLoaded)
        {
            transferWorldIn();
            min = new miniMap();
            addObject(min,1180,114);
            addObject(generator,1180,114);
            addObject(shopping,263,120);
            addObject(repair, 504, 120);
            addObject(hotel, 816, 120);
            min.buildMap();
            addObject(player, 100,100);
            if(item.size()!=0)
                player.setID(item.size());
            mapLoaded=true;
        }
        if(!loadedWorld)
        {
            loadWorld();
            checkItemsAdd(xSpawn,ySpawn);
        }
        else
        {
            List<Player> isPlayerHere = getObjects(Player.class);
            if(isPlayerHere.size()>0)
            {
                checkInventories();
                buttonInput();
                movement();
                checkPlayerWorld();
                shopDelay++; 
            }
        }           
        min.updateMap();
    }

    public Player getPlayer() {
        return player;
    }

    public ShopInv getShopInv() {
        return shopInv;
    }

    private void buttonInput() {
        if (Greenfoot.isKeyDown("space")){
            jumpHold++;
            player.jump(jumpHold);
        }
        else{ jumpHold = 0; player.resetJump();} //Resets counter
        if (Greenfoot.isKeyDown("1")){
            player.changeAction(1);
        }else if (Greenfoot.isKeyDown("2")){
            player.changeAction(2);
        }else if (Greenfoot.isKeyDown("3")){
            player.changeAction(3);
        }else if (Greenfoot.isKeyDown("4")){
            player.changeAction(4);
        }else if (Greenfoot.isKeyDown("5")){
            player.changeAction(5);
        }
    }

    private void movement() {
        if(Greenfoot.isKeyDown("w")){
            player.climbRope(2);
        }
        if(Greenfoot.isKeyDown("s")){
            player.climbRope(-2);
        }
        if (Greenfoot.isKeyDown("a")){
            player.moveX(-2);
        }
        if (Greenfoot.isKeyDown("d")){
            player.moveX(2);
        }
    }

    private void checkInventories() {
        if (Greenfoot.isKeyDown("control") && !shopInv.isShowing()){
            player.toggleInventory();
        }
        if (Greenfoot.mouseClicked(shopping) && player.shopInRange() && !shopInv.isShowing() && shopDelay >= 20){
            if (!player.getInventory().isShowing()) {player.toggleInventory(); player.getInventory().setShowing(true);}
            addObject(shopInv, 1041, 366);
            shopInv.setShowing(true);
            player.getToolBar().setShowing(false);
            shopDelay = 0;
        } else if (Greenfoot.mouseClicked(shopping) && shopInv.isShowing() && shopDelay >= 20
        || !player.shopInRange() && shopInv.isShowing() && shopDelay >= 20){
            if (player.getInventory().isShowing()) {player.toggleInventory(); player.getInventory().setShowing(false);}
            shopInv.setShowing(false);
            player.getToolBar().setShowing(true);
            shopInv.removeShopItems();
            removeObject(shopInv);
            shopDelay = 0;
        }

        if(Greenfoot.mouseClicked(hotel))
        {
            updateRopes();
            generator.saveEverything();
        }
        
        List<ShopItem> shopItems = getObjects(ShopItem.class);
        List<InventoryItem> invItems = getObjects(InventoryItem.class);
        if(shopItems.size()!=0)
        {
            for(int i = 0; i < shopItems.size();i++)
            {
                if(shopItems.size()!=0)
                    shopItems.get(i).checkHover();
                else
                    break;
                
            }
        }
        if(invItems.size()!=0)
        {
            for(int i = 0; i < invItems.size();i++)
            {
                if(invItems.size()!=0)
                    invItems.get(i).checkHover();
                else
                    break;
            }
        }
    }

    private void checkPlayerWorld()
    {
        try
        {
            if(ySpawn==0)
                setBackground("sky.png");
            else
                setBackground("ground.png");
            if(player.getX()<=3&&xSpawn>=25||player.getX()>=1074&&xSpawn<=574)
            {
                updateRopes();
                int y = player.getY(),x=player.getX();
                List<Actor> Objects=getObjects(null);
                removeObjects(Objects);

                if(x<=3)
                {
                    xSpawn-=26;
                    x=1070;
                }
                else
                {
                    xSpawn+=26;
                    x=7;
                }
                loadWorld(xSpawn,ySpawn);
                addObject(player,x,y);
                addObject(min,1187,114);
                if(xSpawn==278&&ySpawn==0)
                {
                    addObject(shopping,263,120);
                    addObject(repair, 504, 120);
                    addObject(hotel, 816, 120);
                }
                checkItemsAdd(xSpawn,ySpawn);
                addObject(generator,1180,114);
            }

            if(player.getY()>=696&&ySpawn<=2970||player.getY()<=68&&ySpawn>0)
            {
                updateRopes();
                int y = player.getY(),x=player.getX();
                List<Actor> Objects=getObjects(null);
                removeObjects(Objects);
                if(y>=675)
                {
                    if(ySpawn>=9)
                        ySpawn+=15;
                    else
                        ySpawn+=9;
                    y=70;
                }
                else
                {
                    if(ySpawn>9)
                        ySpawn-=15;
                    else
                        ySpawn-=9;
                    y=622;
                }

                loadWorld(xSpawn,ySpawn);
                addObject(player,x,y);
                addObject(min,1187,114);
                if(xSpawn==278&&ySpawn==0)
                {
                    addObject(shopping,263,120);
                    addObject(repair, 504, 120);
                    addObject(hotel, 816, 120);
                }
                checkItemsAdd(xSpawn,ySpawn);
                addObject(generator,1180,114);
            }
        }

        catch(IllegalStateException e)
        { System.out.println("GAME OVER");}
    }

    private void updateRopes()
    {
        List<FlyRope> cFRope= getObjects(FlyRope.class);
        for(Rope rope : cFRope)
        {
            itemx.set(rope.returnID(),rope.getX());
            itemy.set(rope.returnID(),(rope.getY()-rope.returnCLength()/2));
        }

        List<SetRope> cRope = getObjects(SetRope.class);
        for(SetRope rope : cRope)
        {
            itemx.set(rope.returnID(),rope.getX());
            itemy.set(rope.returnID(),(rope.getY()-rope.returnCLength()/2));
        }
    }

    private void transferWorldIn()
    {
        String path = "world.txt";
        this.map = new int [3000] [600];
        try
        {
            FileInputStream is =new FileInputStream (path);
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br= new BufferedReader(isr);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Looks like the world has not yet been generated! We are generating it now");
            generator.createWorld();
        }
        catch(UnsupportedEncodingException e)
        {
            System.out.println("Unsupported encoding");
        }
        finally
        {
            try
            {
                InputStream is =new FileInputStream (path);
                InputStreamReader isr = new InputStreamReader(is,"utf-8");
                BufferedReader br= new BufferedReader(isr);
                int value = 0,xCou=0,yCou=0;
                String line;
                boolean moreToGo=true;
                while(moreToGo)
                {
                    map[yCou][xCou]=Integer.parseInt(line=br.readLine());
                    xCou++;
                    if(xCou==600)
                    {
                        xCou=0;
                        yCou++;
                    }
                    if(yCou==3000)
                        moreToGo=false;
                }
                is.close();
                isr.close();
                br.close();
            }
            catch(FileNotFoundException e)
            { System.out.println("Looks like the world has not yet been generated!");}
            catch(IOException e)
            {System.out.println("Permission error");}
        }

        try
        {
            Scanner scan = new Scanner( new File ("itemSave.txt"));
            while(scan.hasNext())
            {
                item.add(scan.nextInt());
                itemxSpawn.add(scan.nextInt());
                itemySpawn.add(scan.nextInt());
                itemx.add(scan.nextInt());
                itemy.add(scan.nextInt());
            }
            scan.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("No item save found");
        }
    }

    private void loadWorld()
    {
        xSpawn=278;
        ySpawn=0;
        loadWorld(278,0);
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void loadWorld(int xBlock, int yBlock)
    {
        int x, y = 220,screenSpawn=13,k=0;
        if(yBlock!=0)
        {
            y=20;
            screenSpawn=18;
        }
        for(int i=yBlock; i < (yBlock+screenSpawn); i++)
        {
            x=20;
            for(int j = xBlock; j <(xBlock+27)&&j<600; j++)
            {
                if(map[i][j]!=0)
                    addObject(new Block(i,j,map[i][j]),x,y);
                x+=40;
            }
            k++;
            y+=40;

            if(k==2)
            {
                k=0;
                loading.increase();
            }
        }
        removeObject (loading);
        loadedWorld=true;
    }

    public void mapMod (int xInt, int yInt, int type)
    {
        map[xInt][yInt]= type;
        min.mapMod(xInt,yInt,type);
    }

    public int mapx ()
    {
        return xSpawn;
    }

    public int mapy()
    {
        return ySpawn;
    }

    public int playerX()
    {
        return player.getX();
    }

    public int playerY()
    {
        return player.getY();
    }

    public int[][] getMap()
    {
        return map;
    }

    public ArrayList<Integer> item()
    {
        return item;
    }

    public ArrayList<Integer> itemx()
    {
        return itemx;
    }

    public ArrayList<Integer> itemy()
    {
        return itemy;
    }

    public ArrayList<Integer> itemxSpawn()
    {
        return itemxSpawn;
    }

    public ArrayList<Integer> itemySpawn()
    {
        return itemySpawn;
    }

    private void checkItemsAdd(int xSpawn, int ySpawn)
    {
        for(int i = 0; i < itemxSpawn.size(); i++)
        {
            if(itemxSpawn.get(i)==xSpawn&&itemySpawn.get(i)==ySpawn)
            {
                if(item.get(i)==0)
                    addObject(new SetRope(true,i), itemx.get(i), itemy.get(i));
                else if(item.get(i)==1)
                    addObject(new Torch(400,i), itemx.get(i), itemy.get(i));
            }
        }
    }

    public void newItemIn(int x, int y, int type)
    {
        this.itemxSpawn.add(this.mapx());
        this.itemySpawn.add(this.mapy());
        this.itemx.add(x);
        this.itemy.add(y);
        this.item.add(type);
    }
}
